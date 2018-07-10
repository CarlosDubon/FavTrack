package com.bonusteam.favtrack.fragments.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.bonusteam.favtrack.api.repositories.DietasRepositorio;
import com.bonusteam.favtrack.api.repositories.RutinasRepositorio;
import com.bonusteam.favtrack.room.pojos.Dieta;
import com.bonusteam.favtrack.room.pojos.Rutina;

import java.util.List;

/**
 * Created by Rodrigo Corvera on 10/7/2018.
 */

public class RutinasFragmentViewModel extends ViewModel {

    private LiveData<List<Rutina>> rutinas;
    private RutinasRepositorio rutinasRepositorio;

    public RutinasFragmentViewModel(RutinasRepositorio rutinasRepositorio) {
        this.rutinasRepositorio = rutinasRepositorio;
        rutinas = new MutableLiveData<>();
        rutinas = this.rutinasRepositorio.getRutinas();
    }

    public LiveData<List<Rutina>> getRutinas() {
        return rutinas;
    }

}
