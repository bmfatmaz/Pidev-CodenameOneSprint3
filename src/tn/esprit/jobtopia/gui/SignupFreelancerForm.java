/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import java.util.Vector;
import static tn.esprit.jobtopia.JobTopia.theme;
import tn.esprit.jobtopia.entities.CurrentUser;
import tn.esprit.jobtopia.entities.Freelancer;
import static tn.esprit.jobtopia.gui.ProfilFreelancer.n;
import tn.esprit.jobtopia.services.ServiceFreelancer;

/**
 *
 * @author Administrateur
 */
public class SignupFreelancerForm extends Form {

    String selectedImage;
    ImageViewer imageIV;
    boolean nom, prenom, pseudo, pass, email, prof, numTel, PDPpressed = true;

    public SignupFreelancerForm() throws IOException {

        setTitle("Signup Freelancer Form");
        setLayout(new FlowLayout(CENTER, CENTER));
          Form previous = new LoginForm();
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        try {
            Freelancer fr = new Freelancer();
            TextField tfNom = new TextField(fr.getNom(), "Nom");
            TextField tfPrenom = new TextField(fr.getPrenom(), "Prenom");
            TextField tfUsername = new TextField(fr.getUsername(), "Username");
            TextField tfEmail = new TextField(fr.getEmail(), "Email");
            TextField tfTelephone = new TextField(fr.getTelephone(), "Telephone");
            TextField password = new TextField(fr.getPassword(), "Password", 0, TextField.PASSWORD);
            TextField tfDsc = new TextField(fr.getDescription(), "Description");

            Vector<String> vectorCat;
            vectorCat = new Vector();
            vectorCat.add("");
            vectorCat.add("WEB");
            vectorCat.add("TRADUCTION");
            vectorCat.add("DESIGN");
            vectorCat.add("RECADTION");
            vectorCat.add("AI");
            vectorCat.add("PHOTOGRAPHIE");

            ComboBox<String> categorie = new ComboBox<>(vectorCat);

            Button selectImageBtn = new Button();
            String urlMark = "http://localhost/woman-6446568e43864";

            EncodedImage enc = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
            Image img = URLImage.createToStorage(enc, "pdptestest.png", urlMark);

            imageIV = new ImageViewer(img);
            selectImageBtn.addActionListener((ActionEvent evt) -> {

                selectedImage = Capture.capturePhoto(900, -1);
                try {
                    System.out.println(selectedImage);
                    imageIV.setImage(Image.createImage(selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Button btnLogin = new Button("Ajouter");
            Container cn = new Container(BoxLayout.y());

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
                    } 
                    else if (categorie.getSelectedItem()=="") {
                        Dialog.show("Warning", "Choisissez une categorie ! ", "OK", null);
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
                        ServiceFreelancer su = new ServiceFreelancer();
                        Freelancer f = new Freelancer();
                        f.setImagePath(selectedImage);
                        f.setNom(tfNom.getText());
                        f.setPassword(password.getText());
                        f.setPrenom(tfPrenom.getText());
                        f.setUsername(tfUsername.getText());
                        f.setEmail(tfEmail.getText());
                        f.setTelephone(tfTelephone.getText());
                        f.setDescription(tfDsc.getText());
                        f.setCategorie(categorie.getSelectedItem().toString());
                        Boolean mod = su.Ajout(f);
                        Dialog.show("Warning", "Profil  Ajouté ! ", "OK", null);
                    }
                } catch (Exception ex) {
                    Dialog.show("Warning", "Profil Non Ajouté", "OK", null);
                }
                
            });

            cn.addAll(tfNom, tfPrenom, tfUsername, tfEmail, tfTelephone, password, tfDsc, categorie, selectImageBtn, btnLogin);
            add(cn);
        } catch (Exception e) {
        }
    }
}
