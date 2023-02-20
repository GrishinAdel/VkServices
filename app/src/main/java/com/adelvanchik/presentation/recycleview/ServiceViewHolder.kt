package com.adelvanchik.presentation.recycleview

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adelvanchik.R

class ServiceViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val tvNameService: TextView = view.findViewById(R.id.tv_name_service)
    val ivIconService: ImageView = view.findViewById(R.id.iv_icon_service)
    val layout: LinearLayout = view.findViewById(R.id.layout)
}