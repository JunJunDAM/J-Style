package com.example.alu2015059.jstyle.Service.MostrarArticulos;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.Domain.Imagen;
import com.example.alu2015059.jstyle.R;
import com.example.alu2015059.jstyle.Repository.Database;
import com.example.alu2015059.jstyle.Repository.SQLiteDBHelper;
import com.example.alu2015059.jstyle.RepositoryTesting.ArticulosBBDD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alu2015059 on 23/05/2018.
 */

public class ArticulosAdapter extends RecyclerView.Adapter<ArticulosAdapter.ViewHolder> implements LoaderManager.LoaderCallbacks<Cursor>{

    List<Articulo> listaArticulos = new ArrayList<>();
    AppCompatActivity activity;

    ArticuloCursorAdapter mAdapter;

    public ArticulosAdapter(List<Articulo> listaArticulos, AppCompatActivity activity){
        super();
        this.listaArticulos = listaArticulos;
        this.activity = activity;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String columnas[] = new String[]{
                Database.ARTICULO.DESCRIPCION,
                Database.ARTICULO.CODIGO,
                Database.ARTICULO.CANTIDAD,
                Database.ARTICULO.SEXO,
                Database.ARTICULO.PRECIO
        };
        Uri baseuri = Database.ARTICULO.CONTENT_URI;
        String selection = null;
        return new CursorLoader(activity, baseuri, columnas, selection, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Uri uriBase = Uri.parse("content://"+ Database.AUTHORITY+"/ARTICULOS");
        data.setNotificationUri(activity.getContentResolver(), uriBase);
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
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

    public class ArticuloCursorAdapter extends CursorAdapter{

        public ArticuloCursorAdapter(Context context){super(context, null, false);}

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.articulo_item, parent, false);
            bindView(view, context, cursor);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            String descripcion = cursor.getString(cursor.getColumnIndex(Database.ARTICULO.DESCRIPCION));
            Double precio = cursor.getDouble(cursor.getColumnIndex(Database.ARTICULO.PRECIO));
            String codigo = cursor.getString(cursor.getColumnIndex(Database.ARTICULO.CODIGO));
            int cantidad = cursor.getInt(cursor.getColumnIndex(Database.ARTICULO.CANTIDAD));

            TextView tv_descripcion = view.findViewById(R.id.ci_ArticuloName);
            tv_descripcion.setText(descripcion);
            TextView tv_precio = view.findViewById(R.id.ai_ArticuloPrice);
            tv_precio.setText(String.valueOf(precio));
            TextView tv_codigo = view.findViewById(R.id.ci_ArticuloCode);
            tv_codigo.setText(codigo);
            TextView tv_cantidad = view.findViewById(R.id.ai_ArticuloCantidad);
            tv_cantidad.setText(String.valueOf(cantidad));
            view.setTag(codigo);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(activity);
        if(listaArticulos == null) return;

        Bitmap imagen = null;

        List<Imagen> listaImagenes = new ArrayList<>();
        listaImagenes = SQLiteDBHelper.getAllImagenes();
        for (Imagen i : listaImagenes){
            if(listaArticulos.get(position).getCodigo().equalsIgnoreCase(i.getCodigo())){
                imagen = i.getBitmap();
            }
        }


        String descripcion = listaArticulos.get(position).getDescripcion();
        Double precio = listaArticulos.get(position).getPrecio();
        String codigo = listaArticulos.get(position).getCodigo();
        int cantidad = listaArticulos.get(position).getCantidad();

        if(cantidad == 0){
            holder.articulo_cantidad.setText(String.valueOf(cantidad));
            holder.articulo_name.setText(descripcion);
            holder.articulo_price.setText(String.valueOf(precio));
            holder.articulo_code.setText(codigo);
            holder.articulo_Image.setImageBitmap(imagen);

            holder.btn_anadirCesta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, "ARTICULO agotado",Toast.LENGTH_SHORT).show();
                }
            });

            SQLiteDBHelper.deleteArticulo(listaArticulos.get(position));
            try {
                ArticulosBBDD.delete(activity.getContentResolver(), listaArticulos.get(position).getCodigo());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            holder.articulo_cantidad.setText(String.valueOf(cantidad));
            holder.articulo_name.setText(descripcion);
            holder.articulo_price.setText(String.valueOf(precio));
            holder.articulo_code.setText(codigo);
            holder.articulo_Image.setImageBitmap(imagen);

            holder.btn_anadirCesta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(activity);
                    listaArticulos.get(position).setCantidad(1);
                    SQLiteDBHelper.insertCompra(listaArticulos.get(position));
                    Toast.makeText(activity, "ARTICULO añadido a la cesta",Toast.LENGTH_SHORT).show();
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
