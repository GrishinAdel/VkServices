package com.adelvanchik.domain.repository

import androidx.lifecycle.LiveData
import com.adelvanchik.domain.entity.Item

interface ServicesRepository {
    fun updateServices()
    fun servicesObserver(): LiveData<List<Item>?>
}