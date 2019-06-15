package com.cheesecakelabs.mnm_todoistsample

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class Utils {

    companion object {
        fun saveLista(context: Context, lista: ListaTarefas) {
            var gson = Gson()
            var preferences = context.getSharedPreferences("MNM_TODOIST_SAMPLE", Context.MODE_PRIVATE)
            preferences.edit().putString("TASKS", gson.toJson(lista).toString()).apply()
        }

        fun getLista(context: Context): ListaTarefas {
            val preferences = context.getSharedPreferences("MNM_TODOIST_SAMPLE", Context.MODE_PRIVATE)
            val lista = preferences.getString("TASKS", "")
            val gson = Gson()
            if (lista != "") {
                return gson.fromJson(lista, ListaTarefas::class.java)
            }
            return ListaTarefas()
        }
    }

}