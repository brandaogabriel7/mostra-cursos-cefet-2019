package brandao.gabriel.mostradecursos2019.util

import android.content.Context
import android.widget.Toast
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

class FileHandler {

    companion object {
        fun saveToFile(context: Context?, filename: String, data: String) {
            var fos: FileOutputStream? = null
            fos = context!!.openFileOutput(filename, Context.MODE_PRIVATE)
            fos.write(data.toByteArray())
            Toast.makeText(context, "Saved to " + context!!.getFilesDir() + "/" + filename, Toast.LENGTH_LONG).show()
            fos?.close()
        }

        fun loadFromFile(context: Context?, filename: String): String {
            var fis: FileInputStream? = null
            fis = context!!.openFileInput(filename)
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

}