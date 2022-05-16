/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import util.EnvioDeEmail;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import model.PostDao;
import model.UserDao;
import modelDominio.Like;
import modelDominio.Post;
import modelDominio.User;


/**
 *
 * @author lucas
 */
public class TrataUserController extends Thread {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;
    private int idUnico;

    public TrataUserController(ObjectInputStream in, ObjectOutputStream out, Socket socket, int idUnico) {
        this.in = in;
        this.out = out;
        this.socket = socket;
        this.idUnico = idUnico;
    }

    @Override
    public void run() {
        // receber comandos do cliente
        String comando;
        System.out.println("Aguardando comandos do cliente: " + idUnico);
        try {
            comando = (String) in.readObject();
            while (!comando.equalsIgnoreCase("finalizar")) {
                System.out.println("Cliente " + idUnico + " enviou o comando: " + comando);
                if (comando.equalsIgnoreCase("EfetuarLogin")) {
                    out.writeObject("ok");
                    System.out.println("esperando usuario");
                    User user = (User) in.readObject();
                    System.out.println("usuario: " + user.getEmail() + " e a senha " + user.getSenha());
                    System.out.println(user);

                    UserDao usdao = new UserDao();
                    User userLogado = usdao.efetuarLogin(user);
                    System.out.println("vou devolver: " + userLogado);
                    out.writeObject(userLogado); //Devolvendo usuário logado
                    
                    
                } else if (comando.equalsIgnoreCase("EnviarCodigo")) {
                    out.writeObject("ok");
                    User user = (User) in.readObject();
                    System.out.println(user);

                    UserDao usdao = new UserDao();
                    int existeEmail = usdao.enviaCodigo(user);
                    // aqui precisa implementar o envio do email
                    String codigo = "";
                    if (existeEmail != -1) {
                        EnvioDeEmail envio = new EnvioDeEmail();
                        codigo = envio.enviarEmail(user);                        
                    }
                                     
                    out.writeObject(codigo); //Devolve codigo
                    out.writeObject(existeEmail); // esse é o cod do usuario
                } else if (comando.equalsIgnoreCase("CriarUsuario")) {
                    out.writeObject("ok");
                    System.out.println("esperando usuario");
                    User user = (User) in.readObject();

                    System.out.println(user);
                    UserDao userDao = new UserDao();
                    int result = userDao.inserir(user);

                    if (result == -1) {
                        out.writeObject("ok");
                    } else {
                        out.writeObject("nok");
                    }
                }  else if (comando.equalsIgnoreCase("CriarPost")) {
                    out.writeObject("ok");
                    System.out.println("esperando post");
                    Post post = (Post) in.readObject();

                    System.out.println(post);
                    PostDao postDao = new PostDao();
                    int result = postDao.criarPost(post);

                    if (result == -1) {
                        out.writeObject("ok");
                    } else {
                        out.writeObject("nok");
                    }
                } else if (comando.equalsIgnoreCase("UpdateUsuario")) {
                    out.writeObject("ok");
                    User user = (User) in.readObject();
                    System.out.println("Usuário: " + user);

                    UserDao usdao = new UserDao();
                    
                    Boolean updateUsu = usdao.updateUsuario(user);
                    
                                  
                    out.writeObject(updateUsu); //Devolve se deu certo
                } else if (comando.equalsIgnoreCase("ClientsLista")) {
                    UserDao userDao = new UserDao();
                    ArrayList<User> listaUser = userDao.getListaUser();
                    out.writeObject(listaUser);        
                    
                } else if (comando.equalsIgnoreCase("ListaPost")) {
                    PostDao postDao = new PostDao();
                    ArrayList<Post> listaPost = postDao.getListaPost();
                    
                    out.writeObject(listaPost);        
                    
                } else if (comando.equalsIgnoreCase("CurtirPost")) {
//                    Like like = new Like();
//                    like curtida = likeDao.curtirPost();
//                    
//                    out.writeObject(listaPost);        
                    
                } else if (comando.equalsIgnoreCase("ListaUserFiltro")){
                    out.writeObject("ok"); 
                    // Nessa consulta eu preciso esperar o nome para realizar um filtro
                    String nome = (String) in.readObject();
                    UserDao usDao = new UserDao();
                    ArrayList<User> listaUsers = usDao.getListaUserQualquer(nome);
                    out.writeObject(listaUsers);// escrevendo a saída
                   
                } else if (comando.equalsIgnoreCase("DeleteUser")){
                    out.writeObject("ok"); 
                    // esperando o objeto bike vir do cliente
                    User user = (User) in.readObject();
                    // criando um Dao para armazenar no Banco
                    UserDao userDao = new UserDao();
                    if (userDao.excluir(user) == -1){
                        out.writeObject("ok");
                    }else{
                        out.writeObject("nok");
                    }
                } else{
                    System.out.println("Comando Inválido!");
                }

                comando = (String) in.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Fechando a conexão do cliente " + idUnico);
        try {
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Conexão encerrada!");
    }
}
