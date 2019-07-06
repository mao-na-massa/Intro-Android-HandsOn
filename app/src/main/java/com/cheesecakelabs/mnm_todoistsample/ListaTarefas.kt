package com.cheesecakelabs.mnm_todoistsample

import com.google.gson.annotations.SerializedName

class ListaTarefas {

    @SerializedName("tarefasFeitas")
    var tarefasTodas: MutableList<Tarefa> = mutableListOf()

    var tarefasNaoFeitas: MutableList<Tarefa> = mutableListOf()

    var nome: String = ""

    var mostrarTodasTarefas: Boolean = false

    fun marcarTarefaComoFeita(tarefa: Tarefa) {
        if (tarefa.feito) {
            tarefasNaoFeitas.remove(tarefa)
        } else {
            tarefasNaoFeitas.add(tarefa)
        }
        // Se tarefa for feita, remover de tarefas nao feitas
        // Se não adicionar na lista de tarefas nao feitas
    }

    fun adicionarNovaTarefa(tarefa: Tarefa) {
        // Como a gente adiciona a descrição da tarefa no nome da Lista?
        tarefasTodas.add(tarefa)
        tarefasNaoFeitas.add(tarefa)
    }

    // Adicionar novas variáveis e métodos??

}