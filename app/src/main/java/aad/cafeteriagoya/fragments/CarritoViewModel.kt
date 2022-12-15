package aad.cafeteriagoya.fragments

import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarritoViewModel: ViewModel() {

    private var productosDBHelper: MiBDOpenHelper ?= null
    private var marcador: MutableLiveData<Double>
    private var contexto: Context? = null

    init {
        marcador = MutableLiveData<Double>()
    }

    fun setMarcador(){
        var aux: Double = marcador.value?: 0.0
        //añadir precio producto
        //marcador.setValue(aux + 1.0)

    }

    fun getMarcador(): MutableLiveData<Double>{
        if(marcador == null){

        }
        return marcador
    }

    fun setDatabase(b: MiBDOpenHelper) {
        this.productosDBHelper = b
    }

    fun getDatabase():MiBDOpenHelper? {
        return productosDBHelper
    }

    fun getContext(): Context{
        return contexto!!
    }

    fun setContext(){

    }
}