package com.cristovancamilo.imcexato.ui.insereIMC;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cristovancamilo.imcexato.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class InsereIMCFragment extends Fragment {

    private TextInputEditText textInputLayoutPeso;
    private TextInputEditText textInputLayoutAltura;
    private TextView textViewIMCResultado;
    private TextView textViewClassificacaoIMC;
    private Button buttonCalcularIMC;
    private double imc;
    String c;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insere_imc, container, false);

        textInputLayoutAltura = view.findViewById(R.id.textInputLayoutAltura);
        textInputLayoutPeso = view.findViewById(R.id.textInputLayoutPeso);
        textViewIMCResultado = view.findViewById(R.id.textViewIMCResultado);
        textViewClassificacaoIMC = view.findViewById(R.id.textViewClassificacao);
        buttonCalcularIMC = view.findViewById(R.id.buttonCalcularIMC);

        buttonCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double altura = Double.parseDouble(textInputLayoutAltura.getText().toString());
                double peso = Double.parseDouble(textInputLayoutPeso.getText().toString());
                imc = peso / (altura * altura);
                calcularIMC();
                textViewIMCResultado.setText("IMC: " + new DecimalFormat("#,##0.00").format(imc));
                textViewClassificacaoIMC.setText(c.toString());
            }
        });

        return view;
    }



    private void calcularIMC() {

        c = "Classificação: ";
        if(imc < 17) {
            c = c + "Muito Abaixo do Peso";
        }else if(imc >= 17 && imc <= 18.49) {
            c = c + "Abaixo do Peso";
        }else if(imc >= 18.5 && imc <= 24.99) {
            c = c + "Peso Normal";
        }else if(imc >= 25 && imc <= 29.99) {
            c = c + "Acima do Peso";
        }else if(imc >= 30 && imc <= 34.99) {
            c = c + "Obesidade I";
        }else if(imc >= 35 && imc <= 39.99) {
            c = c + "Obesidade II (Severa)";
        }else if(imc > 40) {
            c = c + "Obesidade III (Mórbida)";
        }

    }
}
