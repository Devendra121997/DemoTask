package com.example.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(private val onItemClick: (DataModel) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val posts = mutableListOf<DataModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
             // Initialize views
             val idTextView: TextView = itemView.findViewById(R.id.idTextView)
             val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)

            fun bind(post: DataModel) {
                // Bind data to views
                idTextView.text = post.id.toString()
                titleTextView.text = post.title

            }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data to the ViewHolder
        // val post = posts[position]
        holder.bind(posts[position])
        holder.itemView.setOnClickListener { onItemClick(posts[position]) }
        //     holder.itemView.setOnClickListener { onItemClick(item) }

    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun addPosts(newPosts: List<DataModel>) {
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }


    }
