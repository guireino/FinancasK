package com.example.guilherme.financask.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by guilherme on 17/11/17.
 */

fun Calendar.formatadatabr() : String{

    val formatobr = "dd/MM/yyyy"           // criando um formatador da data para data brasileira
    val format = SimpleDateFormat(formatobr)
    val dataFormatada = format.format(this.time)

    return dataFormatada
}