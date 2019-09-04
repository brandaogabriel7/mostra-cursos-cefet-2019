package dataaccess.webservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CourseServiceInitializer {

    private var retrofit: Retrofit? = null

    fun getClient(baseURL: String): Retrofit {
        retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit as Retrofit
    }
}
