package com.gilboot.agriculturemarket.produce

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gilboot.agriculturemarket.databinding.ProduceListItemBinding
import com.gilboot.agriculturemarket.models.Produce

/**
 * Adapter for ProduceList
 */
class ProduceListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Produce, ProduceListAdapter.ViewHolder>(
        ItemDiffCallback()
    ) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produce: Produce = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(produce)
        }
        holder.bind(produce)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder.from(parent)

    class ViewHolder private constructor(private val binding: ProduceListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(produce: Produce) {
            binding.produce = produce
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProduceListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class OnClickListener(val clickListener: (produce: Produce) -> Unit) {
        fun onClick(produce: Produce) = clickListener(produce)
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<Produce>() {
    override fun areItemsTheSame(oldProduce: Produce, newProduce: Produce) =
        oldProduce.id == newProduce.id

    override fun areContentsTheSame(oldProduce: Produce, newProduce: Produce) =
        oldProduce == newProduce

}
