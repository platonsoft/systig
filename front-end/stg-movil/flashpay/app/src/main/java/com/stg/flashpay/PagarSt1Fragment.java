package com.stg.flashpay;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.stg.flashpay.datos.Pago;
import com.stg.flashpay.datos.TransaccionFlashPay;
import com.stg.flashpay.datos.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PagarSt1Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PagarSt1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PagarSt1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private IntentIntegrator scanQR;
    EditText textoNroCuenta;
    EditText textoMontoPago;
    EditText textoClaveTemp;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PagarSt1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PagarSt1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PagarSt1Fragment newInstance(String param1, String param2) {
        PagarSt1Fragment fragment = new PagarSt1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_pagar_st1, container, false);

        Button btn = (Button) vista.findViewById(R.id.button_start_scanQr);
        Button btn_pagar = (Button) vista.findViewById(R.id.button_realizar_pago);
        textoNroCuenta = (EditText) vista.findViewById(R.id.input_nro_cuenta_pago);
        textoMontoPago = (EditText) vista.findViewById(R.id.input__monto_pago);
        textoClaveTemp = (EditText) vista.findViewById(R.id.input_codigo_temporal);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRScan();
            }
        });
        btn_pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarPago();
            }
        });

        //new IntentIntegrator(this).initiateScan(); // `this` is the current Activity
        return vista;
    }

    private void startQRScan(){
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this); // `this` is the current Fragment;
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Lectura de Cuenta");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        //integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    private void enviarPago(){
        // Write a message to the database
        Usuario usuario = (new UsuariosHelper(getContext())).getDatosUsuario();
        TransaccionFlashPay pay = new TransaccionFlashPay();
        pay.setFecha(String.valueOf((new Date()).getTime()));
        pay.setUidReceptor(String.valueOf(textoNroCuenta.getText()));
        pay.setMontoOperacion(String.valueOf(textoMontoPago.getText()));
        String nrotrans = String.valueOf(Math.random()*pay.hashCode()+1).replace(".","").replace(",","");
        FirebaseDatabase.getInstance().getReference("usuarios").child(usuario.getCodigo()).child("transacciones")
                .child("TRPG"+nrotrans).setValue(pay)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Datos agregados correctamente", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "La transaccion fallo: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {

                Toast.makeText(this.getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONObject gson = new JSONObject(result.getContents());
                    textoNroCuenta.setText(gson.getString("cuenta"));
                    textoMontoPago.setText(gson.getString("monto"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(this.getActivity(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
