package tn.esprit.jobtopia.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import tn.esprit.jobtopia.entities.Reclamation;
import tn.esprit.jobtopia.services.ServiceReclamation;
import java.io.IOException;
import java.util.Vector;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane.Divider;

public class AjouterReclamationForm extends Form  {
    
    public AjouterReclamationForm(Resources r) {
        
        setTitle("Ajouter une réclamation");
        
        setLayout(new FlowLayout(CENTER, CENTER));
        Container cn=new Container(BoxLayout.y());
        
        Reclamation reclamation = new Reclamation();
        
        Label label1 =new Label("    Choisir votre titre:");
        TextField tfTitre=new TextField(reclamation.getTitre(),"");

        Label label2 =new Label("    Décrire votre réclamation:");
        TextField tfDescription = new TextField(reclamation.getDescription(), "");
        tfDescription.setSingleLineTextArea(false);
        tfDescription.setRows(4);
        
        Label label3 =new Label("    Choisir un type:");
        ComboBox<String> cbType = new ComboBox<>();
        cbType.addItem("Autre");
        cbType.addItem("Freelancer");
        cbType.addItem("Client");
        cbType.addItem("Message");
        cbType.addItem("Convention");
        cbType.addItem("Offres");
        cbType.addItem("Condidature");
        
        Button bAjouter=new Button("Ajouter");
        
        cn.addAll(label1,tfTitre,new Label("."),label3,cbType,new Label("."),label2,tfDescription,new Label("."),bAjouter);
        
        add(cn);
        
        bAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServiceReclamation sr= new ServiceReclamation();
                Reclamation r = new Reclamation();
                
                r.setTitre(tfTitre.getText());
                r.setDescription(tfDescription.getText());
                r.setType(cbType.getSelectedItem().toString());
                if (r.getTitre().length() < 3) {
                    Dialog.show("Warning", "Le titre doit contenir au moins 3 caractères!", "OK", null);
                } else if (r.getType()=="Choisir un type") {
                    Dialog.show("Warning", "Veuillez sélectionner un type!", "OK", null);
                } else if (r.getDescription().length() < 10) {
                    Dialog.show("Warning", "La description doit contenir au moins 10 caractères!", "OK", null);
                } else {
                    sr.ajouterReclamation(r);
                    Dialog.show("Success", "Réclamation ajoutée avec succès", "OK", null);
                }
            }
        });       
    }

}
 
