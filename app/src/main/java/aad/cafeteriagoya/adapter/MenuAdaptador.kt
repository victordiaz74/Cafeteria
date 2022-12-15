package aad.cafeteriagoya.adapter

import aad.cafeteriagoya.R
import aad.cafeteriagoya.entidades.Producto
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MenuAdaptador(var listaProductos: List<Producto>,
                    private val anadirProducto: (Producto) -> Unit,): RecyclerView.Adapter<ProductoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ProductoViewHolder(itemView = layoutInflater.inflate(R.layout.item_lista, parent, false))
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val item = listaProductos[position]
        holder.render(item,anadirProducto)
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }
}