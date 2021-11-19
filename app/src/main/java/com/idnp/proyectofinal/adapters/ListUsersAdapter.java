package com.idnp.proyectofinal.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idnp.proyectofinal.R;
import com.idnp.proyectofinal.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListUsersAdapter extends RecyclerView.Adapter<ListUsersAdapter.UserViewHolder> {

    ArrayList<User> listaUsuarios;
    ArrayList<User> listaOriginal;

    public ListUsersAdapter(ArrayList<User> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaUsuarios);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_x, null, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUsersAdapter.UserViewHolder holder, int position) {
        holder.viewVacName.setText(listaUsuarios.get(position).getNombres());
        //holder.viewApellido.setText(listaUsuarios.get(position).getApellidos());
        holder.viewVacPlace.setText(listaUsuarios.get(position).getTelefono());
    }

    /*public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaUsuarios.clear();
            listaUsuarios.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<User> collecion = listaUsuarios.stream()
                        .filter(i -> i.getNombres().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaUsuarios.clear();
                listaUsuarios.addAll(collecion);
            } else {
                for (User c : listaOriginal) {
                    if (c.getNombres().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaUsuarios.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }*/

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView viewVacName, viewVacPlace;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            viewVacName = itemView.findViewById(R.id.viewVacName);
            //viewApellido = itemView.findViewById(R.id.viewApellidos);
            viewVacPlace = itemView.findViewById(R.id.viewVacPlace);
            //viewDni = itemView.findViewById(R.id.viewDni);
            //viewCorreo = itemView.findViewById(R.id.viewCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    /*
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaUsuarios.get(getAdapterPosition()).getId());
                    context.startActivity(intent);*/
                }
            });
        }
    }
}