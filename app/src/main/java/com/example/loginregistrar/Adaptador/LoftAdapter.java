package com.example.loginregistrar.Adaptador;

import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

import com.example.loginregistrar.R;
import com.example.loginregistrar.model.loft;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class LoftAdapter extends FirestoreRecyclerAdapter<loft, LoftAdapter.ViewHolder>
{

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public LoftAdapter(@NonNull FirestoreRecyclerOptions<loft> options) {
        super(options);
    }

    //Establecer datos q tendra cada textView
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int i, @NonNull loft loft) {
        holder.nombreLoft.setText(loft.getNombre());
        holder.comentario.setText(loft.getComentario());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.v_duen_frag_lofts,viewGroup,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView nombreLoft, comentario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreLoft= itemView.findViewById(R.id.tvNombreLoft);
            comentario=itemView.findViewById(R.id.tvComentarioLoft);
        }
    }
}
