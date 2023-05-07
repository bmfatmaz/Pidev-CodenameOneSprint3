/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author Salma Majeri
 */
public class AcceuilOffreForm extends Form {

    public AcceuilOffreForm() {
        setTitle("Accueil");
        setLayout(BoxLayout.y());
        setScrollableY(true); // make the form scrollable
        
        // create an empty container to push the content to the center of the page
        Container topSpacer = new Container();
        topSpacer.setUIID("ContainerTopSpacer"); // set a UIID to customize the style
        topSpacer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        // create the buttons
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

 

