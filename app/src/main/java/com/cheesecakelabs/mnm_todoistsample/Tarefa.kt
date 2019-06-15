package com.cheesecakelabs.mnm_todoistsample

import android.text.format.DateFormat
import com.google.gson.annotations.SerializedName
import java.util.*

class Tarefa {

    @SerializedName("description")
    var description: String = ""
    @SerializedName("date")
    var date: String = DateFormat.format("dd/MM/yyyy", Date()).toString()
    @SerializedName("isDone")
    var isDone: Boolean = false

    fun isPriority(): Boolean {
        if (description.contains("#1")) {
            return true
        }
        return false
    }
}