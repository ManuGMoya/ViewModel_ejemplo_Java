package com.manu.chaingroups.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.manu.chaingroups.db.entity.NotaEntity;

import java.util.List;

// anotacion Room para convertir esta clase en Dao
@Dao
public interface NotaDao {

    // Aqui implementamos todos los metodos para interactuar con la BD

    @Insert
    void insert(NotaEntity nota);

    @Update
    void update(NotaEntity nota);

    @Query("DELETE FROM notas")
    void deleteAll();

    @Query("DELETE FROM notas WHERE id = :idNota")
    void deleteById(int idNota);


    // LiveData convierte los datos en dinamicos para aplicar el patron ViewModel
    // y saber mediante un observador cuando esos datos cambian
    @Query("SELECT * FROM notas ORDER BY tituloNota ASC")
    LiveData <List<NotaEntity>> getAll();

    @Query("SELECT * FROM notas where favorita LIKE 'true'")
    LiveData <List<NotaEntity>> getAllFavoritas();
}
