package com.example.alu2015059.jstyle.WebServer.Controller;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.alu2015059.jstyle.Repository.Database;
import com.example.alu2015059.jstyle.WebServer.Sync.Sincronizacion;
import com.example.alu2015059.jstyle.WebServer.Volley.Util.LruBitmapCache;

/**
 * Created by alu2015059 on 31/05/2018.
 */

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;
    private static ContentResolver resolver;

    public static final String AUTHORITY = Database.AUTHORITY;
    public static final String ACCOUNT_TYPE = "com.example.alu2015059.jstyle";
    public static final String ACCOUNT = "stockNow";
    public static final long SYNC_INTERVAL = 60;

    private Sincronizacion sincronizacion;

    Account mAccount;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
        resolver = getContentResolver();

        startSync();
    }

    public static ContentResolver getResolver() {return resolver;}

    public static synchronized AppController getInstance(){return mInstance;}

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader(){
        getRequestQueue();
        if(mImageLoader == null){
            mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag){
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }

    public static Account CreateSyncAccount(Context context){
        Account newAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        AccountManager accountManager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);

        if(accountManager.addAccountExplicitly(newAccount, null, null)){

        }else {

        }
        return newAccount;
    }

    private void startSync() {
        mAccount = CreateSyncAccount(this);
        resolver.setSyncAutomatically(mAccount, AUTHORITY, true);
        resolver.setMasterSyncAutomatically(true);
        resolver.addPeriodicSync(mAccount, AUTHORITY, Bundle.EMPTY, SYNC_INTERVAL);
    }

    public Sincronizacion getSincronizacion(){return sincronizacion;}

    public void setSincronizacion(Sincronizacion sincronizacion){this.sincronizacion = sincronizacion;}
}