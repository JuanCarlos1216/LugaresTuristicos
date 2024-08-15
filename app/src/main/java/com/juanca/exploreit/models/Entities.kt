package com.juanca.exploreit.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Places")
data class Places(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="Id")
    val Id: Int,
    @ColumnInfo(name ="titulo")
    val titulo: String,
    @ColumnInfo(name ="descripcionCorta")
    val descripcionCorta: String,
    @ColumnInfo(name ="descripcionDetallada")
    val descripcionDetallada: String,
    @ColumnInfo(name ="direccion")
    val direccion: String,
    @ColumnInfo(name ="Lat")
    val Lat: Double,
    @ColumnInfo(name ="Lon")
    val Lon: Double,
    @ColumnInfo(name ="cabeceraImagen")
    val cabeceraImagen: String,
): Serializable