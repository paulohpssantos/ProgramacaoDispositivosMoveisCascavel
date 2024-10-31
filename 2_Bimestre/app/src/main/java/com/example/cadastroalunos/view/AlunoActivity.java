package com.example.cadastroalunos.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.adapter.AlunoListAdapter;
import com.example.cadastroalunos.controller.AlunoController;
import com.example.cadastroalunos.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AlunoActivity extends AppCompatActivity {

    private FloatingActionButton btAddAluno;
    private RecyclerView rvAlunos;
    private AlunoController controller;
    private AlertDialog dialog;
    private View viewCadastro;
    private EditText edRa, edNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aluno);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        controller = new AlunoController(this);
        rvAlunos = findViewById(R.id.rvAlunos);
        btAddAluno = findViewById(R.id.btAddAluno);
        btAddAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        atualizarListaAlunos();

    }

    private void atualizarListaAlunos(){
        ArrayList<Aluno> listaAlunos =
                controller.retornarTodosAlunos();

        AlunoListAdapter adapter =
                new AlunoListAdapter(listaAlunos, this);

        rvAlunos.setLayoutManager(new LinearLayoutManager(this));
        rvAlunos.setAdapter(adapter);
    }

    private void abrirCadastro(){
        //Carregar o xml do layout do cadastro
        viewCadastro = getLayoutInflater()
                .inflate(R.layout.dialog_cadastro_aluno, null);

        edRa = viewCadastro.findViewById(R.id.edRa);
        edNome = viewCadastro.findViewById(R.id.edNome);

        //Carregando o popup
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(this);

        builder.setTitle("CADASTRO DE ALUNO"); //setando o titulo da tela
        builder.setView(viewCadastro); //setando o layout carregado
        builder.setCancelable(false);//não deixa o popu fechar ao clicar fora

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("SALVAR", null);
        dialog = builder.create();
        dialog.show();



    }








}