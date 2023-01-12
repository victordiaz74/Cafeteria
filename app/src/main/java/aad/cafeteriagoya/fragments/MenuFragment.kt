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
    var lista: ArrayList<Producto> = ArrayList(DataProvider.listaProductos)
    private var binding: FragmentMenuBinding ?= null
    private lateinit var adaptador: MenuAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        productosBDHelper = carritoViewModel.getDatabase()!!
        val fragmentoBinding = FragmentMenuBinding.inflate(inflater, container, false)
        binding = fragmentoBinding

        rellenarSpinner()
        initRecyclerView()

        binding?.btFiltrar?.setOnClickListener{
            filtrar()
        }

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
            adaptador.listaProductos = ArrayList(lista)
        } else {
            adaptador.listaProductos = ArrayList(DataProvider.listaProductos)
        }
        adaptador.notifyDataSetChanged()
    }

    fun rellenarSpinner() {

        val categorias = arrayOf("Todas", "pincho", "cafe", "refresco", "bocadillo")

        var adaptador: ArrayAdapter<String> = ArrayAdapter(carritoViewModel.getContext(), android.R.layout.simple_spinner_item, categorias)
        binding?.spinner?.adapter = adaptador

    }

    fun initRecyclerView() {

        val recyclerView = binding?.recyclerView
        recyclerView?.layoutManager = LinearLayoutManager(carritoViewModel.getContext())

        adaptador = MenuAdaptador(
            onClickListener = { pos -> dameID(pos) }
        )
        adaptador.MenuAdaptador(carritoViewModel.getContext(), lista)
        /*adaptador = MenuAdaptador(
            listaProductos = DataProvider.listaProductos,
            anadirProducto = { producto -> anadirProducto(producto) })*/

        recyclerView?.adapter = adaptador

    }

    fun dameID(pos: Int){
        carritoViewModel.carrito.add(DataProvider.listaProductos.get(pos-1))
        carritoViewModel.setMarcador()
    }

}