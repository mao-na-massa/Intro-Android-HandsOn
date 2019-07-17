package com.cheesecakelabs.mnm_todoistsample

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_ultima_aula.*
import kotlin.random.Random

class UltimaAulaActivity : AppCompatActivity() {

    var mostrarLingua = true
    var mostrarRetina1 = true
    var coresNariz = mutableListOf<String>("#cacaca", "#ff0000", "#00ff00", "#ffffff", "#caca00", "#ca00ff", "#1100ff")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultima_aula)
        olhoEsquerdo.setOnClickListener {
            mexerBoneco()
        }
        olhoDireito.setOnClickListener {
            mexerBoneco()
        }
        boca.setOnClickListener {
            mexerBoneco()
        }
        nariz.setOnClickListener {
            mexerBoneco()
        }
        corpo.setOnClickListener {
            mexerBoneco()
        }
    }

    fun mexerBoneco() {

        if (mostrarRetina1) {
            var params: RelativeLayout.LayoutParams = cabeca.layoutParams as RelativeLayout.LayoutParams
            params.setMargins(0, 164, 0, 0)
            cabeca.layoutParams = params
            retinaEsquerda1.visibility = View.GONE
            retinaEsquerda2.visibility = View.VISIBLE
            retinaDireita1.visibility = View.GONE
            retinaDireita2.visibility = View.VISIBLE
            bone.visibility = View.VISIBLE
            bracoDireito.visibility = View.GONE
            bracoDireito2.visibility = View.VISIBLE
            bracoEsquerdo.visibility = View.GONE
            bracoEsquerdo2.visibility = View.VISIBLE

            pernaDireita.visibility = View.GONE
            pernaDireita2.visibility = View.VISIBLE
            pernaEsquerda.visibility = View.GONE
            pernaEsquerda2.visibility = View.VISIBLE

        } else {
            var params: RelativeLayout.LayoutParams = cabeca.layoutParams as RelativeLayout.LayoutParams
            params.setMargins(0, 188, 0, 0)
            cabeca.layoutParams = params
            retinaEsquerda2.visibility = View.GONE
            retinaEsquerda1.visibility = View.VISIBLE
            retinaDireita1.visibility = View.VISIBLE
            retinaDireita2.visibility = View.GONE
            bone.visibility = View.GONE
            bracoDireito.visibility = View.VISIBLE
            bracoDireito2.visibility = View.GONE
            bracoEsquerdo.visibility = View.VISIBLE
            bracoEsquerdo2.visibility = View.GONE

            pernaDireita.visibility = View.VISIBLE
            pernaDireita2.visibility = View.GONE
            pernaEsquerda.visibility = View.VISIBLE
            pernaEsquerda2.visibility = View.GONE
        }
        if (mostrarLingua) {
            lingua.visibility = View.GONE
        } else {
            lingua.visibility = View.VISIBLE
        }
        mostrarLingua = !mostrarLingua
        mostrarRetina1 = !mostrarRetina1
        nariz.setBackgroundColor(Color.parseColor(coresNariz[(0..(coresNariz.size-1)).random()]))

    }
}
