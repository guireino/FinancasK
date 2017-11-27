package com.example.guilherme.financask.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.guilherme.financask.R
import com.example.guilherme.financask.extension.format_br
import com.example.guilherme.financask.extension.formatadatabr
import com.example.guilherme.financask.extension.limitaEmAte
import com.example.guilherme.financask.model.Tipo
import com.example.guilherme.financask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

/**
 * Created by guilherme on 15/11/17.
 */
class ListaTransacoesAdapter(private val transacoes: List<Transacao>, private val context: Context) : BaseAdapter() {

    private val limiteDaCategoria = 14

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {

        val viewCriada = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[posicao]

        add_valor(transacao, viewCriada)

        add_icone(transacao, viewCriada)

//        var categoriaFormatada = transacao.categoria
//        if(categoriaFormatada.length > 14){
//            categoriaFormatada = "${categoriaFormatada.substring(0, 14)}..."
//        }

        add_categoria(viewCriada, transacao)
        add_data(viewCriada, transacao)

        return viewCriada
    }

    private fun add_data(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_data.text = transacao.data.formatadatabr()
    }

    private fun add_categoria(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_categoria.text = transacao.categoria.limitaEmAte(limiteDaCategoria)
    }

    private fun add_icone(transacao: Transacao, viewCriada: View) {
        val icone = iconePor(transacao.tipo)

        viewCriada.transacao_icone.setBackgroundResource(icone)
    }

    private fun iconePor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.drawable.icone_transacao_item_receita
        }

        return  R.drawable.icone_transacao_item_despesa
    }

    private fun add_valor(transacao: Transacao, viewCriada: View) {

        val cor: Int = corPor(transacao.tipo)

        viewCriada.transacao_valor.setTextColor(cor)

        viewCriada.transacao_valor.text = transacao.valor.format_br()
    }

    private fun corPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
           return ContextCompat.getColor(context, R.color.receita)
        }

        return ContextCompat.getColor(context, R.color.despesa)
    }

    override fun getItem(posicao: Int): Transacao {
        return transacoes[posicao]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }

}