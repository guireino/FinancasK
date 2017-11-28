package com.example.guilherme.financask.model

import java.math.BigDecimal

/**
 * Created by guilherme on 28/11/17.
 */

class Resumo(private val lista: List<Transacao>) {

    fun receita() : BigDecimal{

        var totalReceita = BigDecimal.ZERO

        for (transacao in lista) {
            if (transacao.tipo == Tipo.RECEITA) {
                totalReceita = totalReceita.plus(transacao.valor) // o plus e um intancia para "+" dividir os valores
            }
        }
        return totalReceita
    }

    fun despesa() :BigDecimal{

        var totalDespesa = BigDecimal.ZERO

        for (transacao in lista) {
            if (transacao.tipo == Tipo.DESPESA) {
                totalDespesa = totalDespesa.plus(transacao.valor)
            }
        }
        return totalDespesa
    }

    fun total() : BigDecimal{
        return receita().subtract(despesa())
    }

}