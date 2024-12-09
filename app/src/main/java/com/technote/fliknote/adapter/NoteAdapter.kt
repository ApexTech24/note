package com.technote.fliknote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.technote.fliknote.R
import com.technote.fliknote.databinding.NoteRowItemBinding
import com.technote.fliknote.model.Note

class NoteAdapter(val itemCallback: (Note) ->Unit, val itemDeleteCallback: (Note) ->Unit):
    ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffUtil()) {

    class NoteViewHolder(val binding:NoteRowItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note:Note){
            binding.note=note
        }
    }

    class NoteDiffUtil: DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem==newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding=NoteRowItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note:Note=getItem(position)
        holder.bind(note)

        holder.itemView.setOnClickListener {
            itemCallback(note)
        }
        holder.binding.deleteIVId.setOnClickListener {
            itemDeleteCallback(note)
        }
    }
}
