package brandao.gabriel.mostradecursos2019.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import brandao.gabriel.mostradecursos2019.R
import brandao.gabriel.mostradecursos2019.entity.Course
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.JsonObject
import dataaccess.webservice.CourseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.lang.StringBuilder


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
                Toast.makeText(applicationContext, saveToFile(response.body().toString()).toString(), Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                Toast.makeText(applicationContext, loadFromFile(), Toast.LENGTH_LONG).show()
            }

        })
    }

    fun setupBottomNavigationView() {

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ApresentacaoFragment()).commit()

        bottomNav.setOnNavigationItemSelectedListener {

            var selectedFragment: androidx.fragment.app.Fragment

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

    fun saveToFile(data: String) {
        var fos: FileOutputStream? = null
        fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
        fos.write(data.toByteArray())
        Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show()
        fos?.close()
    }

    fun loadFromFile(): String {
        var fis: FileInputStream? = null
        fis = openFileInput(FILE_NAME)
        val isr = InputStreamReader(fis)
        val br = BufferedReader(isr)
        val sb = StringBuilder()
        var text = br.readLine()
        while(text != null) {
            sb.append(text).append("\n")
            text = br.readLine()
        }

        fis?.close()

        return sb.toString()


    }

}
