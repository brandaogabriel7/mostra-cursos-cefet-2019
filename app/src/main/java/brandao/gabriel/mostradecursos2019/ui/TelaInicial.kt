package brandao.gabriel.mostradecursos2019.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import brandao.gabriel.mostradecursos2019.R
import brandao.gabriel.mostradecursos2019.entity.Course
import brandao.gabriel.mostradecursos2019.util.FileHandler
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import dataaccess.webservice.CourseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TelaInicial : AppCompatActivity() {

    companion object {
        val FILE_NAME = "courses.json"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        setupBottomNavigationView()
        loadCourses()
    }

    fun loadCourses() {
        CourseService().getCourseList().enqueue(object : Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                val gson = Gson()
                Toast.makeText(applicationContext, FileHandler.saveToFile(this@TelaInicial, FILE_NAME, gson.toJson(response.body())).toString(), Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                Toast.makeText(applicationContext, FileHandler.loadFromFile(this@TelaInicial, FILE_NAME), Toast.LENGTH_LONG).show()
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

}
