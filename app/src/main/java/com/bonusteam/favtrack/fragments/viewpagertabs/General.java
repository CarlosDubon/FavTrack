package com.bonusteam.favtrack.fragments.viewpagertabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonusteam.favtrack.R;

/**
 * Created by Deryan Cruz on 10/07/2018.
 */

public class General extends Fragment {
    private View view;
    public General() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.recycler_view, container, false);
        return view;
    }


}
