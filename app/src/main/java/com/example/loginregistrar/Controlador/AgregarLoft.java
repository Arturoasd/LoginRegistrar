package com.example.loginregistrar.Controlador;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.loginregistrar.Adaptador.LoftAdapter;
import com.example.loginregistrar.R;
import com.example.loginregistrar.model.loft;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgregarLoft extends Fragment {

    EditText edtNombreLoft, edtComentarios;

    RecyclerView recyclerViewLoft;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseFirestore mfirestore;

    DocumentReference newLoftRef = db.collection("lofts").document();

    LoftAdapter mAdapter;

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

        edtNombreLoft = frag.findViewById(R.id.edtNombreLoft);
        edtComentarios = frag.findViewById(R.id.edtComentario);
        Button btnRegis=(Button)frag.findViewById(R.id.btnRegis);
        Button btnVolver=frag.findViewById(R.id.btnVolver);

        recyclerViewLoft = frag.findViewById(R.id.recyclerViewLoft);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                crearDatos();
                recyclerViewLoft.setAdapter(mAdapter);
                mostrarDatos();

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

    LinearLayoutManager mLayout;

    public void mostrarDatos()
    {
        mLayout = new LinearLayoutManager(getActivity());

        mfirestore = FirebaseFirestore.getInstance();
        Query query = mfirestore.collection("lofts");

        FirestoreRecyclerOptions<loft> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<loft>().setQuery(query, loft.class).build();

        mAdapter = new LoftAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();

    }

    //

}