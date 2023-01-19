package aad.cafeteriagoya

import aad.cafeteriagoya.adapter.PedidosAdapter
import aad.cafeteriagoya.databinding.ActivityMainBinding
import aad.cafeteriagoya.databinding.ActivityPedidosBinding
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class PedidosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPedidosBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityPedidosBinding.inflate(layoutInflater)

        initRecyclerViewPedidos()

        binding.btnCasa.setOnClickListener{
            volverCasa()
        }

        setContentView(binding.root)

    }

    fun volverCasa() {
        intent = Intent(this, MainActivity::class.java).apply {
            startActivity(intent)
        }
    }

    fun initRecyclerViewPedidos() {
        var rv = binding.recyclerViewPedidos
        rv.layoutManager = LinearLayoutManager(this)

        val bd = MiBDOpenHelper(this, null)

        var listaPedidos = bd.obtenerCarrito()

        var adapterPedidos = PedidosAdapter(
            onClickListener = { pos -> dameID(pos) }
        )
        adapterPedidos.PedidosAdapter(this, listaPedidos)
        rv.adapter = adapterPedidos
    }

    fun dameID(pos: Int){
        intent = Intent(this, VerPedidoActivity::class.java).apply {
            putExtra("id", pos)
        }

        startActivity(intent)
    }
}