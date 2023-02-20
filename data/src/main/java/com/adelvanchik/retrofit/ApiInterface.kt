package com.adelvanchik.retrofit

import com.adelvanchik.domain.entity.Items
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("semi-final-data.json")
    fun getItems(): Call<Items>

    companion object {

        private const val BASE_URL = "https://mobile-olympiad-trajectory.hb.bizmrg.com/"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

}