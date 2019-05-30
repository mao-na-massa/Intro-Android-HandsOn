package com.cheesecakelabs.mnm_todoistsample

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_todo_list.*
import android.view.inputmethod.InputMethodManager
import java.util.*


class TodoListActivity : AppCompatActivity() {

    /*
    *
    * Atividade: Implementar getNumberOfPendingTasks e setCounter
    *   - Utilizando um array de tarefas feitas
    *   - Encontre o bug, por que acontece?
    *   - Existe uma outra forma de implementar a mesma funcionalidade?
    *
    * */

    var listOfTasks = mutableListOf<String>()
    var listOfDoneTasks = mutableListOf<String>()
    var totalCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        todoList.layoutManager = LinearLayoutManager(this)
        todoList.adapter = TodoListAdapter(this, listOfTasks as ArrayList<String>, { task: String?, isChecked: Boolean ->
            if (isChecked) {
                listOfDoneTasks.add(task!!)
            } else {
                listOfDoneTasks.remove(task!!)
            }
            setCounter()
        })

        newTaskButton.setOnClickListener {
            addNewTask()
        }
    }

    fun clearNewTask() {
        newTaskEdit.setText("")
        newTaskEdit.clearFocus()
        val imm = newTaskEdit.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(newTaskEdit.getWindowToken(), 0)
    }

    fun addNewTask() {
        // Pega a descrição do texto e adicionar em uma variável
        var tarefa = newTaskEdit.text.toString()
        listOfTasks.add(tarefa)
        todoList.adapter.notifyDataSetChanged()
        clearNewTask()
        setCounter()
    }

    fun getNumberOfPendingTasks(): Int {
        var tarefasPendentes = 0
        for (tarefa in listOfTasks) {
            println("Lista de tarefas: ${tarefa}")
            var achou = false
            for (tarefaFeita in listOfDoneTasks) {
                println("Lista de tarefas feitas: ${tarefaFeita}")
                if (tarefa == tarefaFeita) {
                    achou = true
                }
            }
            if (!achou) {
                tarefasPendentes = tarefasPendentes + 1
            }
        }
        return tarefasPendentes
    }

    fun setCounter() {
        var tarefasPendentes = listOfTasks.size - listOfDoneTasks.size
        var totalTarefas = listOfTasks.size
        countPendingText.text = "${tarefasPendentes}"
        countTotalText.text = "${totalTarefas}"
    }

}
