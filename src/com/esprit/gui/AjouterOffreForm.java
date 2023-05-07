/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Offre;
import com.esprit.services.ServiceOffres;
import java.io.IOException;
import java.util.Vector;


/**
 *
 * @author Salma Majeri
 */
public class AjouterOffreForm extends Form  {
    
    public AjouterOffreForm() throws IOException {
    

        setTitle("Ajout Form");
        setLayout(new FlowLayout(CENTER, CENTER));
      //  System.out.println(CurrentUser.getInstance().getId());
         //      Freelancer fr = ServiceFreelancer.getInstance().getFreelancer(CurrentUser.getInstance().getId());

       // TextField tfUsername = new TextField(fr.getUsername());
       Offre fr= new Offre();
        TextField tfTitre = new TextField(fr.getTitre(),"titre");
     tfTitre.addDataChangedListener((int type, int index) -> {
    String text = tfTitre.getText();
   
});

//        Picker datePicker = new Picker();
//        datePicker.setType(Display.PICKER_TYPE_DATE);
        TextField tfDsc = new TextField(fr.getDescription(),"Description");
       TextField tfClient = new TextField(Integer.toString(fr.getClientId()));
        tfClient.setHint("clientId");

              //  TextField cat = new TextField(fr.getCategorie());
      Vector<String> vectorCat;
        vectorCat = new Vector();
         vectorCat.add("");
        vectorCat.add("WEB");
        vectorCat.add("TRADUCTION");
        vectorCat.add("DESIGN");
        vectorCat.add("RECADTION");
        vectorCat.add("AI");
        vectorCat.add("PHOTOGRAPHIE");
        
        
        ComboBox<String>categorie = new ComboBox<>(vectorCat);
       
        Button btnLogin = new Button("Ajouter");
        Container cn = new Container(BoxLayout.y());
       
        btnLogin.addActionListener((ActionEvent e)->{
            ServiceOffres su= new ServiceOffres();
             Offre f=new Offre();
      
        f.setTitre(tfTitre.getText());
    f.setDescription(tfDsc.getText());
    f.setCategorie(categorie.getSelectedItem().toString());
    int clientId = Integer.parseInt(tfClient.getText());
    f.setClientId(clientId);

   if (f.getTitre().length() < 3) {
        Dialog.show("Warning", "Le titre doit contenir au moins 3 caractères", "OK", null);
        tfTitre.getStyle().setFgColor(ColorUtil.BLUE);
    } else if (f.getDescription().length() < 10) {
        Dialog.show("Warning", "La description doit contenir au moins 10 caractères", "OK", null);
        tfDsc.getStyle().setFgColor(ColorUtil.BLUE);
    } else if (f.getCategorie().isEmpty()) {
        Dialog.show("Warning", "Veuillez sélectionner une catégorie", "OK", null);
        categorie.getStyle().setFgColor(ColorUtil.BLUE);
    } else {
   
        Boolean mod = su.Ajout(f);
        Dialog.show("Success", "Offre ajoutée avec succès !", "OK", null);
    }
});
        cn.addAll(tfTitre,tfDsc,categorie,tfClient, btnLogin);
        add(cn);        
    }
   
    
}

    
