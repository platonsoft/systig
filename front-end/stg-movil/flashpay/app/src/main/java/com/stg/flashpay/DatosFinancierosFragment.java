package com.stg.flashpay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DatosFinancierosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DatosFinancierosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosFinancierosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String[] ENTIDADES_BANCARIAS = {"   BANCOLOMBIA", "   BANCO DE BOGOTA", "   DAVIVIENDA"};
    String[] TIPO_CUENTA = {"   AHORROS", "   CORRIENTE"};
    String[] PAISES = {"   COLOMBIA", "   PERU", "   CHILE", "   ARGENTINA", "   PANAMA", "   VENEZUELA"};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DatosFinancierosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatosFinancierosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatosFinancierosFragment newInstance(String param1, String param2) {
        DatosFinancierosFragment fragment = new DatosFinancierosFragment();
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
        final View vista = inflater.inflate(R.layout.fragment_datos_financieros, container, false);
        try {
            @SuppressLint("ResourceType") ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, ENTIDADES_BANCARIAS);
            MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)vista.findViewById(R.id.spinner_entidad_bancaria);
            materialDesignSpinner.setAdapter(arrayAdapter);

            @SuppressLint("ResourceType") ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, TIPO_CUENTA);
            MaterialBetterSpinner materialDesignSpinner2 = (MaterialBetterSpinner)vista.findViewById(R.id.spinner_tipo_cuenta);
            materialDesignSpinner2.setAdapter(arrayAdapter2);

            @SuppressLint("ResourceType") ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, PAISES);
            MaterialBetterSpinner materialDesignSpinner3 = (MaterialBetterSpinner)vista.findViewById(R.id.spinner_pais);
            materialDesignSpinner3.setAdapter(arrayAdapter3);

        }catch (Exception e){
            e.printStackTrace();
        }
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
