package com.cristovancamilo.imcexato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cristovancamilo.imcexato.ui.datepicker.DatePickerFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CadastroActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextInputEditText textInputEditTextNome;
    private RadioGroup radioGroupSexo;
    private RadioButton radioButtonMasculino;
    private RadioButton radioButtonFeminino;
    private EditText editTextNascimento;
    private Button buttonCadastrarUsuario;
    private TextView textViewIdade;

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    CadastroPreferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        preferencias = new CadastroPreferencias(getApplicationContext());

        buttonCadastrarUsuario = findViewById(R.id.buttonCadastrar);
        textInputEditTextNome = findViewById(R.id.textInputEditTextNome);
        radioGroupSexo = findViewById(R.id.radioGroupSexo);
        radioButtonMasculino = findViewById(R.id.radioButtonMasculino);
        radioButtonFeminino = findViewById(R.id.radioButtonFeminino);
        editTextNascimento = findViewById(R.id.editTextNascimento);
        textViewIdade = findViewById(R.id.textViewIdade);

        buttonCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sexo = "";
                if(radioButtonMasculino.isChecked()) {
                    sexo = "Masculino";
                }else if(radioButtonFeminino.isChecked()) {
                    sexo = "Feminino";
                }
               preferencias.salvarPessoa(textInputEditTextNome.getText().toString(), sexo, editTextNascimento.getText().toString());
            }
        });

        editTextNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });



        textInputEditTextNome.setText(preferencias.recuperarNome());
        String sexo = preferencias.recuperarSexo();
        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupSexo);
        if(sexo == "Masculino") {
            radioButtonMasculino.setChecked(true);
        }else if (sexo == "Feminino") {
            radioButtonFeminino.setChecked(true);
        }
        editTextNascimento.setText(preferencias.recuperarNascimento());
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(editTextNascimento.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        textViewIdade.setText(calcularIdade(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)) + " Anos");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
        String currentAge = calcularIdade(year, month, day) + " Anos";
        editTextNascimento.setText(currentDateString);
        textViewIdade.setText(currentAge);
    }

    private String calcularIdade(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }
}
