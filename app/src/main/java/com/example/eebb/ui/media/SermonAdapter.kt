package com.example.eebb.ui.media

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eebb.databinding.ItemSermonBinding
import com.example.eebb.ui.model.Sermon

class SermonAdapter : RecyclerView.Adapter<SermonAdapter.SermonViewHolder>() {

    private val items = mutableListOf<Sermon>()

    fun submit(list: List<Sermon>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SermonViewHolder {
        val binding = ItemSermonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SermonViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SermonViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class SermonViewHolder(private val binding: ItemSermonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sermon: Sermon) {
            binding.sermonTitle.text = sermon.title
            binding.sermonMeta.text = "${sermon.speaker} • ${sermon.duration} • ${sermon.publishedOn}"
            binding.sermonTag.text = sermon.tag
            binding.sermonTag.visibility = if (sermon.tag.isBlank()) View.GONE else View.VISIBLE
        }
    }
}
