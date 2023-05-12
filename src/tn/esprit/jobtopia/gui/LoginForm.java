/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;

import tn.esprit.jobtopia.entities.CurrentUser;

import tn.esprit.jobtopia.entities.User;
import tn.esprit.jobtopia.services.ServiceUser;

/**
 *
 * @author Administrateur
 */
public class LoginForm extends Form {

    public LoginForm() {

        setTitle("Login Form");
        setLayout(new FlowLayout(CENTER, CENTER));
   String urlMark = "http://localhost/logo.png";

            EncodedImage enc = EncodedImage.createFromImage(Image.createImage(500, 200, 0xffff0000), true);
            Image img = URLImage.createToStorage(enc, "logo.png", urlMark);

            ImageViewer imgProfilePic = new ImageViewer(img);

            add(imgProfilePic);
        TextField tfUsername = new TextField("", "Username");
        TextField tfPwd = new TextField("", "Password", 0, TextField.PASSWORD);
        Button btnLogin = new Button("Se connecter");
        Container cn = new Container(BoxLayout.y());

        btnLogin.addActionListener(e -> {
            ServiceUser su = new ServiceUser();
            try {
//                if(tfUsername.getText()=="" ||tfPwd.getText()==""){
//                    Dialog.show("Warning", "Pseudo et mot de passe sont obligatoires! ", "OK", null);
//                }
                
                User user = su.login(tfUsername.getText(), tfPwd.getText());
                if (user.getId() != 0) {
                   
                    if (user.getRole().equals("CLIENT")) {
                        CurrentUser.getInstance().setId(user.getId());
                        new ListFreelancerForm().show();
                    } else {
                         CurrentUser.getInstance().setId(user.getId());
                       new ListOffreForm().show();
                    }
                } else {
                    Dialog.show("Warning", "Invalid username or password ! ", "OK", null);

                }

            } catch (IOException ex) {
                // Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         Button btn = new Button("Sign up Freelancer");
          btn.addActionListener(e -> {
            try {
               new SignupFreelancerForm().show(); // Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
              //  Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
          Button btnC = new Button("Sign up Client");
          btnC.addActionListener(e -> {
            try {
                new SignupClientForm().show(); // Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
              //  Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          Button mdp = new Button("mot de passe oublié");
          mdp.addActionListener(e -> {
              new EnterUsername().show(); // Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        });

        cn.addAll(tfUsername, tfPwd, btnLogin,btn,btnC,mdp);
        add(cn);
    }

}
