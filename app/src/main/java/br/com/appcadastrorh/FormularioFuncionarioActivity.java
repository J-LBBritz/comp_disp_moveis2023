package br.com.appcadastrorh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class FormularioFuncionarioActivity extends AppCompatActivity {

    private EditText etNome, etEmail, etData;
    private RadioGroup area;
    private RadioButton areaSelecionada;
    private Button btnSalvar;
    private Funcionario funcionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etData = findViewById(R.id.etData);
        area = findViewById(R.id.radioArea);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(view -> salvar());

        etData.setInputType(InputType.TYPE_NULL);
        etData.setOnClickListener(v -> {
            // calender class's instance and get current date , month and year from calender
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
            // date picker dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(FormularioFuncionarioActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        // set day of month , month and year value in the edit text
                        etData.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });
    }

    private void salvar() {
        areaSelecionada = findViewById(area.getCheckedRadioButtonId());
        String nome = etNome.getText().toString();
        String dataNasc = etData.getText().toString();
        String email = etEmail.getText().toString();
        String dataNascimento = etData.getText().toString();
        if (nome.isEmpty()) {
            Toast.makeText(this,
                    "O campo nome deve ser preenchido!",
                    Toast.LENGTH_LONG
            ).show();
        } else if (email.isEmpty()) {
            Toast.makeText(this, "O campo E-mail deve ser preenchido!",
                    Toast.LENGTH_LONG
            ).show();
        } else if (dataNascimento.isEmpty()) {
            Toast.makeText(this, "O campo Nascimento deve ser preenchido!",
                    Toast.LENGTH_LONG
            ).show();
        } else if (areaSelecionada == null) {
            Toast.makeText(this, "Você deve selecionar uma area!",
                    Toast.LENGTH_LONG
            ).show();
        } else {
            Area area = Area.valueOf(areaSelecionada.getText().toString().toUpperCase());

            funcionario = new Funcionario(nome, email, dataNasc, area);

            FuncionarioDAO.inserir(this, funcionario);
            etNome.setText("");
            etEmail.setText("");
            etData.setText("");
            this.area.clearCheck();
            funcionario = null;
        }
    }
}