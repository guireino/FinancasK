package com.example.guilherme.financask.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by guilherme on 23/11/17.
 */

fun String.limitaEmAte(caracteres: Int) : String{   // criando função limitar o nome no campo
    if (this.length > caracteres){
        val primeiro_caracter = 0
        return "${this.substring(primeiro_caracter, caracteres)}..."
    }
    return this
}

fun String.convertCalendar() : Calendar {

    val formato_br = SimpleDateFormat("dd/MM/yyyy")
    val dataConvert: Date = formato_br.parse(this)
    val data = Calendar.getInstance()
    data.time = dataConvert
    return data
}