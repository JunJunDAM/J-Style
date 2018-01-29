package com.bisai.bisai.controller.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.managers.TeamCallback;
import com.bisai.bisai.model.Equipo;

import java.util.List;

/**
 * Created by DAM on 3/5/17.
 */

public class CreateTeamActivity extends AppCompatActivity implements TeamCallback{

    private boolean mTwoPane;
    private RecyclerView recyclerView;
    private List<Equipo> equipos;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_team);
    }

    @Override
    public void onSuccessTeam(List<Equipo> teamList) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
