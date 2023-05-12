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
public class EnterCodeForm extends Form {

    public static String code;
    public static int id;

    public EnterCodeForm() {
System.out.println(code);
        setTitle("Code Form");
        setLayout(new FlowLayout(CENTER, CENTER));

        TextField tfUsername = new TextField("", "Code");

        Button btnconf = new Button("Confirmer");
        Container cn = new Container(BoxLayout.y());
       

        btnconf.addActionListener(e -> {
            ServiceUser su = new ServiceUser();
            try {
//                if(tfUsername.getText()=="" ||tfPwd.getText()==""){
//                    Dialog.show("Warning", "Pseudo et mot de passe sont obligatoires! ", "OK", null);
//                }
 if (!tfUsername.getText().equals(code)) {
            Dialog.show("Warning", "Invalid Code ! ", "OK", null);

        }
                su.confirm(id);
                

                    new NewPasswordForm().show();

              

            } catch (IOException ex) {
                // Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        cn.addAll(tfUsername, btnconf);
        add(cn);
    }

}
