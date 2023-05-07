/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
//import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import java.util.Vector;
import static tn.esprit.jobtopia.JobTopia.theme;
//import java.util.EnumSet;
import tn.esprit.jobtopia.entities.Categorie;
import tn.esprit.jobtopia.entities.CurrentUser;
import tn.esprit.jobtopia.entities.Freelancer;
import tn.esprit.jobtopia.entities.User;
import tn.esprit.jobtopia.services.ServiceFreelancer;
import tn.esprit.jobtopia.services.ServiceUser;

/**
 *
 * @author Administrateur
 */
public class ModifFreelancerForm extends Form{
     public ModifFreelancerForm() throws IOException {
    

        setTitle("Modification Form");
        setLayout(new FlowLayout(CENTER, CENTER));
         Toolbar tb = this.getToolbar();
Image icon = theme.getImage("icon.png"); 
Container topBar = BorderLayout.east(new Label(icon));
topBar.add(BorderLayout.SOUTH, new Label("Cool App Tagline...", "SidemenuTagline")); 
topBar.setUIID("SideCommand");
tb.addComponentToSideMenu(topBar);

tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {   try {
                 new ListOffreForm().show();
                 // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
               
                 
                 
                 //Logger.getLogger(AccueilFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
             }
 
            
}); 
tb.addMaterialCommandToSideMenu("Website", FontImage.MATERIAL_WEB, e -> {});
tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> { 
 
             try {
                 new ProfilFreelancer().show();
                 // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
               
                 
                 
                 //Logger.getLogger(AccueilFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
             }
});
tb.addMaterialCommandToSideMenu("Déconnecter", FontImage.MATERIAL_INFO, e -> { new LoginForm().show();});
        System.out.println(CurrentUser.getInstance().getId());
               Freelancer fr = ServiceFreelancer.getInstance().getFreelancer(CurrentUser.getInstance().getId());

       // TextField tfUsername = new TextField(fr.getUsername());
        TextField tfNom = new TextField(fr.getNom());
        TextField tfPrenom = new TextField(fr.getPrenom());
         TextField tfEmail = new TextField(fr.getEmail());
         TextField tfTelephone = new TextField(fr.getTelephone());
        TextField tfDsc = new TextField(fr.getDescription());
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
        Button btnLogin = new Button("Modifier");
        Container cn = new Container(BoxLayout.y());
       
        btnLogin.addActionListener(e->{
            ServiceFreelancer su= new ServiceFreelancer();
             Freelancer f=new Freelancer();
        f.setId(CurrentUser.getInstance().getId());
        f.setNom(tfNom.getText());
        f.setPrenom(tfPrenom.getText());
        f.setEmail(tfEmail.getText());
        f.setTelephone(tfTelephone.getText());
        f.setDescription(tfDsc.getText());
        f.setCategorie(categorie.getSelectedItem().toString());
        Boolean mod = su.Modif(f); // Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
             Dialog.show("Warning", "Profil modifié ! ", "OK", null);
        });
        
        cn.addAll(tfNom,tfPrenom,tfEmail,tfTelephone,tfDsc,categorie, btnLogin);
        add(cn);        
    }
}
