package com.stg.systigpay;

import android.app.ActionBar;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeTransaccionesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeTransaccionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeTransaccionesFragment extends Fragment implements TransaccionesAdapter.OnTranaccionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private TransaccionesAdapter transaccionesAdapter;
    ScrollView scrollView;

    private OnFragmentInteractionListener mListener;

    public HomeTransaccionesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeTransaccionesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeTransaccionesFragment newInstance(String param1, String param2) {
        HomeTransaccionesFragment fragment = new HomeTransaccionesFragment();
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
        View vista = inflater.inflate(R.layout.fragment_home_transacciones, container, false);

        mRecyclerView = vista.findViewById(R.id.recic_v_lista_transacciones);
        scrollView = vista.findViewById(R.id.scroll_home);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);

        Transaccion transaccion = new Transaccion();
        transaccion.setReferencia("00000");
        transaccion.setDescripcion("Transaccion de prueba ");
        transaccion.setFecha("01/01/2020-00:00:00");
        transaccion.setValor("100,000.00");
        transaccion.setMoneda("$COL");

        Transaccion transaccion1 = new Transaccion();
        transaccion1.setReferencia("00001");
        transaccion1.setDescripcion("Transaccion de prueba 1");
        transaccion1.setFecha("01/01/2020-00:00:00");
        transaccion1.setValor("100,000.00 $COL");
        transaccion1.setMoneda("$COL");

        Transaccion transaccion2 = new Transaccion();
        transaccion2.setReferencia("00002");
        transaccion2.setDescripcion("Transaccion de prueba 2");
        transaccion2.setFecha("01/01/2020-00:00:00");
        transaccion2.setValor("100,000.00 $COL");
        transaccion2.setMoneda("$COL");

        Transaccion transaccion3 = new Transaccion();
        transaccion3.setReferencia("00000");
        transaccion3.setDescripcion("Transaccion de prueba ");
        transaccion3.setFecha("01/01/2020-00:00:00");
        transaccion3.setValor("100,000.00 $COL");
        transaccion3.setMoneda("$COL");

        Transaccion transaccion4 = new Transaccion();
        transaccion4.setReferencia("00000");
        transaccion4.setDescripcion("Transaccion de prueba ");
        transaccion4.setFecha("01/01/2020-00:00:00");
        transaccion4.setValor("100,000.00 $COL");
        transaccion4.setMoneda("$COL");


        Transaccion transaccion5 = new Transaccion();
        transaccion5.setReferencia("00000");
        transaccion5.setDescripcion("Transaccion de prueba ");
        transaccion5.setFecha("01/01/2020-00:00:00");
        transaccion5.setValor("100,000.00 $COL");
        transaccion5.setMoneda("$COL");

        Transaccion transaccion6 = new Transaccion();
        transaccion6.setReferencia("00000");
        transaccion6.setDescripcion("Transaccion de prueba ");
        transaccion6.setFecha("01/01/2020-00:00:00");
        transaccion6.setValor("100,000.00 $COL");
        transaccion6.setMoneda("$COL");

        transaccionesAdapter = new TransaccionesAdapter(new Transaccion[]{transaccion, transaccion1, transaccion2,transaccion3, transaccion4, transaccion5, transaccion6},this);
        mRecyclerView.setAdapter(transaccionesAdapter);
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

    @Override
    public void onTransaccionClick(int posicion) {

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
