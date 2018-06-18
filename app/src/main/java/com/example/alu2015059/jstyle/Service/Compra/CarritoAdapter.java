package com.example.alu2015059.jstyle.Service.Compra;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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
import com.example.alu2015059.jstyle.Domain.Imagen;
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
            new_cantidad = itemView.findViewById(R.id.ci_ArticuloCantidad);
        }
    }

    @Override
    public CarritoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carrito_item, parent, false);
        return new CarritoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarritoAdapter.ViewHolder holder, final int position) {
        final SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(activity);
        boolean HAVE_IMAGE = false;
        if(listaArticulos == null) return;

        Bitmap imagen = null;

        List<Imagen> listaImagenes = new ArrayList<>();
        listaImagenes = SQLiteDBHelper.getAllImagenes();
        for (Imagen i : listaImagenes){
            if(listaArticulos.get(position).getCodigo().equalsIgnoreCase(i.getCodigo())){
                imagen = i.getBitmap();
                HAVE_IMAGE = true;
            }
        }

        String descripcion = listaArticulos.get(position).getDescripcion();
        Double precio = listaArticulos.get(position).getPrecio();
        final String codigo = listaArticulos.get(position).getCodigo();
        final int cantidad = listaArticulos.get(position).getCantidad();

        holder.articulo_cantidad.setText(String.valueOf(cantidad));
        holder.articulo_name.setText(descripcion);
        holder.articulo_price.setText(String.valueOf(precio));
        holder.articulo_code.setText(codigo);

        if(HAVE_IMAGE == true){
            holder.articulo_Image.setImageBitmap(imagen);
        }else {

        }

        holder.ibtn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cantS = new_cantidad.getText().toString();
                int cant = Integer.parseInt(cantS);
                List<Articulo> lista = new ArrayList<>();
                lista = SQLiteDBHelper.getAllArticulos();

                if(cant <= 0){
                    Toast.makeText(activity, "Introducir un numero mayor a 0",Toast.LENGTH_SHORT).show();
                    return;
                }

                for(Articulo articulo : lista){
                    if(articulo.getCodigo().equals(listaArticulos.get(position).getCodigo())){
                        int stock = articulo.getCantidad() - cant;
                        if(stock < 0){
                            Toast.makeText(activity, "Cantidad de " + articulo.getDescripcion() + " : " + articulo.getCantidad(),Toast.LENGTH_SHORT).show();
                            new_cantidad.setText(articulo.getCantidad().toString());
                            return;
                        }else {
                            SQLiteDBHelper.updateCantCarrito(listaArticulos.get(position), cant);
                            Toast.makeText(activity, "Carrito actualizado",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        holder.btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(activity);

                SQLiteDBHelper.deleteArticuloFromCarrito(listaArticulos.get(position));

                Toast.makeText(activity, "ARTICULO eliminado del carrito",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listaArticulos == null) return 0;
        return listaArticulos.size();
    }
}
