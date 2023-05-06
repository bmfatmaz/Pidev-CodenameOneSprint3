/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;

import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.util.ArrayList;
import tn.esprit.jobtopia.JobTopia;
import tn.esprit.jobtopia.entities.Freelancer;
import tn.esprit.jobtopia.services.ServiceFreelancer;

/**
 *
 * @author Administrateur
 */
public class DetailsFreelancerForm extends Form{
    static int n;
     public DetailsFreelancerForm() throws IOException {
         n=n+1;
        Form previous = new ListFreelancerForm();
        setTitle("Détails Freelancer");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        setTitle("List Freelancers");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
       Freelancer fr = ServiceFreelancer.getInstance().getFreelancer(ListFreelancerForm.freelancerid);
       

            String urlMark = "http://localhost/" + fr.getImagePath();

            EncodedImage enc = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
            Image img = URLImage.createToStorage(enc, "pdp"+n+".png", urlMark);

            ImageViewer imgProfilePic = new ImageViewer(img);

            add(imgProfilePic);
            addElement(fr);
        

     // System.out.println(ListFreelancerForm.freelancerid);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Freelancer fr) {

        //  CheckBox cb = new CheckBox(fr.getNom());
        //  cb.setEnabled(false);
        //  if (task.getStatus() == 1) {
        //      cb.setSelected(true);
        //  }
        
        add("Nom:   " +fr.getNom());

        add("Prenom:   " +fr.getPrenom());
        add("Categorie:    " +fr.getCategorie());
          add("Email:    " +fr.getEmail());
            add("Description:    " +fr.getDescription());
              add("Salaire:    " +fr.getSalaire());
        
      
       // btnDetails.addActionListener(e -> {freelancerid=fr.getId();
        //        new AddItem(this).show()});

    }
}
