package com.cheesecakelabs.mnm_todoistsample

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Agenda() : Parcelable {

    @SerializedName("listaDeTarefas")
    var listaDeTarefas: MutableList<ListaTarefas> = mutableListOf()

    constructor(parcel: Parcel) : this() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeArray(listaDeTarefas.toTypedArray())

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Agenda> {
        override fun createFromParcel(parcel: Parcel): Agenda {
            return Agenda(parcel)
        }

        override fun newArray(size: Int): Array<Agenda?> {
            return arrayOfNulls(size)
        }
    }

}