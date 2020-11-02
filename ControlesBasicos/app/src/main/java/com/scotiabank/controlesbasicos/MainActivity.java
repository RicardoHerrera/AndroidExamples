package com.scotiabank.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    EditText txtEmail;
    EditText txtDomicilio;
    Button btnFinalizar;
    Spinner cbxOptions;
    RadioButton rbManana, rbTarde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtDomicilio = findViewById(R.id.txtDomicilio);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        cbxOptions = findViewById(R.id.cbxOptions);
        rbManana = findViewById(R.id.rbManana);
        rbTarde = findViewById(R.id.rbTarde);

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtNombre.getText().toString().equals("") ||
                        txtEmail.getText().toString().equals("") ||
                        txtDomicilio.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Debe completar todos los campos", Toast.LENGTH_LONG).show();
                } else {
                    String horario = "";
                    if (rbManana.isChecked()) {
                        horario = "en la mañana";
                    } else {
                        horario = "en la tarde";
                    }
                    Toast.makeText(MainActivity.this, "Pedido guardado correctamente. Será enviado " + horario, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}