/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import modelDominio.User;



/**
 *
 * @author lucas
 */
public class ConexaoController {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    public User user;

    public ConexaoController(Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }
    
    public int codUsuarioParaSenha;
    
    public User efetuarLogin(User user){
        // implementar aqui o protocolo
        String msg;
        try {
            out.writeObject("EfetuarLogin");
            msg = (String) in.readObject();
            
            out.writeObject(user);
            User usu = (User) in.readObject();
            return usu;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Boolean deleteUser (User usu){
        String msg = "";
        try{
            out.writeObject("DeleteUser");
            msg = (String) in.readObject(); // lendo ok
            out.writeObject(usu); // escrevendo a bike
            msg = (String) in.readObject(); // lendo ok
            if (msg.equals("ok")){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }    
    public String enviarCodigo(User user){
        // implementar aqui o protocolo
        String msg;
        try {
            out.writeObject("EnviarCodigo");
            msg = (String) in.readObject();
            
            out.writeObject(user);
            String usu = (String) in.readObject();
            codUsuarioParaSenha = (int) in.readObject();
            return usu;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public Boolean updateUsuario(User user){
        // implementar aqui o protocolo
        String msg;
        try {
            out.writeObject("UpdateUsuario");
            msg = (String) in.readObject();
            System.out.println(user);
            out.writeObject(user);
            Boolean usu = (Boolean) in.readObject();
            return usu;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<User> getListaUser() {
        try {
            out.writeObject("ClientsLista");
            ArrayList<User> listaUser = (ArrayList<User>) in.readObject();
            
            return listaUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<User> getListaUserFiltro(String nome){
        String msg;
        try{
            out.writeObject("ListaUserFiltro");
            msg = (String) in.readObject(); // lendo ok
            out.writeObject(nome); // escrevendo o filtro
            ArrayList<User> listaUser = (ArrayList<User>) in.readObject(); 
            return listaUser;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    } 
}
