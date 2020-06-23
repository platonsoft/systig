package com.stg.systigpay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterPersonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterPersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterPersonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextInputEditText nombre;
    TextInputEditText apellido;
    Spinner documentos;
    TextInputEditText numeroDocumento;
    TextInputEditText fechaNac;
    TextInputEditText email;
    TextInputEditText telefono;


    private OnFragmentInteractionListener mListener;

    public RegisterPersonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterPersonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterPersonFragment newInstance(String param1, String param2) {
        RegisterPersonFragment fragment = new RegisterPersonFragment();
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
        View vista = inflater.inflate(R.layout.fragment_register_person, container, false);
        Resources res = getResources();

        String[] TIPODOCUMENTO = res.getStringArray(R.array.documentos);
        String[] PAISES = res.getStringArray(R.array.paises);
        @SuppressLint("ResourceType") ArrayAdapter<String> arrayDocumentos = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, TIPODOCUMENTO);
        @SuppressLint("ResourceType") ArrayAdapter<String> arrayPaises = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, PAISES);

        androidx.appcompat.widget.Toolbar toolbar = vista.findViewById(R.id.tool_registrar_persona);
        toolbar.setTitle(R.string.text_button_crear_persona);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorButtonText));
        toolbar.setNavigationIcon(R.drawable.left_arrow_32px);

        nombre = (TextInputEditText)vista.findViewById(R.id.nombresTxt);
        apellido = (TextInputEditText)vista.findViewById(R.id.apellidosTxt);
        documentos = vista.findViewById(R.id.spTipoDocumento);
        numeroDocumento = (TextInputEditText)vista.findViewById(R.id.numeroDocumentoTxt);
        fechaNac = (TextInputEditText)vista.findViewById(R.id.fechaNacTxt);
        email = (TextInputEditText)vista.findViewById(R.id.emailTxt);
        telefono = (TextInputEditText)vista.findViewById(R.id.telefonoTxt);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        documentos.setAdapter(arrayDocumentos);

        return vista;
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
