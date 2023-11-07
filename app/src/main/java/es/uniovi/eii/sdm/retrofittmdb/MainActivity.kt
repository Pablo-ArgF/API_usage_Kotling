package es.uniovi.eii.sdm.retrofittmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import es.uniovi.eii.sdm.retrofittmdb.service.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //we call the apiTmdb
        val client = RetrofitClient.makeClient();


        //usamos una corrutina para la carga de datos
        //la lanzamos en el hilo principal, pero el listmovies es de tipo por lo que
        //se genera un hilo worker que carga los datos
        lifecycleScope.launch {
            val listMovies = client.listMovies(
                "popular",
                "b7de82e563696d71e81d0388dae14e3b",
                "es",
                1)
            println(listMovies)
        }
    }
}