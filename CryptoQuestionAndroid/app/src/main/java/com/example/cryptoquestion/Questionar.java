package com.example.cryptoquestion;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import controller.ConexaoController;
import modelDominio.Post;

public class Questionar extends AppCompatActivity {
    EditText etPublicar;
    InformacoesApp infoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etPublicar =findViewById(R.id.etPublicar);

        infoApp = (InformacoesApp) getApplicationContext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if (!etPublicar.equals("")) {
                if (etPublicar.getText().toString().length() <= 300) {
                    String textPost = etPublicar.getText().toString();
                    final Post post = new Post(infoApp.user, textPost);
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ConexaoController ccontToAsk = new ConexaoController(infoApp);
                            Boolean postFeito = ccontToAsk.criarPost(post);

                            if (postFeito) {

                                Intent it = new Intent(Questionar.this, HomeActivity.class);
                                startActivity(it);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(infoApp, "Publicação criada com sucesso!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(infoApp, "Erro ao criar a publicação!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                    t.start();
                }
            }
            return true;
        }

        return super.onOptionsItemSelected(item);



    }
}
