package com.stg.systigpay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stg.systigpay.objetos.ItemTienda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class TiendaAdapter extends RecyclerView.Adapter<TiendaAdapter.MyViewHolder> {
    private List<ItemTienda> itemsTiendaFiltrada;
    private List<ItemTienda> itemsTienda = new ArrayList<>();
    private TiendaAdapter.OnTiendaListener onTiendaListener;

    public TiendaAdapter(List<ItemTienda> itemsTiendaFiltrada, TiendaAdapter.OnTiendaListener onTiendaListener) {
        this.itemsTiendaFiltrada = itemsTiendaFiltrada;
        this.itemsTienda.addAll(itemsTiendaFiltrada);
        this.onTiendaListener= onTiendaListener;
    }

    public interface OnTiendaListener {
        void onTiendaClick(int posicion);
    }

    @NonNull
    @Override
    public TiendaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tienda_item, parent, false);
        TiendaAdapter.MyViewHolder viewHolder = new TiendaAdapter.MyViewHolder(view,this.onTiendaListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TiendaAdapter.MyViewHolder holder, int position) {
        holder.nombreItem.setText(itemsTiendaFiltrada.get(position).getNombreItem());
        holder.nombreTienda.setText(itemsTiendaFiltrada.get(position).getNombreTienda());
        holder.precio.setText("Precio: " + itemsTiendaFiltrada.get(position).getPrecioUnitario() + " IVA:(" + itemsTiendaFiltrada.get(position).getIVA() + ")");

        BigDecimal IVA = itemsTiendaFiltrada.get(position).getIVA().divide(new BigDecimal(100)).multiply(itemsTiendaFiltrada.get(position).getPrecioUnitario());
        BigDecimal descuento = itemsTiendaFiltrada.get(position).getDescuento().divide(new BigDecimal(100)).multiply(itemsTiendaFiltrada.get(position).getPrecioUnitario());
        BigDecimal total = itemsTiendaFiltrada.get(position).getPrecioUnitario().add(IVA).subtract(descuento);

        holder.total.setText("Total: " + total);
    }

    @Override
    public int getItemCount() {
        return itemsTiendaFiltrada.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView idItemTienda;
        public TextView nombreItem;
        public TextView nombreTienda;
        public TextView precio;
        public TextView total;
        public TiendaAdapter.OnTiendaListener onTiendaListener;

        public MyViewHolder(@NonNull final View itemView, TiendaAdapter.OnTiendaListener onTiendaListener) {
            super(itemView);
            this.nombreItem = itemView.findViewById(R.id.tv_nombre_item);
            this.nombreTienda = itemView.findViewById(R.id.tv_nombre_tienda);
            this.precio = itemView.findViewById(R.id.tv_precio);
            this.total = itemView.findViewById(R.id.tv_total);
            this.onTiendaListener = onTiendaListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.onTiendaListener.onTiendaClick(getAdapterPosition());
        }
    }

    public void filtro(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        itemsTiendaFiltrada.clear();

        if (charText.length()==0){
            itemsTiendaFiltrada.addAll(itemsTienda);
        }else{
            for (ItemTienda itemTienda : itemsTienda) {
                if (itemTienda.getNombreItem().toLowerCase(Locale.getDefault()).contains(charText)){
                    itemsTiendaFiltrada.add(itemTienda);
                }
            }
        }
        notifyDataSetChanged();
    }

}
