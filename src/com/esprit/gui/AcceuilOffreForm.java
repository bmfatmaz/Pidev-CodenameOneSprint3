/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;

/**
 *
 * @author Salma Majeri
 */
public class AcceuilOffreForm extends Form {

  public AcceuilOffreForm() throws IOException {
    setTitle("Accueil");
    setLayout(BoxLayout.y());
    setLayout(new FlowLayout(CENTER, CENTER));
    setScrollableY(true);

   int colorStart = 0x02B7EC; 
int colorEnd = 0x0092DF; 
int height = Display.getInstance().getDisplayHeight(); 
Style style = getStyle();
style.setBackgroundType(Style.BACKGROUND_GRADIENT_LINEAR_VERTICAL);
style.setBgColor(colorStart);
style.setBgTransparency(255); // Opaque
style.setBackgroundGradientEndColor(colorEnd);



    // Set the font to Arial

    Container topSpacer = new Container();
    topSpacer.setUIID("ContainerTopSpacer");
    topSpacer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

    Button btnAjouterOffre = new Button("Ajouter Offre");
    Button btnListeOffres = new Button("Liste des Offres");

    btnAjouterOffre.addActionListener(e -> {
        try {
            new AjouterOffreForm().show();
        } catch (IOException ex) {
            // Gérer l'exception
        }
    });

    btnListeOffres.addActionListener(e -> new OffreClientForm().show());
    topSpacer.add(btnAjouterOffre);
    topSpacer.add(btnListeOffres);

    Container bottomSpacer = new Container();
    bottomSpacer.setUIID("ContainerBottomSpacer"); 
    bottomSpacer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

    add(topSpacer);

    add(bottomSpacer);
    // Create an EncodedImage from a file path
    String imagePath = "file:///C:/Users/Salma Majeri/jobtopia/public/Images/2399750.png";
    EncodedImage image = EncodedImage.createFromImage(Image.createImage(imagePath), false);

        // Create an ImageViewer to display the image
        ImageViewer imageViewer = new ImageViewer(image);

        // Add the ImageViewer to the form
        add(imageViewer);

}

    
}

 

