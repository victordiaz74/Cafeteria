package aad.cafeteriagoya

import aad.cafeteriagoya.databinding.ActivityCarritoBinding
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CarritoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarritoBinding
    private lateinit var hora:String
    private lateinit var productosBDHelper: MiBDOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hora = intent.getStringExtra("hora").toString()
        productosBDHelper = MiBDOpenHelper(this, null)
        contenidoCarrito()

        binding.btnPagar.setOnClickListener{
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun contenidoCarrito(){
        var bd = MiBDOpenHelper(this, null)
        var cursor = bd.obtenerCarrito()

        cursor.moveToFirst()
        var texto = ""

        while (!cursor.isAfterLast){
            texto = texto + "\n" + cursor.getString(2) + cursor.getString(3) + "€"
            cursor.moveToNext()
        }
        binding.textView2.text =texto

        binding.txtHora.text = hora
    }

}