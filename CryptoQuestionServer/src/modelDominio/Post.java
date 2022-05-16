/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelDominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author lucas
 */
public class Post implements Serializable {
    private static final long serialVersionUID = 123456789L;
    
    private User user;
    private int codPost;
    private String textPost;
    private Date datePost;
    private ArrayList<Coments> listaComents;
    private ArrayList<Like> listaLike;

    public Post(User user, int codPost, String textPost, Date datePost, ArrayList<Coments> listaComents, ArrayList<Like> listaLike) {
        this.user = user;
        this.codPost = codPost;
        this.textPost = textPost;
        this.datePost = datePost;
        this.listaComents = listaComents;
        this.listaLike = listaLike;
    }

    public Post(User user, int codPost, String textPost, Date datePost) {
        this.user = user;
        this.codPost = codPost;
        this.textPost = textPost;
        this.datePost = datePost;
    }

    public Post(User user, String textPost) {
        this.user = user;
        this.textPost = textPost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCodPost() {
        return codPost;
    }

    public void setCodPost(int codPost) {
        this.codPost = codPost;
    }

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String textPost) {
        this.textPost = textPost;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public ArrayList<Coments> getListaComents() {
        return listaComents;
    }

    public void setListaComents(ArrayList<Coments> listaComents) {
        this.listaComents = listaComents;
    }

    public ArrayList<Like> getListaLike() {
        return listaLike;
    }

    public void setListaLike(ArrayList<Like> listaLike) {
        this.listaLike = listaLike;
    }

    @Override
    public String toString() {
        return "Post{" + "user=" + user + ", codPost=" + codPost + ", textPost=" + textPost + ", datePost=" + datePost + ", listaComents=" + listaComents + ", listaLike=" + listaLike + '}';
    }
}