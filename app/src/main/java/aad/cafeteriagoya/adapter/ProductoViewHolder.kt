package aad.cafeteriagoya.adapter

import aad.cafeteriagoya.R
import aad.cafeteriagoya.entidades.Producto
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ProductoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val imagenView = itemView.findViewById<ImageView>(R.id.imgProducto)
        var textViewProducto = itemView.findViewById<TextView>(R.id.textoProducto)
        var textViewPrecio = itemView.findViewById<TextView>(R.id.precioProducto)
        var button = itemView.findViewById<Button>(R.id.btnEliminar)

        fun render(producto: Producto, onClickListener: (Int) -> Unit) {

            textViewProducto.text = producto.nombre
            textViewPrecio.text= producto.precio.toString()+" €"

            if (producto.categoria == "bocadillo") {
                Picasso.with(imagenView.context).load(R.drawable.bocadillo)
                    .fit().centerCrop().into(imagenView)
            }else if (producto.categoria == "cafe") {
                Picasso.with(imagenView.context).load(R.drawable.cafe)
                    .fit().centerCrop().into(imagenView)
            }else if (producto.categoria == "pincho") {
                Picasso.with(imagenView.context).load(R.drawable.pincho)
                    .fit().centerCrop().into(imagenView)
            }else if (producto.categoria == "refresco") {
                Picasso.with(imagenView.context).load(R.drawable.refresco)
                    .fit().centerCrop().into(imagenView)
            }

            button.setOnClickListener{
                onClickListener(producto.id)
            }


        }



}