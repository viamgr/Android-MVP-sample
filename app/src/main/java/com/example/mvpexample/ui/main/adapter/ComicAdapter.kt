package com.example.mvpexample.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mvpexample.databinding.RowComicsBinding
import com.example.mvpexample.domain.model.ComicsItem

class ComicAdapter(
    private val onItemClickedCallback: (item: ComicsItem) -> Unit
) : ListAdapter<ComicsItem, ComicAdapter.ItemViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            RowComicsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickedCallback
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ItemViewHolder(
        private val binding: RowComicsBinding,
        private val onItemClickedCallback: (item: ComicsItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ComicsItem) =
            with(binding) {
                title.text = item.title
                description.isVisible = item.description != null
                item.description?.let {
                    description.text = it
                }
                image.load(item.image) {
                    crossfade(true)
                }
                root.setOnClickListener {
                    onItemClickedCallback(item)
                }
            }
    }

    private class DiffCallback :
        DiffUtil.ItemCallback<ComicsItem>() {
        override fun areItemsTheSame(
            oldItem: ComicsItem,
            newItem: ComicsItem
        ) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ComicsItem,
            newItem: ComicsItem
        ) =
            oldItem == newItem
    }
}
