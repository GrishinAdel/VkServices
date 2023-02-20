package com.adelvanchik.domain.usecases


import com.adelvanchik.domain.repository.ServicesRepository

class UpdateServicesUseCase(private val servicesRepository: ServicesRepository) {
    operator fun invoke() {
        servicesRepository.updateServices()
    }
}
