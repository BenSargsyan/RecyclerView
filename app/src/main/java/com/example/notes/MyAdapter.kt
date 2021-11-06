package com.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (private var elements:List<NoteElement>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(item:NoteElement)
        {
            itemView.findViewById<EditText>(R.id.titleText).setText(item.title)
            itemView.findViewById<EditText>(R.id.descriptionText).setText(item.description)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(parent.context.applicationContext)
        .inflate(R.layout.fragment_note,parent,false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(elements[position])
    }
    override fun getItemCount()=elements.size

    fun getList():List<NoteElement>
    {
        return elements
    }

     fun removeNote(pos:Int)
    {
        elements.toMutableList().removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun addNote(note:NoteElement)
    {
        elements.toMutableList().add(note)
        notifyItemInserted(elements.size-1)
    }

}