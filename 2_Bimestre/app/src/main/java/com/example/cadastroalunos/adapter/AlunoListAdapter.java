package com.example.cadastroalunos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Aluno;

import java.util.ArrayList;

public class AlunoListAdapter extends
        RecyclerView.Adapter<AlunoListAdapter.ViewHolder> {

    private ArrayList<Aluno> listaAlunos;
    private Context context;

    public AlunoListAdapter(ArrayList<Aluno> listaAlunos,
                            Context context) {
        this.listaAlunos = listaAlunos;
        this.context = context;
    }

    /**
     * Método responsável em carregar o
     * arquivo xml para cada elemento da lista
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =
                LayoutInflater.from(parent.getContext());

        View listItem =
                inflater.inflate(R.layout.item_list_aluno,
                        parent, false);

        return new ViewHolder(listItem);
    }

    /**
     * Método responsável em escrever os dados no layout
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);
        holder.tvRa.setText(String.valueOf(aluno.getRa()));
        holder.tvNome.setText(aluno.getNome());
    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvRa, tvNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvRa = itemView.findViewById(R.id.tvRa);
            this.tvNome = itemView.findViewById(R.id.tvNome);
        }
    }
}
