package com.example.guilherme.financask.model

import java.math.BigDecimal

/**
 * Created by guilherme on 28/11/17.
 */

class Resumo(private val lista: List<Transacao>) {

    fun receita() : BigDecimal{

//        var totalReceita = BigDecimal.ZERO
//        for (transacao in lista) {
//            if (transacao.tipo == Tipo.RECEITA) {
//                totalReceita = totalReceita.plus(transacao.valor) // o plus e um intancia para "+" dividir os valores
//            }
//        }
//        val somaDeReceita: Double = somaPor(Tipo.RECEITA)

        return somaPor(Tipo.RECEITA)
    }

    fun despesa() :BigDecimal{

//        var totalDespesa = BigDecimal.ZERO
//
//        for (transacao in lista) {
//            if (transacao.tipo == Tipo.DESPESA) {
//                totalDespesa = totalDespesa.plus(transacao.valor)
//            }
//        }
//        val somaDeDespasa: Double = somaPor(Tipo.DESPESA)

        return somaPor(Tipo.DESPESA)
    }

    private fun somaPor(tipo: Tipo) : BigDecimal{
        val somDeTransacoes = lista.filter { it.tipo == tipo } // subtituindo o: transacao -> transacao pelo it
                .sumByDouble { it.valor.toDouble() }

        return BigDecimal(somDeTransacoes)
    }

//    fun total() : BigDecimal{
//        return receita().subtract(despesa())
//    }

    val total get() = receita().subtract(despesa())

}