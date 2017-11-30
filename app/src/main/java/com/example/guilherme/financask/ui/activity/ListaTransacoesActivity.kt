package com.example.guilherme.financask.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.guilherme.financask.R
import com.example.guilherme.financask.extension.format_br
import com.example.guilherme.financask.extension.formatadatabr
import com.example.guilherme.financask.model.Tipo
import com.example.guilherme.financask.model.Transacao
import com.example.guilherme.financask.ui.ResumoView
import com.example.guilherme.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import kotlinx.android.synthetic.main.resumo_card.*
import java.math.BigDecimal
import java.util.*

/**
 * Created by guilherme on 14/11/17.
 */

class ListaTransacoesActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val lista: List<Transacao> = listDeExemplo()

        configuraResumo(lista)

        configuraLista(lista)

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
                      .setView(viewCriada).setPositiveButton("Adicionar", null)
                       // O metado setPositiveButton cria um botão para confimar um acão do usuario
                      .setNegativeButton("Cancelar", null).show() // O metado setNegativeButton cancelar a ação do usuario
        }

    }

    private fun configuraResumo(lista: List<Transacao>) {
        val view = window.decorView
        val resumoView = ResumoView(this, view, lista)
        resumoView.atualiza()
    }

    private fun configuraLista(lista: List<Transacao>){
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(lista, this)
    }

    private fun listDeExemplo(): List<Transacao> {
        return listOf(Transacao(valor = BigDecimal(420.5), data = Calendar.getInstance(),
                categoria = "almoço de final semana", tipo = Tipo.DESPESA),
                Transacao(valor = BigDecimal(100.0), tipo = Tipo.RECEITA, categoria = "Economia"),
                Transacao(valor = BigDecimal(200.0), tipo = Tipo.RECEITA, categoria = "Economia"))
    }

}