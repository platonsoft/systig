package com.alcala.systig.ltven;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alcala.systig.ltven.model.Transaccion;

import java.util.List;

public class ItemTransaccionAdapteer extends BaseAdapter {
    private Context context;
    private List<Transaccion> transacciones;

    public ItemTransaccionAdapteer(Context context, List<Transaccion> transacciones) {
        this.context = context;
        this.transacciones = transacciones;
    }

    @Override
    public int getCount() {
        return transacciones.size();
    }

    @Override
    public Object getItem(int position) {
        return transacciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return transacciones.get(position).getIdTransaccion();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.itemtransaccion,null);

        Transaccion transaccion = transacciones.get(position);

        TextView nro = (TextView)convertView.findViewById(R.id.txvNroTransaccion);
        TextView nombreBanco = (TextView)convertView.findViewById(R.id.txvNombreBanco);
        TextView beneficiario = (TextView)convertView.findViewById(R.id.txvBeneficiario);
        TextView montobase = (TextView)convertView.findViewById(R.id.txvMontoMonedaBase);
        TextView montotasa = (TextView)convertView.findViewById(R.id.txvTasa);
        TextView montofinal= (TextView)convertView.findViewById(R.id.txvMontoMonedaFinal);

        //ImageView logoBanco = (ImageView)convertView.findViewById(R.id.imgLogoBanco);
        //ImageView tipoOp = (ImageView)convertView.findViewById(R.id.imageTipoOp);


        nro.setText(transaccion.getCodigo());
        nombreBanco.setText(transaccion.getCliente().getCuenta().getNombreBanco());
        beneficiario.setText(transaccion.getCliente().getApellidos().toUpperCase() + ", " +  transaccion.getCliente().getNombres().toUpperCase());
        montobase.setText(transaccion.getMontoPagado().toString());
        montotasa.setText(transaccion.getTasa().toString());
        montofinal.setText(transaccion.getMontoRecibido().toString());

        //Faltaria referenciar la imagen del logo del banco si se va a cargar


        return convertView;
    }
}
