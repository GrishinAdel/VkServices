package com.adelvanchik.presentation.recycleview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.adelvanchik.R
import com.adelvanchik.domain.entity.Item
import com.squareup.picasso.Picasso


class ServiceListAdapter : ListAdapter<Item, ServiceViewHolder>(ServiceDiffUtilCallback()) {

    var showDetailsAboutTheService: ((item: Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.pattern_one_service,
            parent,
            false
        )
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = getItem(position)
        with(holder) {
            tvNameService.text = service.name
            Picasso.get().load(service.icon_url).into(ivIconService)
            layout.setOnClickListener {
                showDetailsAboutTheService?.invoke(Item(
                    description = service.description,
                    name = service.name,
                    icon_url = service.icon_url,
                    service_url = service.service_url,
                ))
            }
        }
    }

    fun interface ShowDetailsAboutTheService {
        fun invoke()
    }

}