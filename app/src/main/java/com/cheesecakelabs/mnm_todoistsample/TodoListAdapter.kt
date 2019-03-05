package com.cheesecakelabs.mnm_todoistsample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView

class TodoListAdapter(var context: Context, var tasks: ArrayList<TaskModel>, var callback: (task: TaskModel?) -> Unit): RecyclerView.Adapter<TodoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.todo_list_viewholder, parent, false)
        var viewHolder = TodoListViewHolder(view)
        viewHolder.checkBox?.setOnCheckedChangeListener { buttonView, isChecked ->
            viewHolder.taskModel?.isDone = isChecked
            callback(viewHolder.taskModel)
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
    var taskModel: TaskModel? = null

    init {
        textView = view.findViewById(R.id.taskText)
        dateView = view.findViewById(R.id.taskDateText)
        checkBox = view.findViewById(R.id.taskIsDoneCheck)
//        checkBox?.setOnCheckedChangeListener({ buttonView, isChecked ->
//            taskModel?.isDone = isChecked
//            callback(taskModel)
//        })
    }

    fun setup(task: TaskModel) {
        taskModel = task
        textView?.text = task.description
        dateView?.text = task.date
        checkBox?.isChecked = task.isDone
    }

}