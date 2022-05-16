package com.example.cryptoquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import controller.ConexaoController;

public class MainActivity extends AppCompatActivity {
    InformacoesApp infoApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        },5000);

        infoApp = (InformacoesApp) getApplicationContext();
        Thread t = new Thread() {
            @Override
            public void run() {
                ConexaoController ccont = new ConexaoController(infoApp);
                ccont.Conectar();
            }
        };
        t.start();
    }

}


