package org.nekojess.nutriease.ui.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.nekojess.nutriease.databinding.ItemInformationBinding
import org.nekojess.nutriease.util.StringUtils.EMPTY_STRING

internal class ItemListInformationAdapter(private var data: List<Item>) :
    RecyclerView.Adapter<ItemListInformationAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemInformationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val titleTextView = binding.adapterItemTitle
        private val descriptionTextView = binding.adapterItemDescription

        fun bind(data: Item) {
            setTexts(data)
        }

        private fun setTexts(data: Item) {
            titleTextView.text = data.title
            descriptionTextView.text = data.description
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemListInformationAdapter.ViewHolder {
        val binding = ItemInformationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListInformationAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    internal data class Item(
        val title: String = EMPTY_STRING,
        val description: String = EMPTY_STRING
    )
}
