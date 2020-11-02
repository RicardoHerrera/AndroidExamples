package com.scotiabank.autenticacion.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.scotiabank.autenticacion.entidades.User;
import com.scotiabank.autenticacion.util.Constantes;
import com.scotiabank.autenticacion.util.UserDB;

import java.util.ArrayList;

public class DAOUser {

    UserDB dbUser;
    SQLiteDatabase db;
    private Context context;

    public DAOUser(Context context) {
        dbUser = new UserDB(context);
        this.context = context;
    }

    public void openDB() { db = dbUser.getWritableDatabase(); }

    public void registrarUsuario(User user) {

        try {

            ContentValues values = new ContentValues();
            values.put("nombre", user.getNombre());
            values.put("apellido", user.getApellido());
            values.put("usuario", user.getUsuario());
            values.put("password", user.getPassword());

            long resultado = db.insert(Constantes.NOMBRE_TABLA, null, values);

            if (resultado == -1) {
                // Mostrar mensaje de error
                Toast.makeText(context, "Error al insertar", Toast.LENGTH_LONG).show();
            } else {
                // Mostrar un mensaje de éxito
                Toast.makeText(context, "Se insertó correctamente", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> listaUsers = new ArrayList<>();

        try {
            Cursor c = db.rawQuery("SELECT * FROM " + Constantes.NOMBRE_TABLA, null);

            while(c.moveToNext()) {
                listaUsers.add(new User(c.getInt(0), c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4)));
            }

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return listaUsers;
    }

    public User getUserAutenticacion(String usuario, String pwd) {
        User user = null;

        try {
            Cursor c = db.rawQuery("SELECT * FROM " + Constantes.NOMBRE_TABLA +
                    " WHERE usuario = ? AND password = ?", new String[] {usuario, pwd});
            if (c.getCount() > 0) {
                c.moveToFirst();
                user =  new User(c.getInt(0), c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4));
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return user;
    }

}
