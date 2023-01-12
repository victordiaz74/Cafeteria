package aad.cafeteriagoya.adapter

import aad.cafeteriagoya.R
import aad.cafeteriagoya.entidades.Producto
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MenuAdaptador(private val onClickListener: (Int) -> Unit): RecyclerView.Adapter<ProductoViewHolder>(){

    private lateinit var context: Context
    lateinit var listaProductos: ArrayList<Producto>

    fun MenuAdaptador(context: Context, listaProductos: ArrayList<Producto>) {
        this.context = context
        this.listaProductos = listaProductos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ProductoViewHolder(itemView = layoutInflater.inflate(R.layout.item_lista, parent, false))
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        holder.render(listaProductos[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }
}