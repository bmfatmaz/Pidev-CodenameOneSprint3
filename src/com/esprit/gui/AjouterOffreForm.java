/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.capture.Capture;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
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
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
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
     String selectedImage;
    ImageViewer imageIV;
    public AjouterOffreForm() throws IOException {

setTitle("Ajouter Offre");
    setLayout(BoxLayout.y());

    Toolbar tb = new Toolbar();
    setToolbar(tb);

    // Ajouter des boutons à la barre de navigation
    Button menuButton = new Button("Menu");
  tb.addMaterialCommandToSideMenu("Page d'accueil", FontImage.MATERIAL_HOME, e -> {
    try {
        new AcceuilOffreForm().show();
    } catch (IOException ex) {
        // gestion de l'exception
    }
});
  
tb.addMaterialCommandToSideMenu("Liste Offres", FontImage.MATERIAL_LIST, e -> {
   
        new OffreClientForm().show();
   
});

       Offre fr= new Offre();
        TextField tfTitre = new TextField(fr.getTitre(),"titre");
     tfTitre.addDataChangedListener((int type, int index) -> {
    String text = tfTitre.getText();
   
});

       Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        TextField tfDsc = new TextField(fr.getDescription(),"Description");
      
         TextField tfsk1 = new TextField(fr.getDescription(),"Skill1");
           TextField tfsk2 = new TextField(fr.getDescription(),"Skill2");
             TextField tfsk3 = new TextField(fr.getDescription(),"Skill3");

              //  TextField cat = new TextField(fr.getCategorie());
      Vector<String> vectorCat;
        vectorCat = new Vector();
         vectorCat.add("Choisir une catégorie");
        vectorCat.add("WEB");
        vectorCat.add("TRADUCTION");
        vectorCat.add("DESIGN");
        vectorCat.add("RECADTION");
        vectorCat.add("AI");
        vectorCat.add("PHOTOGRAPHIE");
        
        
        ComboBox<String>categorie = new ComboBox<>(vectorCat);
        
      Button selectImageBtn = new Button("Choisir une image");
      String urlMark = "file:///C:/Users/Salma Majeri/jobtopia/public/Images/emploi.jpeg";

EncodedImage enc = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
Image img = URLImage.createToStorage(enc, "pdptestest.png", urlMark);

imageIV = new ImageViewer(img);
selectImageBtn.addActionListener((ActionEvent evt) -> {

    selectedImage = Capture.capturePhoto(900, -1);
    try {
        if (selectedImage != null) {
            System.out.println(selectedImage);
            imageIV.setImage(Image.createImage(selectedImage));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}); 


       
        Button btnLogin = new Button("Ajouter");
        Container cn = new Container(BoxLayout.y());
       
        btnLogin.addActionListener((ActionEvent e)->{
            ServiceOffres su= new ServiceOffres();
             Offre f=new Offre();
      
        f.setTitre(tfTitre.getText());
    f.setDescription(tfDsc.getText());
    f.setCategorie(categorie.getSelectedItem().toString());
      f.setSkill1(tfsk1.getText());
      f.setSkill2(tfsk2.getText());
      f.setSkill3(tfsk3.getText());
      
    
    f.setClientId(52);

   if (f.getTitre().length() < 3) {
        Dialog.show("Warning", "Le titre doit contenir au moins 3 caractères", "OK", null);
        tfTitre.getStyle().setFgColor(ColorUtil.BLUE);
    } else if (f.getDescription().length() < 10) {
        Dialog.show("Warning", "La description doit contenir au moins 10 caractères", "OK", null);
        tfDsc.getStyle().setFgColor(ColorUtil.BLUE);
    } else if (f.getCategorie().isEmpty()) {
        Dialog.show("Warning", "Veuillez sélectionner une catégorie", "OK", null);
        categorie.getStyle().setFgColor(ColorUtil.BLUE);
    } else if (f.getSkill1().isEmpty()) {
          Dialog.show("Warning", "Le compétence1 doit contenir au moins 3 caractères", "OK", null);
        tfsk1.getStyle().setFgColor(ColorUtil.BLUE);
    } else if (f.getSkill2().isEmpty()) {
          Dialog.show("Warning", "Le compétence2 doit contenir au moins 3 caractères", "OK", null);
        tfsk2.getStyle().setFgColor(ColorUtil.BLUE);
         } else if (f.getSkill3().isEmpty()) {
          Dialog.show("Warning", "Le compétence3 doit contenir au moins 3 caractères", "OK", null);
        tfsk3.getStyle().setFgColor(ColorUtil.BLUE);
         }  else {
        if (su.offreExists(f.getTitre()))  {
            Dialog.show("Warning", "L'offre avec le même titre existe déjà", "OK", null);
        } else {
            Boolean mod = su.Ajout(f);
            Dialog.show("Success", "Offre ajoutée avec succès !", "OK", null);
            new OffreClientForm().show();
        }

    }
});
        cn.addAll(tfTitre,tfDsc,categorie,tfsk1,tfsk2,tfsk3,selectImageBtn, btnLogin);
        add(cn);        
    }
   
    
}

    