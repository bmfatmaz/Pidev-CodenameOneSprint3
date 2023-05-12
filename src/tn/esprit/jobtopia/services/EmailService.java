/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.services;

/**
 *
 * @author ASUS
 */


public class EmailService {

  

   /* public static void sendEmail(String recipient, boolean accepted) {
        final String username = "jobtopia594@gmail.com";
        final String password = "deljdmeiuxqvmthd";
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient)
            );
            if (accepted) {
                //Convention convention = new Convention(); 
                message.setSubject("Jobtopia Convention Accepted");
                message.setText("Dear Client ,\n\n"
                        + "Your convention request has been accepted by the freelancer. Please proceed with the job as agreed.\n\n"
                        + "Best regards,\nJobtopia Team");
                //String htmlBody = "<html><body>"
                //+ "<h1 style='color:#2d4065;'>Bienvenue sur Jobtopia " + convention.getClientID() + "!</h1>"
                //+ "</body></html>";
                //message.setContent(htmlBody, "text/html");

            } else {
                message.setSubject("Jobtopia Convention Refused");
                message.setText("Dear Client,\n\n"
                        + "We regret to inform you that your convention request has been refused by the freelancer. We apologize for any inconvenience caused.\n\n"
                        + "Best regards,\nJobtopia Team");
            }

            Transport.send(message);

            System.out.println("Email sent to " + recipient + " successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sendEmailF(String recipient) {
        final String username = "jobtopia594@gmail.com";
        final String password = "deljdmeiuxqvmthd";
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient)
            );

            message.setSubject("Jobtopia Convention est En Attente");

            String htmlBody = "<html><body>"
                    + "<h1 style='color:#2d4065;'>Cher Freelancer,</h1>"
                    + "<p style='color:black;'>Votre demande de convention est maintenant en attente. Cela signifie que le client attend votre confirmation pour poursuivre le travail tel qu'il a été convenu.</p>"
                    + "<p style='color:black;'>Veuillez vérifier les détails de la convention et l'accepter ou la refuser dès que possible. Pour ce faire, veuillez vous connecter à votre compte Jobtopia et accéder à la section Conventions. Si vous avez des questions ou des préoccupations, veuillez nous contacter.</p>"
                    + "<p style='color:black;'>Cordialement,</p>"
                    + "<p style='color:black;'>L'équipe Jobtopia</p>"
                    + "</body></html>";
            message.setContent(htmlBody, "text/html");

            Transport.send(message);

            System.out.println("Email sent to " + recipient + " successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    

}
  public static void sendEmailF(String recipient) {
       final String username = "jobtopia594@gmail.com";
        final String password = "deljdmeiuxqvmthd";
        String host = "smtp.gmail.com";
*/
}