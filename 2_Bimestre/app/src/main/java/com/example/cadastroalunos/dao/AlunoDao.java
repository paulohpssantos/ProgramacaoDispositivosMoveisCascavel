package com.example.cadastroalunos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cadastroalunos.helper.SQLiteDataHelper;
import com.example.cadastroalunos.model.Aluno;

import java.util.ArrayList;

public class AlunoDao implements IGenericDao<Aluno>{

    //Variável responsável por abri a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase baseDados;

    //nome das colunas da tabela
    private String[]colunas = {"RA","NOME"};

    //Nome da tabela
    private String tabela = "ALUNO";

    //Contexto
    private Context context;

    private static AlunoDao instancia;

    public static AlunoDao getInstance(Context context){
        if(instancia == null) {
            instancia = new AlunoDao(context);
        }
        return instancia;
    }

    private AlunoDao(Context context) {
        this.context = context;

        //Abrir a Conexao com a base de dados
        openHelper = new SQLiteDataHelper(this.context,
                "UNIPARCVEL_BD", null, 1);

        baseDados = openHelper.getWritableDatabase();

    }

    /**
     * Método para inserir dados na tabela ALUNO
     * @param obj Aluno
     * @return a linha que foi inserido o registro
     */
    @Override
    public long insert(Aluno obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getRa());
            valores.put(colunas[1], obj.getNome());

            return baseDados.insert(tabela, null, valores);

        }catch (SQLException ex){
            Log.e("UNIPAR",
                    "ERRO: AlunoDao.insert() "+
                    ex.getMessage());
        }

        return 0;
    }

    /**
     * Atualizar registro na tabela aluno
     * @param obj Aluno
     * @return
     */
    @Override
    public long update(Aluno obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getNome());

            String[]identificador = {String.valueOf(obj.getRa())};

            return baseDados.update(tabela,
                    valores,
                    colunas[0]+"= ?",identificador);

        }catch (SQLException ex){
            Log.e("UNIPAR",
                    "ERRO: AlunoDao.update() "+
                            ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Aluno obj) {
        return 0;
    }

    @Override
    public ArrayList<Aluno> getAll() {
        return null;
    }

    @Override
    public Aluno getById(int id) {
        return null;
    }
}
