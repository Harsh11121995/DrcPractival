package com.example.drcpract.api



import com.example.drcpract.BuildConfig
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val DEFAULT_TIMEOUT = 60000L
    private var retrofit: Retrofit? = null
    private val retrofitGet: Retrofit? =
        null // create separate object for GET, to allow retry on connection failure//print request and response on log
    // connection time between device to server
    // response time between server to device
    // send data time between device to server after connection established
    /**
     * get retrofit object for POST specific API, retry on connection failure is restricted
     *
     * @return retrofit object
     */
    val client: Retrofit?
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            if (retrofit == null) {
                val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                    .retryOnConnectionFailure(false)
                    .addInterceptor(loggingInterceptor) //print request and response on log
                    .connectTimeout(
                        DEFAULT_TIMEOUT,
                        TimeUnit.MILLISECONDS
                    ) // connection time between device to server
                    .readTimeout(
                        DEFAULT_TIMEOUT,
                        TimeUnit.MILLISECONDS
                    ) // response time between server to device
                    .writeTimeout(
                        DEFAULT_TIMEOUT,
                        TimeUnit.MILLISECONDS
                    ) // send data time between device to server after connection established
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/place/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}