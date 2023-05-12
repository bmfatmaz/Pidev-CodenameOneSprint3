/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.Vector;
import tn.esprit.jobtopia.entities.CurrentUser;
import tn.esprit.jobtopia.entities.Offre;
import tn.esprit.jobtopia.services.ServiceOffres;

/**
 *
 * @author Administrateur
 */
public class AjouterOffreForm extends Form  {
    
    public AjouterOffreForm() throws IOException {
    
Form previous = new AcceuilOffreForm();
        setTitle("Ajouter Offre");
        setLayout(new FlowLayout(CENTER, CENTER));
      //  System.out.println(CurrentUser.getInstance().getId());
         //      Freelancer fr = ServiceFreelancer.getInstance().getFreelancer(CurrentUser.getInstance().getId());

       // TextField tfUsername = new TextField(fr.getUsername());
       Offre fr= new Offre();
        TextField tfTitre = new TextField(fr.getTitre(),"titre");
     tfTitre.addDataChangedListener((int type, int index) -> {
    String text = tfTitre.getText();
   
});

       Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        TextField tfDsc = new TextField(fr.getDescription(),"Description"); 
         TextField tfsk1 = new TextField(fr.getSkill1(),"Skill1");
           TextField tfsk2 = new TextField(fr.getSkill2(),"Skill2");
             TextField tfsk3 = new TextField(fr.getSkill3(),"Skill3");
       

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
      f.setSkill1(tfsk1.getText());
      f.setSkill2(tfsk2.getText());
      f.setSkill3(tfsk3.getText());
    f.setClientId(CurrentUser.getInstance().getId());

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
         
        } else {
            Boolean mod = su.Ajout(f);
            Dialog.show("Success", "Offre ajoutée avec succès !", "OK", null);
            new OffreClientForm().show();
        }

    
});
        
       cn.addAll(tfTitre,tfDsc,categorie,tfsk1,tfsk2,tfsk3, btnLogin);
        add(cn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
   
    
}

