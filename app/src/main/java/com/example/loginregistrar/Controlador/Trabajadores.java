package com.example.loginregistrar.Controlador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.loginregistrar.R;


public class Trabajadores extends Fragment {

    Fragment RegistrarTrabajador;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_trabajadores, container, false);

        Button registrarTra = vista.findViewById(R.id.btnRegistrarTra);

        RegistrarTrabajador = new RegistrarTrabajador();

        registrarTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.traba, RegistrarTrabajador).commit();

            }
        });

        return vista;
    }
}