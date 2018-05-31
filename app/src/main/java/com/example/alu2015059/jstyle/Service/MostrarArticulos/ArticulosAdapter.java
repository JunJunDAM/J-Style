package com.example.alu2015059.jstyle.Service.MostrarArticulos;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.R;
import com.example.alu2015059.jstyle.Repository.CompraDB;
import com.example.alu2015059.jstyle.Repository.SQLiteDBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alu2015059 on 23/05/2018.
 */

public class ArticulosAdapter extends RecyclerView.Adapter<ArticulosAdapter.ViewHolder>{

    List<Articulo> listaArticulos = new ArrayList<>();
    AppCompatActivity activity;

    public ArticulosAdapter(List<Articulo> listaArticulos, AppCompatActivity activity){
        super();
        this.listaArticulos = listaArticulos;
        this.activity = activity;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView articulo_Image;
        TextView articulo_name, articulo_price, articulo_code, articulo_cantidad;
        Button btn_anadirCesta;

        public ViewHolder(View itemView){
            super(itemView);
            articulo_Image = itemView.findViewById(R.id.ci_ArticuloImage);
            articulo_name = itemView.findViewById(R.id.ci_ArticuloName);
            articulo_price = itemView.findViewById(R.id.ai_ArticuloPrice);
            articulo_cantidad = itemView.findViewById(R.id.ai_ArticuloCantidad);
            btn_anadirCesta = itemView.findViewById(R.id.ci_btn_Eliminar);
            articulo_code = itemView.findViewById(R.id.ci_ArticuloCode);
        }
    }

    @Override
    public ArticulosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.articulo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(listaArticulos == null) return;

        String descripcion = listaArticulos.get(position).getDescripcion();
        Double precio = listaArticulos.get(position).getPrecio();
        String codigo = listaArticulos.get(position).getCodigo();
        int cantidad = listaArticulos.get(position).getCantidad();

        if(cantidad == 0){
            holder.articulo_cantidad.setText(String.valueOf(cantidad));
            holder.articulo_name.setText(descripcion);
            holder.articulo_price.setText(String.valueOf(precio));
            holder.articulo_code.setText(codigo);

            holder.btn_anadirCesta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, "Articulo agotado",Toast.LENGTH_SHORT).show();
                }
            });

            SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(activity);
            SQLiteDBHelper.deleteArticulo(listaArticulos.get(position));

        }else {
            holder.articulo_cantidad.setText(String.valueOf(cantidad));
            holder.articulo_name.setText(descripcion);
            holder.articulo_price.setText(String.valueOf(precio));
            holder.articulo_code.setText(codigo);

            holder.btn_anadirCesta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(activity);
                    listaArticulos.get(position).setCantidad(1);
                    SQLiteDBHelper.insertCompra(listaArticulos.get(position));
                    Toast.makeText(activity, "Articulo añadido a la cesta",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(listaArticulos == null) return 0;
        return listaArticulos.size();
    }
}
