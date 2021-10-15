package com.example.loginregistrar.Controlador;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.loginregistrar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Loft extends Fragment {

    FragmentTransaction transaction;
    Fragment AgregarLoft;
    LinearLayout linearL;

    View vista;

    public Loft() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View frag = inflater.inflate(R.layout.fragment_loft, container, false);

        FloatingActionButton btnAgregar = frag.findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AgregarLoft = new AgregarLoft();


               FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
               ft.replace(R.id.contenedor, AgregarLoft).commit();


            }
        });


        return frag;
    }
}