/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

import static tn.esprit.jobtopia.JobTopia.theme;

/**
 *
 * @author Administrateur
 */
public class AccueilFreelancerForm extends Form{
    public  AccueilFreelancerForm(){
         Toolbar tb = this.getToolbar();
Image icon = theme.getImage("icon.png"); 
Container topBar = BorderLayout.east(new Label(icon));
topBar.add(BorderLayout.SOUTH, new Label("Cool App Tagline...", "SidemenuTagline")); 
topBar.setUIID("SideCommand");
tb.addComponentToSideMenu(topBar);

tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME,e -> {}); 
tb.addMaterialCommandToSideMenu("Website", FontImage.MATERIAL_WEB, e -> {});
tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> { 
 
             try {
                 new ProfilFreelancer().show();
                 // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
               
                 
                 
                 //Logger.getLogger(AccueilFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
             }
});
tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, e -> {});
        Form previous = new Form();
        setTitle("List Freelancers");
        setLayout(BoxLayout.y());}}
