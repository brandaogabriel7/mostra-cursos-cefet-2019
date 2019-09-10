package brandao.gabriel.mostradecursos2019.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import brandao.gabriel.mostradecursos2019.R
import brandao.gabriel.mostradecursos2019.entity.Course
import com.google.gson.Gson
import org.w3c.dom.Text

class CourseDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_description)
        val gson = Gson()
        var course = gson.fromJson(intent.getStringExtra("COURSE"), Course::class.java)
        setupCourseContent(course)
    }

    fun setupCourseContent(course: Course) {
        findViewById<TextView>(R.id.nom_curso).setText(course?.nome)

        for(linha in course!!.apresentacao) {
            findViewById<TextView>(R.id.apresentacao_p).append(linha + "\n")
        }

        for(linha in course!!.objetivo) {
            findViewById<TextView>(R.id.objetivo_p).append(linha + "\n")
        }

        for(linha in course!!.perfilProfissional) {
            findViewById<TextView>(R.id.perfil_profissional_p).append(linha + "\n")
        }

        for(linha in course!!.campoAtuacao) {
            findViewById<TextView>(R.id.campo_atuacao_p).append(linha + "\n")
        }

        for(linha in course!!.formasOferta) {
            findViewById<TextView>(R.id.formas_oferta_p).append(linha + "\n")
        }

        if(course.campi!!.size > 1) findViewById<TextView>(R.id.campi_t).setText("Campi")

        for(linha in course!!.campi) {
            findViewById<TextView>(R.id.campi_p).append(linha + "\n")
        }

    }
}