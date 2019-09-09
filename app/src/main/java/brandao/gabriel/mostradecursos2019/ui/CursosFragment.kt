package brandao.gabriel.mostradecursos2019.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import brandao.gabriel.mostradecursos2019.R
import brandao.gabriel.mostradecursos2019.entity.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CursosFragment : androidx.fragment.app.Fragment() {

    var courses : ArrayList<Course>? = null
    var coursesNames = ArrayList<String>()
    var rootView : View? = null
    var listView : ListView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_cursos, container, false)
        listView = rootView?.findViewById(R.id.courses_list) as ListView
        courses = getCourseObjects()
        for(course in courses!!) {
            coursesNames.add(course.nome)
        }
        setupCoursesList()
        return rootView
    }

    fun getCourseObjects():ArrayList<Course> {
        val gson = Gson()
        var coursesJSON = brandao.gabriel.mostradecursos2019.util.FileHandler.loadFromFile(this.context, TelaInicial.FILE_NAME)
        val type = object: TypeToken<ArrayList<Course>>() {}.type
        println(coursesJSON)

        return gson.fromJson(coursesJSON, type)
    }

    fun setupCoursesList() {
        var adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, coursesNames)
        listView?.adapter = adapter
        for(i in 0..listView!!.childCount) {
            (listView?.getChildAt(i) as View).setOnClickListener {  }
        }
    }

}
