package com.stg.flashpay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.stg.flashpay.dummy.DummyContent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PagarSt0Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PagarSt0Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PagarSt0Fragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PagarSt0Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PagarSt0Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PagarSt0Fragment newInstance(String param1, String param2) {
        PagarSt0Fragment fragment = new PagarSt0Fragment();
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
        View vista = inflater.inflate(R.layout.fragment_pagar_st0, container, false);
        Button pagomanual = (Button) vista.findViewById(R.id.button_realizar_pago_manual);
        Button pagoqr= (Button) vista.findViewById(R.id.button_realizar_pago_qr);
        Button pagousuario = (Button) vista.findViewById(R.id.button_realizar_pago_lista);
        Button pagopendiente = (Button) vista.findViewById(R.id.button_realizar_pago_pendientes);
        pagomanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagaCuentaManual(v, 0);
            }
        });

        pagoqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagaCuentaManual(v, 1);
                Toast.makeText(getContext(),"Por QR",Toast.LENGTH_SHORT).show();
            }
        });

        pagousuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagaCuentaManual(v, 2);
                Toast.makeText(getContext(),"Por Usuario",Toast.LENGTH_SHORT).show();
            }
        });
        pagopendiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagaCuentaManual(v, 3);
                Toast.makeText(getContext(),"Por pendiente",Toast.LENGTH_SHORT).show();
            }
        });
        return vista;
    }

    private void pagaCuentaManual(View v, int opcion){
        Toast.makeText(getContext(),"Por cuenta",Toast.LENGTH_SHORT).show();
        Fragment myFragment = null;
        myFragment = new PagarSt1Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("opc", opcion);
        myFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_base,myFragment).commit();
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
