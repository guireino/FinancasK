package com.example.guilherme.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.guilherme.financask.R
import com.example.guilherme.financask.extension.format_br
import com.example.guilherme.financask.model.Tipo
import com.example.guilherme.financask.model.Transacao
import com.example.guilherme.financask.ui.ResumoView
import com.example.guilherme.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
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