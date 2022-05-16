package com.example.cryptoquestion;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import controller.ConexaoController;
import modelDominio.User;

public class CadastroActivity extends AppCompatActivity {
    EditText etNomeUsuarioCad, etIDUsuarioCad, etEmailCad, etSenhaCad, etDataNascCad;
    Button btVoltar, btCancelarCad, btConfirmarCad;
    InformacoesApp infoApp;
    User user;
    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        etNomeUsuarioCad = findViewById(R.id.etNomeUsuarioCad);
        etIDUsuarioCad = findViewById(R.id.etIDUsuarioCad);
        etEmailCad = findViewById(R.id.etEmailCad);
        etSenhaCad = findViewById(R.id.etSenhaCad);
        etDataNascCad = findViewById(R.id.etDataNascCad);
        btConfirmarCad = findViewById(R.id.btConfirmarCad);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        infoApp = (InformacoesApp) getApplicationContext();
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Calendar calendar = Calendar.getInstance();
        final int ano = calendar.get(Calendar.YEAR);
        final int mes = calendar.get(Calendar.MONTH);
        final int dia = calendar.get(Calendar.DAY_OF_MONTH);
        etDataNascCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CadastroActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,ano,mes,dia);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                mes += 1;
                String date = dia + "/" + mes + "/" + ano;
                etDataNascCad.setText(date);
            }
        };

        btConfirmarCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etNomeUsuarioCad.getText().toString().equals("")) {
                    if (!etIDUsuarioCad.getText().toString().equals("")) {
                        if (!etEmailCad.getText().toString().equals("")) {
                            String email = etEmailCad.getText().toString();
                            if (validaEmail(email)) {
                                if (!etSenhaCad.getText().toString().equals("")) {
                                    String senha = etSenhaCad.getText().toString();
                                    if (senhaForte(senha)) {
                                        if (!etDataNascCad.getText().toString().equals("")) {
                                            String idUser = etIDUsuarioCad.getText().toString();
                                            email = etEmailCad.getText().toString();
                                            senha = etSenhaCad.getText().toString();
                                            String nameUser = etNomeUsuarioCad.getText().toString();
                                            Date dateNasc = null;
                                            try {
                                                dateNasc = new SimpleDateFormat("dd/MM/yyyy").parse(etDataNascCad.getText().toString());
                                            } catch (ParseException ex) {
                                                ex.printStackTrace();
                                            }
                                            user = new User(idUser, email, senha, nameUser, dateNasc);

                                            Thread t = new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ConexaoController ccontUserCad = new ConexaoController(infoApp);
                                                    Boolean criado = ccontUserCad.criarUsuario(user);
                                                    if (criado) {
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                        Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
                                                        startActivity(it);
                                                    } else {
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                Toast.makeText(infoApp, "Erro ao Cadastrar Usuário!", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                    }
                                                }
                                            });
                                            t.start();
                                        } else {
                                            etDataNascCad.setError("Informe sua data de nascimento!");
                                            etDataNascCad.requestFocus();
                                        }
                                    } else {
                                        etSenhaCad.setError("Senha muito fraca!");
                                        etSenhaCad.requestFocus();
                                    }
                                } else {
                                    etSenhaCad.setError("Informe uma senha!");
                                    etSenhaCad.requestFocus();
                                }
                            } else {
                                etEmailCad.setError("Informe um e-mail válido!");
                                etEmailCad.requestFocus();
                            }
                        } else {
                            etEmailCad.setError("Informe um e-mail!");
                            etEmailCad.requestFocus();
                        }
                    } else {
                        etIDUsuarioCad.setError("Informe um ID para ser seu @!");
                        etIDUsuarioCad.requestFocus();
                    }
                } else {
                    etNomeUsuarioCad.setError("Informe o nome de usuário!");
                    etNomeUsuarioCad.requestFocus();
                }
            }


        });

    }

    public static boolean senhaForte(String senha) {
        if (senha.length() < 8) return false;

        boolean achouNumero = false;
        boolean achouMaiuscula = false;
        boolean achouMinuscula = false;
        boolean achouSimbolo = false;

        for (char c : senha.toCharArray()) {
            if (c >= '0' && c <= '9') {
                achouNumero = true;
            } else if (c >= 'A' && c <= 'Z') {
                achouMaiuscula = true;
            } else if (c >= 'a' && c <= 'z') {
                achouMinuscula = true;
            } else {
                achouSimbolo = true;
            }
        }
        return achouNumero && achouMaiuscula && achouMinuscula && achouSimbolo;
    }

    private void limpaCampos() {
        etNomeUsuarioCad.setText("");
        etIDUsuarioCad.setText("");
        etEmailCad.setText("");
        etSenhaCad.setText("");
        etDataNascCad.setText("");

    }

    private boolean validaEmail(final String email) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }
}