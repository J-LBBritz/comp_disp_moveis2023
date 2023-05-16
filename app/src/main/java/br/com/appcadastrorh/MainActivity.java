package br.com.appcadastrorh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button btnAdicionar;
    private ListView lvFuncionarios;
    private List<Funcionario> listFuncionarios;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvFuncionarios = findViewById(R.id.lvFuncionarios);
        btnAdicionar = findViewById(R.id.btnAdd);

        lvFuncionarios.setOnItemLongClickListener((parent, view, position, id) -> {
            deletar(position);
            return true;
        });

        btnAdicionar.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,
                    FormularioFuncionarioActivity.class);
            i.putExtra("acao", "inserir" );
            startActivity(i);
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        carregarFuncionarios();
    }
        private void carregarFuncionarios(){
        listFuncionarios = FuncionarioDAO.getFuncionarios(this);

            if (listFuncionarios.isEmpty()){
                lvFuncionarios.setEnabled(false);
                String[] listaVazia = {getString(R.string.listavazia)};
                adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaVazia);
            }else {
                lvFuncionarios.setEnabled(true);
                adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listFuncionarios);
            }
            lvFuncionarios.setAdapter(adapter);

        }

    private void deletar (int posicao){
        Funcionario funcionario = listFuncionarios.get(posicao);
        System.out.println(funcionario);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir");
        alerta.setIcon(android.R.drawable.ic_dialog_alert);
        alerta.setMessage("Confirma a exclusão de " + funcionario.getNome() + "? ");
        alerta.setNeutralButton("Não", null);
        alerta.setPositiveButton("Sim", (dialog, which) -> {
            FuncionarioDAO.excluir(MainActivity.this, funcionario.getMatricula());
            carregarFuncionarios();
        });
        alerta.show();
    }
}