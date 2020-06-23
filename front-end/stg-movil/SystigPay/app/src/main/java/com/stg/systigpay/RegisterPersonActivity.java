package com.stg.systigpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.stg.systigpay.objetos.Usuario;
import com.stg.systigpay.remote.APIUtils;
import com.stg.systigpay.servicios.UsuarioService;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RegisterPersonActivity extends AppCompatActivity implements RegisterPersonFragment.OnFragmentInteractionListener{

    TextInputEditText nombresPerson;
    TextInputEditText apellidoPerson;
    Spinner tipoDocPerson;
    TextInputEditText nroDocPerson;
    TextInputEditText fechaNacPerson;
    TextInputEditText telefMovilPerson;
    TextInputEditText direccionPerson;
    TextInputEditText emailPerson;
    TextInputEditText codigoPostalPerson;
    FloatingActionButton botonFoto;
    ImageView fotoPerson;

    Usuario usuario = new Usuario();
    UsuarioService usuarioService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_person);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.tool_registrar_persona);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorButtonText));
        toolbar.setNavigationIcon(R.drawable.left_arrow_32px);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        nombresPerson = findViewById(R.id.nombresTxt);
        apellidoPerson = findViewById(R.id.apellidosTxt);
        tipoDocPerson = findViewById(R.id.spTipoDocumento);
        nroDocPerson = findViewById(R.id.numeroDocumentoTxt);
        fechaNacPerson = findViewById(R.id.fechaNacTxt);
        telefMovilPerson = findViewById(R.id.telefonoTxt);
        direccionPerson = findViewById(R.id.direccionTxt);
        emailPerson = findViewById(R.id.emailTxt);
        codigoPostalPerson = findViewById(R.id.codigoPostalTxt);
        fotoPerson = findViewById(R.id.fotoPersona);
        botonFoto = findViewById(R.id.bontoFotoAct);

        usuarioService = APIUtils.getUserService();

        botonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // bm is the bitmap object

            usuario.setFoto64(Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT));
            fotoPerson.setImageBitmap(imageBitmap);
        }
    }

    private boolean validPersona(){
        boolean isValido= true;


        if (nombresPerson.getText().toString().trim().isEmpty()){
            nombresPerson.setError("Debe colocar un nombre valido");
            isValido = false;
        }else{
            usuario.setNombres(nombresPerson.getText().toString());
        }

        if (apellidoPerson.getText().toString().trim().isEmpty()){
            apellidoPerson.setError("Debe colocar un apellido valido");
            isValido = false;
        }else{
            usuario.setApellidos(apellidoPerson.getText().toString());
        }

        if (tipoDocPerson.getSelectedItem().toString().trim().isEmpty()){
            TextView errorText = (TextView)tipoDocPerson.getSelectedView();
            errorText.setError("Debes seleccionar un tipo de documento valido");
            isValido = false;
        }else{
            usuario.setTipoIdentificacion(tipoDocPerson.getSelectedItem().toString().toUpperCase());
        }

        if (nroDocPerson.getText().toString().trim().isEmpty()){
            nroDocPerson.setError("Debe colocar un Numero de documento valido");
            isValido = false;
        }else{
            usuario.setNroIdentificacion(nroDocPerson.getText().toString());
        }

        if (fechaNacPerson.getText().toString().trim().isEmpty()){
            fechaNacPerson.setError("Debe colocar su fecha de nacimiento");
            isValido = false;
        }

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fechaNac = LocalDate.parse(fechaNacPerson.getText(), fmt);
                LocalDate ahora = LocalDate.now();

                Period periodo = Period.between(fechaNac, ahora);

                if (periodo.getYears()<18){
                    fechaNacPerson.setError("Debe ser mayor de 18 a;os");
                    isValido = false;
                }else{
                    usuario.setFechaNacimiento(fechaNacPerson.getText().toString());
                }
            }
        }catch (Exception e){
            fechaNacPerson.setError("Verificar la fecha de nacimiento");
            isValido = false;
        }

        if (telefMovilPerson.getText().toString().trim().isEmpty()){
            telefMovilPerson.setError("Debe colocar un numero de telefono valido");
            isValido = false;
        }else{
            usuario.setTelefonoMovil(telefMovilPerson.getText().toString());
        }

        if (direccionPerson.getText().toString().trim().isEmpty()){
            direccionPerson.setError("Debe colocar una direccion valida");
            isValido = false;
        }else{
            usuario.setDireccion(direccionPerson.getText().toString());
        }

        if (emailPerson.getText().toString().trim().isEmpty()){
            emailPerson.setError("Debe colocar un email valido");
            isValido = false;
        }else{
            usuario.setEmail(emailPerson.getText().toString());
        }

        if (codigoPostalPerson.getText().toString().trim().isEmpty()){
            codigoPostalPerson.setError("Debe colocar un codigo postal valido");
            isValido = false;
        }else{
            usuario.setCodigoPostal(emailPerson.getText().toString());
        }

        if (usuario.getFoto64()== null){
            Toast.makeText(RegisterPersonActivity.this, "Debe incluir la foto", Toast.LENGTH_SHORT).show();
            isValido = false;
        }

        return isValido;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_item_guarda_persona:
                if (validPersona()){
                    agregarUsuario(this.usuario);
                    Log.d(TAG, "onGuardaPersona: Persona Guardada");
                }else{
                    Log.d(TAG, "onGuardaPersona: Persona No Valida");
                }
                return true;
            default:
                Log.d(TAG, "onOptionsItemSelected: Otraaa: " + nombresPerson.getText());
                return super.onOptionsItemSelected(item);
        }
    }

    public void agregarUsuario(Usuario u){
        Call<Usuario> call = usuarioService.addUsuario(u);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegisterPersonActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(RegisterPersonActivity.this, "User No Creado!", Toast.LENGTH_SHORT).show();
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_header_crear_persona, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
