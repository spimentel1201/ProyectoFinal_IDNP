package com.idnp.proyectofinal.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idnp.proyectofinal.R;
import com.idnp.proyectofinal.models.User;
import com.idnp.proyectofinal.models.VaccinationPlace;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListVaccinePlacesAdapter extends RecyclerView.Adapter<ListVaccinePlacesAdapter.VaccinePlaceViewHolder> {

    ArrayList<VaccinationPlace> listaCentros;
    ArrayList<VaccinationPlace> listaOriginal;

    public ListVaccinePlacesAdapter(ArrayList<VaccinationPlace> listaCentrosx) {
        this.listaCentros = listaCentrosx;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaCentrosx);
    }

    @NonNull
    @Override
    public VaccinePlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_x, null, false);
        return new VaccinePlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListVaccinePlacesAdapter.VaccinePlaceViewHolder holder, int position) {
        holder.viewVacName.setText(listaCentros.get(position).getPlaceName());
        holder.viewVacPlace.setText(listaCentros.get(position).getVaccineName());
        //holder.viewVacName.setText(listaCentros.get(position).getFecha().toString());
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaCentros.clear();
            listaCentros.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<VaccinationPlace> collecion = listaCentros.stream()
                        .filter(i -> i.getPlaceName().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaCentros.clear();
                listaCentros.addAll(collecion);
            } else {
                for (VaccinationPlace c : listaOriginal) {
                    if (c.getVaccineName().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaCentros.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaCentros.size();
    }

    public class VaccinePlaceViewHolder extends RecyclerView.ViewHolder {

        TextView viewVacName, viewVacPlace;
        public VaccinePlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            viewVacName = itemView.findViewById(R.id.viewVacName);
            viewVacPlace = itemView.findViewById(R.id.viewVacPlace);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                }
            });
        }
    }
}