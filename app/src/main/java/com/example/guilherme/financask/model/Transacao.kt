package com.example.guilherme.financask.model

import java.math.BigDecimal
import java.util.*

/**
 * Created by guilherme on 16/11/17.
 */
class Transacao (val valor: BigDecimal,val tipo: Tipo,
                 val categoria: String = "Indefinida",
                 val data: Calendar = Calendar.getInstance()){

//    val valor: BigDecimal = valor
//    private val categoria: String = categoria
//    private val data: Calendar = data
//    fun getValor() : BigDecimal {
//        return valor
//    }

//    constructor(valor: BigDecimal, tipo: Tipo) : this(valor, tipo, "Indefinida")
//
//    constructor(valor: BigDecimal, tipo: Tipo, data: Calendar) : this(valor, tipo, "Indefinida", data)

}