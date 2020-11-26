package id.saipulmuiz.forwaapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.saipulmuiz.forwaapp.R
import id.saipulmuiz.forwaapp.data.model.EventList
import id.saipulmuiz.forwaapp.databinding.ItemEventBinding
import id.saipulmuiz.forwaapp.databinding.ItemUserBinding

class EventListAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

    private var list: List<EventList> = listOf()

    inner class ViewHolder(val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_event,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        holder.binding.event = data
        holder.itemView.setOnClickListener {
            listener.onEventClickListener(it, data)
        }
    }

    fun setList(list: List<EventList>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface Listener{
        fun onEventClickListener(view: View, data: EventList)
    }

}