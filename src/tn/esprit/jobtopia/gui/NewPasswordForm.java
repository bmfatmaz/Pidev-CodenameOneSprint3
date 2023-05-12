/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import tn.esprit.jobtopia.entities.User;
import tn.esprit.jobtopia.services.ServiceUser;

/**
 *
 * @author Administrateur
 */
public class NewPasswordForm extends Form{
       public NewPasswordForm() {

        setTitle("New Password Form");
        setLayout(new FlowLayout(CENTER, CENTER));

        TextField newpass = new TextField("", "New Password", 0, TextField.PASSWORD);
             TextField confirmpass = new TextField("", "Confirm Password", 0, TextField.PASSWORD);

        Button conf = new Button("Confirmer");
        Container cn = new Container(BoxLayout.y());

        conf.addActionListener(e -> {
            ServiceUser su = new ServiceUser();
            try {
//                if(tfUsername.getText()=="" ||tfPwd.getText()==""){
//                    Dialog.show("Warning", "Pseudo et mot de passe sont obligatoires! ", "OK", null);
//                }
                System.out.println(newpass.getText());
                if(!confirmpass.getText().equals(newpass.getText())||confirmpass.getText().equals("")||newpass.getText().equals("")){
                
                 Dialog.show("Warning", "Confirmer votre mot de passe correctement ! ", "OK", null);
                
                }
                su.newPassword(newpass.getText(),EnterCodeForm.id);
               
                  
                       new LoginForm().show();
                    
                

            } catch (IOException ex) {
                // Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        

        cn.addAll(newpass, confirmpass,conf);
        add(cn);
    }
}
