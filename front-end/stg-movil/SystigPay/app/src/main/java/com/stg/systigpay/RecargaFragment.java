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
import android.view.ViewParent;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecargaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecargaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecargaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View detalleRecarga;

    private OnFragmentInteractionListener mListener;

    public RecargaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecargaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecargaFragment newInstance(String param1, String param2) {
        RecargaFragment fragment = new RecargaFragment();
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
        View vista = inflater.inflate(R.layout.fragment_recarga, container, false);
        Resources res = getResources();
        Spinner spMetodoRecarga = vista.findViewById(R.id.spMetodoPago);
        ViewStub layoutRecarga = vista.findViewById(R.id.form_recarga);
        layoutRecarga.setLayoutResource(R.layout.form_recaudos_recarga_efectivo);
        detalleRecarga = layoutRecarga.inflate();

        String[] METODOS_RECARGA = res.getStringArray(R.array.metodo_recarga_colombia);
        @SuppressLint("ResourceType") ArrayAdapter<String> arrayMetodosRecarga = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, METODOS_RECARGA);
        spMetodoRecarga.setAdapter(arrayMetodosRecarga);
        spMetodoRecarga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0) {
                    ViewStub viewStub = deflate(detalleRecarga);
                    viewStub.setLayoutResource(R.layout.form_recaudos_recarga_efectivo);
                    detalleRecarga = viewStub.inflate();

                    Spinner spEntidad = vista.findViewById(R.id.spEntidad);
                    String[] ENTIDAD_RECARGA_EFECTIVO = res.getStringArray(R.array.entidades_recarga_colombia_efectivo);
                    @SuppressLint("ResourceType") ArrayAdapter<String> arrayEntidadRecarga = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, ENTIDAD_RECARGA_EFECTIVO);
                    spEntidad.setAdapter(arrayEntidadRecarga);

                    TextView tvDescripcion = vista.findViewById(R.id.tv_descripcion_cuenta);
                    TextView tvNumero = vista.findViewById(R.id.tv_nro_cuenta);
                    TextView tvTitular = vista.findViewById(R.id.tv_titular_cuenta);
                    Button btnConfirma = vista.findViewById(R.id.button_confirmar);

                    btnConfirma.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RecargaConfirmaFragment nextFrag= new RecargaConfirmaFragment();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.contenedor_recarga, nextFrag)
                                    .commit();
                        }
                    });

                    spEntidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0){
                                tvDescripcion.setText("CUENTA DE AHORROS BANCOLOMBIA");
                                tvNumero.setText("675000125-68");
                                tvTitular.setText("JESUS J ALCALA P");
                            }else if (position == 1){
                                tvDescripcion.setText("CUENTA DE AHORROS BANCO DE BOGOTA");
                                tvNumero.setText("009524166");
                                tvTitular.setText("JESUS J ALCALA P");
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }else if(position==1){
                    ViewStub viewStub = deflate(detalleRecarga);
                    viewStub.setLayoutResource(R.layout.form_recaudos_recarga_tc);
                    detalleRecarga = viewStub.inflate();
                    Button btnConfirma = vista.findViewById(R.id.button_confirmar);

                    btnConfirma.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RecargaConfirmaFragment nextFrag= new RecargaConfirmaFragment();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.contenedor_recarga, nextFrag)
                                    .commit();
                        }
                    });
                }else if(position==2){
                    ViewStub viewStub = deflate(detalleRecarga);
                    viewStub.setLayoutResource(R.layout.form_recaudos_transferencia);
                    detalleRecarga = viewStub.inflate();

                    Button btnConfirma = vista.findViewById(R.id.button_confirmar);

                    btnConfirma.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RecargaConfirmaFragment nextFrag= new RecargaConfirmaFragment();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.contenedor_recarga, nextFrag)
                                    .commit();
                        }
                    });

                    Spinner spEntidad = vista.findViewById(R.id.spEntidad);
                    Spinner sptipoCliente= vista.findViewById(R.id.sptipoCliente);
                    Spinner sptipoDocumento= vista.findViewById(R.id.spTipoDocumento);

                    String[] ENTIDAD_RECARGA_EFECTIVO = res.getStringArray(R.array.entidades_recarga_colombia_transferencia);
                    String[] TIPO_CLIENTE_RECARGA_EFECTIVO = res.getStringArray(R.array.tipo_cliente_recarga_colombia_transferencia);
                    String[] TIPO_DOCU_RECARGA_EFECTIVO = res.getStringArray(R.array.documentos);
                    @SuppressLint("ResourceType") ArrayAdapter<String> arrayEntidadRecarga = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, ENTIDAD_RECARGA_EFECTIVO);
                    @SuppressLint("ResourceType") ArrayAdapter<String> arrayTipoClienteRecarga = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, TIPO_CLIENTE_RECARGA_EFECTIVO);
                    @SuppressLint("ResourceType") ArrayAdapter<String> arrayTipoDocuRecarga = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_spinner_dropdown_item, TIPO_DOCU_RECARGA_EFECTIVO);
                    spEntidad.setAdapter(arrayEntidadRecarga);
                    sptipoCliente.setAdapter(arrayTipoClienteRecarga);
                    sptipoDocumento.setAdapter(arrayTipoDocuRecarga);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return vista;
    }

    public static ViewStub deflate(View view) {
        ViewParent viewParent = view.getParent();
        if (viewParent != null && viewParent instanceof ViewGroup) {
            int index = ((ViewGroup) viewParent).indexOfChild(view);
            int inflatedId = view.getId();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            ((ViewGroup) viewParent).removeView(view);
            Context context = ((ViewGroup) viewParent).getContext();
            ViewStub viewStub = new ViewStub(context);
            viewStub.setInflatedId(inflatedId);
            viewStub.setLayoutParams(layoutParams);
            ((ViewGroup) viewParent).addView(viewStub, index);
            return viewStub;
        } else {
            throw new IllegalStateException("Inflated View has not a parent");
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
