package com.manu.chaingroups.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.manu.chaingroups.db.dao.NotaDao;
import com.manu.chaingroups.db.entity.NotaEntity;

// Definimos las entidades
// La version se utiliza para la migraciones
@Database(entities = {NotaEntity.class},version = 1)
public abstract class NotaRoomDatabase extends RoomDatabase {

    // declaramos un objeto de tipo Dao
    public abstract NotaDao notaDao();

    // Para guardar la instancia de la base de datos
    // Segun la documentacion tiene que ser static y volatile
    // Es un objeto del tipo de esta misma clase
    private static volatile NotaRoomDatabase INSTANCE;

    // Este metodo se invocará para obtener la instancia de la BD
    // Como va a ser invocado desde diferentes partes del codigo, hay que pasarle un context
    // Con este metodo podremos realizar operaciones sobre las entidades de la BD
    // Solo se debe definir esta clase una vez y solo instanciar en los sitios donde lo necesitemos
    public static NotaRoomDatabase getDatabase(final Context context){
        // en caso de que no se haya instanciado
        if(INSTANCE == null){
            // Instanciamos esta clase
            synchronized (NotaRoomDatabase.class){
                // Volvemos a comprobar que no existe ninguna instancia
                 if(INSTANCE == null){
                     // Creamos la instancia de la BD con el contexto recibido e indicando el
                     // objeto (nsme)
                     INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                             NotaRoomDatabase.class, "notas_database")
                             .build();
                 }
            }
        }

        // en caso de que ya se haya instanciado
        return INSTANCE;
    }
}
