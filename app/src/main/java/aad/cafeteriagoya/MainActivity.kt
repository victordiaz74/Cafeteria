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
        setContentView(binding.root)


        binding.buttonMain.setOnClickListener{
            guardarHora()
        }
    }


    fun guardarHora() {
        if(binding.tvTiempo.text.equals("")) {
            Toast.makeText(this, "Introduce una hora", Toast.LENGTH_SHORT).show()
        }else{
            intent = Intent(this, MenuActivity::class.java).apply {
                putExtra("hora", binding.tvTiempo.text.toString())
            }

            startActivity(intent)
        }
    }


}



