package com.bonusteam.favtrack.fragments.ViewModels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.bonusteam.favtrack.api.repositories.DietasRepositorio;
import com.bonusteam.favtrack.api.repositories.RutinasRepositorio;
import com.bonusteam.favtrack.room.pojos.Rutina;

/**
 * Created by Rodrigo Corvera on 10/7/2018.
 */

public class RutinasViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final RutinasRepositorio rutinasRepositorio;

    public RutinasViewModelFactory (RutinasRepositorio dietasRepositorio) {
            this.rutinasRepositorio = dietasRepositorio;
            }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new RutinasFragmentViewModel(rutinasRepositorio);
        }

}
