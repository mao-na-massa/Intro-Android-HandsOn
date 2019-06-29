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
import java.text.DateFormat
import java.text.SimpleDateFormat

class TodoListAdapter(var context: Context, open var lista: ListaTarefas, var callback: (task: Tarefa?) -> Unit): RecyclerView.Adapter<TodoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.todo_list_viewholder, parent, false)
        var viewHolder = TodoListViewHolder(view)

        return viewHolder
    }

    override fun getItemCount(): Int {
        // Como mostrar as tarefas feitas e não feitas?
        return lista.tarefasTodas.size
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        // Precisamos montar a célula com o dado
        holder.checkBox?.setOnCheckedChangeListener(null)
        holder.setup(lista.tarefasTodas[position])
        holder.checkBox?.setOnCheckedChangeListener { buttonView, isChecked ->
            // O que devemos fazer quando o checkbox é marcado?
            holder.taskModel?.feito = isChecked
            callback(holder.taskModel)
            notifyDataSetChanged()
        }
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
    fun setup(tarefa: Tarefa) {
        taskModel = tarefa
        textView?.text = tarefa.descricao
        dateView?.text = SimpleDateFormat("dd/MM/YYYY hh:mm").format(tarefa.data)
        checkBox?.isChecked = tarefa.feito

        if (tarefa.prioritaria()) {
            view.setBackgroundColor(Color.parseColor("#CC0000"))
        } else {
            view.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }

    }

}