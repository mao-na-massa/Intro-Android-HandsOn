package com.cheesecakelabs.mnm_todoistsample

import com.google.gson.annotations.SerializedName

class ListaTarefas {

    @SerializedName("tarefasFeitas")
    var tarefasTodas: MutableList<Tarefa> = mutableListOf()
    @SerializedName("tarefasNaoFeitas")
    var tarefasNaoFeitas: MutableList<Tarefa> = mutableListOf()
    @SerializedName("nomeDaLista")
    var nomeDaLista: String = "NOME DA LISTA 1"
    var mostrarTodas = false

    fun adicionarNovaTarefa(tarefa: Tarefa) {
        tarefasTodas.add(tarefa)
        tarefasNaoFeitas.add(tarefa)
    }

    fun marcarTarefaComoFeita(tarefa: Tarefa) {
        if (tarefa.feito) {
            tarefasNaoFeitas.remove(tarefa)
        } else {
            tarefasNaoFeitas.add(tarefa)
        }
    }

}