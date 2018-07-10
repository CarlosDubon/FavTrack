package com.bonusteam.favtrack.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.bonusteam.favtrack.R;
import com.bonusteam.favtrack.utilities.SharedPreference;

public class FavTrack extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_track);

        SharedPreference.init(getApplicationContext());
        if(SharedPreference.checkLogin()){
            finishAffinity();
            Log.d(TAG, "onCreate: No login");
        }

        Button btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(v->{
            SharedPreference.logOutUser();
            finish();
        });
    }
}
