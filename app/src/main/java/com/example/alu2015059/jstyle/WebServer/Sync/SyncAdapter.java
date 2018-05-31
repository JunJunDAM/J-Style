package com.example.alu2015059.jstyle.WebServer.Sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

/**
 * Created by alu2015059 on 31/05/2018.
 */

public class SyncAdapter  extends AbstractThreadedSyncAdapter{

    public SyncAdapter (Context context, boolean autoInitialize){
        super(context, autoInitialize);
    }

    public SyncAdapter(Context context, boolean autoInitiaize, boolean allowParallelSyncs){
        super(context, autoInitiaize, allowParallelSyncs);
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {

    }
}
