/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
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

        Form previous = new HomeCandidatureForm();
        setTitle("Liste des candidatures");
        setLayout(BoxLayout.y());

        ArrayList<Candidature> candidature = ServiceCandidature.getInstance().ShowAllCandidatures();
        for (Candidature c : candidature) {
            addElement(c);
            Button btnDetail = new Button("Details");
            btnDetail.addActionListener((ActionEvent e) -> {
                DetailsCandidForm.n = c.getId();
                try {
                    new DetailsCandidForm().show();

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());

                }

            });
            add(btnDetail);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });

    }

    public void addElement(Candidature ca) {

        add("Lettre de motivation:   " + ca.getLettreMotivation());

        add("Etat:   " + ca.getEtatID());

    }
    /*
    public void addCardView(Candidature ca) {
        Button btnDetail = new Button("Details");

        Container cardContainer = new Container(new BorderLayout());
        cardContainer.getStyle().setBorder(Border.createLineBorder(1, 0xaaaaaa));

        Label lettreMotivationLabel = new Label("Lettre Motivation: " + ca.getLettreMotivation());
        Label cvLabel = new Label("Cv: " + ca.getCv());
        Label etatLabel = new Label("Etat: " + ca.getEtatID());

        Container contentContainer = new Container(new GridLayout(3, 1));
        contentContainer.add(lettreMotivationLabel);
        contentContainer.add(cvLabel);
        contentContainer.add(etatLabel);

        cardContainer.add(BorderLayout.CENTER, contentContainer);
        btnDetail.addActionListener((ActionEvent e) -> {
            cadidid = ca.getId();
            try {
                new DetailsCandidForm().show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());

            }

        });
        addAll(cardContainer, btnDetail);

    }*/
}
