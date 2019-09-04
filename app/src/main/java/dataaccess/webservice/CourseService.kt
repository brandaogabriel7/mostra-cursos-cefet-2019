package dataaccess.webservice

import brandao.gabriel.mostradecursos2019.entity.Course
import retrofit2.Call
import retrofit2.http.GET

interface CourseService {
    @GET("courses")
    fun getCourseList() : Call<ArrayList<Course>>
}

