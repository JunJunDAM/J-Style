package com.bisai.bisai.controller.managers;

import com.bisai.bisai.model.Equipo;

import java.util.List;

/**
 * Created by DAM on 3/5/17.
 */

public interface TeamCallback {
    void onSuccessTeam(List<Equipo> teamList);
    void onFailure(Throwable t);
}
