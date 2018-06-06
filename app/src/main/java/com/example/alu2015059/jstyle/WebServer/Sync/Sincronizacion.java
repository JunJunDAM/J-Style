package com.example.alu2015059.jstyle.WebServer.Sync;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.Domain.ArticuloSYNC;
import com.example.alu2015059.jstyle.RepositoryTesting.ArticulosBBDD;
import com.example.alu2015059.jstyle.RepositoryTesting.ArticulosSync;
import com.example.alu2015059.jstyle.RepositoryTesting.C;
import com.example.alu2015059.jstyle.WebServer.Volley.ArticuloVolley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alu2015059 on 31/05/2018.
 */

public class Sincronizacion {
    private static final String LOG_TAG = "Sync";
    private static ContentResolver resolver;
    private static Context context;
    private static boolean serverResonse = false;

    public Sincronizacion(Context context){
        this.resolver = context.getContentResolver();
        this.context = context;
    }

    public synchronized static boolean isServerResonse(){
        return serverResonse;
    }

    public synchronized static void setServerResonse(boolean serverResonse){
        Sincronizacion.serverResonse = serverResonse;
    }

    public synchronized boolean sincronizar(){
        if(isServerResonse()){
            return true;
        }

        sendUpdateToServer();
        recieveUptadeFromServer();

        return true;
    }

    private static void recieveUptadeFromServer() {
        ArticuloVolley.getAllArticulos();
    }

    public static void sendUpdateToServer() {
        List<ArticuloSYNC> syncs = ArticulosSync.readAll(resolver);
        for(ArticuloSYNC sync : syncs){
            switch (sync.getOperacion()){
                case C.CREATE:
                    Articulo articulo = null;
                    try {
                        articulo = ArticulosBBDD.read(resolver, sync.getCodigo());
                        ArticuloVolley.addArticulo(articulo, true, sync.getCodigo());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case C.UPDATE:
                    try {
                        articulo = ArticulosBBDD.read(resolver, sync.getCodigo());
                        ArticuloVolley.updateArticulo(articulo, true, sync.getCodigo());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case C.DELETE:
                    ArticuloVolley.deleteArticulo(sync.getCodigo(), true, sync.getCodigo());
                    break;
            }
        }
    }

    public static void sendPostRecieveServerUpdate(JSONArray jsonArray){
        try{
            List<String> codigosActualizados = new ArrayList<>();
            List<Articulo> newArticulos = new ArrayList<>();
            List<Articulo> oldArticulos = new ArrayList<>();
            List<String> codigosNoActualizados = new ArrayList<>();
            for(Articulo a : oldArticulos) codigosNoActualizados.add(a.getCodigo());

            JSONObject jsonObject = null;
            for (int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                newArticulos.add(new Articulo(jsonObject.getString("descripcion"), jsonObject.getString("codigo"), jsonObject.getInt("cantidad"), jsonObject.getString("sexo"), jsonObject.getDouble("precio")));
            }

            for(Articulo articulo: newArticulos){
                try {
                    if(codigosNoActualizados.contains(articulo.getCodigo())){
                        ArticulosBBDD.update(resolver, articulo);
                    }else {
                        ArticulosBBDD.insert(resolver, articulo);
                    }
                    codigosActualizados.add(articulo.getCodigo());
                }catch (Exception e){
                    Log.i("Sincronizacion",
                            "Ya existe en la BD");
                }
            }

            for (Articulo articulo : oldArticulos){
                if(!codigosActualizados.contains(articulo.getCodigo())){
                    try {
                        ArticulosBBDD.delete(resolver, articulo.getCodigo());
                    }catch (Exception e){
                        Log.i("sincronizacion", "Error al borrar el registro con codigo:" + articulo.getCodigo());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
