package com.cheesecakelabs.mnm_todoistsample

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class Utils {

    companion object {
        fun saveLista(context: Context, lista: ListaTarefas) {
            val preferences = context.getSharedPreferences("MNM_TODOIST_SAMPLE", Context.MODE_PRIVATE)
            var gson = Gson()
            val agendaString = preferences.getString("AGENDA", "")
            var agenda = Agenda()
            var index = -1
            if (agendaString != "") {
                agenda = gson.fromJson(agendaString, Agenda::class.java)
                index = agenda.listaDeTarefas.indexOf(lista)
            }
            if (index >= 0) {
                agenda.listaDeTarefas.removeAt(index)
            }
            agenda.listaDeTarefas.add(lista)
            Log.d("DEBUG", agendaString)
            preferences.edit().putString("AGENDA", gson.toJson(agenda).toString()).apply()
        }

        fun getLista(context: Context, nome: String): ListaTarefas? {
            val preferences = context.getSharedPreferences("MNM_TODOIST_SAMPLE", Context.MODE_PRIVATE)
            val agendaString = preferences.getString("AGENDA", "")
            Log.d("DEBUG", agendaString)
            val gson = Gson()
            if (agendaString != "") {
                var agenda = gson.fromJson(agendaString, Agenda::class.java)

                for (lista in agenda.listaDeTarefas) {
                    if (lista.nome == nome) {
                        return lista
                    }
                }

            }
            return null
        }
    }

}