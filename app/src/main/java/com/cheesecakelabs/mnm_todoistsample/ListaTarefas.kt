package com.cheesecakelabs.mnm_todoistsample

import com.google.gson.annotations.SerializedName

class ListaTarefas {

    @SerializedName("tarefas")
    var tarefas: MutableList<Tarefa> = mutableListOf()

}