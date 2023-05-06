/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
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

        TextField tfUsername = new TextField("", "Email here...");
        TextField tfPwd = new TextField("", "Password here...", 0, TextField.PASSWORD);
        Button btnLogin = new Button("Se connecter");
        Container cn = new Container(BoxLayout.y());

        btnLogin.addActionListener(e -> {
            ServiceUser su = new ServiceUser();
            try {
                User user = su.login(tfUsername.getText(), tfPwd.getText());
                if (user.getId() != 0) {
                   
                    if (user.getRole().equals("CLIENT")) {
                        CurrentUser.getInstance().setId(user.getId());
                        new ListFreelancerForm().show();
                    } else {
                         CurrentUser.getInstance().setId(user.getId());
                        new AccueilFreelancerForm().show();
                    }
                } else {
                    Dialog.show("Warning", "Invalid username or password ! ", "OK", null);

                }

            } catch (IOException ex) {
                // Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        cn.addAll(tfUsername, tfPwd, btnLogin);
        add(cn);
    }

}
