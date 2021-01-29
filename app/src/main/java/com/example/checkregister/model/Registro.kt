package com.example.checkregister.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registro")
data class Registro (@PrimaryKey(autoGenerate = true) @NonNull var id: Int = 0,
                     var nombreProducto: String,
                     var precio: Int,
                     var cantidad: Int,
                     var total: Int)