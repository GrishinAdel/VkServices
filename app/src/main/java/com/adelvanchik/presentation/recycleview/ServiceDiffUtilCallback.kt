package com.adelvanchik.presentation.recycleview

import androidx.recyclerview.widget.DiffUtil
import com.adelvanchik.domain.entity.Item

class ServiceDiffUtilCallback: DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}