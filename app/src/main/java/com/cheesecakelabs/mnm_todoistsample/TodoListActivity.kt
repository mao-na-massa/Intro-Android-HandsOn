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

    var lista: ListaTarefas = ListaTarefas()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        todoList.layoutManager = LinearLayoutManager(this)
        todoList.adapter = TodoListAdapter(this, lista, { task: Tarefa? ->
            setCounter()
        })

        newTaskButton.setOnClickListener {
            addNewTask()
        }
    }

    fun addNewTask() {
        var description = newTaskEdit.text.toString()
        // Como fazemos para adicionar uma nova tarefa?
        var tarefa = Tarefa()
        tarefa.descricao = description
        lista.tarefas.add(tarefa)


        todoList.adapter.notifyDataSetChanged()
        clearNewTask()
        setCounter()
    }

    fun clearNewTask() {
        newTaskEdit.setText("")
        newTaskEdit.clearFocus()
        val imm = newTaskEdit.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(newTaskEdit.getWindowToken(), 0)
    }

    fun setCounter() {
        var pendingCount = 0
        // Como implementamos o contador dessa vez?

        for(tarefa in lista.tarefas) {
            if (!tarefa.feito) {
                pendingCount += 1
            }
        }

        countPendingText.text = pendingCount.toString()
        countTotalText.text = lista.tarefas.size.toString()

        if (pendingCount > 5) {
            countStatusView.setBackgroundColor(Color.parseColor("#CC0000"))
        } else if (pendingCount > 2) {
            countStatusView.setBackgroundColor(Color.parseColor("#FFFF00"))
        } else {
            countStatusView.setBackgroundColor(Color.parseColor("#00CC00"))
        }
    }

    override fun onPause() {
        super.onPause()
        Utils.saveLista(this, lista)
    }

    override fun onResume() {
        super.onResume()
//        lista.tarefas.clear()
        lista = Utils.getLista(this)
        (todoList.adapter as TodoListAdapter).lista = lista
        todoList.adapter.notifyDataSetChanged()
        setCounter()
    }
}
