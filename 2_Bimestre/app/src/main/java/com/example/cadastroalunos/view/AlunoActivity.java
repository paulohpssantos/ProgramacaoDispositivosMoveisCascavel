package com.example.cadastroalunos.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        builder.setCancelable(false);//não deixa o popup fechar ao clicar fora

        //Adicionando o botão cancelar, que fechará o dialog
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });

        //Adicionando o botão salvar
        //nesse ponto nào vamos adicionar o método
        //onclick, pois é necessário que os EditTexts
        // da tela estejam criados, e nesse momento
        //não estão criados
        builder.setPositiveButton("SALVAR", null);

        //Cria o dialog com o layout e os campos carrgados
        dialog = builder.create();

        //Após carregar o dialog precisamos adicionar
        // o método onClickListener no no botão SALVAR
        // agora os EditTexts estão criados em memória
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button bt = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salvarDados();
                    }
                });
            }
        });

        //Exibe o dialog na tela
        dialog.show();

    }

    private void salvarDados(){
        //Envia os dados de ra e nome
        //para gravar o aluno, validando
        //os campos e se existe o aluno cadastrado
        String retorno = controller
                .salvarAluno(edRa.getText().toString(),
                        edNome.getText().toString());

        //Informa o erro caso no campo RA
        // tenha problema
        //com o RA do aluno
        if(retorno.contains("RA")){
            edRa.setError(retorno);
            edRa.requestFocus();
            return;
        }

        //Informa o erro no campo Nome
        // caso tenha algum problema com
        //nome do aluno
        if(retorno.contains("NOME")){
            edNome.setError(retorno);
            edNome.requestFocus();
            return;
        }

        //Ao gravar o aluno atualiza a lista
        //e fecha o dialog
        if(retorno.contains("sucesso")){
            atualizarListaAlunos();
            dialog.dismiss();
        }

        //Exibe mensagem
        Toast.makeText(this,
                retorno,
                Toast.LENGTH_LONG).show();
    }








}