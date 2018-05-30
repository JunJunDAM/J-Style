package com.example.alu2015059.jstyle.Service.Compra;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.R;
import com.example.alu2015059.jstyle.Repository.SQLiteDBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alu2015059 on 28/05/2018.
 */

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {

    List<Articulo> listaArticulos = new ArrayList<>();
    AppCompatActivity activity;
    EditText new_cantidad;

    public CarritoAdapter(List<Articulo> listaArticulos, AppCompatActivity activity){
        super();
        this.listaArticulos = listaArticulos;
        this.activity = activity;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView articulo_Image;
        TextView articulo_name, articulo_price, articulo_code;
        EditText articulo_cantidad;
        Button btn_eliminar;
        ImageButton ibtn_save;

        public ViewHolder(View itemView){
            super(itemView);
            articulo_Image = itemView.findViewById(R.id.ci_ArticuloImage);
            articulo_name = itemView.findViewById(R.id.ci_ArticuloName);
            articulo_price = itemView.findViewById(R.id.ci_ArticuloPrice);
            articulo_cantidad = itemView.findViewById(R.id.ci_ArticuloCantidad);
            btn_eliminar = itemView.findViewById(R.id.ci_btn_Eliminar);
            articulo_code = itemView.findViewById(R.id.ci_ArticuloCode);
            ibtn_save = itemView.findViewById(R.id.ci_ibtn_saveCant);
        }
    }

    @Override
    public CarritoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carrito_item, parent, false);
        return new CarritoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarritoAdapter.ViewHolder holder, final int position) {

        if(listaArticulos == null) return;

        String descripcion = listaArticulos.get(position).getDescripcion();
        Double precio = listaArticulos.get(position).getPrecio();
        final String codigo = listaArticulos.get(position).getCodigo();
        final int cantidad = listaArticulos.get(position).getCantidad();

        holder.articulo_cantidad.setText(String.valueOf(cantidad));
        holder.articulo_name.setText(descripcion);
        holder.articulo_price.setText(String.valueOf(precio));
        holder.articulo_code.setText(codigo);

        /*holder.ibtn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(activity);
                SQLiteDatabase sqLiteDatabase = SQLiteDBHelper.getWritableDatabase();

                Editable cantS = new_cantidad.getText();
                int cant = Integer.valueOf(cantS);

                SQLiteDBHelper.updateCant(listaArticulos.get(position), cantS);

                Toast.makeText(activity, "Articulo eliminado del carrito",Toast.LENGTH_SHORT).show();

                sqLiteDatabase.close();
            }
        });*/

        holder.btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(activity);
                SQLiteDatabase sqLiteDatabase = SQLiteDBHelper.getWritableDatabase();

                SQLiteDBHelper.deleteArticuloFromCarrito(listaArticulos.get(position));

                Toast.makeText(activity, "Articulo eliminado del carrito",Toast.LENGTH_SHORT).show();

                sqLiteDatabase.close();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listaArticulos == null) return 0;
        return listaArticulos.size();
    }
}
