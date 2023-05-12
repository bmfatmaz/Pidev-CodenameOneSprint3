/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.esprit.entities.Offre;
import com.esprit.services.ServiceOffres;
import java.io.IOException;
import java.util.ArrayList;




/**
 *
 * @author Salma Majeri
 */
public class OffreClientForm extends Form {
   public static int offreid;

public OffreClientForm() {
  
    setTitle("Liste Offres");
    setLayout(BoxLayout.y());

    Toolbar tb = new Toolbar();
    setToolbar(tb);

    // Ajouter des boutons à la barre de navigation
    Button menuButton = new Button("Menu");
  tb.addMaterialCommandToSideMenu("Page d'accueil", FontImage.MATERIAL_HOME, e -> {
    try {
        new AcceuilOffreForm().show();
    } catch (IOException ex) {
        // gestion de l'exception
    }
});
  
tb.addMaterialCommandToSideMenu("Ajouter Offre", FontImage.MATERIAL_ADD, e -> {
    try {
        new AjouterOffreForm().show();
    } catch (IOException ex) {
        // gestion de l'exception
    }
});

    ArrayList<Offre> offres = ServiceOffres.getInstance().getTasks();
    for (Offre o : offres) {
        addElement(o);
    }

   
}

public void addElement(Offre fr) {
    // Ajouter des éléments à la liste
    add("Titre:   " + fr.getTitre());
    add("Description:   " + fr.getDescription());
    add("Etat:   " + fr.getEtat());
    add("Date Création:   " + fr.getDateCreation());

    Button btnModif = new Button("Modifier");
    add(btnModif);
    btnModif.addActionListener((ActionEvent e) -> {
        offreid = fr.getId();
        try {
            new ModifierOffreForm(offreid).show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });

    Button btnSupp = new Button("Supprimer");
    add(btnSupp);
    Style style = btnSupp.getAllStyles();
    style.setFgColor(0xFF0000);
    btnSupp.addActionListener((ActionEvent e) -> {
        if (Dialog.show("Confirmation", "Voulez-vous vraiment supprimer cette offre ?", "Oui", "Non")) {
            boolean success = ServiceOffres.getInstance().supprimerOffre(fr);
            if (success) {
                refreshList();
            } else {
                Dialog.show("Erreur", "Une erreur s'est produite lors de la suppression de l'offre", "OK", null);
            }
        }
    });
}


public void refreshList() {
    removeAll();
    ArrayList<Offre> offres = ServiceOffres.getInstance().getTasks();
    for (Offre o : offres) {
        addElement(o);
    }
    revalidate();
}
}