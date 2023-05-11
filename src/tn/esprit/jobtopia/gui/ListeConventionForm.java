/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.util.Resources;
import com.google.zxing.qrcode.encoder.QRCode;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import javafx.scene.control.Separator;
import tn.esprit.jobtopia.entities.Convention;
import tn.esprit.jobtopia.services.ServiceConvention;
import java.util.Date;

public class ListeConventionForm extends Form {

    public static int conventionId;

    public ListeConventionForm(int clientId) {
        Form previous = new Form();
        setTitle("Liste des conventions");
        setLayout(BoxLayout.y());

        ArrayList<Convention> clientConventions = ServiceConvention.getInstance().getConventionsByClientId(clientId);
        for (Convention convention : clientConventions) {
            addElement(convention);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    //Convention convention = ServiceConvention.getInstance().getConventionsById(conventionId);
    public void addElement(Convention c) {

        Container conventionContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Label projectNameLabel = new Label(c.getProjectName());
        projectNameLabel.getStyle().setFgColor(0x0000ff); // Set text color to blue
        conventionContainer.add(projectNameLabel);

        Label freelancerLabel = new Label("Freelancer: " + c.getFreelancerUsername());
        conventionContainer.add(freelancerLabel);

        Label startDateLabel = new Label("Début: " + c.getStartDate());
        conventionContainer.add(startDateLabel);

        Label endDateLabel = new Label("Fin: " + c.getEndDate());
        conventionContainer.add(endDateLabel);

        Label paymentLabel = new Label("Montant: " + c.getPaymentAmount());
        conventionContainer.add(paymentLabel);

        Label etatLabel = new Label("Etat: " + c.getEtat());
        conventionContainer.add(etatLabel);
        add(conventionContainer);

        Button detailsButton = new Button("Détails");
        conventionContainer.add(detailsButton);
        detailsButton.addActionListener(e -> {
    // Show dialog with buttons
    Dialog dialog = new Dialog("Détails");

    // Add modify button
    Button modifyButton = new Button("Modifier");
    modifyButton.addActionListener(modifyEvent -> {
        // Handle modify event
        dialog.dispose();
    });
    dialog.add(modifyButton);

    // Add delete button
    Button deleteButton = new Button("Supprimer");
    deleteButton.addActionListener(deleteEvent -> {
        // Handle delete event
        dialog.dispose();
    });
    dialog.add(deleteButton);

    // Show dialog
    dialog.show();
});
        
    }

}
