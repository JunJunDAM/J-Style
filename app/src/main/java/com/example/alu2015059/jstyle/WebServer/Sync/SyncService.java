package com.example.alu2015059.jstyle.WebServer.Sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by alu2015059 on 31/05/2018.
 */

public class SyncService extends Service {

    private static SyncAdapter syncAdapter = null;
    private static final Object syncAdapterLock = new Object();

    @Override
    public void onCreate(){
        synchronized (syncAdapterLock){
            if(syncAdapter == null){
                syncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return syncAdapter.getSyncAdapterBinder();
    }
}
