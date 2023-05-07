/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import tn.esprit.jobtopia.entities.Offre;
import tn.esprit.jobtopia.services.ServiceOffres;

/**
 *
 * @author Administrateur
 */
public class ModifierOffreForm extends Form {
    
    

public ModifierOffreForm(int offreId) throws IOException {
    Form previous = new OffreClientForm();
    setTitle("Modification Offre");
    setLayout(new FlowLayout(CENTER, CENTER));
        
    Offre o = ServiceOffres.getInstance().getOffreBYid();System.out.println(o);
    TextField tfTitre = new TextField(o.getTitre());
    TextField tfDescription = new TextField(o.getDescription());
    TextField tfCatg = new TextField(o.getCategorie());
     
    Button btnLogin = new Button("Modifier");
    Container cn = new Container(BoxLayout.y());
       
    btnLogin.addActionListener(e->{
        ServiceOffres su= new ServiceOffres();
       // o.setId(ListOffreForm.offreid);
        o.setTitre(tfTitre.getText());
        o.setDescription(tfDescription.getText());
        o.setCategorie(tfCatg.getText());
        boolean mod = su.Modif(o); 
        if (mod) {
            Dialog.show("Success", "Offre modifié ! ", "OK", null);
        } else {
            Dialog.show("Error", "Erreur lors de la modification de l'offre", "OK", null);
        }
    });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    cn.addAll(tfTitre, tfDescription, tfCatg, btnLogin);
    add(cn);        
}

  

}
