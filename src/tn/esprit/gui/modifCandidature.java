/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import tn.esprit.entities.Candidature;
import tn.esprit.services.ServiceCandidature;

/**
 *
 * @author HP
 */
public class modifCandidature extends Form {

    public static int id;

    public modifCandidature(int candidid) throws IOException {
        setTitle("Modification Offre");
        setLayout(new FlowLayout(CENTER, CENTER));
        Candidature c = ServiceCandidature.getInstance().getOneCandidature(candidid);
        Form previous = new DetailsCandidForm();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
        System.out.println(c);
        
        Label lettre = new Label("Lettre de motivation");
        TextField tfLettre = new TextField(c.getLettreMotivation());

        Button btnLogin = new Button("Modifier");
        Container cn = new Container(BoxLayout.y());

        btnLogin.addActionListener(e -> {
            ServiceCandidature sc = new ServiceCandidature();
            c.setId(DetailsCandidForm.n);
            c.setLettreMotivation(tfLettre.getText());
            boolean mod = sc.Modif(c);
            if (mod) {
                Dialog.show("Success", "Offre modifié ! ", "OK", null);
                new ListCandidatureForm().show();
            } else {
                Dialog.show("Error", "Erreur lors de la modification de l'offre", "OK", null);
            }
        });

        cn.addAll(lettre,tfLettre, btnLogin);
        add(cn);
    }

}
