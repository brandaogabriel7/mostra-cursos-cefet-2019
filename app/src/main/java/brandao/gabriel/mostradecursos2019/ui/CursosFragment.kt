package brandao.gabriel.mostradecursos2019.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import brandao.gabriel.mostradecursos2019.R
import brandao.gabriel.mostradecursos2019.entity.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import brandao.gabriel.mostradecursos2019.util.FileHandler

class CursosFragment : androidx.fragment.app.Fragment() {

    var courses : ArrayList<Course>? = null
    var coursesNames = ArrayList<String>()
    var rootView : View? = null
    var listView : ListView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_cursos, container, false)
        listView = rootView?.findViewById(R.id.courses_list) as ListView
        courses = getCourseObjects()
        if(courses != null) {
            for(course in courses!!) {
                coursesNames.add(course.nome)
            }
            setupCoursesList()
        }
        return rootView
    }

    fun getCourseObjects(): ArrayList<Course>? {
        val gson = Gson()
        var coursesJSON = FileHandler.loadFromFile(this.context, TelaInicial.FILE_NAME)
        if(coursesJSON != null) {
            val type = object: TypeToken<ArrayList<Course>>() {}.type
            println(coursesJSON)

            return gson.fromJson(coursesJSON, type)
        }
        else return null
    }

    fun setupCoursesList() {
        var adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, coursesNames)
        listView?.adapter = adapter
        var intent = Intent(context, CourseDescriptionActivity::class.java)
        listView?.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            run {
                val gson = Gson()
                var course = gson.toJson(courses?.get(i))
                intent.putExtra("COURSE", course)
                startActivity(intent)
            }
        }
    }

}
