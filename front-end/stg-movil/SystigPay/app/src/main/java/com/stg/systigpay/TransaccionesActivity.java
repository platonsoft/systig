package com.stg.systigpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import static android.content.ContentValues.TAG;

public class TransaccionesActivity extends AppCompatActivity
        implements HomeTransaccionesFragment.OnFragmentInteractionListener,
        TiendaFragment.OnFragmentInteractionListener,
        NotificacionesFragment.OnFragmentInteractionListener, SearchView.OnQueryTextListener{

    private Fragment nextFrag = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacciones);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.tool_menu_home);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorButtonText));
        setSupportActionBar(toolbar);

        com.google.android.material.navigation.NavigationView navigationView = findViewById(R.id.NavMenuSide);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        BottomNavigationView navPie = findViewById(R.id.navigationViewPie);

        navPie.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.pieResumen:
                        toolbar.getMenu().findItem(R.id.search).setVisible(false);
                        nextFrag= new HomeTransaccionesFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedor_home, nextFrag)
                                .commit();
                        return true;
                    case R.id.pieTienda:
                        toolbar.getMenu().findItem(R.id.search).setVisible(true);
                        nextFrag= new TiendaFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedor_home, nextFrag)
                                .commit();
                        return true;
                    case R.id.pieNotificaciones:
                        toolbar.getMenu().findItem(R.id.search).setVisible(false);
                        nextFrag= new NotificacionesFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedor_home, nextFrag)
                                .commit();
                        return true;
                }
                return false;
            }
        });


    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent detail;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_toggle_home);
        switch (menuItem.getItemId()){
            case R.id.nav_item_recargas:
                drawer.closeDrawer(GravityCompat.END);
                detail = new Intent(this, RecargaActivity.class);
                this.startActivity(detail);
                return true;
            case R.id.nav_item_transferir:
                drawer.closeDrawer(GravityCompat.END);
                detail = new Intent(this, TransferirActivity.class);
                this.startActivity(detail);
                return true;
            case R.id.nav_item_liberar:
                drawer.closeDrawer(GravityCompat.END);
                detail = new Intent(this, LiberarActivity.class);
                this.startActivity(detail);
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: OOOOk");
        switch (item.getItemId()) {
            case R.id.button_menu_sidebar:
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_toggle_home);
                if (drawer.isDrawerOpen(GravityCompat.END)){
                    drawer.closeDrawer(GravityCompat.END);
                }else{
                    drawer.openDrawer(GravityCompat.END);
                }
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
        getMenuInflater().inflate(R.menu.menu_header_home, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo( new ComponentName(this, TransaccionesActivity.class)));
        //searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);


        EditText searchEditText = (EditText) searchView.findViewById(R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorIcons));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorDivider));

        ImageView imageView = searchView.findViewById(R.id.search_close_btn);
        imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.delete_sign_26px));

        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)){
            ((TiendaFragment)nextFrag).tiendaAdapter.filtro("");
        }else{
            ((TiendaFragment)nextFrag).tiendaAdapter.filtro(newText);
        }
        return false;
    }
}
