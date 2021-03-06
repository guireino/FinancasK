package com.example.guilherme.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.guilherme.financask.R
import com.example.guilherme.financask.dao.TransacaoDAO
import com.example.guilherme.financask.model.Tipo
import com.example.guilherme.financask.model.Transacao
import com.example.guilherme.financask.ui.ResumoView
import com.example.guilherme.financask.ui.adapter.ListaTransacoesAdapter
import com.example.guilherme.financask.ui.dialog.add_TransacaoDialog
import com.example.guilherme.financask.ui.dialog.alteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

/**
 * Created by guilherme on 14/11/17.
 */

class ListaTransacoesActivity : AppCompatActivity(){

    private val dao = TransacaoDAO()
    private val transacoes = dao.transacoes
    // private lateinit var viewActivity: View --> metado lateinit faz que variaveu inicializa ne algum momento que o aplicativo quiser

    private val viewActivity by lazy { // A instancia lazy faz certo momento o codigo a variaveu seja iniciada
        window.decorView
    }

    private val viewGroupActivity by lazy {
        viewActivity as ViewGroup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

//        viewActivity = window.decorView

//        val lista: List<Transacao> = listDeExemplo()

        configuraResumo()
        configuraLista()
        configuraFab()

    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialog(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialog(Tipo.DESPESA)
        }
    }

    private fun chamaDialog(tipo: Tipo) {
        add_TransacaoDialog(viewGroupActivity, this).chamaDialog(tipo) { transacaoCriada ->
        add(transacaoCriada)
                lista_transacoes_adiciona_menu.close(true)
        }
    }

    private fun add(transacao: Transacao) {
        dao.add(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
//        val view = viewActivity
        val resumoView = ResumoView(this, viewActivity, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista(){
        val transacoesAdapter = ListaTransacoesAdapter(transacoes, this)
        with(lista_transacoes_listview){
            adapter = transacoesAdapter
            lista_transacoes_listview.setOnItemClickListener{ parent, view, posicao, id ->
                val transacao = transacoes[posicao]
                dialogAlteracao(transacao, posicao)
            }

            setOnCreateContextMenuListener{ menu, view, menuInfo ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {    // criando ação para remover o campo
        val idMenu = item?.itemId
        if (idMenu == 1){
            val contextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val posicaoTransacao = contextMenuInfo.position
            remove(posicaoTransacao)
        }
        return super.onContextItemSelected(item)
    }

    private fun remove(posicao: Int) {
        dao.remove(posicao)
        atualizaTransacoes()
    }

    private fun dialogAlteracao(transacao: Transacao, posicao: Int) {
        alteraTransacaoDialog(viewGroupActivity, this).configuraDialog(transacao) { transacaoAlterada ->
                        altera(transacaoAlterada, posicao)
                }
    }

    private fun altera(transacao: Transacao, posicao: Int) {
        dao.altera(transacao, posicao)  // criando metado para altera lista
        atualizaTransacoes()
    }

//    private fun listDeExemplo(): List<Transacao> {   // como criar um campo na linha de codigo
//        return listOf(Transacao(valor = BigDecimal(420.5), data = Calendar.getInstance(),
//                categoria = "almoço de final semana", tipo = Tipo.DESPESA),
//                Transacao(valor = BigDecimal(100.0), tipo = Tipo.RECEITA, categoria = "Economia"),
//                Transacao(valor = BigDecimal(200.0), tipo = Tipo.RECEITA, categoria = "Economia"))
//    }

}