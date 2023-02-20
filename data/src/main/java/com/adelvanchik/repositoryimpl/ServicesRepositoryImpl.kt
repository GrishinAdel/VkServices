package com.adelvanchik.repositoryimpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adelvanchik.domain.entity.Item
import com.adelvanchik.domain.entity.Items
import com.adelvanchik.domain.repository.ServicesRepository
import com.adelvanchik.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServicesRepositoryImpl : ServicesRepository {

    private var servicesLiveData = MutableLiveData<List<Item>?>()

    override fun updateServices() {
        val apiInterface = ApiInterface.create().getItems()
        var items: List<Item>? = null

        apiInterface.enqueue(object : Callback<Items> {
            override fun onResponse(call: Call<Items>, response: Response<Items>) {
                servicesLiveData.value = response.body()?.items

            }

            override fun onFailure(call: Call<Items>, t: Throwable) {
                servicesLiveData.value = null
            }
        })
    }

    override fun servicesObserver(): LiveData<List<Item>?> {
        return servicesLiveData
    }
}