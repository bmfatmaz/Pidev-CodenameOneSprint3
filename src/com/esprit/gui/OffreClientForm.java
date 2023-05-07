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
public class OffreClientForm extends Form {
    
      public OffreClientForm() {
        Form previous = new Form();
        setTitle("Liste Offres");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
          ArrayList<Offre> offres = ServiceOffres.getInstance().getTasks();
        int n = 0;
        for (Offre o : offres) {
            n = n + 1;
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
        
        add("Etat:   " +fr.getEtat());
       
         add("Date Création:   " +fr.getDateCreation());

        Button btnDetails = new Button("Détails");
        add(btnDetails);
}
}
