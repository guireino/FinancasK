package com.example.guilherme.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.BaseAdapter
import com.example.guilherme.financask.R
import java.security.AccessControlContext

/**
 * Created by guilherme on 15/11/17.
 */
class ListaTransacoesAdapter(transacoes: List<String>, context: Context) : BaseAdapter() {

    private val transacoes = transacoes
    private val context = context

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        var inflate = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        return inflate
    }

    override fun getItem(posicao: Int): String {
        return transacoes[posicao]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }

}