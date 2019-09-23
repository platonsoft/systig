package com.stg.flashpay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private UsuariosHelper datosUsuario = new UsuariosHelper(this);
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button loginRegistro;
    ProgressBar loadingProgressBar;

    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.usernameEditText = findViewById(R.id.input_usuario_login);
        this.passwordEditText = findViewById(R.id.input_clave_login);
        this.loginButton = findViewById(R.id.login_login);
        this.loginRegistro = findViewById(R.id.login_registro);
        this.loadingProgressBar = findViewById(R.id.loading_login);
        mAuth = FirebaseAuth.getInstance();

        loginRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarCuenta();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                if (validarDatos()){
                    mAuth.signInWithEmailAndPassword(String.valueOf(usernameEditText.getText()),String.valueOf(passwordEditText.getText()))
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        UsuariosHelper usuarioData = new UsuariosHelper(LoginActivity.this);
                                        if(usuarioData.getDatosUsuario().getCodigo().equals(user.getUid())){
                                            // Sign in success, update UI with the signed-in user's information
                                            loadingProgressBar.setVisibility(View.INVISIBLE);
                                            Log.d("LoginActivity", "InicioSesion:Correcto");
                                            sesionIniciada();
                                        }else{
                                            // If sign in fails, display a message to the user.
                                            loadingProgressBar.setVisibility(View.INVISIBLE);
                                            Log.w("LoginActivity", "InicioSesion:Fallido No puede iniciar sesion en este dispositivo", task.getException());
                                            sesionRechazada();
                                        }
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        loadingProgressBar.setVisibility(View.INVISIBLE);
                                        Log.w("LoginActivity", "InicioSesion:Fallido", task.getException());
                                        sesionRechazada();
                                    }
                                }
                            });
                }
            }
        });
        Toast.makeText(this, "Introduzca sus credenciales de acceso", Toast.LENGTH_LONG).show();
    }
    private void registrarCuenta(){
        Intent intent = new Intent (this, RegistroActivity.class);
        startActivityForResult(intent, 0);
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
        if(!String.valueOf(this.usernameEditText.getText()).contains("@") &&
                String.valueOf(this.usernameEditText.getText())!=""){
            return false;
        }
        if (String.valueOf(this.passwordEditText.getText()).length()< 5 &&
                String.valueOf(this.passwordEditText.getText())!=""){
            return false;
        }
        return true;
    }
}
