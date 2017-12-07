package com.example.guilherme.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.example.guilherme.financask.R
import com.example.guilherme.financask.extension.formatadatabr
import com.example.guilherme.financask.model.Tipo
import com.example.guilherme.financask.model.Transacao

/**
 * Created by guilherme on 01/12/17.
 */
class alteraTransacaoDialog(viewGroup: ViewGroup,
                            private val context: Context) : formularioTransacaoDialog(context, viewGroup){

    override val tituloBotaoPositivo: String
        get() = "Alterar"

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        } else {
            return R.string.altera_despesa
        }
    }

    fun configuraDialog(transacao: Transacao, delegate: (transacao: Transacao) -> Unit) {

        val tipo = transacao.tipo

        super.chamaDialog(tipo, delegate)
        inicializaCampos(transacao)
    }

    private fun inicializaCampos(transacao: Transacao) {
        inicializaCampoValor(transacao)
        inicializaCampoData(transacao)
        inicializaCampoCategoria(transacao)
    }

    private fun inicializaCampoCategoria(transacao: Transacao) {
        val tipo = transacao.tipo
        val categoriasRetornadas = context.resources.getStringArray(categoriaPor(tipo))
        val posicaoCategoria = categoriasRetornadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }

    private fun inicializaCampoData(transacao: Transacao) {
        campoData.setText(transacao.data.formatadatabr())
    }

    private fun inicializaCampoValor(transacao: Transacao) {
        campoValor.setText(transacao.valor.toString())
    }

}