package com.example.alu2015059.jstyle.WebServer.Volley.Util;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by alu2015059 on 31/05/2018.
 */
/*
public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageCache{
    public static int getDefaultLruCacheSize(){
        final int max_Memory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cache_size = max_Memory / 8;
        return cache_size;
    }

    public LruBitmapCache(){this(getDefaultLruCacheSize());}

    public LruBitmapCache(int kBSize){super(kBSize);}

    @Override
    protected int sizeOf(String key, Bitmap bitmap){
        return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url){
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap){put(url, bitmap);}
}
*/