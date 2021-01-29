package com.example.checkregister.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.checkregister.databinding.RegistroItemBinding

class RegistroAdapter: RecyclerView.Adapter<RegistroAdapter.RegistroViewHolder>() {
    private var mListRegistro = listOf<Registro>()
    private val selectedRegistro = MutableLiveData<Registro>()

    fun selectedItem(): LiveData<Registro> = selectedRegistro

    fun update(listRegistro: List<Registro>) {
        mListRegistro = listRegistro
        notifyDataSetChanged()
    }

    inner class RegistroViewHolder(private val binding: RegistroItemBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind(registro: Registro) {
            binding.tvNombreProducto.text = registro.nombreProducto
            binding.tvCantidad.text = registro.cantidad.toString()
            binding.tvPrecio.text = registro.precio.toString()
            binding.tvTotal.text = registro.total.toString()
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedRegistro.value = mListRegistro[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroViewHolder {
        return RegistroViewHolder(RegistroItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = mListRegistro.size

    override fun onBindViewHolder(holder: RegistroAdapter.RegistroViewHolder, position: Int) {
        val registro = mListRegistro[position]
        holder.bind(registro)
    }
}