package com.example.guilherme.financask.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

/**
 * Created by guilherme on 22/11/17.
 */

fun BigDecimal.format_br() : String{

    val formato_br = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return formato_br.format(this)
            .replace("R$", "R$ ").replace("-R$","R$ -")  // replace formata valor
}