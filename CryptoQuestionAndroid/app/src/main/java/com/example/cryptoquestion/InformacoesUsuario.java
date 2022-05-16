package com.example.cryptoquestion;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;

import java.text.SimpleDateFormat;

import modelDominio.Post;

public class InformacoesUsuario extends AppCompatActivity {
    TextView tvNomeUsuario, tvIdUsuario, tvEmailInfo, tvDateNasc;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_usuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvNomeUsuario = findViewById(R.id.tvNomeUsuario);
        tvIdUsuario = findViewById(R.id.tvIdUsuario);
        tvEmailInfo = findViewById(R.id.tvEmailInfo);
        tvDateNasc = findViewById(R.id.tvDateNasc);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            post =(Post) b.get("Post");
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String form = formato.format(post.getUser().getDateNasc());
        tvNomeUsuario.setText(post.getUser().getNameUser());
        tvIdUsuario.setText("@" + post.getUser().getIdUser());
        tvEmailInfo.setText(post.getUser().getEmail());
        tvDateNasc.setText(form);
    }

}
