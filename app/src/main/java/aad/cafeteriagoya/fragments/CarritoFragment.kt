package aad.cafeteriagoya.fragments

import aad.cafeteriagoya.MainActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aad.cafeteriagoya.R
import aad.cafeteriagoya.databinding.FragmentCarritoBinding
import aad.cafeteriagoya.databinding.FragmentMenuBinding
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.content.Intent
import androidx.fragment.app.activityViewModels


class CarritoFragment : Fragment() {

    private lateinit var productoBDHelper: MiBDOpenHelper
    private val carritoViewModel: CarritoViewModel by activityViewModels()
    private var binding: FragmentCarritoBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        productoBDHelper = carritoViewModel.getDatabase()!!
        val fragmentoBinding = FragmentCarritoBinding.inflate(inflater, container, false)
        binding = fragmentoBinding

        //cargar()


        return fragmentoBinding.root
    }


    /*fun irInicio(){
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }*/
}