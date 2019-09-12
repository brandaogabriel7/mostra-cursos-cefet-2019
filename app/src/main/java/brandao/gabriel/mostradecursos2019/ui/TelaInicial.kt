package brandao.gabriel.mostradecursos2019.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import brandao.gabriel.mostradecursos2019.R
import brandao.gabriel.mostradecursos2019.entity.Course
import brandao.gabriel.mostradecursos2019.util.FileHandler
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import dataaccess.webservice.CourseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.FileOutputStream
import java.net.URL


class TelaInicial : AppCompatActivity() {

    companion object {
        val FILE_NAME = "courses.json"
        var isListReady = false
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
                isListReady = FileHandler.saveToFile(this@TelaInicial, FILE_NAME, gson.toJson(response.body()))
                for(course in response.body()!!) {
                    val downloadImage = DownloadImage()
                    downloadImage.imageName = course.nome + ".jpg"
                    downloadImage.execute(course.caminhoImagem)
                }
                println("terminou")
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                if(FileHandler.loadFromFile(this@TelaInicial, FILE_NAME) == null) {
                    Toast.makeText(this@TelaInicial, "Você ainda não possui os dados dos cursos. Conecte-se à Internet e reinicie o app para carregá-los.", Toast.LENGTH_LONG).show()
                }
                else isListReady = true
            }

        })
    }

    fun setupBottomNavigationView() {

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ApresentacaoFragment(), "CURRENT").commit()

        bottomNav.setOnNavigationItemSelectedListener {

            var selectedFragment : Fragment? = null

            when (it.itemId) {
                R.id.nav_apresentacao -> {
                    selectedFragment = ApresentacaoFragment()
                }
                R.id.nav_programacao -> {
                    selectedFragment = ProgramacaoFragment()
                }
                R.id.nav_cursos -> {
                    if(isListReady) selectedFragment = CursosFragment()
                    else {
                        selectedFragment = supportFragmentManager.findFragmentByTag("CURRENT") as Fragment
                    }
                }
                R.id.nav_sobre -> {
                    selectedFragment = SobreFragment()
                }
                else -> selectedFragment = ApresentacaoFragment()
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment, "CURRENT").commit()
            true
        }
    }

    private inner class DownloadImage : AsyncTask<String, Void, Bitmap>() {
        private val TAG = "DownloadImage"
        var imageName : String? = null
        private fun downloadImageBitmap(sUrl: String): Bitmap? {
            var bitmap: Bitmap? = null
            try {
                val inputStream = URL(sUrl).openStream()   // Download Image from URL
                bitmap = BitmapFactory.decodeStream(inputStream)       // Decode Bitmap
                inputStream.close()
            } catch (e: Exception) {
                Log.d(TAG, "Exception 1, Something went wrong!")
                e.printStackTrace()
            }

            return bitmap
        }

        fun saveImage(context: Context, b: Bitmap, imageName: String) {
            val foStream: FileOutputStream
            try {
                foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE)
                b.compress(Bitmap.CompressFormat.PNG, 100, foStream)
                foStream.close()
            } catch (e: Exception) {
                Log.d("saveImage", "Exception 2, Something went wrong!")
                e.printStackTrace()
            }

        }

        override fun doInBackground(vararg params: String): Bitmap? {
            return downloadImageBitmap(params[0])
        }

        override fun onPostExecute(result: Bitmap) {
            saveImage(applicationContext, result, imageName.toString())
        }
    }

}
