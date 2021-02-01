package com.example.checkregister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.checkregister.databinding.FragmentSecondBinding
import com.example.checkregister.model.Registro
import com.example.checkregister.model.RegistroViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: RegistroViewModel by activityViewModels()
    private var idReg: Int = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.etNombreItem.setText(it.nombreProducto)
                binding.etPrecio.setText(it.precio.toString())
                binding.sCantidad.value = it.cantidad!!
                binding.tvResultado.text = it.total.toString()
            }
        })
        var cantidad: Int = 0
        var precio: Int = 0
        var nomItem: String = ""
        binding.sCantidad.setOnValueChangedListener { numberPicker, i, i2 ->
            nomItem = binding.etNombreItem.text.toString()
            precio = binding.etPrecio.text.toString().toInt()
            cantidad = numberPicker.value
            viewModel.cantidadSuma(cantidad, precio).observe(viewLifecycleOwner, Observer {
                it?.let {
                    binding.tvResultado.text = it.toString()
                }
            })
        }
//        var resultado = viewModel.total(precio, cantidad)

        binding.btnGuardar.setOnClickListener {
            saveRegistro(nomItem, precio, cantidad, binding.tvResultado.text.toString().toInt() )
            viewModel.selected(null)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.sCantidad.minValue = 1
        binding.sCantidad.maxValue = 30
        binding.sCantidad.wrapSelectorWheel = true
    }

    fun saveRegistro(nomItem: String, precio: Int, cantidad: Int, total: Int) {
        if (nomItem.isNullOrEmpty()) {
            Toast.makeText(activity, "Debe ingresar todos los datos", Toast.LENGTH_LONG).show()
        } else {
            val newReg = Registro(nombreProducto = nomItem,
                    precio = precio, cantidad = binding.sCantidad.value, total = total)
            viewModel.insertRegistro(newReg)
        }
    }
}