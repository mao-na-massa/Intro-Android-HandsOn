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
        var description = newTaskEdit.text.toString()
        listOfTasks.add(description)
        totalCount = totalCount + 1
        todoList.adapter.notifyDataSetChanged()
        setCounter()
        clearNewTask()
    }

    fun getNumberOfPendingTasks(): Int {
        var numberOfPendingTasks = 0
        for (task in listOfTasks) {
            var found = false
            for (doneTask in listOfDoneTasks) {
                if (doneTask == task) {
                    found = true
                }
            }
            if (!found) {
                numberOfPendingTasks++
            }
        }
        return numberOfPendingTasks
    }

    fun setCounter() {
        var numberOfPending = getNumberOfPendingTasks()
        countPendingText.text = numberOfPending.toString()
        countTotalText.text = listOfTasks.size.toString()
        if (numberOfPending > 5) {
            countStatusView.setBackgroundColor(Color.parseColor("#CC0000"))
        } else if (numberOfPending > 2) {
            countStatusView.setBackgroundColor(Color.parseColor("#FFFF00"))
        } else {
            countStatusView.setBackgroundColor(Color.parseColor("#00CC00"))
        }
    }

}
