package com.cheesecakelabs.mnm_todoistsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_todo_list.*

class TodoListActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        todoList.layoutManager = LinearLayoutManager(this)
        todoList.adapter = TodoListAdapter(this, listOfTasks as ArrayList<String>)

        listOfTasks.add("TESTE")
        listOfTasks.add("TESTE 1")
        listOfTasks.add("TESTE 2")
        listOfTasks.add("TESTE 3")
        listOfTasks.add("TESTE 4")
        listOfTasks.add("TESTE 5")


    }
}
