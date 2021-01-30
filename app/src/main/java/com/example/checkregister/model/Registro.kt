package com.example.checkregister.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "registro_table")
data class Registro (@PrimaryKey(autoGenerate = true) @NonNull var id: Int? = 0,
                     var nombreProducto: String?,
                     var precio: Int?,
                     var cantidad: Int?,
                     var total: Int?){
    constructor(): this(0,"",0,0,0)
}