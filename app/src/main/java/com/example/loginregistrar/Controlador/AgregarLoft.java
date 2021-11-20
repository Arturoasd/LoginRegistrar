package com.example.loginregistrar.Controlador;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.loginregistrar.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AgregarLoft extends Fragment {

    EditText edtNombreLoft, edtComentarios;

    ListView listViewLoft;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseFirestore mfirestore;
    DocumentReference newLoftRef = db.collection("lofts").document();

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

        View frag = inflater.inflate(R.layout.v_duen_frag_loft, container, false);

        listViewLoft = frag.findViewById(R.id.listViewLoft);

        edtNombreLoft = frag.findViewById(R.id.edtNombreLoft);
        edtComentarios = frag.findViewById(R.id.edtComentario);
        Button btnRegis=frag.findViewById(R.id.btnRegis);
        Button btnVolver=frag.findViewById(R.id.btnVolver);

        //BOTON para agregar y mostrar datos en lista
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                crearDatos();
            }

        });

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

    //Insertar loft
    private void crearDatos()
    {
        String nombre,comentario;
        nombre= edtNombreLoft.getText().toString();
        comentario = edtComentarios.getText().toString();

        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombre);
        map.put("comentario", comentario);

        newLoftRef.collection("lofts").document().set(map);
    }

    ArrayAdapter<String> adapter;
    ArrayList<String>arrayList=new ArrayList<>();
    //Cargar y mostrar datos en ListView
    private void mostrarDatos(LayoutInflater inflater, ViewGroup container)
    {

        View frag = inflater.inflate(R.layout.v_duen_frag_loft, container, false);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mRootChild = mDatabase.child("lofts");
        adapter = new ArrayAdapter<String>(this.getContext(), R.layout.v_duen_frag_lofts, arrayList);

        mRootChild.addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s){
                String string = dataSnapshot.getValue(String.class);
                arrayList.add(string);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s){

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot){
                String string = dataSnapshot.getValue(String.class);
                arrayList.remove(string);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s){

            }

            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });

    }
}





















