package com.example.cryptoquestion;

import android.app.Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


import modelDominio.Post;
import modelDominio.User;

public class InformacoesApp extends Application {
    public Socket socket;
    public ObjectInputStream in;
    public ObjectOutputStream out;
    public User user;
    public Post post;
    public ArrayList<Post> listaPost;
}
