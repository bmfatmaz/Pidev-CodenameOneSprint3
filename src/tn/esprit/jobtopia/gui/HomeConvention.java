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
public class HomeConvention extends Form {
    public HomeConvention() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnListeConvention = new Button("Liste des Conventions");
       
      
        Button btnAjoutConvention = new Button("Ajouter une Convention");
        
        //btnListeConvention.addActionListener(e-> new ListeConventionForm().show());
        //btnAjoutConvention.addActionListener(e-> new AjouterConventionForm(this).show());
        addAll(btnListeConvention);
        //,btnAjoutConvention
        
        
    }}
    

