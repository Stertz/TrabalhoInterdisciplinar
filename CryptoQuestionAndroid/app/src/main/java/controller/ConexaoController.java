package controller;

import android.util.Log;

import com.example.cryptoquestion.InformacoesApp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import modelDominio.Post;
import modelDominio.User;

public class ConexaoController {
    InformacoesApp infoApp;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public User user;

    public void Conectar() {
        try {
            System.out.println("Conectando no servidor...");
            infoApp.socket = new Socket("10.0.2.2", 12345);
            infoApp.out = new ObjectOutputStream(infoApp.socket.getOutputStream());
            infoApp.in = new ObjectInputStream(infoApp.socket.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ConexaoController(InformacoesApp infoApp) {
        this.infoApp = infoApp;
    }

    public User efetuarLogin(User user){
        // implementar aqui o protocolo
        String msg;
        try {

            infoApp.out.writeObject("EfetuarLogin");
            msg = (String) infoApp.in.readObject();

            //if

            infoApp.out.writeObject(user);
            User usu = (User) infoApp.in.readObject();

            return usu;
        } catch (Exception e) {
            Log.d("teste","Dentro do catch: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Boolean criarUsuario(User user){
        // implementar aqui o protocolo
        String msg;
        try {
            infoApp.out.writeObject("CriarUsuario");
            msg = (String) infoApp.in.readObject();
            Log.d("teste","recebi OK || o User: " + user.getEmail());

            infoApp.out.writeObject(user);

            String usu = (String) infoApp.in.readObject();
            if(usu.equals("ok")){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean criarPost(Post post){
        // implementar aqui o protocolo
        String msg;
        try {
            infoApp.out.writeObject("CriarPost");
            msg = (String) infoApp.in.readObject();

            infoApp.out.writeObject(post);

            String pub = (String) infoApp.in.readObject();
            if(pub.equals("ok")){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Post> LikePost() {
        try {
            infoApp.out.writeObject("LikePost");
            ArrayList<Post> likePost = (ArrayList<Post>) infoApp.in.readObject();
            Log.d("postt", "LikePost: " + likePost);
            return likePost;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Post> getListaPost() {
        try {
            Log.d("postt", "entrei no getListaPost: ");
            infoApp.out.writeObject("ListaPost");
            ArrayList<Post> listaPost = (ArrayList<Post>) infoApp.in.readObject();
            Log.d("postt", "getListaPost: " + listaPost);

            return listaPost;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
