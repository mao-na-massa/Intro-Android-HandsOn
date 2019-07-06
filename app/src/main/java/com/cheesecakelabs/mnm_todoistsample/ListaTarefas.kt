package com.cheesecakelabs.mnm_todoistsample

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ListaTarefas(nome: String): Parcelable {

    @SerializedName("tarefasFeitas")
    var tarefasTodas: MutableList<Tarefa> = mutableListOf()

    @SerializedName("tarefasNaoFeitas")
    var tarefasNaoFeitas: MutableList<Tarefa> = mutableListOf()

    @SerializedName("nome")
    var nome: String = ""

    var mostrarTodasTarefas: Boolean = false

    constructor(parcel: Parcel) : this("TESTE") {
        nome = parcel.readString()
        mostrarTodasTarefas = parcel.readByte() != 0.toByte()
    }

    init {
        this.nome = nome
    }

    fun marcarTarefaComoFeita(tarefa: Tarefa) {
        if (tarefa.feito) {
            tarefasNaoFeitas.remove(tarefa)
        } else {
            tarefasNaoFeitas.add(tarefa)
        }
    }

    fun adicionarNovaTarefa(tarefa: Tarefa) {
        tarefasTodas.add(tarefa)
        tarefasNaoFeitas.add(tarefa)
    }

    override fun toString(): String {

        return nome
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
        parcel.writeByte(if (mostrarTodasTarefas) 1 else 0)
        parcel.writeArray(tarefasTodas.toTypedArray())
        parcel.writeArray(tarefasNaoFeitas.toTypedArray())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListaTarefas> {
        override fun createFromParcel(parcel: Parcel): ListaTarefas {
            return ListaTarefas(parcel)
        }

        override fun newArray(size: Int): Array<ListaTarefas?> {
            return arrayOfNulls(size)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is ListaTarefas) {
            return false
        }
        return other.nome == this.nome
    }

}