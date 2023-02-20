package com.adelvanchik.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adelvanchik.domain.entity.Item
import com.adelvanchik.domain.usecases.ListServicesObserverUseCase
import com.adelvanchik.domain.usecases.UpdateServicesUseCase
import com.adelvanchik.repositoryimpl.ServicesRepositoryImpl

class MainViewModel() : ViewModel() {

    // Поскольку сторонние библиотеки подключать запрещено, то внедрять di нет возможности
    // Реализация будем напрямую без зависимостей di (Koin, Dagger, Hilt..)

    private val servicesRepositoryImpl = ServicesRepositoryImpl()
    private val updateServicesUseCase = UpdateServicesUseCase(servicesRepositoryImpl)
    private val listServicesObserverUseCase = ListServicesObserverUseCase(servicesRepositoryImpl)

    private val _listServicesLiveData: LiveData<List<Item>?> = listServicesObserverUseCase()
    val listServicesLiveData: LiveData<List<Item>?>
        get() = _listServicesLiveData


    fun getListServices() {
        updateServicesUseCase()
    }

}