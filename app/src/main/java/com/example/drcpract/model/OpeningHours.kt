package com.example.drcpract.model

import com.google.gson.annotations.SerializedName


data class OpeningHours(

    @SerializedName("open_now") var openNow: Boolean? = null

)