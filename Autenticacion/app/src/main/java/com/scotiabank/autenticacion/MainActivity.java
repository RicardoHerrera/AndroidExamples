package com.scotiabank.autenticacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scotiabank.autenticacion.entidades.User;
import com.scotiabank.autenticacion.modelo.DAOUser;

public class MainActivity extends AppCompatActivity {

    EditText txtUsuario, txtPassword;
    Button btnEntrar;
    DAOUser daoUser = new DAOUser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoUser.openDB();

        txtUsuario = findViewById(R.id.edtUser);
        txtPassword = findViewById(R.id.edtPassword);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtUsuario.getText().toString().equals("") ||
                        txtPassword.getText().toString().equals("")) {
                    // Mostrar toast
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Llena todos los campos", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    User usuario = daoUser.getUserAutenticacion(txtUsuario.getText().toString(),
                            txtPassword.getText().toString());

                    if (usuario == null) {
                        Toast toast = Toast.makeText(MainActivity.this,
                                "Uno o más campos no son correctos", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        // Navegar a la nueva pantalla
                        Intent intent = new Intent(MainActivity.this, UserList.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }
}