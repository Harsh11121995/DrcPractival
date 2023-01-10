package com.example.drcpract.model

import com.google.gson.annotations.SerializedName


data class Geometry(

    @SerializedName("location") var location: Location? = Location(),
    @SerializedName("viewport") var viewport: Viewport? = Viewport()

)