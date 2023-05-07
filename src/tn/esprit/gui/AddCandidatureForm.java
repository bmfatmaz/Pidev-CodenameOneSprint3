/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import entities.Candidature;
import java.io.IOException;
import java.util.Vector;
import services.ServiceCandidature;

/**
 *
 * @author HP
 */
public class AddCandidatureForm extends Form {

    private String filePath;

    public AddCandidatureForm(Form previous) {
        setTitle("Ajouter une candidature");
        setLayout(BoxLayout.y());

        TextField tfLettreMotivation = new TextField("", "Lettre De Motivation");
        TextField tfOffreId = new TextField("", "offreID ");
        TextField tfFreelancerId = new TextField("", "freelancerID ");
        CheckBox etat = new CheckBox("Etat");
        Button fileSelectButton = new Button("Select File");
        fileSelectButton.addActionListener(e -> {
            Display.getInstance().openGallery((ActionListener) (ActionEvent evt) -> {
                if (evt != null && evt.getSource() != null) {
                    filePath = (String) evt.getSource();
                }
            }, Display.GALLERY_IMAGE);
        });

        Button btnConfirmer = new Button("Confirmer la candidature");

        btnConfirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent t) {
                String lettreMotivation = tfLettreMotivation.getText();
                if (lettreMotivation.equals("")) {
                    Dialog.show("Error", "Lettre motivation non valide", "OK", null);
                    return; // exit the method
                }
                String filePath = AddCandidatureForm.this.filePath;
                if (filePath == null || filePath.equals("")) {
                    Dialog.show("Error", "CV non valide", "OK", null);
                    return; // exit the method
                }
                int freelancerId = 0;
                try {
                    freelancerId = Integer.parseInt(tfFreelancerId.getText());
                } catch (NumberFormatException ex) {
                    Dialog.show("Error", "Freelancer id non valide", "OK", null);
                    return; // exit the method
                }
                int offreId = 0;
                try {
                    offreId = Integer.parseInt(tfOffreId.getText());
                } catch (NumberFormatException ex) {
                    Dialog.show("Error", "Offre id non valide", "OK", null);
                    return; // exit the method
                }
                Candidature candidature = new Candidature(freelancerId, offreId, lettreMotivation, filePath);
                if (ServiceCandidature.getInstance().AjouterCandidature(candidature)) {
                    Dialog.show("Success", "Candidature ajouté", "OK", null);
                } else {
                    Dialog.show("Error", "Request Error", "Ok", null);
                }
            }
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });

        addAll(tfFreelancerId, tfLettreMotivation, tfOffreId, fileSelectButton, etat, btnConfirmer);
    }
    /*public AddCandidatureForm() throws IOException {

        setTitle("Ajout Form");
        setLayout(new FlowLayout(CENTER, CENTER));
        //  System.out.println(CurrentUser.getInstance().getId());
        //      Freelancer fr = ServiceFreelancer.getInstance().getFreelancer(CurrentUser.getInstance().getId());

        // TextField tfUsername = new TextField(fr.getUsername());
        Candidature fr = new Candidature();
        TextField tfLettreMotivation = new TextField(fr.getLettreMotivation(), "lettremotivation");
        tfLettreMotivation.addDataChangedListener((int type, int index) -> {
            String text = tfLettreMotivation.getText();

        });

//        Picker datePicker = new Picker();
//        datePicker.setType(Display.PICKER_TYPE_DATE);
        Button fileSelectButton = new Button("Select File");
        fileSelectButton.addActionListener(e -> {
            Display.getInstance().openGallery((ActionListener) (ActionEvent evt) -> {
                if (evt != null && evt.getSource() != null) {
                    filePath = (String) evt.getSource();
                }
            }, Display.GALLERY_IMAGE);
        });
        TextField tfOffreId = new TextField(Integer.toString(fr.getOfferID()));
        tfOffreId .setHint("offreID");
        TextField tfFreelancerID = new TextField(Integer.toString(fr.getfreelancerID()));
        tfOffreId .setHint("FreelancerID");

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

        ComboBox<String> categorie = new ComboBox<>(vectorCat);

        Button btnLogin = new Button("Ajouter");
        Container cn = new Container(BoxLayout.y());

        btnLogin.addActionListener((ActionEvent e) -> {
            ServiceCandidature su = new ServiceCandidature();
            Candidature f = new Candidature();
            String filePath = AddCandidatureForm.this.filePath;
            int OffreId = Integer.parseInt(tfOffreId.getText());
            int FreelancerID = Integer.parseInt(tfFreelancerID.getText());
            f.setLettreMotivation(tfLettreMotivation.getText());
            //f.setCv(filePath);
            f.setOfferID(OffreId);
            f.setfreelancerID(FreelancerID);
            
            if (f.getLettreMotivation().length() < 3) {
                Dialog.show("Warning", "La lettre de motivation doit contenir au moins 3 caractères", "OK", null);
                tfLettreMotivation.getStyle().setFgColor(ColorUtil.BLUE);
            } else if (f.getCv().length() < 0) {
                Dialog.show("Warning", "CV non valide ", "OK", null);
                fileSelectButton.getStyle().setFgColor(ColorUtil.BLUE);
            } else {

                Boolean mod = su.Ajout(f);
                Dialog.show("Success", "Offre ajoutée avec succès !", "OK", null);
            }
        });
        cn.addAll(tfFreelancerID, tfOffreId, fileSelectButton, tfLettreMotivation, btnLogin);
        add(cn);
    }*/

}
