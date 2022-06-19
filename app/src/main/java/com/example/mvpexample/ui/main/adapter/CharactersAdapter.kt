package com.example.mvpexample.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mvpexample.databinding.RowCharactersBinding
import com.example.mvpexample.domain.model.CharacterItem

class CharactersAdapter(
    private val onItemClickedCallback: (item: CharacterItem) -> Unit
) : ListAdapter<CharacterItem, CharactersAdapter.ItemViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            RowCharactersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickedCallback
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ItemViewHolder(
        private val binding: RowCharactersBinding,
        private val onItemClickedCallback: (item: CharacterItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterItem) =
            with(binding) {
                title.text = item.title
                image.load(item.image) {
                    crossfade(true)
                }
                root.setOnClickListener {
                    onItemClickedCallback(item)
                }
            }
    }

    private class DiffCallback :
        DiffUtil.ItemCallback<CharacterItem>() {
        override fun areItemsTheSame(
            oldItem: CharacterItem,
            newItem: CharacterItem
        ) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CharacterItem,
            newItem: CharacterItem
        ) =
            oldItem == newItem
    }
}
