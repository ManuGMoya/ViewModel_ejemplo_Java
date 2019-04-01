package com.manu.chaingroups.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

// anaotacion Room para indicar que es una entidad (tabla)
@Entity(tableName = "notas")
public class NotaEntity {

    // anotacion para indicar que es de valor unico y ademas autogenerado
    @PrimaryKey(autoGenerate = true)
    public int id;

    // Si queremos cambiar el nombre de la columna
    @ColumnInfo(name = "tituloNota")
    public String titulo;
    public String contenido;
    public boolean favorita;
    public String color;

    public NotaEntity(String titulo, String contenido, boolean favorita, String color) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.favorita = favorita;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isFavorita() {
        return favorita;
    }

    public void setFavorita(boolean favorita) {
        this.favorita = favorita;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
