package aad.cafeteriagoya

import aad.cafeteriagoya.databinding.ActivityFragmentoBinding
import aad.cafeteriagoya.fragments.CarritoFragment
import aad.cafeteriagoya.fragments.CarritoViewModel
import aad.cafeteriagoya.fragments.MenuFragment
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer


class FragmentoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentoBinding
    private lateinit var hora:String
    private lateinit var productosBDHelper: MiBDOpenHelper
    private val carritoViewModel: CarritoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentoBinding.inflate(layoutInflater)
        carritoViewModel.setContext(this)
        setContentView(binding.root)

        productosBDHelper = MiBDOpenHelper(this, null)

        carritoViewModel.setDatabase(productosBDHelper)

        hora = intent.getStringExtra("hora").toString()

        binding.btCarrito.setOnClickListener {
            mostrarFragmentoCarrito()
        }

        binding.btMenu.setOnClickListener {
            mostrarFragmentoMenu()
        }


        val nameObserver = Observer<String>{
            valor -> binding!!.textoPuntosMarcador?.setText(valor.toString())
        }
        carritoViewModel.getMarcador().observe(this, nameObserver)

    }

    private fun mostrarFragmentoCarrito() {
        //se establece la transaccion entre fragments
        val transaction= supportFragmentManager.beginTransaction()
        //se instancia el fragment al que vamos a cambiar
        val fragmentoCarrito = CarritoFragment()

        //se indica el elemento R al que se cambia
        transaction?.replace(R.id.fragmentContainerView,fragmentoCarrito)
        transaction?.addToBackStack(null)
        //se muestra el fragment
        transaction?.commit()
    }

    private fun mostrarFragmentoMenu() {
        //se establece la transaccion entre fragments
        val transaction= supportFragmentManager?.beginTransaction()
        //se instancia el fragment al que vamos a cambiar
        val fragmentoMenu = MenuFragment()

        //se indica el elemento R al que se cambia
        transaction?.replace(R.id.fragmentContainerView,fragmentoMenu)
        transaction?.addToBackStack(null)
        //se muestra el fragment
        transaction?.commit()
    }



    fun siguiente(){
        intent = Intent(this, CarritoActivity::class.java).apply {
            putExtra("hora", hora)
        }
        startActivity(intent)
    }
}