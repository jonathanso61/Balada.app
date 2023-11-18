package com.ifmg.balada

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DespesaDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "despesas.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "despesas"
        const val COLUMN_ID = "_id"
        const val COLUMN_NOME = "nome"
        const val COLUMN_VALOR = "valor"
        const val COLUMN_DATA = "data"
        const val COLUMN_IS_ENTRADA = "is_entrada"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
        CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY,
            $COLUMN_NOME TEXT,
            $COLUMN_VALOR REAL,
            $COLUMN_DATA TEXT,
            $COLUMN_IS_ENTRADA INTEGER
        )
    """.trimIndent()

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}
