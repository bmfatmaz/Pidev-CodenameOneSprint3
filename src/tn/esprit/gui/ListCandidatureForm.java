/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.entities.Candidature;
import java.io.IOException;
import java.util.ArrayList;
import tn.esprit.services.ServiceCandidature;

/**
 *
 * @author HP
 */
public class ListCandidatureForm extends Form {


    public static int cadidid;

    public ListCandidatureForm() {
        Form previous = new Form();
        setTitle("Liste des candidatures");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Candidature> candidature = ServiceCandidature.getInstance().ShowAllCandidatures();
        int n = 0;
        for (Candidature c : candidature) {
            n = n + 1;
            addElement(c);

        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Candidature ca) {

        //  CheckBox cb = new CheckBox(fr.getNom());
        //  cb.setEnabled(false);
        //  if (task.getStatus() == 1) {
        //      cb.setSelected(true);
        //  }
        add("Lettre Motivation:   " + ca.getLettreMotivation());

        add("Cv:   " + ca.getCv());

        add("Etat:   " + ca.getEtatID());


    }

}
