package com.example.guilherme.financask.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.guilherme.financask.R
import com.example.guilherme.financask.extension.formatadatabr
import com.example.guilherme.financask.model.Tipo
import com.example.guilherme.financask.model.Transacao
import com.example.guilherme.financask.ui.ResumoView
import com.example.guilherme.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

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

        val view = window.decorView
        val viewCriada = LayoutInflater.from(this).inflate(R.layout.form_transacao, view as ViewGroup, false)

        val ano = 2017
        val mes = 0
        val dia = 18

        val hoje = Calendar.getInstance()
        viewCriada.form_transacao_data.setText(hoje.formatadatabr())
        viewCriada.form_transacao_data.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, ano, mes, dia ->
                val dataSelecionada = Calendar.getInstance()
                dataSelecionada.set(ano, mes, dia)
                viewCriada.form_transacao_data.setText(dataSelecionada.formatadatabr())
            }, ano, mes, dia).show()
        }

        val adapter = ArrayAdapter.createFromResource(this, R.array.categorias_de_receita,
                android.R.layout.simple_spinner_dropdown_item)
        viewCriada.form_transacao_categoria.adapter = adapter

        lista_transacoes_adiciona_receita.setOnClickListener {
//            Toast.makeText(this, "Clique de receita", Toast.LENGTH_LONG).show() enviando mensagem texto no android
              AlertDialog.Builder(this).setTitle(R.string.adiciona_receita)

                      .setView(viewCriada).setPositiveButton("Adicionar", { dialogInterface, i ->
                               // O metado setPositiveButton cria um botão para confimar um acão do usuario
                              val valorEmTexto = viewCriada.form_transacao_valor.text.toString()
                              val dataEmTexto = viewCriada.form_transacao_data.text.toString()
                              val categoriaEmTexto = viewCriada.form_transacao_categoria.selectedItem.toString()

                              val valor = try{
                                  BigDecimal(valorEmTexto)
                              }catch (exception: NumberFormatException){
                                  Toast.makeText(this,"Falha na converção de valor", Toast.LENGTH_LONG).show()
                                  BigDecimal.ZERO
                              }

                              val formato_br = SimpleDateFormat("dd/MM/yyyy")
                              val dataConvert: Date = formato_br.parse(dataEmTexto)
                              val data = Calendar.getInstance()
                              data.time = dataConvert

                              val transacaoCriada = Transacao(tipo = Tipo.RECEITA, valor = valor,
                                      data = data, categoria = categoriaEmTexto)

                              atualizaTransacoes(transacaoCriada)
                              lista_transacoes_adiciona_menu.close(true)

//                              Toast.makeText(this, "${transacaoCriada.valor} - " +
//                                            "${transacaoCriada.categoria} - " +
//                                            "${transacaoCriada.data.formatadatabr()} - " +
//                                            "${transacaoCriada.tipo}", Toast.LENGTH_LONG).show()

                      }).setNegativeButton("Cancelar", null).show() // O metado setNegativeButton cancelar a ação do usuario
        }

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