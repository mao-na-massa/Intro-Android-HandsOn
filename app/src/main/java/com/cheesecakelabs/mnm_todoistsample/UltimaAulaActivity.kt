package com.cheesecakelabs.mnm_todoistsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_ultima_aula.*

class UltimaAulaActivity : AppCompatActivity() {

    var mostrarLingua = true
    var mostrarRetina1 = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultima_aula)
        olhoEsquerdo.setOnClickListener {
            if (mostrarRetina1) {
                retinaEsquerda1.visibility = View.GONE
                retinaEsquerda2.visibility = View.VISIBLE
            } else {
                retinaEsquerda2.visibility = View.GONE
                retinaEsquerda1.visibility = View.VISIBLE
            }
            mostrarRetina1 = !mostrarRetina1
        }
        boca.setOnClickListener {
            if (mostrarLingua) {
                lingua.visibility = View.GONE
            } else {
                lingua.visibility = View.VISIBLE
            }
            mostrarLingua = !mostrarLingua
        }
    }
}
