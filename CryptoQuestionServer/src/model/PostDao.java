/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import factory.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelDominio.Coments;
import modelDominio.Like;
import modelDominio.Post;
import modelDominio.User;
import util.HashSal;

/**
 *
 * @author lucas
 */
public class PostDao {
    private Connection con;

    public PostDao() {
        con = Conector.getConnection();
    }
    
    public ArrayList<Post> getListaPost() {
        Statement stmt = null;
        ArrayList<Post> listaPost = new ArrayList<>();
        
        try {
            String sql = "select * from post";
            
            //preparando
            stmt = con.createStatement();
            
            //executando
            ResultSet result = stmt.executeQuery("select * from post left outer join usuario on usuario.codUser = post.codUser ORDER BY post.datePost DESC;");
            
            while (result.next()) {                
                               
                // criar uma marca e adicionar na lista
                //idUser, int codUser, String email, String senha, String nameUser, Date dateNasc,
                Post post = new Post(new User(result.getString("idUser"),
                                              result.getInt("codUser"),
                                              result.getString("email"),
                                              result.getString("senha"),
                                              result.getString("nameUser"),
                                              new java.util.Date(result.getDate("dateNasc").getTime())),
                                              result.getInt("codPost"),
                                              result.getString("textPost"),
                                              new java.util.Date(result.getDate("datePost").getTime()));
                listaPost.add(post);
                System.out.println(post);
            }

            // fechado
            result.close();
            stmt.close();
            con.close();
            
            return listaPost;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }  
    }
    
    public int criarPost(Post post) {
        PreparedStatement stmt = null;
        
        try {
            try {
                // eu controlo as TRANSAÇÕES com o banco, por isso colocamos auto commit para false
                con.setAutoCommit(false);

                String sql = "insert into post (codUser, textPost, datePost) \n" +
                "values (?, ?, current_timestamp());";
                // trocar os parametros
                stmt = con.prepareStatement(sql);

                stmt.setInt(1, post.getUser().getCodUser());
                stmt.setString(2, post.getTextPost());
                
                //executar script
                stmt.execute();
                // efetivar a transaçao
                con.commit(); // <- importante

                return -1;
            } catch (SQLException e) {
                try {
                    e.printStackTrace();
                    // cancela a trnsação se deu erro
                    con.rollback();
                    return e.getErrorCode();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return ex.getErrorCode();
                }
            }
        } finally {
            // uma forma de garantir que ele entre aqui, independentemente de ter dado
            // erro ou não
            try {
                stmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return e.getErrorCode();
            } 
        }
    }
}
