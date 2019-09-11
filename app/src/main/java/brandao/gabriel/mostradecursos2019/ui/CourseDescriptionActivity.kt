package brandao.gabriel.mostradecursos2019.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import brandao.gabriel.mostradecursos2019.R
import brandao.gabriel.mostradecursos2019.entity.Course
import com.google.gson.Gson
import java.io.FileInputStream


class CourseDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_description)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val gson = Gson()
        var course = gson.fromJson(intent.getStringExtra("COURSE"), Course::class.java)
        actionBar?.title = course.nome
        setupCourseContent(course)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    fun setupCourseContent(course: Course) {
        findViewById<ImageView>(R.id.course_img).setImageBitmap(loadImageBitmap(this, course.nome + ".jpg"))

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

    fun loadImageBitmap(context: Context, imageName: String): Bitmap? {
        var bitmap: Bitmap? = null
        val fiStream: FileInputStream
        try {
            fiStream = context.openFileInput(imageName)
            bitmap = BitmapFactory.decodeStream(fiStream)
            fiStream.close()
        } catch (e: Exception) {
            Log.d("saveImage", "Exception 3, Something went wrong!")
            e.printStackTrace()
        }

        return bitmap
    }
}