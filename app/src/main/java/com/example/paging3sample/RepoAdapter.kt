package com.example.paging3sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class RepoAdapter(val nameClick: (name: String) -> Unit) : PagingDataAdapter<Repo, RepoAdapter.ViewHoler>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return  oldItem == newItem
            }

        }
    }

    class ViewHoler(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name_text)
        val description: TextView = itemView.findViewById(R.id.description_text)
        val starCount: TextView = itemView.findViewById(R.id.star_count_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoAdapter.ViewHoler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        val holder = ViewHoler(view)
        holder.name.setOnClickListener {
            val name = getItem(position = holder.layoutPosition)?.name ?: ""
            nameClick(name)
        }
        return holder
    }

    override fun onBindViewHolder(holder: RepoAdapter.ViewHoler, position: Int) {
        val repo = getItem(position)
        if (repo != null) {
            holder.name.text = repo.name
            holder.description.text = repo.description
            holder.starCount.text = repo.starCount.toString()
        }
    }
}