package com.cheesecakelabs.mnm_todoistsample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TodoListAdapter(var context: Context, var tasks: ArrayList<String>): RecyclerView.Adapter<TodoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.todo_list_viewholder, parent, false)
        return TodoListViewHolder(view)
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

    init {
        textView = view.findViewById(R.id.taskText)
    }

    fun setup(text: String) {
        textView?.text = text
    }

}