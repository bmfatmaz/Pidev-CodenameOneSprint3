/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import tn.esprit.jobtopia.entities.Client;
import tn.esprit.jobtopia.entities.CurrentUser;
import tn.esprit.jobtopia.entities.Freelancer;
import tn.esprit.jobtopia.services.ServiceClient;
import tn.esprit.jobtopia.services.ServiceFreelancer;

/**
 *
 * @author Administrateur
 */
public class ProfilClient extends Form {
     static int n;
     public ProfilClient() throws IOException {
         n=n+1;
       // Form previous = new ListFreelancerForm();
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
       Client c = ServiceClient.getInstance().getClient(CurrentUser.getInstance().getId());
       

            String urlMark = "http://localhost/" + c.getImagePath();

            EncodedImage enc = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
            Image img = URLImage.createToStorage(enc, "pdp"+n+".png", urlMark);

            ImageViewer imgProfilePic = new ImageViewer(img);

            add(imgProfilePic);
            addElement(c);
        

     // System.out.println(ListFreelancerForm.freelancerid);

       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Client c) {

        //  CheckBox cb = new CheckBox(fr.getNom());
        //  cb.setEnabled(false);
        //  if (task.getStatus() == 1) {
        //      cb.setSelected(true);
        //  }
        
        add("Nom:   " +c.getNom());

        add("Prenom:   " +c.getPrenom());
     //   add("Categorie:    " +c.getCategorie());
          add("Email:    " +c.getEmail());
            add("Telephone:    " +c.getTelephone());
              add("Profession:    " +c.getProfession());
        
    Button btnModif = new Button();
    add(btnModif);
        btnModif.addActionListener(e -> {
            try {
                new ModifClient().show();
            } catch (IOException ex) {
               // Logger.getLogger(ProfilFreelancer.class.getName()).log(Level.SEVERE, null, ex);
            }
});

    }
}
