package com.bonusteam.favtrack.fragments.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.bonusteam.favtrack.api.repositories.DietasRepositorio;
import com.bonusteam.favtrack.room.pojos.Dieta;

import java.util.List;

/**
 * Created by Rodrigo Corvera on 10/7/2018.
 */

public class DietsFragmentViewModel extends ViewModel{

        private LiveData<List<Dieta>> dietas;
        private DietasRepositorio dietasRepositorio;

        public DietsFragmentViewModel(DietasRepositorio dietasRepositorio) {
            this.dietasRepositorio = dietasRepositorio;
            dietas = new MutableLiveData<>();
            dietas = this.dietasRepositorio.getDietas();
        }

        public LiveData<List<Dieta>> getDietas() {
            return dietas;
        }

}
