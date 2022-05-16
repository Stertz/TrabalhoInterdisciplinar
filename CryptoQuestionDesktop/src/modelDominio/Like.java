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
public class Like implements Serializable {
    private static final long serialVersionUID = 123456789L;
    
    private User user;
    private Post post;
    private int codLike;
    private Date dateLike;

    public Like(User user, Post post, int codLike, Date dateLike) {
        this.user = user;
        this.post = post;
        this.codLike = codLike;
        this.dateLike = dateLike;
    }

    public Date getDateLike() {
        return dateLike;
    }

    public void setDateLike(Date dateLike) {
        this.dateLike = dateLike;
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

    public int getCodLike() {
        return codLike;
    }

    public void setCodLike(int codLike) {
        this.codLike = codLike;
    }
    
}
