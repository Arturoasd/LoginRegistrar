package com.example.loginregistrar.Controlador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.loginregistrar.R;

public class Clientes extends Fragment {

    Fragment RegistrarCliente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_clientes, container, false);

        Button btnRegistrarCl = vista.findViewById(R.id.btnRegistrarTra);

        btnRegistrarCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                RegistrarCliente = new RegistrarCliente();

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.lista,RegistrarCliente ).commit();
            }
        });

        return vista;
    }
}