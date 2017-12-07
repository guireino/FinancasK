package com.example.guilherme.financask.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.guilherme.financask.R
import com.example.guilherme.financask.extension.convertCalendar
import com.example.guilherme.financask.extension.formatadatabr
import com.example.guilherme.financask.model.Tipo
import com.example.guilherme.financask.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

/**
 * Created by guilherme on 06/12/17.
 */
abstract class formularioTransacaoDialog(private val context: Context, private val viewGroup: ViewGroup) {

    private val viewCriada = criaLayout()
    protected val campoValor = viewCriada.form_transacao_valor
    protected val campoCategoria = viewCriada.form_transacao_categoria
    protected val campoData = viewCriada.form_transacao_data
    abstract protected val tituloBotaoPositivo: String

    fun chamaDialog(tipo: Tipo, delegate: (transacao: Transacao) -> Unit) {

        configuraData()
        configuraCategoria(tipo)

        //            Toast.makeText(this, "Clique de receita", Toast.LENGTH_LONG).show() enviando mensagem texto no android
        configuraFormulario(tipo, delegate) // O metado setNegativeButton cancelar a ação do usuario
    }

    private fun configuraFormulario(tipo: Tipo, delegate: (transacao: Transacao) -> Unit) {

        val titulo = tituloPor(tipo)

        AlertDialog.Builder(context)
                .setTitle(titulo)
                .setView(viewCriada)
                .setPositiveButton(tituloBotaoPositivo, { _, _ ->

            // O metado setPositiveButton cria um botão para confimar um acão do usuario
            val valorEmTexto = campoValor.text.toString()
            val dataEmTexto = campoData.text.toString()
            val categoriaEmTexto = campoCategoria.selectedItem.toString()

            val valor = convertValor(valorEmTexto)
            val data = dataEmTexto.convertCalendar()

            val transacaoCriada = Transacao(tipo = tipo, valor = valor,
                    data = data, categoria = categoriaEmTexto)

            delegate(transacaoCriada)

//            atualizaTransacoes(transacaoCriada)
//            lista_transacoes_adiciona_menu.close(true)

            //                              Toast.makeText(this, "${transacaoCriada.valor} - " +
            //                                            "${transacaoCriada.categoria} - " +
            //                                            "${transacaoCriada.data.formatadatabr()} - " +
            //                                            "${transacaoCriada.tipo}", Toast.LENGTH_LONG).show()

        }).setNegativeButton("Cancelar", null).show()
    }

    abstract protected fun tituloPor(tipo: Tipo): Int

    private fun convertValor(valorEmTexto: String) : BigDecimal {
        return try {
            BigDecimal(valorEmTexto)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Falha na converção de valor", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
    }

    private fun configuraCategoria(tipo: Tipo) {

        val categorias = categoriaPor(tipo)

        val adapter = ArrayAdapter.createFromResource(context, categorias,
                android.R.layout.simple_spinner_dropdown_item)
        campoCategoria.adapter = adapter
    }

    protected fun categoriaPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.array.categorias_de_receita
        }
        return R.array.categorias_de_despesa
    }

    private fun configuraData() {

        val hoje = Calendar.getInstance()

        val ano = hoje.get(Calendar.YEAR)   // colocando data
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        campoData.setText(hoje.formatadatabr())
        campoData.setOnClickListener {
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, ano, mes, dia ->
                val dataSelecionada = Calendar.getInstance()
                dataSelecionada.set(ano, mes, dia)
                campoData.setText(dataSelecionada.formatadatabr())
            }, ano, mes, dia).show()
        }
    }

    private fun criaLayout() : View {
//        val view = window.decorView
        return LayoutInflater.from(context).inflate(R.layout.form_transacao, viewGroup, false)
    }
}