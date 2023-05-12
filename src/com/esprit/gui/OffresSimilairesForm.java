/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Offre;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Salma Majeri
 */
public class OffresSimilairesForm extends Form {

    public OffresSimilairesForm(ArrayList<Offre> offresSimilaires) {
        setTitle("Offres similaires");
        setLayout(BoxLayout.y());

        for (Offre offre : offresSimilaires) {
            // Ajoutez ici les éléments d'affichage pour chaque offre similaire
            // Utilisez les méthodes add() pour ajouter des labels, des boutons, etc.
        }
    }
}
