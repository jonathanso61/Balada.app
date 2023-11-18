package com.ifmg.balada

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextValue: EditText
    private lateinit var editTextDate: EditText
    private lateinit var radioEntrada: RadioButton
    private lateinit var radioSaida: RadioButton
    private lateinit var btnCadastro: Button
    private lateinit var btnResumo: Button

    private var isEntrada = true
    private lateinit var dbHelper: DespesaDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DespesaDBHelper(this)

        editTextName = findViewById(R.id.editTextName)
        editTextValue = findViewById(R.id.editTextValue)
        editTextDate = findViewById(R.id.editTextDate)
        radioEntrada = findViewById(R.id.radioEntrada)
        radioSaida = findViewById(R.id.radioSaida)
        btnCadastro = findViewById(R.id.btnCadastro)
        btnResumo = findViewById(R.id.btnResumo)

        btnCadastro.setOnClickListener {
            cadastrarDespesa()
        }

        btnResumo.setOnClickListener {
            val intent = Intent(this, Segundatela::class.java)
            intent.putExtra("NOME", editTextName.text.toString())
            intent.putExtra("VALOR", editTextValue.text.toString().toDoubleOrNull() ?: 0.0)
            intent.putExtra("DATA", editTextDate.text.toString())
            intent.putExtra("IS_ENTRADA", isEntrada)
            startActivity(intent)
        }

        radioEntrada.setOnClickListener {
            isEntrada = true
            radioSaida.isChecked = false
        }

        radioSaida.setOnClickListener {
            isEntrada = false
            radioEntrada.isChecked = false
        }
    }

    private fun cadastrarDespesa() {
        val nome = editTextName.text.toString()
        val valor = editTextValue.text.toString().toDoubleOrNull()
        val data = editTextDate.text.toString()

        if (nome.isNotEmpty() && valor != null) {
            val despesaValues = ContentValues().apply {
                put(DespesaContract.DespesaEntry.COLUMN_NOME, nome)
                put(DespesaContract.DespesaEntry.COLUMN_VALOR, valor)
                put(DespesaContract.DespesaEntry.COLUMN_DATA, data)
                put(DespesaContract.DespesaEntry.COLUMN_IS_ENTRADA, if (isEntrada) 1 else 0)
            }

            val db = dbHelper.writableDatabase
            val newRowId = db.insert(DespesaContract.DespesaEntry.TABLE_NAME, null, despesaValues)

            if (newRowId == -1L) {
                Toast.makeText(
                    this,
                    "Erro ao inserir despesa no banco de dados",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Despesa inserida com sucesso", Toast.LENGTH_SHORT).show()
                limparCampos()
            }
        } else {
            Toast.makeText(this, "Nome e Valor são obrigatórios", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limparCampos() {
        editTextName.text.clear()
        editTextValue.text.clear()
        editTextDate.text.clear()
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}