package com.example.alu2015059.jstyle.WebServer.Authenticator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.alu2015059.jstyle.WebServer.Authenticator.Authenticator;

/**
 * Created by alu2015059 on 31/05/2018.
 */

public class AuthenticatorService extends Service {

    private Authenticator mAuthenticator;

    @Override
    public void onCreate(){
        mAuthenticator = new Authenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
