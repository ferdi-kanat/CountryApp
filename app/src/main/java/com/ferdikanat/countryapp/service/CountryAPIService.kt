package com.ferdikanat.countryapp.service

import com.ferdikanat.countryapp.model.Country
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {
    private val api = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountryAPI::class.java)

    fun getData(): Call<List<Country>>{
        return api.getCountries()
    }
}