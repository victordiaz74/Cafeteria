package aad.cafeteriagoya.adapter

import aad.cafeteriagoya.DataProvider
import aad.cafeteriagoya.R
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PedidosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var txtProducto = itemView.findViewById<TextView>(R.id.numeroPedido)
    var txtPrecio = itemView.findViewById<TextView>(R.id.precioPedido)
    var btnVerPedido = itemView.findViewById<Button>(R.id.btnVerPedido)

    fun render(id: Int, pedido: String, onClickListener: (Int) -> Unit){
        txtProducto.text = id.toString()
        txtPrecio.text = damePrecio(pedido)

        btnVerPedido.setOnClickListener{
            onClickListener(id)
        }
    }

    private fun damePrecio(pedido: String):String{
        var productos = pedido.split("-")
        var dinero = 0.0

        for(p in productos){
            var l = p
            dinero += DataProvider.listaProductos[p.toInt()-1].precio
        }

        return dinero.toString()
    }
}