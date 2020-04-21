package com.stg.systigpay;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

public class RegisterEmpresaActivity extends AppCompatActivity implements RegisterEmpresaFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_empresa);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
