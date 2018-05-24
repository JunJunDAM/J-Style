package com.example.alu2015059.jstyle.Service.MostrarArticulos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alu2015059 on 23/05/2018.
 */

public class ArticulosAdapter extends RecyclerView.Adapter<ArticulosAdapter.ViewHolder>{

    private Articulo articulo;

    List<Articulo> listaArticulos = new ArrayList<>();

    /*public ArticulosAdapter(List<Articulo> listaArticulos){
        super();
        this.listaArticulos = listaArticulos;
    }*/

    public ArticulosAdapter(Articulo articulo){
        super();
        this.articulo = articulo;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView articulo_Image;
        TextView articulo_name, articulo_price, articulo_cant;
        Button btn_anadirCesta;

        public ViewHolder(View itemView){
            super(itemView);
            articulo_Image = itemView.findViewById(R.id.ai_ArticuloImage);
            articulo_name = itemView.findViewById(R.id.ai_ArticuloName);
            articulo_cant = itemView.findViewById(R.id.ai_ArticuloCant);
            articulo_price = itemView.findViewById(R.id.ai_ArticuloPrice);
        }
    }

    @Override
    public ArticulosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.articulo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(articulo == null) return;
        //if (listaArticulos == null) return;

        holder.articulo_name.setText(articulo.getDescripcion());
        holder.articulo_price.setText(String.valueOf(articulo.getPrecio()));
        holder.articulo_cant.setText(articulo.getCantidad());

        /*for(Articulo a : listaArticulos){
            holder.articulo_name.setText(a.getDescripcion());
            //holder.articulo_Image;
            holder.articulo_price.setText(String.valueOf(a.getPrecio()));
            holder.articulo_cant.setText(String.valueOf(a.getCantidad()));
        }*/
    }

    @Override
    public int getItemCount() {
        /*if(listaArticulos == null) return 0;
        return listaArticulos.size();*/
        if(articulo == null) return 0;
        return listaArticulos.size();
    }
}
