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
import java.util.ArrayList;

/**
 *
 * @author Salma Majeri
 */
public class DetailsOffreFrom extends Form {
        static int n;
public DetailsOffreFrom() throws IOException {
    n = n + 1;
    Form previous = new ListOffreForm();
    setTitle("Détails Offre");
    setLayout(BoxLayout.y());
    Offre fr = ServiceOffres.getInstance().getOffre();
   
   
    // Récupérer les offres similaires
    ArrayList<Offre> similarOffres = ServiceOffres.getInstance().getSimilarOffres();
    addSimilarOffres(similarOffres);
   
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}

public void addSimilarOffres(ArrayList<Offre> offres) {
    add("Offres similaires:");
    for (Offre offre : offres) {
        add("Titre:   " + offre.getTitre());
        add("Description:   " + offre.getDescription());
        add("Categorie:    " + offre.getCategorie());
        add("Compétence1:   " + offre.getSkill1());
        add("Compétence2:   " + offre.getSkill2());
        add("Compétence3:    " + offre.getSkill3());
        add("-------------------------------");
    }
}
}
    
