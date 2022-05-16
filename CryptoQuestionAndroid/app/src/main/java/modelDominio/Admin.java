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
public class Admin extends User implements Serializable {
    private static final long serialVersionUID = 123456789L;
    
    public Admin(String idUser, int codUser, String email, String senha, String nameUser, Date dateNasc, byte[] photoUser) {
        super(idUser, codUser, email, senha, nameUser, dateNasc, photoUser);
    }    
}
