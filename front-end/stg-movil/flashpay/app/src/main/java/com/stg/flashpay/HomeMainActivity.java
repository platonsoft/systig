package com.stg.flashpay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.stg.flashpay.datos.Usuario;
import com.stg.flashpay.dummy.DummyContent;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Base64;

import static java.util.Base64.*;

public class HomeMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeSt1Fragment.OnFragmentInteractionListener,
        PagarSt1Fragment.OnFragmentInteractionListener,
        RecargaSt1Fragment.OnFragmentInteractionListener,
        LiberarSt1Fragment.OnFragmentInteractionListener,
        TransaccionFragment.OnListFragmentInteractionListener,
        EstadisticasSt1Fragment.OnFragmentInteractionListener,
        EventosFragment.OnListFragmentInteractionListener,
        TerminosSt1Fragment.OnFragmentInteractionListener,
        SoporteSt1Fragment.OnFragmentInteractionListener,
        ConfiguracionSt1Fragment.OnFragmentInteractionListener
{
    private UsuariosHelper datosUsuario = new UsuariosHelper(this);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        Usuario myUsuario = datosUsuario.getDatosUsuario();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ImageView labelFoto = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.fotoUsuario);
        TextView labelUsuario = (TextView)navigationView.getHeaderView(0).findViewById(R.id.nombreUsuario);
        TextView labelEmail = (TextView)navigationView.getHeaderView(0).findViewById(R.id.emailUsuario);

        byte[] byteArray = android.util.Base64.decode(myUsuario.getFotoX64(), android.util.Base64.DEFAULT);;
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedDrawable.setCornerRadius(bitmap.getHeight());

        labelUsuario.setText(myUsuario.getApellidos()+", " + myUsuario.getNombres());
        labelEmail.setText(myUsuario.getEmail());
        labelFoto.setImageDrawable(roundedDrawable);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment myFragment = null;

        if (id == R.id.nav_home) {
            myFragment = new HomeSt1Fragment();
        } else if (id == R.id.nav_realizar_pago) {
            myFragment = new PagarSt1Fragment();
        } else if (id == R.id.nav_recibir_recarga) {
            myFragment = new RecargaSt1Fragment();
        } else if (id == R.id.nav_liberar_banco) {
            myFragment = new LiberarSt1Fragment();
        } else if (id == R.id.nav_historico) {
            myFragment = new TransaccionFragment();
        } else if (id == R.id.nav_ver_estadistica) {
            myFragment = new EstadisticasSt1Fragment();
        } else if (id == R.id.nav_eventos) {
            myFragment = new EventosFragment();
        } else if (id == R.id.nav_terminos_condiciones) {
            myFragment = new TerminosSt1Fragment();
        } else if (id == R.id.nav_soporte) {
            myFragment = new SoporteSt1Fragment();
        } else if (id == R.id.nav_configuracion) {
            myFragment = new ConfiguracionSt1Fragment();
        } else if (id == R.id.nav_salida) {
            //myFragment = new TransaccionFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_base,myFragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
