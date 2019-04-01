package com.manu.chaingroups;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;


import com.manu.chaingroups.db.entity.NotaEntity;

import java.util.List;

// Hay q extender de AndroidViewModel como indica la documentacion
public class NuevaNotaDialogViewModel extends AndroidViewModel{
    // Aqui van los datos que queremos transferir
    private LiveData <List<NotaEntity>> allNotas;
    // El repository es la clase que nos facilita los datos
    private NotaRepository notaRepository;
    // Recibimos el contexto de la aplicacion
    public NuevaNotaDialogViewModel(Application application){
        super(application);

        notaRepository = new NotaRepository(application);

        // Hay que hacer la lista LiveData
        allNotas = notaRepository.getall();
    }


    // Metodo para devolvere la lista. Metodo de consulta
    // El fragmento que necesita recibir la nueva lista de datos
    public LiveData<List<NotaEntity>> getAllNotas(){return allNotas;}

    // El fragmento que inserte una nueva Nota debera comunicarlo a este ViewModel
    public void insertarNota(NotaEntity nuevaNotaEntity){
        notaRepository.insert(nuevaNotaEntity);
    }
}
