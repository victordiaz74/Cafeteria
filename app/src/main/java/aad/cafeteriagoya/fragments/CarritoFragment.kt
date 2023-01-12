package aad.cafeteriagoya.fragments

import aad.cafeteriagoya.MainActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aad.cafeteriagoya.R
import aad.cafeteriagoya.adapter.CarritoAdapter
import aad.cafeteriagoya.databinding.FragmentCarritoBinding
import aad.cafeteriagoya.databinding.FragmentMenuBinding
import aad.cafeteriagoya.entidades.Producto
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager


class CarritoFragment : Fragment() {

    private lateinit var productoBDHelper: MiBDOpenHelper
    private val carritoViewModel: CarritoViewModel by activityViewModels()
    private var binding: FragmentCarritoBinding?= null
    lateinit var adapterCarrito: CarritoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        productoBDHelper = carritoViewModel.getDatabase()!!
        val fragmentoBinding = FragmentCarritoBinding.inflate(inflater, container, false)
        binding = fragmentoBinding
        cargar()
        binding?.btInicio?.setOnClickListener(){
            pagar()
        }

        return fragmentoBinding.root
    }

    fun cargar() {
        val recyclerView = binding?.rvCarrito
        recyclerView?.layoutManager = LinearLayoutManager(carritoViewModel.getContext())

        adapterCarrito = CarritoAdapter(
            onClickListener = { pos -> dameID(pos) }
        )

        adapterCarrito.CarritoAdapter(carritoViewModel.getContext(),  carritoViewModel.carrito)

        recyclerView?.adapter = adapterCarrito
    }

    fun dameID(pos: Producto) {
        carritoViewModel.carrito.remove(pos)

        carritoViewModel.setMarcador()

        cargar()
    }

    fun pagar(){
        var productoSeleccionado = ""

        for(p in carritoViewModel.carrito) {
            productoSeleccionado = productoSeleccionado + "-" + p.id
        }

        productoSeleccionado = productoSeleccionado.substring(productoSeleccionado.length-1)
        var base = carritoViewModel.getDatabase()

        if (carritoViewModel.carrito.size > 0){
            base?.aniadirProducto(productoSeleccionado)

            Toast.makeText(carritoViewModel.getContext(), "El pago se ha procesado", Toast.LENGTH_SHORT).show()
        }

        irInicio()
    }

    fun irInicio(){
        var intent = Intent(carritoViewModel.getContext(), MainActivity::class.java)
        startActivity(intent)
    }
}