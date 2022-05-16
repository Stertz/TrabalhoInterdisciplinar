/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ConexaoController;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author lucas
 */
public class CryptoQuestionClient {
    
    public static ConexaoController ccont;
    
    public static void main(String[] args) {
        Socket socket;
        ObjectInputStream in;
        ObjectOutputStream out;
        
        try {
            socket = new Socket("127.0.0.1", 12345);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            ccont = new ConexaoController(socket, in, out);
            ScreenLogin screenLogin = new ScreenLogin();
            screenLogin.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}