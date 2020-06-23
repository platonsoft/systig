package com.stg.systigpay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stg.systigpay.objetos.Notificacion;

import java.text.SimpleDateFormat;

public class NotificacionesAdapter extends RecyclerView.Adapter<NotificacionesAdapter.MyViewHolder> {
    private Notificacion[] notificacions;
    private NotificacionesAdapter.OnNotificacionListener onNotificacionListener;

    public NotificacionesAdapter(Notificacion[] notificacions, NotificacionesAdapter.OnNotificacionListener onNotificacionListener) {
        this.notificacions = notificacions;
        this.onNotificacionListener = onNotificacionListener;
    }

    public interface OnNotificacionListener {
        void onNotificacionClick(int posicion);
    }

    @NonNull
    @Override
    public NotificacionesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notificacion, parent, false);
        NotificacionesAdapter.MyViewHolder viewHolder = new NotificacionesAdapter.MyViewHolder(view,this.onNotificacionListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String outputPattern = "dd/MM/yyyy hh:mm:ss";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        holder.titulo.setText(notificacions[position].getTitulo());
        holder.descripcion.setText(notificacions[position].getDescripcion());
        holder.fecha.setText(outputFormat.format(notificacions[position].getFechaRegistro()));

        if (notificacions[position].getEstado()){
            holder.estatus.setImageResource(R.drawable.bell_on_48px);
        }else{
            holder.estatus.setImageResource(R.drawable.bell_off_48px);
        }
    }

    @Override
    public int getItemCount() {
        return notificacions.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView titulo;
        public TextView descripcion;
        public TextView fecha;
        public ImageView estatus;
        public NotificacionesAdapter.OnNotificacionListener onNotificacionListener;

        public MyViewHolder(@NonNull final View itemView, NotificacionesAdapter.OnNotificacionListener onNotificacionListener) {
            super(itemView);
            this.titulo = itemView.findViewById(R.id.tv_titulo);
            this.descripcion = itemView.findViewById(R.id.tv_descripcion);
            this.fecha= itemView.findViewById(R.id.tv_fecha);
            this.estatus= itemView.findViewById(R.id.img_campana);
            this.onNotificacionListener = onNotificacionListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.onNotificacionListener.onNotificacionClick(getAdapterPosition());
        }
    }
}