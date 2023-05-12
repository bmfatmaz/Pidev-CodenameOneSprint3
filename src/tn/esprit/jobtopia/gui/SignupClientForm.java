/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.capture.Capture;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import java.util.Vector;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import tn.esprit.jobtopia.entities.Client;
import tn.esprit.jobtopia.entities.Freelancer;
import tn.esprit.jobtopia.services.ServiceClient;
import tn.esprit.jobtopia.services.ServiceFreelancer;
import com.codename1.util.StringUtil;
import com.twilio.Twilio;
import com.twilio.rest.lookups.v1.PhoneNumber;
//import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Administrateur
 */
public class SignupClientForm extends Form {

    String selectedImage;
    ImageViewer imageIV;
    boolean nom, prenom, pseudo, pass, email, prof, numTel, PDPpressed = true;

    public SignupClientForm() throws IOException {

        setTitle("Signup Client Form");
        setLayout(new FlowLayout(CENTER, CENTER));
        //  System.out.println(CurrentUser.getInstance().getId());
        //      Freelancer fr = ServiceFreelancer.getInstance().getFreelancer(CurrentUser.getInstance().getId());

        // TextField tfUsername = new TextField(fr.getUsername());
        try {
            Client fr = new Client();
            TextField tfNom = new TextField(fr.getNom(), "Nom");
            TextField tfPrenom = new TextField(fr.getPrenom(), "Prenom");
            TextField tfUsername = new TextField(fr.getUsername(), "Username");
            TextField tfEmail = new TextField(fr.getEmail(), "Email");
            TextField tfTelephone = new TextField(fr.getTelephone(), "Telephone");
            TextField password = new TextField(fr.getPassword(), "Password", 0, TextField.PASSWORD);
            TextField tfDsc = new TextField(fr.getProfession(), "Profession");
            //  TextField cat = new TextField(fr.getCategorie());

            // ComboBox<String> cat = new ComboBox();
            //       EnumSet.allOf(Categorie.class).stream().forEach(s -> cat.addItem(s.toString()));
            //TextField tfPwd = new TextField("", TextField.PASSWORD);
            Button selectImageBtn = new Button();
            String urlMark = "http://localhost/woman-6446568e43864";

            EncodedImage enc = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
            Image img = URLImage.createToStorage(enc, "pdptest.png", urlMark);

            imageIV = new ImageViewer(img);
            selectImageBtn.addActionListener((ActionEvent evt) -> {

                selectedImage = Capture.capturePhoto(900, -1);
                System.out.println(selectedImage);
                try {
                    System.out.println(selectedImage);
                    imageIV.setImage(Image.createImage(selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Button btnLogin = new Button("Ajouter");
            Container cn = new Container(BoxLayout.y());
            Container cn1 = new Container(BoxLayout.y());
            btnLogin.addActionListener(e -> {
                try {
                    String email = tfEmail.getText().trim();
                    boolean isValidEmail = email.contains("@") && email.endsWith(".com");
                    if (tfNom.getText().equals("")) {
                        Dialog.show("Warning", "Nom obligatoire! ", "OK", null);
                        nom = false;
                    } //
                    //           
                    else if (tfPrenom.getText().equals("")) {
                        Dialog.show("Warning", "Prenom obligatoire ! ", "OK", null);
                        prenom = false;
                    } else if (tfUsername.getText().equals("")) {
                        Dialog.show("Warning", "Pseudo Obligatoire! ", "OK", null);
                        pseudo = false;
                    } //            
                    //
                    else if (!isValidEmail) {
                        Dialog.show("Warning", "Entrer une adresse mail valide ! ", "OK", null);
                        //  email = false;
                    } //
                    else if (!tfTelephone.getText().startsWith("+216") && tfTelephone.getText().length() != 8) {
                        Dialog.show("Warning", "Entrer un numéro valide ! ", "OK", null);
                        numTel = false;
                    } //
                    else if (tfDsc.getText().equals("")) {
                        Dialog.show("Warning", "Vous devez entrer votre profession ! ", "OK", null);
                        prof = false;
                    } //
                    else if (password.getText().length() < 8) {
                        Dialog.show("Warning", "Mot de passe doit avoir minimum 8 caractères ! ", "OK", null);

                        pass = false;
                    } else if (selectedImage == null) {
                        Dialog.show("Warning", "Entrez une photo de profil ! ", "OK", null);

                        pass = false;
                    } else {
                        ServiceClient su = new ServiceClient();
                        Client f = new Client();

                        f.setNom(tfNom.getText());
                        f.setPassword(password.getText());
                        f.setPrenom(tfPrenom.getText());
                        f.setUsername(tfUsername.getText());
                        f.setEmail(tfEmail.getText());
                        f.setTelephone(tfTelephone.getText());
                        f.setProfession(tfDsc.getText());
                        f.setImagePath(selectedImage);

                        Boolean aj = su.Ajout(f);
                        Session session;
                        Dialog.show("Warning", "Profil  Ajouté ! ", "OK", null);
//              String from = "fatmazahra.benhajminiaoui@esprit.tn";
//              
//                        String passwordmail = "223JFT3425";
//
//                        String to = "fatmabelhaj15@gmail.com";
//
//                        String host = "smtp.gmail.com";
//                        String port = "587";
//
////                        Properties properties = System.getProperties();
////                        properties.setProperty("mail.smtp.host", host);
////                        properties.setProperty("mail.smtp.port", port);
////                        properties.setProperty("mail.smtp.auth", "true");
////                        properties.setProperty("mail.smtp.starttls.enable", "true");
//                        session = Session.getDefaultInstance(new Properties(),
//                                new javax.mail.Authenticator() {
//                            protected PasswordAuthentication getPasswordAuthentication() {
//                                return new PasswordAuthentication(from, passwordmail);
//                            }
//                        });
//                        try {
//                            MimeMessage message = new MimeMessage(session);
//
//                            message.setFrom(new InternetAddress(from));
//
//                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//                            message.setSubject("Confirmation Sign Up");
//
//                            String htmlBody = "<html><body>"
//                                    + "<h1 style='color:#2d4065;'>Bienvenue sur Jobtopia !</h1>"
//                                    + "<p style='color:black;'>Hello</p>"
//                                    + "</body></html>";
//                            message.setContent(htmlBody, "text/html");
//                            Transport.send(message);
//                            System.out.println("Sent message successfully....");
//                        } catch (MessagingException mex) {
//                            mex.printStackTrace();
//                        }
                               
                    }
                } catch (Exception ex) {
                    Dialog.show("Warning", "Profil Non Ajouté", "OK", null);
                    cn1.removeAll(); //Logger.getLogger(SignupClientForm.class.getName()).log(Level.SEVERE, null, ex1);
                }

            });

            cn.addAll(tfNom, tfPrenom, tfUsername, tfEmail, tfTelephone, password, tfDsc, selectImageBtn, btnLogin);
            add(cn);
            add(cn1);
        } catch (Exception e) {
        }
    }
}
