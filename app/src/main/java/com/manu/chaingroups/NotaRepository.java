package com.manu.chaingroups;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.manu.chaingroups.db.NotaRoomDatabase;
import com.manu.chaingroups.db.dao.NotaDao;
import com.manu.chaingroups.db.entity.NotaEntity;

import java.util.List;

public class NotaRepository {

    // La relacion del repositorio es conla Interface dao
    private NotaDao notadao;

    // LiveData convierte los datos en dinamicos para aplicar el patron ViewModel
    // y saber mediante un observador cuando esos datos cambian
    private LiveData<List<NotaEntity>> allNotas;
    private LiveData<List<NotaEntity>> allFavoritas;

    // Recibimos una instancia de la aplicacion para a partir de el instanciar la BD (NotaRoomDatabase)
    public NotaRepository(Application application) {
        // El contexto que espera recibir NotaRoomDatabase es la instancia de la aplicacion
        // que recibimos por parametro
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);

        // Obtenemos una instancia de Notadao para poder acceder a las operaciones
        notadao = db.notaDao();

        // Lo podemos hacer aqui en el constructor o como en el metodo comentado
        allNotas = notadao.getAll();
        allFavoritas = notadao.getAllFavoritas();
    }

    /* // Declaramos los metodos para acceder a la BD
    public LiveData<List<NotaEntity>> getall(){
         // accedemos a los metodos a traves del objeto NotaDao
         return notadao.getAll();
    }*/


    // Es mas correcto hacerlo asi
    public LiveData<List<NotaEntity>> getall() {
        return allNotas;
    }

    public LiveData<List<NotaEntity>> getallFavs() {
        return allFavoritas;
    }

    // Metodos para la insercion de datos
    public void insert (NotaEntity nota){
        // Aqui instanciamos a la clase AsyncTask y le pasamos la nota recibida en este metodo insert
        new insertAsyncTask(notadao).execute(nota);
    }

    // para ejecutar procesos en segundo plano utilizamos AsyncTask
    private static class insertAsyncTask extends AsyncTask<NotaEntity, Void, Void>{
        private NotaDao notaDaoAsynkTask;

        insertAsyncTask(NotaDao dao){
            notaDaoAsynkTask = dao;
        }

        @Override
        // Por parametro recibimos un Array de parametros (atributo Rest)
        protected Void doInBackground(NotaEntity... notaEntities) {
            // para acceder a una unica nota, pasamos como paratro el
            // primer elemento del Array notaEntities
            notaDaoAsynkTask.insert(notaEntities[0]);
            return null;
        }
    }
    public void update (NotaEntity nota) {
        new updateAsyncTask(notadao).execute(nota);
    }

    private static class updateAsyncTask extends AsyncTask<NotaEntity, Void, Void> {
        private NotaDao notaDatoAsyncTask;

        updateAsyncTask(NotaDao dao) {
            notaDatoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDatoAsyncTask.update(notaEntities[0]);
            return null;
        }


}}
