package util;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;
import modelDominio.User;

public class EnvioDeEmail {
    public String enviarEmail (User user) throws Exception{
        // Define email e senha do emissor da mensagem
        String myAddress = "CryptoQuestion@outlook.com";
        String myPassword = "LucasJoaoPedro";
        // Define o email do destinatário da mensagem
        
        String email = user.getEmail();
        String receiver = email;

        // Configura as propriedades do Outlook
        Properties prop = setProperties();

        // Cria uma nova seção, autenticada com email e senha
        Session session = getSession(prop, myAddress, myPassword);

        String codigo;
        String maiusculo = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String minusculo = "abcdefghijklmnopqrstuvwxyz";
        String num = "0123456789";
        String combinacao = maiusculo + minusculo + num;
        int len = 5;
        char[] senha = new char[len];
        Random r = new Random();
        for (int i = 0; i < len; i++) {
            senha[i] = combinacao.charAt(r.nextInt(combinacao.length()));
        }
        codigo = new String(senha);

        String subject = "Código de Recuperação de Senha";
        String bodyMsg = "O código de verificação é: " + codigo;
        Message message = createMessage(session, myAddress, receiver, subject, bodyMsg);
        Transport.send(message);
        System.out.println( "Mensagem enviada!" );
        return codigo;
    }

    private static Properties setProperties(){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.office365.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return prop;
    }

    private static Session getSession(Properties prop, String myAddress, String myPassword){
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAddress, myPassword);
            }
        });
        return session;
    }

    private static Message createMessage(Session session, String myAddress, String receiver, String subject, String bodyMsg) throws MessagingException {
        // Define o emissor da mensagem
        InternetAddress me = new InternetAddress(myAddress);
        // Cria uma mensagem com a seção e define o emissor
        Message message = new MimeMessage(session);
        message.setFrom(me);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        // Define o assunto da mensagem
        message.setSubject(subject);

        // Define o corpo da mensagem
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        String mimeType = "text/html;charset=UTF-8";
        mimeBodyPart.setContent(bodyMsg, mimeType);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        return message;
    }
}
