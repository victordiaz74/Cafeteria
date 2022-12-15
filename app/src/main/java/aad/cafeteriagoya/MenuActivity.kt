package aad.cafeteriagoya

import aad.cafeteriagoya.DataProvider.Companion.listaProductos
import aad.cafeteriagoya.adapter.MenuAdaptador
import aad.cafeteriagoya.databinding.ActivityMenuBinding
import aad.cafeteriagoya.entidades.Producto
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var hora:String
    private lateinit var productosBDHelper: MiBDOpenHelper
    private lateinit var adaptador: MenuAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)

        setContentView(binding.root)

        productosBDHelper = MiBDOpenHelper(this, null)

        rellenarSpinner()
        initRecyclerView()

        hora = intent.getStringExtra("hora").toString()

        binding.btCarrito.setOnClickListener {
            siguiente()
        }

        binding.btFiltrar.setOnClickListener{
            filtrar()
        }

    }


    fun filtrar() {

        val categoria = binding.spinner.selectedItem.toString()

        val lista = mutableListOf<Producto>()

        if (categoria != "Todas") {
            for (p in DataProvider.listaProductos) {
                if (p.categoria == categoria) {
                    lista.add(p)
                }
            }
            adaptador.listaProductos = lista
        } else {
            adaptador.listaProductos = DataProvider.listaProductos
        }
        adaptador.notifyDataSetChanged()
    }

    fun rellenarSpinner() {

        val categorias = arrayOf("Todas", "pincho", "cafe", "refresco", "bocadillo")

        var adaptador: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        binding.spinner.adapter = adaptador

    }

    fun initRecyclerView() {

        adaptador = MenuAdaptador(
            listaProductos = DataProvider.listaProductos,
            anadirProducto = { producto -> anadirProducto(producto) })

        binding.recyclerView.adapter = adaptador
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun anadirProducto(p: Producto) {
        productosBDHelper.anadirProducto(p)
        Toast.makeText(this, p.nombre + "ha sido añadido", Toast.LENGTH_SHORT).show();
    }

    fun siguiente(){
        intent = Intent(this, CarritoActivity::class.java).apply {
            putExtra("hora", hora)
        }
        startActivity(intent)
    }
}