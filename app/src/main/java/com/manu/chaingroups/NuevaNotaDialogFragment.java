package com.manu.chaingroups;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.manu.chaingroups.db.entity.NotaEntity;

public class NuevaNotaDialogFragment extends DialogFragment{



    public static NuevaNotaDialogFragment newInstance() {
        return new NuevaNotaDialogFragment();
    }

    private View view;
    private EditText edTitulo, edContenido;
    private RadioGroup rgColor;
    private Switch swFavorita;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nueva_nota_dialog_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Para el titulo del dialogo
        builder.setTitle("Nueva nota");
        builder.setMessage("Introduzca los datos de la nueva nota")
                .setPositiveButton("Guardar la nota", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String titulo = edTitulo.getText().toString();
                        String contenido = edContenido.getText().toString();
                        String color = "azul";
                        switch (rgColor.getCheckedRadioButtonId()){
                            case R.id.radioButtonColorRojo:
                                color = "rojo";
                                break;
                            case R.id.radioButtonColorVerde:
                                color = "verde";
                                break;
                        }

                        boolean esFavorita = swFavorita.isChecked();

                        // Le comunicamos al viewModel que tenemos una nueva nota
                        // Instanciamos el ViewModel.
                        NuevaNotaDialogViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NuevaNotaDialogViewModel.class);
                        mViewModel.insertarNota(new NotaEntity(titulo,contenido,esFavorita,color));
                        // Cerramos el cuadro de dialogo
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Cerramos el cuadro de dialogo
                        dialog.dismiss();
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        // El segundo parametro es null porque no se devuelve a otro layout
        // sino que se va a insertar en este dialogo
        view = inflater.inflate(R.layout.nueva_nota_dialog_fragment,null);

        // Recuperamos los componentes
        edTitulo = view.findViewById(R.id.editTextTitulo);
        edContenido =  view.findViewById(R.id.editTextContenido);
        rgColor = view.findViewById(R.id.radioGroupColor);
        swFavorita = view.findViewById(R.id.switchNotaFavorita);

        // Para añadir la vista al cuadro de dialogo
        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
