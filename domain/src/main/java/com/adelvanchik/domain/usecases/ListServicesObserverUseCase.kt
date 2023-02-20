package com.adelvanchik.domain.usecases

import androidx.lifecycle.LiveData
import com.adelvanchik.domain.entity.Item
import com.adelvanchik.domain.repository.ServicesRepository

class ListServicesObserverUseCase(private val servicesRepository: ServicesRepository) {
    operator fun invoke(): LiveData<List<Item>?> {
        return servicesRepository.servicesObserver()
    }
}