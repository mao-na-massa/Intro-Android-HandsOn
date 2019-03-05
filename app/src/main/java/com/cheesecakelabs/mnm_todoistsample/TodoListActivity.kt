package com.cheesecakelabs.mnm_todoistsample

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_todo_list.*
import android.view.inputmethod.InputMethodManager
import java.util.*


class TodoListActivity : AppCompatActivity() {

    /*
    *
    * Variáveis e tipos de dados
    *
    * */

    var listOfTasks = mutableListOf<TaskModel>()
    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        todoList.layoutManager = LinearLayoutManager(this)
        todoList.adapter = TodoListAdapter(this, listOfTasks as ArrayList<TaskModel>)

        newTaskButton.setOnClickListener {
            addNewTask()
        }
    }

    fun addNewTask() {
        var description = newTaskEdit.text.toString()
        var task = TaskModel()
        task.description = description
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

    override fun onPause() {
        super.onPause()
        Utils.saveTasksToSharedPreferences(this, listOfTasks)
    }

    override fun onResume() {
        super.onResume()
        listOfTasks.clear()
        listOfTasks.addAll(Utils.getTasksFromSharedPreferences(this))
        todoList.adapter.notifyDataSetChanged()
    }
}
