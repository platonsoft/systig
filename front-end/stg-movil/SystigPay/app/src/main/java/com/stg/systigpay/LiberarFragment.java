package com.stg.systigpay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LiberarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LiberarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiberarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LiberarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LiberarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LiberarFragment newInstance(String param1, String param2) {
        LiberarFragment fragment = new LiberarFragment();
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
        View vista = inflater.inflate(R.layout.fragment_liberar, container, false);

        Resources res = getResources();
        Spinner spEntidad = vista.findViewById(R.id.spEntidad);
        Spinner spTipoCuentas = vista.findViewById(R.id.spTipoCuenta);

        String[] ENTIDADES = res.getStringArray(R.array.entidades_recarga_colombia_transferencia);
        String[] TIPOS_CUENTA_BANCARIA = res.getStringArray(R.array.tipos_cuenta_bancaria);
        @SuppressLint("ResourceType") ArrayAdapter<String> arrayEntidades = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, ENTIDADES);
        @SuppressLint("ResourceType") ArrayAdapter<String> arrayTiposCuenta = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, TIPOS_CUENTA_BANCARIA);

        spEntidad.setAdapter(arrayEntidades);
        spTipoCuentas.setAdapter(arrayTiposCuenta);

        Button btnConfirma = vista.findViewById(R.id.button_confirmar);

        btnConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiberarResumenFragment nextFrag= new LiberarResumenFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedor_liberar, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });

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
