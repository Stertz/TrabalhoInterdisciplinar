/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TrataUserController;
import factory.Conector;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
/**
 *
 * @author Lucas F P Stertz
 */
public class Principal {
    public static void main(String [] args){
        // Conectar com o banco de dados
        Connection con = Conector.getConnection();
        
        try {
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("--- Servidor inicializado. Aguardando conexão de clientes! ---");
            
            ConectaServidor cs = new ConectaServidor(servidor, con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ConectaServidor extends Thread {
    private ServerSocket servidor;
    private int idUnico; //Identificar com um número o cliente conectado
    private Connection con;

    // Construtor que recebe servidor e conexão
    public ConectaServidor(ServerSocket servidor, Connection con) {
        this.servidor = servidor;
        this.con = con;
        idUnico = 0;
        this.start(); // isso aqui inicializa a thread e chama o método run
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket cliente = servidor.accept();
                // Criando os objetos para receber e enviar dados
                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
                idUnico++;
                System.out.println("--- Cliente conectado! ---");
               
                TrataUserController trataClient = new TrataUserController(in, out, cliente, idUnico);
                trataClient.start();                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}