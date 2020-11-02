package com.scotiabank.autenticacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.scotiabank.autenticacion.entidades.User;
import com.scotiabank.autenticacion.modelo.DAOUser;

public class CrearUsuario extends AppCompatActivity {

    EditText edtNombre, edtApellido, edtUsuario, edtPassword;
    Button btnGuardar;

    DAOUser daoUser = new DAOUser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        daoUser.openDB();

        edtNombre = findViewById(R.id.edtNombre);
        edtApellido = findViewById(R.id.edtApellido);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtNewPassword);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNombre.getText().toString().equals("")) {
                    // Mostrar mensaje que llene los campos
                } else {
                    // Guardar la data en BD
                    // Creamos un objeto nuevo de usuario con lo llenado en el formulario
                    User usuario = new User(edtNombre.getText().toString(),  edtApellido.getText().toString(),
                    edtUsuario.getText().toString(), edtPassword.getText().toString());
                    // Guardamos el objeto nuevo en la base de datos
                    daoUser.registrarUsuario(usuario);
                }
            }
        });

    }
}