package dataaccess.webservice

import brandao.gabriel.mostradecursos2019.entity.Course
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://mostra-cursos-api.herokuapp.com"

interface CourseService {

    @GET("courses")
    fun getCourseList() : Call<ArrayList<Course>>

    companion object {

        operator fun invoke() : CourseService {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CourseService::class.java)
        }
    }
}

