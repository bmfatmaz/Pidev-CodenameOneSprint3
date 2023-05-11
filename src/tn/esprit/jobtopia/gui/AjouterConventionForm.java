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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import tn.esprit.jobtopia.entities.Convention;
import tn.esprit.jobtopia.services.ServiceConvention;
import java.util.Date;


public class AjouterConventionForm extends Form {

    public AjouterConventionForm(Form previous) {
        setTitle("Add a new Convention");
        setLayout(new FlowLayout(CENTER, CENTER));

        TextField projectNameField = new TextField("", "Project Name");
        Picker startDatePicker = new Picker();
        startDatePicker.setType(Display.PICKER_TYPE_DATE);
        Picker endDatePicker = new Picker();
        endDatePicker.setType(Display.PICKER_TYPE_DATE);
        TextField paymentAmountField = new TextField("", "Payment Amount");

        Button btnValider = new Button("Add Convention");

        btnValider.addActionListener((ActionEvent evt) -> {
            if (projectNameField.getText().isEmpty() || paymentAmountField.getText().isEmpty()) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    Convention convention = new Convention();
                    convention.setProjectName(projectNameField.getText());

                    // Get the selected dates from the pickers
                    Date startDate = new Date(startDatePicker.getDate().getTime());
                    Date endDate = new Date(endDatePicker.getDate().getTime());

                    convention.setStartDate(startDate);
                    convention.setEndDate(endDate);
                    convention.setPaymentAmount(Double.parseDouble(paymentAmountField.getText()));

                    if (ServiceConvention.getInstance().addConvention(convention)) {
                        Dialog.show("Success", "Convention added successfully", new Command("OK"));
                        //new ListeConventionForm(previous).show();
                    } else {
                        Dialog.show("ERROR", "Failed to add Convention", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Please enter a valid payment amount", new Command("OK"));
                }
            }
        });

        addAll(projectNameField, startDatePicker, endDatePicker, paymentAmountField, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
