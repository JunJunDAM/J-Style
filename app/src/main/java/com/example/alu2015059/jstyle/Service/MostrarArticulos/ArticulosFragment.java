package com.example.alu2015059.jstyle.Service.MostrarArticulos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.alu2015059.jstyle.R;

/**
 * Created by alu2015059 on 24/05/2018.
 */

public class ArticulosFragment extends Fragment {

    private ListView articulosList;

    public ArticulosFragment(){

    }

    public static ArticulosFragment newInstance(){
        ArticulosFragment fragment = new ArticulosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.pagina_articulos_adm, container, false);



        return root;
    }
}
