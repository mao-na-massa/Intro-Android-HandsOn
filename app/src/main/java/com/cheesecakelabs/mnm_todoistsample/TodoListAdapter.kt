package com.cheesecakelabs.mnm_todoistsample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import java.util.*

class TodoListAdapter(var context: Context, var tasks: ArrayList<String>, var callback: (task: String?, isChecked: Boolean) -> Unit): RecyclerView.Adapter<TodoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.todo_list_viewholder, parent, false)
        var viewHolder = TodoListViewHolder(view)
        viewHolder.checkBox?.setOnCheckedChangeListener { buttonView, isChecked ->
            callback(viewHolder.taskModel, isChecked)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.setup(tasks[position])
    }
}

class TodoListViewHolder(var view: View): RecyclerView.ViewHolder(view) {

    var textView: TextView? = null
    var dateView: TextView? = null
    var checkBox: CheckBox? = null
    var taskModel: String? = null

    init {
        textView = view.findViewById(R.id.taskText)
        dateView = view.findViewById(R.id.taskDateText)
        checkBox = view.findViewById(R.id.taskIsDoneCheck)
    }

    fun setup(task: String) {
        taskModel = task
        textView?.text = task
        dateView?.text = Date().toLocaleString()
    }

}