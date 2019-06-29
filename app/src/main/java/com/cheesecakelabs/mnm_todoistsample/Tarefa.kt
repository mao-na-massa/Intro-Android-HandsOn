package com.cheesecakelabs.mnm_todoistsample

import android.text.format.DateFormat
import com.google.gson.annotations.SerializedName
import java.util.*

class Tarefa {

    var descricao: String = ""
    var data: Date = Date()
    var feito: Boolean = false

    fun prioritaria(): Boolean {
        if (descricao.startsWith("#1")) {
            return true
        }
        return false
    }

}