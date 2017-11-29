package com.example.guilherme.financask.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import com.example.guilherme.financask.R
import com.example.guilherme.financask.extension.format_br
import com.example.guilherme.financask.model.Resumo
import com.example.guilherme.financask.model.Tipo
import com.example.guilherme.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.*
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

/**
 * Created by guilherme on 27/11/17.
 */
class ResumoView(private val context: Context, private val view: View, lista: List<Transacao>) {

    private val resumo: Resumo = Resumo(lista)

    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza(){
        add_Receita()
        add_Despesa()
        add_total()
    }

    private fun add_Receita() {
        val totalReceita = resumo.receita()
        with(view.resumo_card_receita){
            setTextColor(corReceita)
            text = totalReceita.format_br()
        }
//        view.resumo_card_receita.setTextColor(corReceita)
//        view.resumo_card_receita.text = totalReceita.format_br()
    }

    private fun add_Despesa() {
        val totalDespesa = resumo.despesa()

        with(view.resumo_card_despesa){
            setTextColor(corDespesa)
            text = totalDespesa.format_br()
        }
//        view.resumo_card_despesa.setTextColor(corDespesa)
//        view.resumo_card_despesa.text = totalDespesa.format_br()
    }

    private fun add_total(){
        val total = resumo.total
        val cor = corPor(total)
        with(view.resumo_card_total){
            setTextColor(cor)
            text = total.format_br()
        }
    }

    private fun corPor(valor: BigDecimal): Int {
        if (valor >= BigDecimal.ZERO) { // "compareTo" ou ">="
            return corReceita
        }
        return corDespesa
    }

}