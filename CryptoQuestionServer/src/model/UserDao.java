/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import util.HashSal;
import factory.Conector;
import java.sql.*;
import java.util.ArrayList;
import modelDominio.Admin;
import modelDominio.Client;
import modelDominio.User;

/**
 *
 * @author lucas
 */
public class UserDao {
    private Connection con;

    public UserDao() {
        con = Conector.getConnection();
    }
    
    public ArrayList<User> getListaUser() {
        Statement stmt = null;
        ArrayList<User> listaUser = new ArrayList<>();
        
        try {
            String sql = "select * from usuario";
            
            //preparando
            stmt = con.createStatement();
            
            //executando
            ResultSet result = stmt.executeQuery("Select * From usuario;");
            
            while (result.next()) {                
                // criar uma marca e adicionar na lista
                
                User user = new User(result.getString("idUser"),
                                     result.getInt("codUser"),
                                     result.getString("email"),
                                     result.getString("senha"),
                                     result.getString("nameUser"),
                                     result.getDate("dateNasc"),
                                     result.getBytes("photoUser"));
                listaUser.add(user);
                System.out.println(user);
            }

            // fechado
            result.close();
            stmt.close();
            con.close();
            
            return listaUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }  
    }
    
    public User efetuarLogin (User user) {
        PreparedStatement stmt = null;
        User userLogado = null;
        
        try {
            String sql = "select * from usuario where email = ? and senha = ?";
            
            //preparando
            stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            String senhaHash = HashSal.getPasswordDigest(user.getSenha());
            stmt.setString(2, senhaHash);
            
            //executando
            ResultSet result = stmt.executeQuery();
            
            while (result.next()) {                
                if (result.getInt("typeUser") ==1) {
                    // é um adm
                    //idUser, codUser, email, senha, nameUser, dateNasc, photoUser
                    userLogado = new Admin(          result.getString("idUser"),
                                                     result.getInt("codUser"),
                                                     result.getString("email"),
                                                     result.getString("senha"),
                                                     result.getString("nameUser"),
                                                     result.getDate("dateNasc"),
                                                     result.getBytes("photoUser"));
                } else {
                    // é um user comum
                    userLogado = new Client(         result.getString("idUser"),
                                                     result.getInt("codUser"),
                                                     result.getString("email"),
                                                     result.getString("senha"),
                                                     result.getString("nameUser"),
                                                     result.getDate("dateNasc"),
                                                     result.getBytes("photoUser"));
                }
            }
            
            System.out.println(userLogado);
            // fechado
            result.close();
            stmt.close();
            con.close();
            
            return userLogado;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }  
    }
    
    public int inserir(User user) {
        PreparedStatement stmt = null;
        
        try {
            try {
                // eu controlo as TRANSAÇÕES com o banco, por isso colocamos auto commit para false
                con.setAutoCommit(false);

                String sql = "insert into usuario (idUser, email, senha, nameUser, dateNasc, typeUser, photoUser) \n" +
                " values (?, ?, ?, ?, ?, 2, null);";
                // trocar os parametros
                stmt = con.prepareStatement(sql);

                stmt.setString(1, user.getIdUser());
                stmt.setString(2, user.getEmail());
                String senhaHash = HashSal.getPasswordDigest(user.getSenha());
                stmt.setString(3, senhaHash);
                stmt.setString(4, user.getNameUser());
                stmt.setDate(5, new java.sql.Date(user.getDateNasc().getTime()));
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
    
    public int enviaCodigo (User user) {
        PreparedStatement stmt = null;
        User userLogado = null;
        
        try {
            String sql = "select * from usuario where email = ?";
            
            //preparando
            stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            
            //executando
            ResultSet result = stmt.executeQuery();
            
            int retorno;
            if (result.next()) {
                retorno = result.getInt("codUser");
                
            } else {
                retorno = -1;
            }
            
            System.out.println(userLogado);
            // fechado
            result.close();
            stmt.close();
            con.close();
            return retorno;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public Boolean updateUsuario (User user) {
        PreparedStatement stmt = null;
        
        try {
            try {
                // eu controlo as TRANSAÇÕES com o banco, por isso colocamos auto commit para false
                con.setAutoCommit(false);
                
                
                
                String sql = "update usuario set senha = ? where codUser = ?";
                // trocar os parametros
                stmt = con.prepareStatement(sql);
                String senhaHash = HashSal.getPasswordDigest(user.getSenha());
                stmt.setString(1, senhaHash);
                stmt.setInt(2, user.getCodUser());
                //executar script
                stmt.execute();
                // efetivar a transaçao
                con.commit(); // <- importante

                return true;
            } catch (SQLException e) {
                try {
                    e.printStackTrace();
                    // cancela a trnsação se deu erro
                    con.rollback();
                    return false;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return false;
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
                return false;
            } 
        }
    }
    
    public ArrayList<User> getListaUserQualquer(String nome) {
        Statement stmt = null; // usado para rodar SQL
        ArrayList<User> listaUser = new ArrayList<User>();

        try {
            // cria o objeto para rodar o SQL
            stmt = con.createStatement();
            // passando a string SQL que faz o SELECT
            ResultSet result = stmt.executeQuery("select * from usuario where (idUser like '%" + nome + "%' OR nameUser like '%" + nome + "%' OR email like '%" + nome + "%' OR dateNasc like '%" + nome + "%') ");

            while (result.next()) {                
                // criar uma marca e adicionar na lista
                
                User user = new User(result.getString("idUser"),
                                     result.getInt("codUser"),
                                     result.getString("email"),
                                     result.getString("senha"),
                                     result.getString("nameUser"),
                                     result.getDate("dateNasc"),
                                     result.getBytes("photoUser"));
                listaUser.add(user);
                System.out.println(user);
            }
            
            result.close();// fechando o resultado
            stmt.close();// fechando statment
            con.close(); // fechando conexão com o banco
            return listaUser; // retornando a lista de marcas
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + "-" + e.getMessage());
            return null;
        }

    }
    
     public int excluir(User usu) {
        //vai receber o script SQL de INSERT 
        PreparedStatement stmt = null;
        try {
            try {
                // desliga o autocommit
                con.setAutoCommit(false);
                // o ? será substituído pelo valor
                String sql = "delete from usuario where codUser = ? ";
                stmt = con.prepareStatement(sql);
                //substituir os ? do script SQL
                stmt.setInt(1, usu.getCodUser());
                
                //executar o SCRIPT SQL
                stmt.execute();
                //efetivar a transação
                con.commit();
                return -1; // <- indica que tudo deu CERTO
            } catch (SQLException e) {
                try {
                    con.rollback(); // cancelando a transação 
                    return e.getErrorCode(); // devolvendo o erro
                } catch (SQLException ex) {
                    return ex.getErrorCode();
                }
            }
        } finally {// isto será executado dando ERRO ou NÃO
            try {
                stmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                return e.getErrorCode();
            }
        }
    }
}
