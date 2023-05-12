/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
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
import com.codename1.ui.TextField;
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
import tn.esprit.jobtopia.services.Connection;

public class ListeConventionForm extends Form {

    public static int conventionId;
    private int clientId;

    public ListeConventionForm(int clientId) {
        this.clientId = clientId;
        Form previous = new Form();
        setTitle("Liste des conventions");
        setLayout(BoxLayout.y());

        TextField tfSearch = new TextField("", "Search by project name");
        add(tfSearch);
        Button searchButton = new Button("Search");
        add(searchButton);

        // Search button ActionListener
        searchButton.addActionListener(e -> {
            String searchQuery = tfSearch.getText();
            System.out.println(searchQuery);

            ArrayList<Convention> matchingConventions = ServiceConvention.getInstance().searchByProjectName(clientId, searchQuery);
            removeAll(); // Clear existing convention elements

            for (Convention convention : matchingConventions) {
                addElement(convention);
            }

            revalidate();
        });

        // Retrieve and display conventions
        ArrayList<Convention> clientConventions = ServiceConvention.getInstance().getConventionsByClientId(clientId);
        for (Convention convention : clientConventions) {
            addElement(convention);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeConvention().show());
    
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
        

        Button detailsButton = new Button("Actions");
        conventionContainer.add(detailsButton);
        detailsButton.addActionListener(e -> {
            
            Dialog dialog = new Dialog("Actions");

            
           

             Button deleteButton = new Button("Supprimer");
            deleteButton.addActionListener(deleteEvent -> {
                
                dialog.dispose();

               
                deleteConvention(c.getId());
            });
            dialog.add(deleteButton);

           
            dialog.show();
        });

    }

    private void deleteConvention(int conventionId) {
        if (Dialog.show("Confirmation", "Voulez-vous supprimer cette convention ?", "Oui", "Non")) {
         String url = Connection.BASE_URL +  "/deleteConventionJSON/" + conventionId;

        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setHttpMethod("DELETE");

        NetworkManager.getInstance().addToQueueAndWait(request);

        if (request.getResponseCode() == 200) {
            refreshConventionList(clientId);
        } else {
            // Failed to delete convention
            // Handle the error condition
        }
    }
    }
    public void refreshConventionList(int clientId) {
        removeAll(); // Clear existing convention elements

        ArrayList<Convention> clientConventions = ServiceConvention.getInstance().getConventionsByClientId(clientId);
        for (Convention convention : clientConventions) {
            addElement(convention);
        }

        revalidate(); // Revalidate the form to update the UI
    }
    
}
