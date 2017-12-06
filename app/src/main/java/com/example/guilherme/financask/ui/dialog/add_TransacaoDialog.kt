package com.example.guilherme.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.example.guilherme.financask.R
import com.example.guilherme.financask.model.Tipo

/**
 * Created by guilherme on 01/12/17.
 */
class add_TransacaoDialog(viewGroup: ViewGroup, context: Context) : formularioTransacaoDialog(context, viewGroup) {

    override val tituloBotaoPositivo: String
        get() = "Adicionar"

    override fun tituloPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA) {
            R.string.adiciona_receita
        } else {
            R.string.adiciona_despesa
        }
    }

}