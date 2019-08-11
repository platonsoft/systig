package com.stg.flashpay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private UsuariosHelper datosUsuario = new UsuariosHelper(this);
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    ProgressBar loadingProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.usernameEditText = findViewById(R.id.input_usuario_login);
        this.passwordEditText = findViewById(R.id.input_clave_login);
        this.loginButton = findViewById(R.id.login_login);
        this.loadingProgressBar = findViewById(R.id.loading_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                if (validarDatos()){
                    Boolean logeo = datosUsuario.inicioSesion(String.valueOf(usernameEditText.getText()),String.valueOf(passwordEditText.getText()));
                    if (logeo){
                        sesionIniciada();
                    }else{
                        sesionRechazada();
                    }
                }
            }
        });
        Toast.makeText(this, "Introduzca sus credenciales de acceso", Toast.LENGTH_LONG).show();
    }

    private void sesionIniciada(){
        Toast.makeText(this, "Inicio correcto, Bienvenido", Toast.LENGTH_LONG).show();
        Intent intent = new Intent (this, HomeMainActivity.class);
        startActivityForResult(intent, 0);
    }

    private void sesionRechazada(){
        Toast.makeText(this, "Error: Acceso denegado", Toast.LENGTH_LONG).show();
    }

    private Boolean validarDatos(){
        Boolean resultado = true;
        if(!String.valueOf(this.usernameEditText.getText()).contains("@") &&
                String.valueOf(this.usernameEditText.getText())!=""){
            resultado = false;
        }
        if (String.valueOf(this.passwordEditText.getText()).length()< 5 &&
                String.valueOf(this.passwordEditText.getText())!=""){
            resultado = false;
        }

        return resultado;
    }
}
