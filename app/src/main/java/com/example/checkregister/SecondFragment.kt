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
        binding.btnGuardar.setOnClickListener {
            saveRegistro()
            viewModel.selected(null)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.sCantidad.minValue = 1
        binding.sCantidad.maxValue = 30
        binding.sCantidad.wrapSelectorWheel = true
    }

    fun saveRegistro() {
        val nomItem: String = binding.etNombreItem.text.toString()
        val precio: Int = binding.etPrecio.text.toString().toInt()
        binding.sCantidad.setOnValueChangedListener { numberPicker, i, i2 ->
            val cantidad = i2
        }
        val total = precio * binding.sCantidad.value
        if (nomItem.isNullOrEmpty() || precio == 0) {
            Toast.makeText(activity, "Debe ingresar todos los datos", Toast.LENGTH_LONG).show()
        } else {
            if (idReg == 0) {
                val newReg = Registro(nombreProducto = nomItem,
                        precio = precio, cantidad = binding.sCantidad.value, total = total )
                viewModel.insertRegistro(newReg)
            } else {
                val newReg = Registro(id = idReg, nombreProducto = nomItem,
                        precio = precio, cantidad = binding.sCantidad.value, total = total )
                viewModel.updateRegistro(newReg)
            }
        }
    }
}