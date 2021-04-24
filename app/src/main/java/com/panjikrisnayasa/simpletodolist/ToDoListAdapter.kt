package com.panjikrisnayasa.simpletodolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class ToDoListAdapter : RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>() {

    var listToDo = ArrayList<ToDoList>()
        set(listToDo) {
            if (listToDo.size > 0) {
                this.listToDo.clear()
            }
            this.listToDo.addAll(listToDo)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_to_do, parent, false)
        return ToDoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.bind(listToDo[position])
    }

    override fun getItemCount(): Int {
        return this.listToDo.size
    }

    inner class ToDoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(toDoList: ToDoList) {
            val image: CircleImageView = itemView.findViewById(R.id.image_item_to_do)
            val title: TextView = itemView.findViewById(R.id.text_item_title)
            val description: TextView = itemView.findViewById(R.id.text_item_description)
            val createdAt: TextView = itemView.findViewById(R.id.text_item_created_at)
            val createdBy: TextView = itemView.findViewById(R.id.text_item_created_by)

            Glide.with(itemView.context).load(toDoList.image).into(image)
            title.text = toDoList.title
            description.text = toDoList.description
            createdAt.text = String.format("Created at: %s", toDoList.date)
            createdBy.text = String.format("Created by: %s")
        }
    }
}