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
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.twilio.Twilio;
import java.io.IOException;
import java.util.ArrayList;

//import java.util.logging.Level;
//import java.util.logging.Logger;
import tn.esprit.jobtopia.JobTopia;
import static tn.esprit.jobtopia.JobTopia.theme;
import tn.esprit.jobtopia.entities.Freelancer;
import tn.esprit.jobtopia.services.ServiceFreelancer;

/**
 *
 * @author Administrateur
 */
public class ListFreelancerForm extends Form {

    public static int freelancerid;

    public ListFreelancerForm() {

        Toolbar tb = this.getToolbar();
        Image icon = theme.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.SOUTH, new Label("JobTopia", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            this.show(); // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
        });
        tb.addMaterialCommandToSideMenu("Offres", FontImage.MATERIAL_WEB, e -> {

            new AcceuilOffreForm().show();
            // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
        });
        tb.addMaterialCommandToSideMenu("Conventions", FontImage.MATERIAL_SETTINGS, e -> { 
 
    new HomeConvention().show();
    // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
});tb.addMaterialCommandToSideMenu("Contacts", FontImage.MATERIAL_SETTINGS, e -> { 
 
    new ContactsForm().show();
    // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
});
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> {

            try {
                new ProfilClient().show();
                // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {

                //Logger.getLogger(AccueilFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tb.addMaterialCommandToSideMenu("Déconnecter", FontImage.MATERIAL_INFO, e -> {
            new LoginForm().show();
        });
        Form previous = new Form();
        setTitle("List Freelancers");
        setLayout(BoxLayout.y());
        TextField tfsearch = new TextField("", "search here");
        add(tfsearch);
        Button search = new Button("search");
        add(search);
        search.addActionListener((ActionEvent e) -> {
            removeAll();
            String searchQ = tfsearch.getText();
            System.out.println(searchQ);
            ArrayList<Freelancer> frs = ServiceFreelancer.getInstance().Search(searchQ);
            //System.out.println(frs);

            int n = 200;
            for (Freelancer t : frs) {
                n = n + t.getId();

                String urlMark = "http://localhost/" + t.getImagePath();

                EncodedImage enc = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
                Image img = URLImage.createToStorage(enc, "mark" + n + ".png", urlMark);

                ImageViewer imgProfilePic = new ImageViewer(img);

                add(imgProfilePic);
                addElement(t); // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        revalidate();
        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Freelancer> freelancers = ServiceFreelancer.getInstance().getAllTasks();
        int n = 0;
        for (Freelancer t : freelancers) {
            n = n + 1;

            String urlMark = "http://localhost/" + t.getImagePath();

            EncodedImage enc = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffff0000), true);
            Image img = URLImage.createToStorage(enc, "mark" + n + ".png", urlMark);

            ImageViewer imgProfilePic = new ImageViewer(img);

            add(imgProfilePic);

            addElement(t);
        }

        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void addElement(Freelancer fr) {

        //  CheckBox cb = new CheckBox(fr.getNom());
        //  cb.setEnabled(false);
        //  if (task.getStatus() == 1) {
        //      cb.setSelected(true);
        //  }
        add("Nom:   " + fr.getNom());

        add("Prenom:   " + fr.getPrenom());
        add("Categorie:    " + fr.getCategorie());
        add("Note:" +  Math.round(fr.getNote() * 100.0) / 100.0);
        Slider ratingSlider = new Slider();
        ratingSlider.setPreferredSize(new Dimension(300, 50));
        ratingSlider.setEditable(true);
        ratingSlider.setMinValue(0);
        ratingSlider.setMaxValue(5);

        ratingSlider.setProgress((int) fr.getNote());
        add(ratingSlider);
        ratingSlider.addActionListener(e -> {
            int rating = ratingSlider.getProgress();
            ServiceFreelancer.getInstance().Noter(fr.getId(), ratingSlider.getProgress());
            //refreshList();
            new ListFreelancerForm().show();
            System.out.println(rating);
            // ServiceFreelancer.getInstance().saveRating(fr.getId(), rating);
        });

//      add(FlowLayout.encloseCenter(createStarRankSlider()));
        Button btnDetails = new Button("Détails");

        add(btnDetails);
        btnDetails.addActionListener((ActionEvent e) -> {
            freelancerid = fr.getId();
            try {
                new DetailsFreelancerForm().show();
            } catch (IOException ex) {
                // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void refreshList() {
        removeAll();
        ArrayList<Freelancer> frs = ServiceFreelancer.getInstance().getAllTasks();
        for (Freelancer fr : frs) {
            addElement(fr);
        }
        revalidate();
    }

}
