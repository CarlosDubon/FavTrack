package com.bonusteam.favtrack.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonusteam.favtrack.R;
import com.bonusteam.favtrack.adapters.MultimediaSuggestionsAdapter;
import com.bonusteam.favtrack.room.pojos.Multimedia;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager gridLayoutManager;
    private List<Multimedia> list = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        MultimediaSuggestionsAdapter adapter = new MultimediaSuggestionsAdapter(getContext());
        recyclerView=v.findViewById(R.id.recycler_view);
        Multimedia multimedia= new Multimedia("1","hola","descripcion","mo","1","2018","idmultimedia","avatar",1,1);
        list.add(multimedia);
        gridLayoutManager = new LinearLayoutManager(getContext());
        adapter.setMultimediaList(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        return v;
    }

}
