package com.cheesecakelabs.mnm_todoistsample

import android.content.Context
import android.graphics.Color
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_todo_list.*
import android.view.inputmethod.InputMethodManager
import java.util.*


class TodoListActivity : AppCompatActivity() {

    var lista: ListaTarefas = ListaTarefas("LISTA")
    var nome: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        lista = intent.extras.getParcelable<ListaTarefas>("LISTA_TAREFA")
        nome = lista.nome

        todoList.layoutManager = LinearLayoutManager(this)
        todoList.adapter = TodoListAdapter(this, lista) { tarefa: Tarefa? ->
            // O que devemos fazer quando marcar uma tarefa?
            lista.marcarTarefaComoFeita(tarefa!!)
            setCounter()
        }

        newTaskButton.setOnClickListener {
            addNewTask()
        }

        switchTarefas.setOnCheckedChangeListener { buttonView, isChecked ->
            // O que fazer quando o toggle é ativado ou desativado?
            lista.mostrarTodasTarefas = isChecked
            todoList.adapter.notifyDataSetChanged()
        }

        nomeListaText.text = lista.nome

        nomeListaText.setOnClickListener {
            atualizarNomeLayout.visibility = View.VISIBLE
        }

        atualizarNomeButton.setOnClickListener {
            lista.nome = atualizarNomeText.text.toString()
            nomeListaText.text = lista.nome
            atualizarNomeLayout.visibility = View.GONE
        }

    }

    fun addNewTask() {
        var description = newTaskEdit.text.toString()
        var tarefa = Tarefa()
        tarefa.descricao = description
        lista.adicionarNovaTarefa(tarefa)

        todoList.adapter.notifyDataSetChanged()
        clearNewTask()
        setCounter()
    }

    fun clearNewTask() {
        newTaskEdit.setText("")
        newTaskEdit.clearFocus()
        val imm = newTaskEdit.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(newTaskEdit.getWindowToken(), 0)
    }

    fun setCounter() {
        countPendingText.text = lista.tarefasNaoFeitas.size.toString()
        countTotalText.text = lista.tarefasTodas.size.toString()

        if (lista.tarefasNaoFeitas.size > 5) {
            countStatusView.setBackgroundColor(Color.parseColor("#CC0000"))
        } else if (lista.tarefasNaoFeitas.size > 2) {
            countStatusView.setBackgroundColor(Color.parseColor("#FFFF00"))
        } else {
            countStatusView.setBackgroundColor(Color.parseColor("#00CC00"))
        }
    }

    override fun onPause() {
        super.onPause()
        Utils.saveLista(this, lista)
    }

    override fun onResume() {
        super.onResume()
        Utils.getLista(this, nome).let {
            if (it != null) {
                lista = it
            }
        }
        (todoList.adapter as TodoListAdapter).lista = lista
        todoList.adapter.notifyDataSetChanged()
        setCounter()
    }
}
