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

    User user;
    Post post;
    String textComents;
    Date dateComents;

    public Coments(User user, Post post, String textComents, Date dateComents) {
        this.user = user;
        this.post = post;
        this.textComents = textComents;
        this.dateComents = dateComents;
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
