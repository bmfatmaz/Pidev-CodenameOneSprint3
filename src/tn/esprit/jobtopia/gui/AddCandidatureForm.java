/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.jobtopia.entities.Candidature;
import com.codename1.ext.filechooser.FileChooser;
import java.io.IOException;
import tn.esprit.jobtopia.entities.CurrentUser;

import tn.esprit.jobtopia.services.ServiceCandidature;

/**
 *
 * @author HP
 */
public class AddCandidatureForm extends Form {

    private String filePath;
public static int offreid;
    public AddCandidatureForm() throws IOException {
        Form previous=new DetailsOffreFrom();
        setTitle("Ajouter une candidature");
        setLayout(BoxLayout.y());

        TextArea tfLettreMotivation = new TextField("", "Lettre De Motivation");
      
        Button fileSelectButton = new Button("Select PDF");
        fileSelectButton.addActionListener(e -> {
            if (FileChooser.isAvailable()) {
                FileChooser.showOpenDialog(".pdf", e2 -> {
                    String file = (String) e2.getSource();
                    if (file == null) {
                        Dialog.show("Error", "No file was selected", "OK", null);
                    } else {
                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                        }
                        if ("pdf".equals(extension)) {
                            filePath = file;
                            Dialog.show("Success", "Selected file: " + filePath, "OK", null);
                        } else {
                            Dialog.show("Error", "Invalid file selected. Please select a PDF file", "OK", null);
                        }
                    }
                    
                });
            }
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
               
             
                String score = "";
                Candidature candidature = new Candidature(offreid, CurrentUser.getInstance().getId(), lettreMotivation, filePath, score);
                if (ServiceCandidature.getInstance().AjouterCandidature(candidature)) {
                    Dialog.show("Error", "Request Error", "OK", null);
                } else {
                    Dialog.show("Success", "Candidature ajouté", "Ok", null);
                }
            }
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });

        addAll( tfLettreMotivation, fileSelectButton, btnConfirmer);
    }

}
