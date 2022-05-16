package com.example.cryptoquestion;

import android.content.Intent;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import controller.ConexaoController;
import modelDominio.User;


public class LoginActivity extends AppCompatActivity {
    EditText etEmailLogin, etSenha, etEmailCad, etSenhaCad;
    Button btEntrar, btCadastrese;
    InformacoesApp infoApp;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmailCad =findViewById(R.id.etEmailCad);
        etSenhaCad = findViewById(R.id.etSenhaCad);
        etEmailLogin = findViewById(R.id.etEmailLogin);
        etSenha = findViewById(R.id.etSenha);
        btCadastrese = findViewById(R.id.btCadastrese);
        btEntrar = findViewById(R.id.btEntrar);

        infoApp = (InformacoesApp) getApplicationContext();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etEmailLogin.getText().toString().equalsIgnoreCase("")) {
                    if (!etSenha.getText().toString().equalsIgnoreCase("")) {
                        String email = etEmailLogin.getText().toString();
                        String senha = etSenha.getText().toString();
                        user = new User (email, senha);
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ConexaoController ccontUser = new ConexaoController(infoApp);
                                User usuLogado = ccontUser.efetuarLogin(user);

                                if (usuLogado != null) {
                                    infoApp.user = usuLogado;
                                    Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(it);
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(infoApp, "Erro ao efetuar LOGIN!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                        t.start();

                    } else {
                        etSenha.setError("Informe a senha!");
                        etSenha.requestFocus();
                    }
                } else {
                    etEmailLogin.setError("Informe o email!");
                        etSenha.requestFocus();
                    }
                }
        });
        btCadastrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent (LoginActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });
    }

}
