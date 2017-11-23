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
class ListaTransacoesAdapter(transacoes: List<Transacao>, context: Context) : BaseAdapter() {

    private val transacoes = transacoes
    private val context = context

    private val limiteDaCategoria = 14

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[posicao]

        if (transacao.tipo == Tipo.RECEITA) {
            viewCriada.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.receita))
        } else {
            viewCriada.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.despesa))
        }

        if (transacao.tipo == Tipo.RECEITA){
            viewCriada.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
        }else{
            viewCriada.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        }

//        var categoriaFormatada = transacao.categoria
//        if(categoriaFormatada.length > 14){
//            categoriaFormatada = "${categoriaFormatada.substring(0, 14)}..."
//        }

        viewCriada.transacao_valor.text = transacao.valor.format_br()
        viewCriada.transacao_categoria.text = transacao.categoria.limitaEmAte(limiteDaCategoria)
        viewCriada.transacao_data.text = transacao.data.formatadatabr()

        return viewCriada
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