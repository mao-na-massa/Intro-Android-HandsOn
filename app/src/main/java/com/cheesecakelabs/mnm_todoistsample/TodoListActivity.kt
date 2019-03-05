package com.cheesecakelabs.mnm_todoistsample

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_todo_list.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


class TodoListActivity : AppCompatActivity() {

    /*
    *
    * Variáveis e tipos de dados
    *
    * */

    var listOfTasks = mutableListOf<String>()
    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        todoList.layoutManager = LinearLayoutManager(this)
        todoList.adapter = TodoListAdapter(this, listOfTasks as ArrayList<String>)

        newTaskButton.setOnClickListener {
            addNewTask()
        }

    }

    fun addNewTask() {
        var task = newTaskEdit.text.toString()
        listOfTasks.add(task)
        count = count + 1
        todoList.adapter.notifyDataSetChanged()
        todoList.scrollToPosition(listOfTasks.size - 1)
        clearNewTask()
    }

    fun clearNewTask() {
        newTaskEdit.setText("")
        newTaskEdit.clearFocus()
        val imm = newTaskEdit.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(newTaskEdit.getWindowToken(), 0)
    }
}
