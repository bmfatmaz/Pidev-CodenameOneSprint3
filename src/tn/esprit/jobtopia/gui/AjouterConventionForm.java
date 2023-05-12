package tn.esprit.jobtopia.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import tn.esprit.jobtopia.entities.Convention;
import tn.esprit.jobtopia.services.ServiceConvention;
import java.util.Date;
import tn.esprit.jobtopia.gui.QRCode;
import tn.esprit.jobtopia.services.EmailService;

public class AjouterConventionForm extends Form {

    public AjouterConventionForm(Form previous) {
        setTitle("Ajouter une Convention");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        TextField projectNameField = new TextField("", "Nom du Projet");
        Picker startDatePicker = new Picker();
        startDatePicker.setType(Display.PICKER_TYPE_DATE);
        Picker endDatePicker = new Picker();
        endDatePicker.setType(Display.PICKER_TYPE_DATE);
        TextField paymentAmountField = new TextField("", "Montant de Paiement");
        TextField freelancerIdField = new TextField("", "ID du Freelancer");

        Button btnValider = new Button("Ajouter une Convention");

        btnValider.addActionListener((ActionEvent evt) -> {
            if (projectNameField.getText().isEmpty() || paymentAmountField.getText().isEmpty() || freelancerIdField.getText().isEmpty()) {
                Dialog.show("Alerte", "Veuillez remplir tous les champs", new Command("OK"));
            } else {
                try {
                    Convention convention = new Convention();
                    convention.setProjectName(projectNameField.getText());

                    // Récupérer les dates sélectionnées à partir des sélecteurs
                    Date startDate = new Date(startDatePicker.getDate().getTime());
                    Date endDate = new Date(endDatePicker.getDate().getTime());

                    // Check if start date is before end date
                    if (startDate.getTime() > endDate.getTime()) {
                        Dialog.show("Erreur", "La date de début doit être antérieure à la date de fin", new Command("OK"));
                        return;
                    }

                    convention.setStartDate(startDate);
                    convention.setEndDate(endDate);

                    double paymentAmount = Double.parseDouble(paymentAmountField.getText());
                    // Check if payment amount is positive
                    if (paymentAmount <= 0) {
                        Dialog.show("Erreur", "Le montant de paiement doit être positif", new Command("OK"));
                        return;
                    }
                    convention.setPaymentAmount(paymentAmount);
                    convention.setFreelancerID(Integer.parseInt(freelancerIdField.getText()));

                    if (ServiceConvention.getInstance().addConvention(convention)) {
                        Dialog.show("Succès", "Convention ajoutée avec succès", new Command("OK"));
                        // Generate QR code
                        QRCode qrCodeGenerator = new QRCode();
                        String qrCodeData = generateQRCodeData(convention);
                        Image qrCodeImage = qrCodeGenerator.getQrCode(qrCodeData);

                        showQRCode(qrCodeImage);
                        

                        //new ListeConventionForm(previous).show();
                    } else {
                        Dialog.show("ERREUR", "Échec de l'ajout de la convention", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERREUR", "Veuillez saisir un montant de paiement valide", new Command("OK"));
                }
            }
        });

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.add(projectNameField);
        container.add(startDatePicker);
        container.add(endDatePicker);
        container.add(paymentAmountField);
        container.add(freelancerIdField);
        container.add(btnValider);

        add(container);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeConvention().show());
    }

    private void showQRCode(Image qrCodeImage) {
        Form qrCodeForm = new Form("QR Code");
        Toolbar toolbar = qrCodeForm.getToolbar();
        toolbar.setBackCommand("", e -> showBack());
        toolbar.setTitleCentered(true);
        Style style = qrCodeForm.getUnselectedStyle();
        style.setBgColor(0xFFFFFF); 
        qrCodeForm.setLayout(new BorderLayout());

        Container qrCodeContainer = new Container(new FlowLayout(CENTER));
        Label qrCodeLabel = new Label(qrCodeImage);
        qrCodeContainer.add(qrCodeLabel);
        qrCodeForm.add(BorderLayout.CENTER, qrCodeContainer);

        qrCodeForm.show();
    }

    private String generateQRCodeData(Convention convention) {
        String projectName = convention.getProjectName();
        Date startDate = convention.getStartDate();
        Date endDate = convention.getEndDate();
        double paymentAmount = convention.getPaymentAmount();
        int freelancerId = convention.getFreelancerID();

        // Customize the format according to your requirements
        StringBuilder qrCodeData = new StringBuilder();
        qrCodeData.append("Project Name: ").append(projectName).append("\n");
        qrCodeData.append("Start Date: ").append(startDate).append("\n");
        qrCodeData.append("End Date: ").append(endDate).append("\n");
        qrCodeData.append("Payment Amount: ").append(paymentAmount).append("\n");

        return qrCodeData.toString();
    }

}
