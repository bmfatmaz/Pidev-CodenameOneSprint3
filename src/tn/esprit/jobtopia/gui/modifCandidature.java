/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import tn.esprit.jobtopia.gui.ListCandidatureForm;
import tn.esprit.jobtopia.gui.HomeCandidatureForm;
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
import tn.esprit.jobtopia.entities.Candidature;
import tn.esprit.jobtopia.services.ServiceCandidature;

/**
 *
 * @author HP
 */
public class modifCandidature extends Form {

    public static int id;

    public modifCandidature() throws IOException {

        ServiceCandidature sc = new ServiceCandidature();
        Candidature ss = ServiceCandidature.getInstance().getOneCandidature(id);
        Toolbar tb = this.getToolbar();

        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            new ListCandidatureForm().show();
        });
        tb.addMaterialCommandToSideMenu("Offres", FontImage.MATERIAL_WEB, e -> {

            new HomeCandidatureForm().show();
            // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
        });

        setTitle("Modification Form");
        setLayout(new FlowLayout(CENTER, CENTER));
        Candidature c = ServiceCandidature.getInstance().getOneCandidature(id);System.out.println(c);
        TextField lettremotivation = new TextField(c.getLettreMotivation());
        TextField cv = new TextField(c.getCv());

        Button btnModif = new Button("Modifier");
        Container cn = new Container(BoxLayout.y());

        btnModif.addActionListener(e -> {
            modifCandidature.id = c.getId();
            c.setLettreMotivation(lettremotivation.getText());
            c.setCv(cv.getText());
            Boolean mod = sc.Modif(c);
            //  f.setCategorie(cat.getText());

            Dialog.show("Warning", "Profil modifié ! ", "OK", null);
        });

        cn.addAll(lettremotivation, cv);
        add(cn);
    }
}
