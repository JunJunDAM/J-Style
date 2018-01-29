package com.bisai.bisai.controller.managers;

import com.bisai.bisai.model.UserToken;

/**
 * Created by sergi on 06/04/2017.
 */

public interface LoginCallback {
    void onSuccess(UserToken userToken);
    void onFailure(Throwable t);
}
