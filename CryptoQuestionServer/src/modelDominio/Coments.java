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
public class Coments implements Serializable {
    private static final long serialVersionUID = 123456789L;
    
    private int codComents;
    private User user;
    private Post post;
    private String textComents;
    private Date dateComents;

    public Coments(int codComents, User user, Post post, String textComents, Date dateComents) {
        this.codComents = codComents;
        this.user = user;
        this.post = post;
        this.textComents = textComents;
        this.dateComents = dateComents;
    }

    public Coments(int codComents) {
        this.codComents = codComents;
    }

    public int getCodComents() {
        return codComents;
    }

    public void setCodComents(int codComents) {
        this.codComents = codComents;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getTextComents() {
        return textComents;
    }

    public void setTextComents(String textComents) {
        this.textComents = textComents;
    }

    public Date getDateComents() {
        return dateComents;
    }

    public void setDateComents(Date dateComents) {
        this.dateComents = dateComents;
    }
    
    
}
