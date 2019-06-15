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
    var totalCount: Int = 0

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
        var task = Tarefa()
        task.description = description
        lista.tarefas.add(task)
        totalCount = totalCount + 1
        todoList.adapter.notifyDataSetChanged()
        todoList.scrollToPosition(lista.tarefas.size - 1)
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
        for (task in lista.tarefas) {
            if (!task.isDone) {
                pendingCount = pendingCount + 1
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
        lista.tarefas.clear()
        lista = Utils.getLista(this)
        (todoList.adapter as TodoListAdapter).lista = lista
        todoList.adapter.notifyDataSetChanged()
        setCounter()
    }
}
