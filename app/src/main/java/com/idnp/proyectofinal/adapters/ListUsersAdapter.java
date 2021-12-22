package com.idnp.proyectofinal.adapters;

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

public class ListUsersAdapter extends RecyclerView.Adapter<ListUsersAdapter.UsuarioViewHolder> {

    ArrayList<User> listaUsuarios;
    ArrayList<User> listaOriginal;

    public ListUsersAdapter(ArrayList<User> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaUsuarios);
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_x, null, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        holder.viewNombre.setText(listaUsuarios.get(position).getNombres());
        holder.viewApellido.setText(listaUsuarios.get(position).getApellidos());
        holder.viewCorreo.setText(listaUsuarios.get(position).getCorreo_electronico());
    }

    public void filtrado(final String txtBuscar) {
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
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewApellido,viewCorreo;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewUserName);
            viewApellido = itemView.findViewById(R.id.viewUserLastName);
            viewCorreo = itemView.findViewById(R.id.viewUserEmail);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaUsuarios.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });*/
        }
    }
}