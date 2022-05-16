/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import java.sql.*;

/**
 *
 * @author Lucas F P Stertz
 */

// Classe que faz a conexão do banco de dados
public class Conector {
    private static Connection con;
    
    public static Connection getConnection(){
        try {
            String url = "jdbc:mysql://localhost:3306/";
            String banco = "cryptoquestion";
            String usuario = "root";
            String senha = "";
            
            con = DriverManager.getConnection(url+banco, usuario, senha);
            System.out.println("--- Conexão com o Banco de Dados feita com sucesso! ---");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }   
}
