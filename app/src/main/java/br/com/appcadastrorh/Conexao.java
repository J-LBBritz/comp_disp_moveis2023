package br.com.appcadastrorh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String NOME_BD = "banco_funcionarios";
    private static final int VERSAO_BD = 1;

    public Conexao(Context context){
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE funcionario (" +
                " matricula INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                " nome TEXT NOT NULL," +
                " email TEXT NOT NULL," +
                " dataNasc TEXT NOT NULL," +
                " area TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
