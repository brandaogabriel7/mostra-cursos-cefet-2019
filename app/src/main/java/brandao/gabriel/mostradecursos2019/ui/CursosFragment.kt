package brandao.gabriel.mostradecursos2019.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import brandao.gabriel.mostradecursos2019.R
import brandao.gabriel.mostradecursos2019.entity.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CursosFragment : androidx.fragment.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var courses = getCourseObjects()
        setupCoursesList(courses, inflater)
        return inflater.inflate(R.layout.fragment_cursos, container, false)
    }

    fun getCourseObjects():ArrayList<Course> {
        val gson = Gson()
        var coursesJSON = brandao.gabriel.mostradecursos2019.util.FileHandler.loadFromFile(this.context, TelaInicial.FILE_NAME)
        val type = object: TypeToken<ArrayList<Course>>() {}.type
        var courses = gson.fromJson<ArrayList<Course>>(coursesJSON, type)

        return courses
    }

    fun setupCoursesList(courses: ArrayList<Course>, inflater: LayoutInflater) {
        

    }

}
