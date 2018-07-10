package com.bonusteam.favtrack.fragments.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.bonusteam.favtrack.api.repositories.LibroRepository;
import com.bonusteam.favtrack.room.pojos.LibrosEntity;

import java.util.List;

public class LibrosViewModel extends AndroidViewModel {

    LibroRepository libroRepository;

    public LibrosViewModel(@NonNull Application application) {
        super(application);
        this.libroRepository = new LibroRepository(application);
    }

    public LiveData<List<LibrosEntity>> getAllLibros() {
        return libroRepository.getAllLibros();
    }

    public LiveData<LibrosEntity> getLibro(String id) {
        return libroRepository.getLibro(id);
    }

    public LiveData<Integer> isFavorite(String id) {
        return libroRepository.isFavorite(id);
    }

    public LiveData<List<LibrosEntity>> getFavoritesLibros() {
        return libroRepository.getFavoritesLibros();
    }

    public LiveData<Integer> isVisto(String id){
        return libroRepository.isVisto(id);
    }

    public void insertLibro(LibrosEntity librosEntity) {
        libroRepository.insertLibro(librosEntity);
    }

    public void updateLibros(int favorite, String id) {
        libroRepository.updateFavoritesLibros(favorite, id);
    }

    public void deleteLibro(LibrosEntity librosEntity) {
        libroRepository.deleteLibros(librosEntity);
    }


}
