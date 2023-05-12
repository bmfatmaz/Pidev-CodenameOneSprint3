/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import static tn.esprit.jobtopia.JobTopia.theme;
import tn.esprit.jobtopia.entities.CurrentUser;

/**
 *
 * author ASUS
 */
public class HomeConvention extends Form {
    public HomeConvention() {
        
        setTitle("Accueil");
        setLayout(BoxLayout.y());
          Toolbar tb = this.getToolbar();
Image icon = theme.getImage("icon.png"); 
Container topBar = BorderLayout.east(new Label(icon));
topBar.add(BorderLayout.SOUTH, new Label("JobTopia", "SidemenuTagline")); 
topBar.setUIID("SideCommand");
tb.addComponentToSideMenu(topBar);

tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> { new ListFreelancerForm().show(); // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
}); 
tb.addMaterialCommandToSideMenu("Offres", FontImage.MATERIAL_WEB, e -> { 
 
    new AcceuilOffreForm().show();
    // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
});
tb.addMaterialCommandToSideMenu("Conventions", FontImage.MATERIAL_SETTINGS, e -> { 
 
    new HomeConvention().show();
    // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
});
tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> { 
 
             try {
                 new ProfilClient().show();
                 // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
               
                 
                 
                 //Logger.getLogger(AccueilFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
             }});
tb.addMaterialCommandToSideMenu("Déconnecter", FontImage.MATERIAL_INFO, e -> { new LoginForm().show();});
      
        Button btnListeConvention = new Button("Liste des Conventions");
        Button btnAjoutConvention = new Button("Ajouter une Convention");
        
        btnListeConvention.addActionListener(e -> new ListeConventionForm(CurrentUser.getInstance().getId()).show());
        btnAjoutConvention.addActionListener(e -> new AjouterConventionForm(this).show());
        
        addAll(btnListeConvention, btnAjoutConvention);
    }
    
}
