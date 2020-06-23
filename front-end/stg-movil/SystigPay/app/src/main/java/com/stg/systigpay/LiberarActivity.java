package com.stg.systigpay;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import static android.content.ContentValues.TAG;

public class LiberarActivity extends AppCompatActivity
        implements LiberarFragment.OnFragmentInteractionListener,
        LiberarResumenFragment.OnFragmentInteractionListener,
        LiberarConfirmaFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liberar);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.tool_menu_liberar);
        toolbar.setTitle(R.string.text_accion_liberar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorButtonText));
        toolbar.setNavigationIcon(R.drawable.left_arrow_32px);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.button_menu_qr_liberar:
                Log.d(TAG, "onOptionsItemSelected: Configurar llamada a QR");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_header_liberar, menu);
        return true;
    }
}
