package com.example.guilherme.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.example.guilherme.financask.R
import com.example.guilherme.financask.delegate.TransacaoDelegate
import com.example.guilherme.financask.model.Tipo
import com.example.guilherme.financask.model.Transacao
import com.example.guilherme.financask.ui.ResumoView
import com.example.guilherme.financask.ui.adapter.ListaTransacoesAdapter
import com.example.guilherme.financask.ui.dialog.add_TransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

/**
 * Created by guilherme on 14/11/17.
 */

class ListaTransacoesActivity : AppCompatActivity(){

    private val transacoes: MutableList<Transacao> = mutableListOf() // metado MutableList cria um lista mutil valor que pode ser add

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

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
        add_TransacaoDialog(window.decorView as ViewGroup, this).configuraDialog(tipo, object : TransacaoDelegate {
            override fun delegate(transacao: Transacao) {
                atualizaTransacoes(transacao)
                lista_transacoes_adiciona_menu.close(true)
            }
        })
    }


    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista(){
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

//    private fun listDeExemplo(): List<Transacao> {   // como criar um campo na linha de codigo
//        return listOf(Transacao(valor = BigDecimal(420.5), data = Calendar.getInstance(),
//                categoria = "almoço de final semana", tipo = Tipo.DESPESA),
//                Transacao(valor = BigDecimal(100.0), tipo = Tipo.RECEITA, categoria = "Economia"),
//                Transacao(valor = BigDecimal(200.0), tipo = Tipo.RECEITA, categoria = "Economia"))
//    }

}