package dataaccess.webservice

object APIUtils {

    val BASE_URL = "https://mostra-cursos-api.herokuapp.com/courses"

    val courseService: CourseService
        get() = CourseServiceInitializer.getClient(BASE_URL).create(CourseService::class.java)

}
