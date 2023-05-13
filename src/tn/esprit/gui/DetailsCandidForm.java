/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import tn.esprit.entities.Candidature;
import tn.esprit.services.ServiceCandidature;

/**
 *
 * @author HP
 */
public class DetailsCandidForm extends Form {

    public static int n;

    public DetailsCandidForm() throws IOException {

        Form previous = new ListCandidatureForm();
        setTitle("Détails Candidature");
        setLayout(BoxLayout.y());
        setTitle("List Candidature");
        setLayout(BoxLayout.y());
        Candidature ca = ServiceCandidature.getInstance().getOneCandidature(n);
        addElement(ca);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Candidature ca) {

        add("Lettre de motivation:   " + ca.getLettreMotivation());

        add("Etat:   " + ca.getEtatID());

        Button readLettreBtn = new Button("Read Lettre");
        readLettreBtn.addActionListener(e -> {
            try {
                Candidature c = ServiceCandidature.getInstance().readLettre(n);
            } catch (Exception ev) {
                System.out.println("aa333333");
            }

        });
        Button btnSupp = new Button("Supprimer");
        btnSupp.addActionListener((ActionEvent e) -> {
            if (Dialog.show("Confirmation", "Voulez-vous vraiment supprimer cette candidature ?", "Oui", "Non")) {

                boolean success = ServiceCandidature.getInstance().Del(n);
                if (success) {
                    Dialog.show("Success", "La candidature à été supprimée avec succes", "OK", null);
                } else {
                    Dialog.show("Erreur", "Une erreur s'est produite lors de la suppression de l'offre", "OK", null);
                }

            }
            new ListCandidatureForm().show();

        });
        Candidature c = new Candidature();
        Button btnModif = new Button("Modifier");
        btnModif.addActionListener((ActionEvent e) -> {
            modifCandidature.id = c.getId();
            try {
                new modifCandidature(n).show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        
        
        Button btnAccepter = new Button("Accepter");
        btnAccepter.addActionListener((ActionEvent e) -> {
            if (Dialog.show("Confirmation", "Voulez-vous vraiment accepter cette candidature ?", "Oui", "Non")) {

                Candidature success= ServiceCandidature.getInstance().Accepter(n);
                if (success.equals("Accepté")) {
                    Dialog.show("Success", "La candidature à été Acceptée avec succes", "OK", null);
                } else {
                    Dialog.show("Erreur", "Une erreur s'est produite lors de l'acceptation de l'candidature", "OK", null);
                }

            }
            new ListCandidatureForm().show();
        });
        
        
        Button btnRefuser = new Button("Refuser");
        btnRefuser.addActionListener((ActionEvent e) -> {
            if (Dialog.show("Confirmation", "Voulez-vous vraiment Refuser cette cadndiature ?", "Oui", "Non")) {

                boolean success = ServiceCandidature.getInstance().Refuser(n);
                if (success) {
                    Dialog.show("Success", "La candidature à été Refusée", "OK", null);
                } else {
                    Dialog.show("Erreur", "Une erreur s'est produite lors de la refus de l'candidature", "OK", null);
                }

            }
            new ListCandidatureForm().show();
        });

        addAll(readLettreBtn, btnSupp, btnModif);
    }

}
