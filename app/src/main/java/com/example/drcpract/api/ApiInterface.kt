package com.example.drcpract.api

import com.example.drcpract.model.UserDataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    // user
    @GET("search/json")
    fun getData(@Query("key") key: String,
                @Query("location") location: String,
                @Query("rankby") rankby: String,
                @Query("types") types: String,
                @Query("sensor") sensor: String,
    ): Observable<UserDataModel>


}