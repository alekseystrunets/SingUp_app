package com.example.singup_app.data.api.server

import android.os.Parcelable
import com.example.singup_app.presentation.ui.activity.Objects
import com.example.singup_app.presentation.ui.activity.SomeObject
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerInt {
        @GET("/public/collection/v1/objects")
        suspend fun getAllObjects(): Objects

        @GET("/public/collection/v1/objects/{objectId}")
        suspend fun getSomeObject(@Path(value = "objectId") objectId: Int): SomeObject
}
@kotlinx.parcelize.Parcelize
data class Objects(
    @SerializedName("total") val total: Int,
    @SerializedName("objectIds") val objectIds:ArrayList<Int>
): Parcelable

@kotlinx.parcelize.Parcelize
data class SomeObject(
    @SerializedName("objectID") val objectId : Int,
    @SerializedName("primaryImage") val primaryImage : String
): Parcelable