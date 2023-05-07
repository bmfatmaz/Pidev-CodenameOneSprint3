/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;

/**
 *
 * @author Administrateur
 */
public class AcceuilOffreForm extends Form {

    public AcceuilOffreForm() {
        setTitle("Accueil");
        setLayout(BoxLayout.y());
        setLayout(new FlowLayout(CENTER, CENTER));
        setScrollableY(true); 
        
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

 