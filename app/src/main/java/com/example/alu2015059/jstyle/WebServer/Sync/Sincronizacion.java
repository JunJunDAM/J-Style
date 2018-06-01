package com.example.alu2015059.jstyle.WebServer.Sync;

import android.content.ContentResolver;
import android.content.Context;

import com.example.alu2015059.jstyle.Domain.Articulo;
import com.example.alu2015059.jstyle.Repository.SQLiteDBHelper;

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

        if(true){
            sendUpdateToServer();
        }else{
            recieveUptadeFromServer();
        }
        return true;
    }

    private void recieveUptadeFromServer() {

    }

    private void sendUpdateToServer() {
        //SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper();
        //List<Articulo> listaArticulos =  sqLiteDBHelper.getAllArticulos();
    }
}
