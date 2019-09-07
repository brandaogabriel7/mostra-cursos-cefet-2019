package brandao.gabriel.mostradecursos2019.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.widget.Toast
import brandao.gabriel.mostradecursos2019.R
import brandao.gabriel.mostradecursos2019.entity.Course
import com.google.gson.Gson
import com.google.gson.JsonObject
import dataaccess.webservice.CourseService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader



class TelaInicial : AppCompatActivity() {

    private val FILE_NAME = "courses.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        setupBottomNavigationView()
        loadCourses()
    }

    fun loadCourses() {
        CourseService().getCourseList().enqueue(object : Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                Toast.makeText(applicationContext, "aaaaa", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun setupBottomNavigationView() {

        val bottomNav:BottomNavigationView = findViewById(R.id.bottom_navigation)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ApresentacaoFragment()).commit()

        bottomNav.setOnNavigationItemSelectedListener {

            var selectedFragment:Fragment

            when (it.itemId) {
                R.id.nav_apresentacao -> {
                    selectedFragment = ApresentacaoFragment()
                }
                R.id.nav_programacao -> {
                    selectedFragment = ProgramacaoFragment()
                }
                R.id.nav_cursos -> {
                    selectedFragment = CursosFragment()
                }
                else -> selectedFragment = ApresentacaoFragment()
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
            true
        }
    }

        fun saveToJSONFile(content: String) {
        var fos: FileOutputStream? = openFileOutput(FILE_NAME, MODE_PRIVATE)

        fos?.write(content.toByteArray())
        Toast.makeText(this, "escreveu no arquivo", Toast.LENGTH_LONG).show()
        fos?.close()
    }

    fun loadFromJSONFile() : JsonObject {
        val gson = Gson()
        val fis: FileInputStream? = openFileInput(FILE_NAME)

        val isr = InputStreamReader(fis)
        val br = BufferedReader(isr)

        fis?.close()

        val course = gson.fromJson(br, JsonObject::class.java)

        return course
    }

}
