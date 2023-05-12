/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
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
import static tn.esprit.jobtopia.JobTopia.theme;
import tn.esprit.jobtopia.entities.Client;
import tn.esprit.jobtopia.entities.CurrentUser;
import tn.esprit.jobtopia.entities.Freelancer;
import tn.esprit.jobtopia.services.ServiceClient;
import tn.esprit.jobtopia.services.ServiceFreelancer;

/**
 *
 * @author Administrateur
 */
public class ModifClient extends Form {

    public ModifClient() throws IOException {
        Toolbar tb = this.getToolbar();
        Image icon = theme.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.SOUTH, new Label("Cool App Tagline...", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            new ListFreelancerForm().show(); // Logger.getLogger(ListFreelancerForm.class.getName()).log(Level.SEVERE, null, ex);
        });
        tb.addMaterialCommandToSideMenu("Offres", FontImage.MATERIAL_WEB, e -> {

            new AcceuilOffreForm().show();
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

        setTitle("Modification Form");
        setLayout(new FlowLayout(CENTER, CENTER));
        System.out.println(CurrentUser.getInstance().getId());
        Client c = ServiceClient.getInstance().getClient(CurrentUser.getInstance().getId());

        // TextField tfUsername = new TextField(fr.getUsername());
        TextField tfNom = new TextField(c.getNom());
        TextField tfPrenom = new TextField(c.getPrenom());
        TextField tfEmail = new TextField(c.getEmail());
        TextField tfTelephone = new TextField(c.getTelephone());
        TextField tfProf = new TextField(c.getProfession());
        //  TextField cat = new TextField(fr.getCategorie());

        // ComboBox<String> cat = new ComboBox();
        //       EnumSet.allOf(Categorie.class).stream().forEach(s -> cat.addItem(s.toString()));
        //TextField tfPwd = new TextField("", TextField.PASSWORD);
        Button btnLogin = new Button("Modifier");
        Container cn = new Container(BoxLayout.y());

        btnLogin.addActionListener(e -> {
            ServiceClient su = new ServiceClient();
            Client f = new Client();
            f.setId(CurrentUser.getInstance().getId());
            f.setNom(tfNom.getText());
            f.setPrenom(tfPrenom.getText());
            f.setEmail(tfEmail.getText());
            f.setTelephone(tfTelephone.getText());
            f.setProfession(tfProf.getText());
            //  f.setCategorie(cat.getText());
            Boolean mod = su.Modif(f); // Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            Dialog.show("Warning", "Profil modifié ! ", "OK", null);
        });

        cn.addAll(tfNom, tfPrenom, tfEmail, tfTelephone, tfProf, btnLogin);
        add(cn);
    }
}
