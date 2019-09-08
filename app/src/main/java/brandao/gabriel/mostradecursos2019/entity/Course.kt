package brandao.gabriel.mostradecursos2019.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Course (
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("idtTipo")
    @Expose
    val idtTipo: String,
    @SerializedName("caminhoImagem")
    @Expose
    val caminhoImagem: String,
    @SerializedName("nome")
    @Expose
    val nome: String,
    @SerializedName("apresentacao")
    @Expose
    val apresentacao: Array<String>,
    @SerializedName("objetivo")
    @Expose
    val objetivo: Array<String>,
    @SerializedName("perfilProfissional")
    @Expose
    val perfilProfissional: Array<String>,
    @SerializedName("campoAtuacao")
    @Expose
    val campoAtuacao: Array<String>,
    @SerializedName("formasOferta")
    @Expose
    val formasOferta: Array<String>,
    @SerializedName("campi")
    @Expose
    val campi: Array<String>
)