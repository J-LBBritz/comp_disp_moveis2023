package br.com.appcadastrorh;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public static void inserir(Context context, Funcionario funcionario) {
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", funcionario.getNome());
        valores.put("email", funcionario.getEmail());
        valores.put("dataNasc", funcionario.getDataNasc().toString());
        valores.put("area", funcionario.getArea().toString());


        db.insert("funcionario", null, valores);
    }

    public static void editar(Context context, Funcionario funcionario) {
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", funcionario.getNome());
        valores.put("email", funcionario.getEmail());
        valores.put("dataNasc", funcionario.getDataNasc().toString());
        valores.put("modalidade", funcionario.getArea().toString());

        db.update("socio", valores,
                " matricula = " + funcionario.getMatricula(), null);
    }

    public static void excluir(Context context, int matricula) {
        SQLiteDatabase db = new Conexao(context).getWritableDatabase();

        db.delete("funcionario",
                " matricula = " + matricula, null);
    }

    public static List<Funcionario> getFuncionarios(Context context) {
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        List<Funcionario> lista = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT matricula, nome, email, dataNasc, area FROM funcionario",
                null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Funcionario funcionario = new Funcionario();
                funcionario.setMatricula(cursor.getInt(0));
                funcionario.setNome(cursor.getString(1));
                funcionario.setEmail(cursor.getString(2));
                funcionario.setDataNasc(cursor.getString(3));
                funcionario.setArea(Area.valueOf(cursor.getString(4)));

                lista.add(funcionario);
            } while (cursor.moveToNext());
        }
        return lista;
    }
}