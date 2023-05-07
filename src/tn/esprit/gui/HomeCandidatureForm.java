/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author HP
 */
public class HomeCandidatureForm extends Form {

    public HomeCandidatureForm() {
        
        setTitle("Home Page");
        setLayout(BoxLayout.y());
        Button btnAdd = new Button("Ajouter une candidature");
        Button btnShow = new Button("Afficher la liste des candidatures"); 
        
        btnAdd.addActionListener((evt) -> {
        });
        btnShow.addActionListener((evt) -> {
        });
        
        addAll(btnAdd,btnShow);
        
    }
    
}
