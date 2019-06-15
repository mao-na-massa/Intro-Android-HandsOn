package com.cheesecakelabs.mnm_todoistsample

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView

class TodoListAdapter(var context: Context, open var lista: ListaTarefas, var callback: (task: Tarefa?) -> Unit): RecyclerView.Adapter<TodoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.todo_list_viewholder, parent, false)
        var viewHolder = TodoListViewHolder(view)
        viewHolder.checkBox?.setOnCheckedChangeListener { buttonView, isChecked ->
            // O que devemos fazer quando o checkbox é marcado?
            callback(viewHolder.taskModel)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        // Devemos retornar o número de tarefas no Adapter
        return 0
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        // Precisamos montar a célula com o dado
//        holder.setup(lista.tarefas[position])
    }
}

class TodoListViewHolder(var view: View): RecyclerView.ViewHolder(view) {

    var textView: TextView? = null
    var dateView: TextView? = null
    var checkBox: CheckBox? = null
    var taskModel: Tarefa? = null

    init {
        textView = view.findViewById(R.id.taskText)
        dateView = view.findViewById(R.id.taskDateText)
        checkBox = view.findViewById(R.id.taskIsDoneCheck)
    }

    // Aqui devemos montar o layout
    fun setup(task: Tarefa) {
        taskModel = task
    }

}