/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDominio;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lucas
 */
public class User implements Serializable {
    private static final long serialVersionUID = 123456789L;
    
    private String idUser;
    private int codUser;
    private String email;
    private String senha;
    private String nameUser;
    private Date dateNasc;
    private byte[] photoUser;

    public User(String idUser, int codUser, String email, String senha, String nameUser, Date dateNasc, byte[] photoUser) {
        this.idUser = idUser;
        this.codUser = codUser;
        this.email = email;
        this.senha = senha;
        this.nameUser = nameUser;
        this.dateNasc = dateNasc;
        this.photoUser = photoUser;
    }

    public User(int codUser) {
        this.codUser = codUser;
    }

    public User(String idUser, String email, String senha, String nameUser, Date dateNasc) {
        this.idUser = idUser;
        this.email = email;
        this.senha = senha;
        this.nameUser = nameUser;
        this.dateNasc = dateNasc;
    }

    public User(String idUser, int codUser, String email, String senha, String nameUser, Date dateNasc) {
        this.idUser = idUser;
        this.codUser = codUser;
        this.email = email;
        this.senha = senha;
        this.nameUser = nameUser;
        this.dateNasc = dateNasc;
    }
    
    public User(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public User(String email) {
        this.email = email;
    }

    public byte[] getPhotoUser() {
        return photoUser;
    }

    public void setPhotoUser(byte[] photoUser) {
        this.photoUser = photoUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getCodUser() {
        return codUser;
    }

    public void setCodUser(int codUser) {
        this.codUser = codUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Date getDateNasc() {
        return dateNasc;
    }

    public void setDateNasc(Date dateNasc) {
        this.dateNasc = dateNasc;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", codUser=" + codUser + ", email=" + email + ", senha=" + senha + ", nameUser=" + nameUser + ", dateNasc=" + dateNasc + ", photoUser=" + photoUser + '}';
    }   
}