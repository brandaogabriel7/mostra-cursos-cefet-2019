package brandao.gabriel.mostradecursos2019.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Course (
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("idtTipo")
    @Expose
    val idtTipo: Char,
    @SerializedName("caminhoImagem")
    @Expose
    val caminhoImagem: String,
    @SerializedName("nome")
    @Expose
    val nome: String,
    @SerializedName("apresentacao")
    @Expose
    val apresentacao: String,
    @SerializedName("objetivo")
    @Expose
    val objetivo: String,
    @SerializedName("perfilProfissional")
    @Expose
    val perfilProfissional: String,
    @SerializedName("campoAtuacao")
    @Expose
    val campoAtuacao: String,
    @SerializedName("formasOferta")
    @Expose
    val formasOferta: String,
    @SerializedName("campi")
    @Expose
    val campi:String = ""
)