package com.example.drcpract.model

import com.google.gson.annotations.SerializedName


data class UserDataModel(

    @SerializedName("html_attributions") var htmlAttributions: ArrayList<String> = arrayListOf(),
    @SerializedName("next_page_token") var nextPageToken: String? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf(),
    @SerializedName("status") var status: String? = null

)