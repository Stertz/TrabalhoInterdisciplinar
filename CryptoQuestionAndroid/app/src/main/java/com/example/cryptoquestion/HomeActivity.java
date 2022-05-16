package com.example.cryptoquestion;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import controller.ConexaoController;
import modelDominio.Post;

public class HomeActivity extends AppCompatActivity {
    ListView lvFeed;
    InformacoesApp infoApp;
    RecyclerView rvFeed;
    ImageButton ibCurtir, ibMensagem, ibDoar, ibComparilhar;
    TextView tvCurtir;
    Adapter postAdapter;
    ArrayList<Post> listaPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        rvFeed = (RecyclerView) findViewById(R.id.rvFeed);

        ibMensagem = findViewById(R.id.ibMensagem);
        ibCurtir = findViewById(R.id.ibCurtir);
        ibDoar = findViewById(R.id.ibDoar);
        ibComparilhar = findViewById(R.id.ibCompartilhar);

        tvCurtir = findViewById(R.id.tvCurtir);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("To Ask", null).show();
                Intent it = new Intent(HomeActivity.this, Questionar.class);
                startActivity(it);
            }
        });



        infoApp = (InformacoesApp) getApplicationContext();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                ConexaoController ccontPost = new ConexaoController(infoApp);
                listaPost = ccontPost.getListaPost();
                Log.d("postt", "onCreate: " + listaPost.size());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (listaPost != null) {
                            postAdapter = new Adapter(listaPost, trataCliqueItem);
                            rvFeed.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                            rvFeed.setItemAnimator(new DefaultItemAnimator());
                            rvFeed.setAdapter(postAdapter);
                        }
                    }
                });
            }
        });
        t.start();
    }

    Adapter.PostOnClickListener trataCliqueItem = new Adapter.PostOnClickListener() {
        @Override
        public void onClickPost(View view, int position) {
            Post post = listaPost.get(position);


            Intent it = new Intent(HomeActivity.this, InformacoesUsuario.class);
            it.putExtra("Post", post);
            startActivity(it);
        }
    };
}
