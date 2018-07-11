package com.bonusteam.favtrack.fragments.ViewModels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.bonusteam.favtrack.api.repositories.DietasRepositorio;

/**
 * Created by Rodrigo Corvera on 10/7/2018.
 */

public class DietsViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private final DietasRepositorio dietasRepositorio;

    public DietsViewModelFactory (DietasRepositorio dietasRepositorio) {
        this.dietasRepositorio = dietasRepositorio;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DietsFragmentViewModel(dietasRepositorio);
    }


}
