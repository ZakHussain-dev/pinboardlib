package com.zhussain.pinboard.network

import android.text.util.Rfc822Token
import android.text.util.Rfc822Tokenizer
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zhussain.pinboard.model.UsersProperty
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.*

private const val BASE_URL = "https://pastebin.com/raw/"


/**
 * Build the Moshi object that Retrofit will be using, to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(Date::class.java,Rfc3339DateJsonAdapter().nullSafe())
    .add(KotlinJsonAdapterFactory())
    .build()



/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface UsersApiService {
    /**
     * Returns a Retrofit callback that delivers a String
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("wgkJgazE")
    fun getProperties():
            Deferred<List<UsersProperty>> // coroutine ...

}


/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */

object UsersApi {

    val retrofitService: UsersApiService by lazy{
        retrofit.create(UsersApiService::class.java)
    }

}