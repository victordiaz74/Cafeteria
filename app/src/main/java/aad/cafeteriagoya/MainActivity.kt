package aad.cafeteriagoya

import aad.cafeteriagoya.databinding.ActivityMainBinding
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.buttonMain.setOnClickListener{
            guardarHora()
        }

        binding.buttonMain2.setOnClickListener{
            misPedidos()
        }
        setContentView(binding.root)
    }


    fun guardarHora() {
        var hora = binding.tvTiempo.text.toString()

        if(hora.equals("")) {
            Toast.makeText(this, "Introduce una hora", Toast.LENGTH_SHORT).show()
        }else {
            intent = Intent(this, FragmentoActivity::class.java).apply {
                putExtra("hora", binding.tvTiempo.text.toString())
            }
            startActivity(intent)
        }
    }

    fun misPedidos(){
        intent = Intent(this, PedidosActivity::class.java)

        startActivity(intent)
    }

}



