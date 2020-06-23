package com.stg.systigpay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransaccionesAdapter extends RecyclerView.Adapter<TransaccionesAdapter.MyViewHolder> {
    private Transaccion[] transacciones;
    private OnTranaccionListener onTranaccionListener;

    public TransaccionesAdapter(Transaccion[] transacciones,OnTranaccionListener onTranaccionListener) {
        this.transacciones = transacciones;
        this.onTranaccionListener= onTranaccionListener;
    }

    public interface OnTranaccionListener {
        void onTransaccionClick(int posicion);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaccion, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view,this.onTranaccionListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.referencia.setText(transacciones[position].getReferencia());
        holder.descripcion.setText(transacciones[position].getDescripcion());
        holder.fecha.setText(transacciones[position].getFecha());
        holder.valor.setText(transacciones[position].getValor() + " " + transacciones[position].getMoneda());
    }

    @Override
    public int getItemCount() {
        return transacciones.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView referencia;
        public TextView descripcion;
        public TextView fecha;
        public TextView valor;
        public OnTranaccionListener onTransaccionListener;

        public MyViewHolder(@NonNull final View itemView, OnTranaccionListener onTranaccionListener) {
            super(itemView);
            this.referencia = itemView.findViewById(R.id.tv_referencia);
            this.descripcion = itemView.findViewById(R.id.tv_descripcion);
            this.fecha= itemView.findViewById(R.id.tv_fecha);
            this.valor= itemView.findViewById(R.id.tv_valor);
            this.onTransaccionListener = onTranaccionListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.onTransaccionListener.onTransaccionClick(getAdapterPosition());
        }
    }
}
