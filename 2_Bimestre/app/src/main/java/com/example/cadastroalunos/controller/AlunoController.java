package com.example.cadastroalunos.controller;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.cadastroalunos.dao.AlunoDao;
import com.example.cadastroalunos.model.Aluno;

import java.util.ArrayList;

public class AlunoController {

    private Context context;

    public AlunoController(Context context) {
        this.context = context;
    }

    public String salvarAluno(String ra, String nome){
        try{
            if(TextUtils.isEmpty(ra)){
                return "Informe o RA do Aluno";
            }
            if(TextUtils.isEmpty(nome)){
                return "Informe o NOME do Aluno";
            }

            //Verifica se existe um aluno cadastrado com
            // o RA informado
            Aluno aluno = AlunoDao
                    .getInstance(context)
                    .getById(Integer.parseInt(ra));

            if(aluno != null){
                return "O RA ("+ra+") já está cadastrado.";
            }else{
                aluno = new Aluno();
                aluno.setRa(Integer.parseInt(ra));
                aluno.setNome(nome);

                long id = AlunoDao.getInstance(context).insert(aluno);
                if(id > 0){
                    return "Aluno cadastrado com sucesso!";
                }else{
                    return "Não foi possível gravar os dados do Aluno." +
                            " Solicite suporte técnico.";
                }
            }


        }catch (Exception ex){
            Log.e("Unipar",
                    "Erro salvarAluno(): "+ex.getMessage());
        }
        return "Erro ao gravar dados do Aluno. " +
                "Solicite suporte técnico.";
    }

    public ArrayList<Aluno> retornarTodosAlunos(){
        return AlunoDao.getInstance(context).getAll();
    }

}
