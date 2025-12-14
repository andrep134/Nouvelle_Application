package com.example.eebb.ui.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eebb.databinding.ItemEventCardBinding
import com.example.eebb.ui.model.Event

class EventsAdapter(
    private val onAddToCalendar: (Event) -> Unit
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    private val items = mutableListOf<Event>()

    fun submit(events: List<Event>) {
        items.clear()
        items.addAll(events)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class EventViewHolder(private val binding: ItemEventCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.eventTitle.text = event.title
            binding.eventSchedule.text = event.schedule
            binding.eventLocation.text = event.location
            binding.eventDescription.text = event.description
            binding.addToCalendarButton.setOnClickListener { onAddToCalendar(event) }
        }
    }
}
