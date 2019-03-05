package com.cheesecakelabs.mnm_todoistsample

import android.content.Context
import com.google.gson.Gson
import org.json.JSONArray

class Utils {

    companion object {
        fun saveTasksToSharedPreferences(context: Context, tasks: MutableList<TaskModel>) {
            var gson = Gson()
            var preferences = context.getSharedPreferences("MNM_TODOIST_SAMPLE", Context.MODE_PRIVATE)
            var jsonArray = JSONArray()
            for (task in tasks) {
                jsonArray.put(gson.toJson(task))
            }
            preferences.edit().putString("TASKS", jsonArray.toString()).apply()
        }

        fun getTasksFromSharedPreferences(context: Context): MutableList<TaskModel> {
            var listOfTasks = mutableListOf<TaskModel>()
            val preferences = context.getSharedPreferences("MNM_TODOIST_SAMPLE", Context.MODE_PRIVATE)
            val jsonArray = JSONArray(preferences.getString("TASKS", "[]"))
            val gson = Gson()
            for (i in 0..jsonArray.length() - 1) {
                var taskJSON = jsonArray.getString(i)
                listOfTasks.add(gson.fromJson(taskJSON, TaskModel::class.java))
            }

            return listOfTasks
        }
    }

}