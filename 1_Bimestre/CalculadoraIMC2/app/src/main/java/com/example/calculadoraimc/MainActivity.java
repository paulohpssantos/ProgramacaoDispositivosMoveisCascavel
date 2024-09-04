package com.example.calculadoraimc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {

    private EditText edAltura;
    private EditText edPeso;
    private Button btHomem;
    private Button btMulher;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edAltura = findViewById(R.id.edAltura);
        edPeso = findViewById(R.id.edPeso);
        btHomem = findViewById(R.id.btHomem);
        btMulher = findViewById(R.id.btMulher);
        tvResultado = findViewById(R.id.tvResultado);

        btHomem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularIMC();
            }
        });

    }

    private void calcularIMC(){

        /*retornando texto do campo editText e
          convertendo em int (Integer.parseInt)*/
        int peso = Integer.parseInt(edPeso.getText().toString());
        double altura = Double.parseDouble(edAltura.getText().toString());
        double imc = peso / (altura * altura);
        tvResultado.setText("O IMC é: "+imc);

    }











}