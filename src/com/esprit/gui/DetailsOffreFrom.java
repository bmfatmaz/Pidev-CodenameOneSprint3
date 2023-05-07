/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Offre;
import static com.esprit.gui.ListOffreForm.offreid;
import com.esprit.services.ServiceOffres;
import java.io.IOException;

/**
 *
 * @author Salma Majeri
 */
public class DetailsOffreFrom extends Form {
        static int n;
    public DetailsOffreFrom() throws IOException {
           n=n+1;
        Form previous = new ListOffreForm();
        setTitle("Détails Offre");
        setLayout(BoxLayout.y());
        Offre fr = ServiceOffres.getInstance().getOffre();
       
        addElement(fr);
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Offre fr) {

       
        add("Titre:   " +fr.getTitre());
        add("Description:   " +fr.getDescription());
        add("Categorie:    " +fr.getCategorie());
        add("Compétence1:   " +fr.getSkill1());
        add("Compétence2:   " +fr.getSkill2());
        add("Compétence3:    " +fr.getSkill3());
        Button btnDetails = new Button("Postuler Facilement");
        add(btnDetails);
        
      
       // btnDetails.addActionListener(e -> {freelancerid=fr.getId();
        //        new AddItem(this).show()});

    }

    }
    
