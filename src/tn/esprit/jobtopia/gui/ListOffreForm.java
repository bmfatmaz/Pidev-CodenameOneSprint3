/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

/**
 *
 * @author Administrateur
 */
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import static tn.esprit.jobtopia.JobTopia.theme;
import tn.esprit.jobtopia.entities.Offre;
import tn.esprit.jobtopia.services.ServiceOffres;

/**
 *
 * @author Salma Majeri
 */
public class ListOffreForm extends Form{
    
    public static int offreid;
    public ListOffreForm() throws IOException {
        Form previous = new Form();
        setTitle("Offres Disponibles");
        setLayout(BoxLayout.y());
        
 Toolbar tb = this.getToolbar();
Image icon = theme.getImage("icon.png"); 
Container topBar = BorderLayout.east(new Label(icon));
topBar.add(BorderLayout.SOUTH, new Label("Cool App Tagline...", "SidemenuTagline")); 
topBar.setUIID("SideCommand");
tb.addComponentToSideMenu(topBar);

tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> { 
 
             try {
                 new ProfilFreelancer().show();
                 // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
               
                 
                 
                 //Logger.getLogger(AccueilFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
             }
}); 
tb.addMaterialCommandToSideMenu("Website", FontImage.MATERIAL_WEB, e -> {});
tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> { 
 
             try {
                 new ProfilFreelancer().show();
                 // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
               
                 
                 
                 //Logger.getLogger(AccueilFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
             }
});
tb.addMaterialCommandToSideMenu("Déconnecter", FontImage.MATERIAL_INFO, e -> { new LoginForm().show();});
        

    
        ArrayList<Offre> offres = ServiceOffres.getInstance().getAllTasks();
        int n = 0;
        for (Offre o : offres) {
            n = n + 1;
//          String logoPath = "file:///C:/Users/Salma Majeri/jobtopia/public/Images/" + o.getLogoPath();
//            Image img = Image.createImage(logoPath);
//            ImageViewer imgProfilePic = new ImageViewer(img);
//            add(imgProfilePic);
  
            addElement(o);

        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    
    public void addElement(Offre fr) {

        //  CheckBox cb = new CheckBox(fr.getNom());
        //  cb.setEnabled(false);
        //  if (task.getStatus() == 1) {
        //      cb.setSelected(true);
        //  }
        
        add("Titre:   " +fr.getTitre());

        add("Description:   " +fr.getDescription());
        
        add("Categorie:   " +fr.getCategorie());

        Button btnDetails = new Button("Détails");
        add(btnDetails);
        btnDetails.addActionListener((ActionEvent e) -> {offreid=fr.getId();
          try{
              new DetailsOffreFrom().show();
          
          } catch (IOException ex) {
            System.out.println(ex.getMessage());
                  
          }
});
          Button btnQRCode = new Button("QR Code");
add(btnQRCode);
btnQRCode.addActionListener((ActionEvent e) -> {
    offreid = fr.getId();
    String qrCodeContent = "Titre: " + fr.getTitre() + "\n"
            + "Description: " + fr.getDescription() + "\n"
            + "Categorie: " + fr.getCategorie();

    Image qrCodeImage = generateQRCode(qrCodeContent, 1000);
    if (qrCodeImage != null) {
        ImageViewer qrCodeImageViewer = new ImageViewer(qrCodeImage);
        Dialog qrCodeDialog = new Dialog();
        qrCodeDialog.setLayout(new BorderLayout());
        qrCodeDialog.add(BorderLayout.CENTER, qrCodeImageViewer);

        // Ajouter le bouton Annuler
        Button btnCancel = new Button("Annuler");
        btnCancel.addActionListener((ae) -> {
            qrCodeDialog.dispose(); // Fermer la boîte de dialogue QR Code
        });
        qrCodeDialog.add(BorderLayout.SOUTH, btnCancel);

        qrCodeDialog.show();
    }
});
    }
public Image generateQRCode(String content, int size) {
    try {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, size, size, hints);

        // Conversion du BitMatrix en image
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
            }
        }
        Image image = Image.createImage(pixels, width, height);
        return image;
    } catch (WriterException e) {
        e.printStackTrace();
        return null;
    }
}

    }

    
