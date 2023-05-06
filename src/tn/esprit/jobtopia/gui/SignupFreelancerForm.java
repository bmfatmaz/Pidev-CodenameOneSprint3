/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import java.util.Vector;
import tn.esprit.jobtopia.entities.CurrentUser;
import tn.esprit.jobtopia.entities.Freelancer;
import tn.esprit.jobtopia.services.ServiceFreelancer;

/**
 *
 * @author Administrateur
 */
public class SignupFreelancerForm extends Form {
     public SignupFreelancerForm() throws IOException {
    

        setTitle("Modification Form");
        setLayout(new FlowLayout(CENTER, CENTER));
      //  System.out.println(CurrentUser.getInstance().getId());
         //      Freelancer fr = ServiceFreelancer.getInstance().getFreelancer(CurrentUser.getInstance().getId());

       // TextField tfUsername = new TextField(fr.getUsername());
       Freelancer fr= new Freelancer();
        TextField tfNom = new TextField(fr.getNom(),"Nom");
        TextField tfPrenom = new TextField(fr.getPrenom(),"Prenom");
         TextField tfUsername = new TextField(fr.getUsername(),"Username");
         TextField tfEmail = new TextField(fr.getEmail(),"Email");
         TextField tfTelephone = new TextField(fr.getTelephone(),"Telephone");
         TextField password = new TextField(fr.getPassword(),"Password",0,TextField.PASSWORD);
        TextField tfDsc = new TextField(fr.getDescription(),"Description");
              //  TextField cat = new TextField(fr.getCategorie());
      Vector<String> vectorCat;
        vectorCat = new Vector();
         vectorCat.add("");
        vectorCat.add("WEB");
        vectorCat.add("TRADUCTION");
        vectorCat.add("DESIGN");
        vectorCat.add("RECADTION");
        vectorCat.add("AI");
        vectorCat.add("PHOTOGRAPHIE");
        
        
        ComboBox<String>categorie = new ComboBox<>(vectorCat);
       // ComboBox<String> cat = new ComboBox();
         
       //       EnumSet.allOf(Categorie.class).stream().forEach(s -> cat.addItem(s.toString()));
        
        //TextField tfPwd = new TextField("", TextField.PASSWORD);
        Button btnLogin = new Button("Ajouter");
        Container cn = new Container(BoxLayout.y());
       
        btnLogin.addActionListener(e->{
            ServiceFreelancer su= new ServiceFreelancer();
             Freelancer f=new Freelancer();
      
        f.setNom(tfNom.getText());
        f.setPassword(password.getText());
        f.setPrenom(tfPrenom.getText());
        f.setUsername(tfUsername.getText());
        f.setEmail(tfEmail.getText());
        f.setTelephone(tfTelephone.getText());
        f.setDescription(tfDsc.getText());
        f.setCategorie(categorie.getSelectedItem().toString());
        Boolean mod = su.Ajout(f); // Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
             Dialog.show("Warning", "Profil Ajouté ! ", "OK", null);
        });
        
        cn.addAll(tfNom,tfPrenom,tfUsername,tfEmail,tfTelephone,password,tfDsc,categorie, btnLogin);
        add(cn);        
    }
}
