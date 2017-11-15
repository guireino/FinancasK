package com.example.guilherme.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.example.guilherme.financask.R
import com.example.guilherme.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

/**
 * Created by guilherme on 14/11/17.
 */

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val lista: List<String> = listOf("Comida - R$ 20,50", "Rmonomia - R$ 100,00")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)

        lista_transacoes_listview.setAdapter(ListaTransacoesAdapter(lista, this));
    }

}