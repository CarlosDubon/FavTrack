package com.bonusteam.favtrack.fragments.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.bonusteam.favtrack.api.repositories.UserRepository;
import com.bonusteam.favtrack.room.pojos.Usuario;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
    }

    public LiveData<Usuario> getUsuario() {
        return userRepository.getUser();
    }

    public void updateUsuario(Usuario usuario) {
        userRepository.updateUser(usuario);
    }

    public void insertUsuario(Usuario usuario) {
        userRepository.insertUser(usuario);
    }

    public void deleteUsuario(Usuario usuario) {
        userRepository.deleteUser(usuario);
    }
}
