/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

/**
 *
 * @author Administrateur
 */
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


import java.io.IOException;
import java.util.ArrayList;
import static tn.esprit.jobtopia.JobTopia.theme;
import tn.esprit.jobtopia.entities.Offre;
import tn.esprit.jobtopia.services.ServiceOffres;

/**
 *
 * @author Salma Majeri
 */
public class ListOffreForm extends Form{
    
    public static int offreid;
    public ListOffreForm() throws IOException {
        Form previous = new Form();
        setTitle("Offres Disponibles");
        setLayout(BoxLayout.y());
        
 Toolbar tb = this.getToolbar();
Image icon = theme.getImage("icon.png"); 
Container topBar = BorderLayout.east(new Label(icon));
topBar.add(BorderLayout.SOUTH, new Label("Cool App Tagline...", "SidemenuTagline")); 
topBar.setUIID("SideCommand");
tb.addComponentToSideMenu(topBar);

tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> { 
 
             try {
                 new ProfilFreelancer().show();
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
        

    
        ArrayList<Offre> offres = ServiceOffres.getInstance().getAllTasks();
        int n = 0;
        for (Offre o : offres) {
            n = n + 1;
//          String logoPath = "file:///C:/Users/Salma Majeri/jobtopia/public/Images/" + o.getLogoPath();
//            Image img = Image.createImage(logoPath);
//            ImageViewer imgProfilePic = new ImageViewer(img);
//            add(imgProfilePic);
  
            addElement(o);

        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    
    public void addElement(Offre fr) {

        //  CheckBox cb = new CheckBox(fr.getNom());
        //  cb.setEnabled(false);
        //  if (task.getStatus() == 1) {
        //      cb.setSelected(true);
        //  }
        
        add("Titre:   " +fr.getTitre());

        add("Description:   " +fr.getDescription());
        
        add("Categorie:   " +fr.getCategorie());

        Button btnDetails = new Button("Détails");
        add(btnDetails);
        btnDetails.addActionListener((ActionEvent e) -> {offreid=fr.getId();
          try{
              new DetailsOffreFrom().show();
          
          } catch (IOException ex) {
            System.out.println(ex.getMessage());
                  
          }
});

    }

    
}