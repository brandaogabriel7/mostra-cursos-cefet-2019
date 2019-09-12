package brandao.gabriel.mostradecursos2019.util

import android.content.Context
import java.io.*

class FileHandler {

    companion object {
        fun saveToFile(context: Context?, filename: String, data: String) : Boolean {
            var fos: FileOutputStream? = null
            fos = context!!.openFileOutput(filename, Context.MODE_PRIVATE)
            fos.write(data.toByteArray())
            fos?.close()
            return true
        }

        fun loadFromFile(context: Context?, filename: String): String {
                var fis: FileInputStream?
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