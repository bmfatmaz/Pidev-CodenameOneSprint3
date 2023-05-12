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
import com.twilio.Twilio;
import java.io.IOException;
import tn.esprit.jobtopia.entities.CurrentUser;
import tn.esprit.jobtopia.entities.User;
import tn.esprit.jobtopia.services.ServiceUser;

/**
 *
 * @author Administrateur
 */
public class EnterUsername extends Form{
     public EnterUsername() {

        setTitle("Username Form");
        setLayout(new FlowLayout(CENTER, CENTER));

        TextField tfUsername = new TextField("", "Username");
     
        Button conf = new Button("Confirmer");
        Container cn = new Container(BoxLayout.y());

        conf.addActionListener(e -> {
            ServiceUser su = new ServiceUser();
            try {
//                if(tfUsername.getText()=="" ||tfPwd.getText()==""){
//                    Dialog.show("Warning", "Pseudo et mot de passe sont obligatoires! ", "OK", null);
//                }
                
                User user = su.validateUsername(tfUsername.getText());
                if (user.getId() != 0) {
                   
                   Twilio.init("ACe52426eb8abc7329bc01bf0a13fed53b", "c3801b5212c7b6b18ac970eea997a53b");

                        com.twilio.type.PhoneNumber to = new com.twilio.type.PhoneNumber(user.getTelephone());
                        com.twilio.type.PhoneNumber from = new com.twilio.type.PhoneNumber("+12762966633");

                        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(to, from, "Bienvenue. Veuillez confirmer votre identité par ce code"+EnterCodeForm.code).create();
         
      
                       new EnterCodeForm().show();
                    
                } else {
                    Dialog.show("Warning", "Invalid username ! ", "OK", null);

                }

            } catch (IOException ex) {
                // Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        

        cn.addAll(tfUsername, conf);
        add(cn);
    }

}
