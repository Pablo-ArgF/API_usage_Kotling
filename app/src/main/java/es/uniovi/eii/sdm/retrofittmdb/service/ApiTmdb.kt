package es.uniovi.eii.sdm.retrofittmdb.service

import es.uniovi.eii.sdm.retrofittmdb.service.model.MovieListResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiTmdb {

    //reproducimos la peticion de pedir peliculas populares
    //https://api.themoviedb.org/3/movie/popular?api_key=%7bcodigo%7d&language=es-ES&page=1
    //el suspend hace que el metodo no se ejecute bloqueando el hilo
    @GET("movie/{type}")
    suspend fun listMovies(
        @Path("type") type:String,
        @Query("api_key") apiKey:String,
        @Query("language")language:String,
        @Query("page")page:Int
    ): MovieListResult
}

//clase singleton por usar object
object RetrofitClient {
    fun makeClient():ApiTmdb {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiTmdb::class.java) //pasamos la clase sobre la que se construye
    }
}