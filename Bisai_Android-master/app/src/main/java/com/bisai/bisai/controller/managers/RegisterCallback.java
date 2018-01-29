package com.bisai.bisai.controller.managers;

/**
 * Created by sergi on 17/04/2017.
 */

public interface RegisterCallback {
    void onSuccess();
    void onFailure(Throwable t);
}