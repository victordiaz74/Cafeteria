package aad.cafeteriagoya.fragments

import aad.cafeteriagoya.DataProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aad.cafeteriagoya.adapter.MenuAdaptador
import aad.cafeteriagoya.databinding.FragmentMenuBinding
import aad.cafeteriagoya.entidades.Producto
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

class MenuFragment : Fragment() {

    private lateinit var productosBDHelper: MiBDOpenHelper
    private val carritoViewModel: CarritoViewModel by activityViewModels()
    private var binding: FragmentMenuBinding ?= null
    private lateinit var adaptador: MenuAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rellenarSpinner()
        initRecyclerView()

        binding?.btFiltrar?.setOnClickListener{
            filtrar()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        productosBDHelper = carritoViewModel.getDatabase()!!
        val fragmentoBinding = FragmentMenuBinding.inflate(inflater, container, false)
        binding = fragmentoBinding

        return fragmentoBinding.root
    }



    fun filtrar() {

        val categoria = binding?.spinner?.selectedItem.toString()

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

        var adaptador: ArrayAdapter<String> = ArrayAdapter(carritoViewModel.getContext(), android.R.layout.simple_spinner_item, categorias)
        binding?.spinner?.adapter = adaptador

    }

    fun initRecyclerView() {

        adaptador = MenuAdaptador(
            listaProductos = DataProvider.listaProductos,
            anadirProducto = { producto -> anadirProducto(producto) })

        binding?.recyclerView?.adapter = adaptador
        binding?.recyclerView?.layoutManager = LinearLayoutManager(carritoViewModel.getContext())

    }

    fun anadirProducto(p: Producto) {
        productosBDHelper.anadirProducto(p)
        //Toast.makeText(this, p.nombre + "ha sido añadido", Toast.LENGTH_SHORT).show();
    }

}