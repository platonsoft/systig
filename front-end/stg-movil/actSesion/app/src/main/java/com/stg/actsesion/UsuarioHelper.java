package com.stg.flashpay;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UsuarioHelper extends SQLiteOpenHelper {

    private Context thisContext;
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    public static final String NOMBRE_DB="DBUsuario";
    public static final String NOMBRE_TABLA="Usuario";

    private String sqlCreate = "CREATE TABLE IF NOT EXISTS "+ NOMBRE_TABLA +" (" +
            "codigo TEXT, " +
            "nombres TEXT, " +
            "apellidos TEXT, " +
            "tipo_documento TEXT, " +
            "nro_documento TEXT, " +
            "fecha_nacimiento TEXT, " +
            "email TEXT, " +
            "telefono TEXT, " +
            "pais TEXT, " +
            "entidad_bancaria TEXT, " +
            "numero_cuenta_bancaria TEXT, " +
            "tipo_cuenta_bancaria TEXT, " +
            "nombre_usuario TEXT, " +
            "clave_usuario TEXT, " +
            "pin_usuario TEXT, " +
            "fotox64 TEXT)";

    public UsuarioHelper(@Nullable Context context) {
        super(context, NOMBRE_DB, null, 1);
        this.thisContext = context;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Usuario");
        db.execSQL(sqlCreate);
    }

    public boolean insertaUsuarioFirebase(final Usuario usuario) {
        return mAuth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getClave())
                .addOnCompleteListener((Activity) thisContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "creacionUsuario:Correcto");
                            usuario.setCodigo(mAuth.getCurrentUser().getUid());
                            FirebaseDatabase.getInstance().getReference("usuarios")
                                    .child(mAuth.getCurrentUser().getUid()).setValue(usuario)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            insertarUsuarioBD(usuario, mAuth.getCurrentUser().getUid());
                                            Toast.makeText(thisContext, "Se ha registrado correctamente.", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(thisContext, "La transaccion fallo: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {
                            Log.w(TAG, "creacionUsuario:Fallido", task.getException());
                            Toast.makeText(thisContext, "Error al crear el usuario.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).isSuccessful();
    }

    private Boolean insertarUsuarioBD(Usuario usuario, String uid){
        ContentValues nuevoRegistro = new ContentValues();

        nuevoRegistro.put("codigo",uid);
        nuevoRegistro.put("nombres",usuario.getNombres());
        nuevoRegistro.put("apellidos",usuario.getApellidos());
        nuevoRegistro.put("tipo_documento",usuario.getTipoDocumento());
        nuevoRegistro.put("nro_documento",usuario.getNroDocumento());
        nuevoRegistro.put("fecha_nacimiento",usuario.getFechaNacimiento());
        nuevoRegistro.put("email",usuario.getEmail());
        nuevoRegistro.put("telefono",usuario.getTelefono());
        nuevoRegistro.put("pais",usuario.getPais());
        nuevoRegistro.put("entidad_bancaria",usuario.getEntidadBancaria());
        nuevoRegistro.put("numero_cuenta_bancaria",usuario.getNumeroCuentaBancaria());
        nuevoRegistro.put("tipo_cuenta_bancaria",usuario.getTipoCuentaBancaria());
        nuevoRegistro.put("nombre_usuario",usuario.getUsuario());
        nuevoRegistro.put("clave_usuario",usuario.getClave());
        nuevoRegistro.put("pin_usuario",usuario.getPin());
        nuevoRegistro.put("fotox64",usuario.getFotoX64());

        SQLiteDatabase db = this.getWritableDatabase();

        System.out.println("Creando registro en BD");
        db.insert(NOMBRE_TABLA,null,nuevoRegistro);
        System.out.println("Se ha creado el registro correctamente");
        return true;
    }

    public Usuario getDatosUsuario() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db!=null){
            Cursor registro = db.rawQuery(" SELECT * FROM " + NOMBRE_TABLA, null);
            if(registro.moveToFirst()){
                Usuario usuario = new Usuario();
                usuario.setCodigo(registro.getString(registro.getColumnIndex("codigo")));
                usuario.setNombres(registro.getString(registro.getColumnIndex("nombres")));
                usuario.setApellidos(registro.getString(registro.getColumnIndex("apellidos")));
                usuario.setTipoDocumento(registro.getString(registro.getColumnIndex("tipo_documento")));
                usuario.setNroDocumento(registro.getString(registro.getColumnIndex("nro_documento")));
                usuario.setFechaNacimiento(registro.getString(registro.getColumnIndex("nro_documento")));
                usuario.setEmail(registro.getString(registro.getColumnIndex("email")));
                usuario.setTelefono(registro.getString(registro.getColumnIndex("telefono")));
                usuario.setPais(registro.getString(registro.getColumnIndex("pais")));
                usuario.setEntidadBancaria(registro.getString(registro.getColumnIndex("entidad_bancaria")));
                usuario.setNumeroCuentaBancaria(registro.getString(registro.getColumnIndex("numero_cuenta_bancaria")));
                usuario.setTipoCuentaBancaria(registro.getString(registro.getColumnIndex("tipo_cuenta_bancaria")));
                usuario.setUsuario(registro.getString(registro.getColumnIndex("nombre_usuario")));
                usuario.setClave(registro.getString(registro.getColumnIndex("clave_usuario")));
                usuario.setPin(registro.getInt(registro.getColumnIndex("pin_usuario")));
                usuario.setFotoX64(registro.getString(registro.getColumnIndex("fotox64")));
                return usuario;
            }
        }
        return null;
    }

    public Boolean inicioSesion(String usuario, String clave){
        return mAuth.signInWithEmailAndPassword(usuario, clave)
                .addOnCompleteListener((Activity) thisContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "InicioSesion:Correcto");
                            Toast.makeText(thisContext, "Bienvenido.", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "InicioSesion:Fallido", task.getException());
                            Toast.makeText(thisContext, "Acceso Denegado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).isSuccessful();
    }

}
