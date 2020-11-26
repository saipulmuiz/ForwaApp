package id.saipulmuiz.forwaapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.saipulmuiz.forwaapp.R
import id.saipulmuiz.forwaapp.data.model.UserSearch
import id.saipulmuiz.forwaapp.databinding.ItemUserBinding

// UserSearch Recycler View Adapter; Keyword : Adapter
class UserSearchAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<UserSearchAdapter.ViewHolder>() {

    private var list: List<UserSearch> = listOf()

    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_user,
                parent,
                false
            )
        )

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        holder.binding.user = data
        holder.itemView.setOnClickListener {
            listener.onUserClickListener(it, data)
        }
    }

    // Function : for change data in adapter
    fun setList(list: List<UserSearch>) {
        this.list = list
        notifyDataSetChanged()
    }

    // Interface : for listener onClick item
    interface Listener {
        fun onUserClickListener(view: View, data: UserSearch)
    }
}