package com.stg.systigpay;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

public class RegisterPersonActivity extends AppCompatActivity implements RegisterPersonFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_person);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
