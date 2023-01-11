package aad.cafeteriagoya.fragments

import aad.cafeteriagoya.entidades.Producto
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarritoViewModel: ViewModel() {

    private var productosDBHelper: MiBDOpenHelper ?= null
    private var marcador: MutableLiveData<String> = MutableLiveData<String>()
    lateinit var contexto: Context
    var precioTotal: Double = 0.0
    var carrito = ArrayList<Producto>()

    init{
        marcador.value = "$precioTotal€"
    }

    fun setMarcador(){
        precioTotal = 0.0

        for(p in carrito)
        {
            precioTotal += p.precio
        }

        marcador.value = "Total: $precioTotal€"

    }

    fun getMarcador(): MutableLiveData<String>{
        return marcador
    }

    fun setDatabase(b: MiBDOpenHelper) {
        this.productosDBHelper = b
    }

    fun getDatabase():MiBDOpenHelper? {
        return productosDBHelper
    }

    fun getContext(): Context{
        return contexto
    }

    fun setContext(contexto: Context){
        this.contexto = contexto
    }
}