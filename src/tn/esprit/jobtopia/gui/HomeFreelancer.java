/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ASUS
 */
public class HomeFreelancer extends Form {

    public HomeFreelancer() {

        setTitle("Accueil");
        setLayout(BoxLayout.y());

        add(new Label("Bienvenue sur JobTopia"));
        Button btnListeConvention = new Button("Liste des Conventions");

        btnListeConvention.addActionListener(e -> new ListeConventionForm(1).show());

        addAll(btnListeConvention);
    }

}
