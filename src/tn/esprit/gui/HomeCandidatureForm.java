/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import tn.esprit.entities.Candidature;

/**
 *
 * @author HP
 */
public class HomeCandidatureForm extends Form {

    public HomeCandidatureForm() {

        setTitle("Home Page");
        setLayout(BoxLayout.y());
        Candidature c = new Candidature();
        Button btnAdd = new Button("Ajouter une candidature");
        Button btnShow = new Button("Afficher la liste des candidatures");
        btnAdd.addActionListener((ActionEvent e) -> {
            new AddCandidatureForm().show();

        });
        btnShow.addActionListener((ActionEvent e) -> {

            new ListCandidatureForm().show();

        });
        add(btnAdd);
        add(btnShow);

    }
}
