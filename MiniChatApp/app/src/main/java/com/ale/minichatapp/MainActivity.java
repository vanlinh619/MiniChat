package com.ale.minichatapp;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.ale.minichatapp.handler.ApiUtils;
import com.ale.minichatapp.handler.SOService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SOService soService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.nav_host_fragment_main);
//        NavController navController = navHostFragment.getNavController();

        soService = ApiUtils.getSOService();
        login();
    }

    public void login() {
        soService.test().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.isSuccessful()) {
                    Log.d("MainActivity", response.body().toString());
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
                t.printStackTrace();
            }
        });
    }
}