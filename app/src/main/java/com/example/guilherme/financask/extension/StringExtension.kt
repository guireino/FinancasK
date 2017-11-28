package com.example.guilherme.financask.extension

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