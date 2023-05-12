/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import static tn.esprit.jobtopia.JobTopia.theme;

/**
 *
 * @author Administrateur
 */
public class AcceuilOffreForm extends Form {

    public AcceuilOffreForm() {
                        Toolbar tb = this.getToolbar();
Image icon = theme.getImage("icon.png"); 
Container topBar = BorderLayout.east(new Label(icon));
topBar.add(BorderLayout.SOUTH, new Label("Cool App Tagline...", "SidemenuTagline")); 
topBar.setUIID("SideCommand");
tb.addComponentToSideMenu(topBar);

tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> { new ListFreelancerForm().show(); // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
}); 
tb.addMaterialCommandToSideMenu("Offres", FontImage.MATERIAL_WEB, e -> { 
 
    new AcceuilOffreForm().show();
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
      setTitle("Accueil");
    setLayout(BoxLayout.y());
    setLayout(new FlowLayout(CENTER, CENTER));
    setScrollableY(true);

   int colorStart = 0x02B7EC; 
int colorEnd = 0x0092DF; 
int height = Display.getInstance().getDisplayHeight(); 
Style style = getStyle();
style.setBackgroundType(Style.BACKGROUND_GRADIENT_LINEAR_VERTICAL);
style.setBgColor(colorStart);
style.setBgTransparency(255); // Opaque
style.setBackgroundGradientEndColor(colorEnd);

        Container topSpacer = new Container();
        topSpacer.setUIID("ContainerTopSpacer");
        topSpacer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
     
        Button btnAjouterOffre = new Button("Ajouter Offre");
        Button btnListeOffres = new Button("Liste des Offres");
        
        btnAjouterOffre.addActionListener(e -> {
            try {
                new AjouterOffreForm().show();
            } catch (IOException ex) {
                // Gérer l'exception
            }
        });
        
        btnListeOffres.addActionListener(e -> new OffreClientForm().show());
                topSpacer.add(btnAjouterOffre);
        topSpacer.add(btnListeOffres);
   
        Container bottomSpacer = new Container();
        bottomSpacer.setUIID("ContainerBottomSpacer"); 
        bottomSpacer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
      
        add(topSpacer);
     
        add(bottomSpacer);
    }
    
}

 