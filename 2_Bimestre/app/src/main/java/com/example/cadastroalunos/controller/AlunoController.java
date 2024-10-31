package com.example.cadastroalunos.controller;

import android.content.Context;

import com.example.cadastroalunos.dao.AlunoDao;
import com.example.cadastroalunos.model.Aluno;

import java.util.ArrayList;

public class AlunoController {

    private Context context;

    public AlunoController(Context context) {
        this.context = context;
    }

    public String salvarAluno(String ra, String nome){
        return null;
    }

    public ArrayList<Aluno> retornarTodosAlunos(){
        return AlunoDao.getInstance(context).getAll();
    }

}
