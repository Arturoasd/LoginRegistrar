package com.example.loginregistrar.Controlador;

import android.content.ContentProviderOperation;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginregistrar.R;

public class AgregarLoft extends Fragment {

    EditText edtNombreLoft, edtComentarios;
    Button btnVolver, btnRegis;

    public AgregarLoft() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View frag = inflater.inflate(R.layout.fragment_agregar_loft, container, false);

        EditText edtNombreLoft = frag.findViewById(R.id.edtNombreLoft);
        Button btnVolver=frag.findViewById(R.id.btnVolver);

        Fragment me=this;

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().remove(me).commit();
            }
        });

        return frag;
    }
}