package com.ifmg.balada

import android.content.ContentValues
import android.content.Context


class DespesaDAO(context: Context) {

    private val dbHelper = DespesaDBHelper(context)

    fun inserirDespesa(despesa: Despesa) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DespesaDBHelper.COLUMN_IS_ENTRADA, if (despesa.isEntrada) 1 else 0)
            put(DespesaDBHelper.COLUMN_NOME, despesa.nome)
            put(DespesaDBHelper.COLUMN_VALOR, despesa.valor)
            put(DespesaDBHelper.COLUMN_DATA, despesa.data)
        }

        db.insert(DespesaContract.DespesaEntry.TABLE_NAME, null, values)
        db.close()
    }
}