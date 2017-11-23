package com.example.guilherme.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.guilherme.financask.R
import com.example.guilherme.financask.model.Tipo
import com.example.guilherme.financask.model.Transacao
import com.example.guilherme.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

/**
 * Created by guilherme on 14/11/17.
 */

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val lista = listOf(Transacao(valor = BigDecimal(20.5), data = Calendar.getInstance(),
                           categoria = "almoço de final semana", tipo = Tipo.DESPESA),
                           Transacao(valor =  BigDecimal(100.0), tipo = Tipo.RECEITA, categoria = "Economia"),
                           Transacao(valor = BigDecimal(200.0), tipo = Tipo.RECEITA, categoria = "Economia"))

        lista_transacoes_listview.adapter = ListaTransacoesAdapter(lista, this);
    }

}