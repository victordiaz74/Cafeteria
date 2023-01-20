package aad.cafeteriagoya

import aad.cafeteriagoya.databinding.ActivityVerPedidoBinding
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class VerPedidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerPedidoBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityVerPedidoBinding.inflate(layoutInflater)

        var id = intent.getIntExtra("id", -1)

        mostrarPedido(id)

        setContentView(binding.root)
    }

    fun mostrarPedido(id: Int){
        var bd = MiBDOpenHelper(this, null)
        var cursor = bd.obtenerPedido(id)

        cursor.moveToFirst()

        var contenidoPos = cursor.getString(1)

        var contenido = contenidoPos.split("-")
        var cadena = ""

        for(c in contenido){
            cadena = cadena + DataProvider.listaProductos.get(c.toInt()-1)
        }

        binding.idPedido.text = id.toString()
        binding.contenidoPedido.text = cadena
    }

}