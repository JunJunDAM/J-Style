package com.example.alu2015059.jstyle.WebServer.Volley;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.Repository.Database;
import com.example.alu2015059.jstyle.RepositoryTesting.ArticulosSync;
import com.example.alu2015059.jstyle.WebServer.Controller.AppController;
import com.example.alu2015059.jstyle.WebServer.Sync.Sincronizacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alu2015059 on 31/05/2018.
 */

public class ArticuloVolley {
    //https://www.youtube.com/watch?v=Ff0OiuOiuOJm3Q
    final static String ruta = Database.WEB_SERVER_PATH + "/ARTICULO";

    public static void getAllArticulos(){
        String tag_json_obj = "getAllArticulos";
        String url = ruta;

        AppController.getInstance().getSincronizacion().setServerResonse(true);

        JsonArrayRequest getRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        Sincronizacion.sendPostRecieveServerUpdate(response);
                        AppController.getInstance().getSincronizacion().setServerResonse(false);
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AppController.getInstance().getSincronizacion().setServerResonse(false);
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(getRequest, tag_json_obj);
    }

    public static void addArticulo (Articulo articulo, final boolean articuloSync, final String codigo){
        String tag_json_obj = "addArticulo";
        String url = ruta;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("descripcion", articulo.getDescripcion());
            jsonObject.put("codigo", articulo.getCodigo());
            jsonObject.put("cantidad", articulo.getCantidad());
            jsonObject.put("sexo", articulo.getSexo());
            jsonObject.put("precio", articulo.getPrecio());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AppController.getInstance().getSincronizacion().setServerResonse(true);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (articuloSync) ArticulosSync.delete(AppController.getResolver(), codigo);
                AppController.getInstance().getSincronizacion().setServerResonse(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppController.getInstance().getSincronizacion().setServerResonse(false);
            }
        }
        );
        AppController.getInstance().getSincronizacion().setServerResonse(false);
    }

    public static void updateArticulo(Articulo articulo, final boolean articuloSync, final String codigo){
        String tag_json_obj = "updateArticulo";
        String url = ruta + "/" + articulo.getCodigo();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("descripcion", articulo.getDescripcion());
            jsonObject.put("codigo", articulo.getCodigo());
            jsonObject.put("cantidad", articulo.getCantidad());
            jsonObject.put("sexo", articulo.getSexo());
            jsonObject.put("precio", articulo.getPrecio());
        }catch (JSONException e){
            e.printStackTrace();
        }

        AppController.getInstance().getSincronizacion().setServerResonse(true);

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (articuloSync) ArticulosSync.delete(AppController.getResolver(), codigo);
                AppController.getInstance().getSincronizacion().setServerResonse(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppController.getInstance().getSincronizacion().setServerResonse(false);
            }
        });
        AppController.getInstance().addToRequestQueue(putRequest, tag_json_obj);
    }

    public static void deleteArticulo(String codigo, final boolean articuloSync, final String codigoSync){
        String tag_json_obj = "updateArticulo";
        String url = ruta + "/" + codigo;

        AppController.getInstance().getSincronizacion().setServerResonse(true);

        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (articuloSync) ArticulosSync.delete(AppController.getResolver(), codigoSync);
                AppController.getInstance().getSincronizacion().setServerResonse(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppController.getInstance().getSincronizacion().setServerResonse(false);
            }
        });
        AppController.getInstance().addToRequestQueue(deleteRequest, tag_json_obj);
    }
}
