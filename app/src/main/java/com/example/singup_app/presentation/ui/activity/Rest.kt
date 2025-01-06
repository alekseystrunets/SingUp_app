package com.example.singup_app.presentation.ui.activity

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.singup_app.R
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class Rest : AppCompatActivity() {

    val retrofit = Retrofit.Builder().baseUrl("https://collectionapi.metmuseum.org").addConverterFactory(GsonConverterFactory.create()).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest2)
        val buttonClick =   findViewById<AppCompatButton>(R.id.buttonClick)
        val image = findViewById<AppCompatImageView>(R.id.image)
        val api = retrofit.create(Server::class.java)


        buttonClick.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO){
                    val currentBody = api.getSomeObject(45)

                    withContext(Dispatchers.Main){
                        Glide.with(this@Rest).load(currentBody.primaryImage).into(image)
                    }
                }
            }

//            lifecycleScope.launch(Dispatchers.IO) {
//                val responseBody = api.getAllObjects()
//                Log.d("Response","$responseBody")
//            }
        }
    }
}
 interface Server {
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
):Parcelable
