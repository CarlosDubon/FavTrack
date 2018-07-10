package com.bonusteam.favtrack.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.bonusteam.favtrack.R;
import com.bonusteam.favtrack.api.network.AppExecutors;
import com.bonusteam.favtrack.api.network.NetworkUtils;
import com.bonusteam.favtrack.utilities.SharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    EditText txtUser, txtPassword;
    CircularProgressButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Se inicializa la clase SharedPreference
        SharedPreference.init(getApplicationContext());

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v-> simulateLogin());
        //checkLogin();
    }

    private void simulateLogin() {
        txtUser = findViewById(R.id.id_edit_user_login);
        txtPassword = findViewById(R.id.id_edit_pass_login);
        btnLogin.startAnimation();
        AppExecutors.getInstance().networkIO().execute(()->{
            try {
                Thread.sleep(3000);
                SharedPreference.logInUser(txtUser.getText().toString(),
                        txtPassword.getText().toString());
                btnLogin.doneLoadingAnimation(Color.parseColor("#2BB29B")
                        , BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
                Intent intent = new Intent(getApplicationContext(), FavTrack.class);
                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        btnLogin.doneLoadingAnimation(Color.parseColor("#B24419")
                , BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
    }

    private void checkLogin() {
        txtUser = findViewById(R.id.id_edit_user_login);
        txtPassword = findViewById(R.id.id_edit_pass_login);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v->{
            final String user = txtUser.getText().toString();
            final String pass = txtPassword.getText().toString();

            if(user.trim().length() == 0) txtUser.setError("User is required");
            if(pass.trim().length() == 0) txtPassword.setError("Password is required");

            if(user.trim().length() >0 && pass.trim().length()>0){
                btnLogin.startAnimation();

                Call<String> loginService = NetworkUtils.getClientInstance().login(user, pass);
                loginService.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            SharedPreference.logInUser(user, response.body());
                            btnLogin.doneLoadingAnimation(Color.parseColor("#B24419")
                                    , BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
                            Intent intent = new Intent(getApplicationContext(), FavTrack.class);
                            startActivity(intent);
                            finish();
                        } else {
                            btnLogin.revertAnimation();
                            try {
                                if(response.errorBody()!=null){
                                    String error = response.errorBody().string();
                                    JSONObject object = new JSONObject(error);
                                    new AlertDialog.Builder(LoginActivity.this)
                                            .setTitle("Error")
                                            .setMessage(error)
                                            .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                                            .show();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.printStackTrace();
                        btnLogin.revertAnimation();
                        Snackbar.make(v,"Login failed. Try again later.",Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        btnLogin.dispose();
    }
}
