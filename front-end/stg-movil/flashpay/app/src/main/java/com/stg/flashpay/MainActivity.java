package com.stg.flashpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private UsuariosHelper datosUsuario = new UsuariosHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread hilo_carga = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    otro();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        hilo_carga.start();
    }

    private void otro(){
        Intent intent = new Intent (this, LoginActivity.class);
        startActivityForResult(intent, 0);
    }

}
