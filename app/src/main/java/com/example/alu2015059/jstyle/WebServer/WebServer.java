package com.example.alu2015059.jstyle.WebServer;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class WebServer {
    final static String ruta = "";

    public static List<Articulo> getAllArticulos(){
        List<Articulo> listaArticulos = new ArrayList<>();
        try {
            URL url = new URL(ruta);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String data;

            while ((data = br.readLine()) != null){
                stringBuilder.append(data);
            }

            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                listaArticulos.add(new Articulo(object.getString("descripcion"), object.getString("codigo"), object.getInt("cantidad"), object.getString("sexo"), object.getDouble("precio")));
            }

            return listaArticulos;
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addArticulo(Articulo articulo){
        try{
            URL url = new URL(ruta);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.connect();

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            Gson gson = new Gson();
            outputStream.writeBytes(URLEncoder.encode(gson.toJson(articulo), "UTF-8"));
            outputStream.flush();
            outputStream.close();
            return true;
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
