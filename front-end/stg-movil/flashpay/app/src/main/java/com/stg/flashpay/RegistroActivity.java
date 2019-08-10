package com.stg.flashpay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.stg.flashpay.datos.Usuario;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

public class RegistroActivity extends AppCompatActivity implements
        DatosPersonalesFragment.OnFragmentInteractionListener,
        DatosFinancierosFragment.OnFragmentInteractionListener,
        CredencialesFragment.OnFragmentInteractionListener,
        FotoValidarFragment.OnFragmentInteractionListener{

    private int steep = 1;
    private UsuariosHelper datosUsuario = new UsuariosHelper(this);;
    private DatosPersonalesFragment fr;
    private DatosFinancierosFragment fr2;
    private CredencialesFragment fr3;
    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button btnNext = (Button)findViewById(R.id.btn_sgte);

        cambioSteep();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambioSteep();
            }
        });
    }

    private void cambioSteep(){

        switch (steep){
            case 1:
                this.fr = new DatosPersonalesFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment,fr)
                        .addToBackStack(null)
                        .commit();
                steep+=1;
                break;
            case 2:
                TextInputEditText nombre = (TextInputEditText)this.fr.getView().findViewById(R.id.input_nombres);
                TextInputEditText apellido = (TextInputEditText)this.fr.getView().findViewById(R.id.input_apellidos);
                MaterialBetterSpinner tipoDocumento = (MaterialBetterSpinner) this.fr.getView().findViewById(R.id.spinner_tipo_documento);
                TextInputEditText numeroDocumento = (TextInputEditText)this.fr.getView().findViewById(R.id.input_nro_documento);
                TextInputEditText fechaNac = (TextInputEditText)this.fr.getView().findViewById(R.id.input_fecha_nac);
                TextInputEditText email = (TextInputEditText)this.fr.getView().findViewById(R.id.input_email);
                TextInputEditText telefono = (TextInputEditText)this.fr.getView().findViewById(R.id.input_phone);

                this.usuario.setNombres(String.valueOf(nombre.getText()));
                this.usuario.setApellidos(String.valueOf(apellido.getText()));
                this.usuario.setTipoDocumento(String.valueOf(tipoDocumento.getText()).trim());
                this.usuario.setNroDocumento(new BigInteger(String.valueOf(numeroDocumento.getText())));
                this.usuario.setFechaNacimiento(String.valueOf(fechaNac.getText()));
                this.usuario.setEmail(String.valueOf(email.getText()));
                this.usuario.setTelefono(String.valueOf(telefono.getText()));


                this.fr2 = new DatosFinancierosFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment,fr2)
                        .addToBackStack(null)
                        .commit();
                steep+=1;
                break;
            case 3:
                MaterialBetterSpinner pais = (MaterialBetterSpinner) this.fr2.getView().findViewById(R.id.spinner_pais);
                MaterialBetterSpinner entidadBancaria = (MaterialBetterSpinner) this.fr2.getView().findViewById(R.id.spinner_entidad_bancaria);
                TextInputEditText numeroCuentaBancaria = (TextInputEditText)this.fr2.getView().findViewById(R.id.input_nro_cuenta);
                MaterialBetterSpinner tipoCuentaBancaria = (MaterialBetterSpinner) this.fr2.getView().findViewById(R.id.spinner_tipo_cuenta);

                this.usuario.setPais(String.valueOf(pais.getText()));
                this.usuario.setEntidadBancaria(String.valueOf(entidadBancaria.getText()));
                this.usuario.setNumeroCuentaBancaria(String.valueOf(numeroCuentaBancaria.getText()).trim());
                this.usuario.setTipoCuentaBancaria(String.valueOf(tipoCuentaBancaria.getText()));

                this.fr3 = new CredencialesFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment,fr3)
                        .addToBackStack(null)
                        .commit();
                steep+=1;
                break;
            case 4:
                TextInputEditText nombreUsuario= (TextInputEditText)this.fr3.getView().findViewById(R.id.input_usuario);
                TextInputEditText claveAcceso = (TextInputEditText)this.fr3.getView().findViewById(R.id.input_clave);
                TextInputEditText pinSeguridad = (TextInputEditText)this.fr3.getView().findViewById(R.id.input_pin);

                this.usuario.setUsuario(String.valueOf(nombreUsuario.getText()));
                this.usuario.setClave(String.valueOf(claveAcceso.getText()).trim());
                this.usuario.setPin(Integer.valueOf(String.valueOf(pinSeguridad.getText())));

                Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Bundle args = new Bundle();
                args.putByteArray("foto", byteArray);

                this.usuario.setFotoX64(Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT));
                datosUsuario.insertaUsuario(this.usuario);

                FotoValidarFragment fr3=new FotoValidarFragment();
                fr3.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment,fr3)
                        .addToBackStack(null)
                        .commit();
                System.out.println(this.usuario.toString());
            }
        //steep+=1;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
