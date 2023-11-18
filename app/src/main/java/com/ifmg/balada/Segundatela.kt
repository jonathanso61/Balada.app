package com.ifmg.balada

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Segundatela : AppCompatActivity() {

    private lateinit var textViewCaixa: TextView
    private lateinit var textViewGasto: TextView
    private lateinit var textViewTotal: TextView

    private var somaEntradas = 0.0
    private var somaSaidas = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.segundatela)

        textViewCaixa = findViewById(R.id.textViewCaixa)
        textViewGasto = findViewById(R.id.textViewGasto)
        textViewTotal = findViewById(R.id.textViewTotal)

        try {
            val nome = intent.getStringExtra("NOME")
            val valor = intent.getDoubleExtra("VALOR", 0.0)
            val data = intent.getStringExtra("DATA")
            val isEntrada = intent.getBooleanExtra("IS_ENTRADA", false)

            if (isEntrada) {
                somaEntradas += valor
            } else {
                somaSaidas += valor
            }

            val saldo = somaEntradas - somaSaidas

            textViewCaixa.text = "Entradas: $somaEntradas"
            textViewGasto.text = "Saídas: $somaSaidas"
            textViewTotal.text = "Saldo: $saldo"

        } catch (e: Exception) {
            Log.e("Segundatela", "Erro ao processar dados da Intent: ${e.message}")
            e.printStackTrace()
        }
    }
}

