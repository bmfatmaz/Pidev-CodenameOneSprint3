/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author saddo
 */
public class NewContactForm extends Form {

    public NewContactForm() {
        setTitle("Nouveau Contact");
        
        TextField receiverUsernameField = new TextField("", "Pseudo du Contact");
        TextField messageField = new TextField("", "Votre Message", 200, TextField.ANY);

        Button addButton = new Button("Add Contact");
        addButton.addActionListener(evt -> {
            String receiverUsername = receiverUsernameField.getText();
            String message = messageField.getText();
            if (receiverUsername.isEmpty() || message.isEmpty()) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
            } else {
                ConnectionRequest request = new ConnectionRequest() {
                    @Override
                    protected void readResponse(InputStream input) throws IOException {
                        JSONParser parser = new JSONParser();
                        Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                        boolean success = (Boolean) response.get("success");
                        if (success) {
                            Dialog.show("Succès", "Contact ajouté", "OK", null);
                            ContactsForm contactsForm = new ContactsForm();
                            contactsForm.show();
                        } else {
                            String message = (String) response.get("message");
                            Dialog.show("Erreur", message, "OK", null);
                        }
                    }
                    @Override
                    protected void handleErrorResponseCode(int code, String message) {
                        Dialog.show("Error", "HTTP error: " + code + " " + message, "OK", null);
                    }
                };
                request.setUrl("http://localhost:8000/message/newContact-from-codename-one?receiver_username="+ receiverUsername + "&message=" + message);
                request.setPost(false);
                request.setFailSilently(true);
                NetworkManager.getInstance().addToQueue(request);
                ContactsForm contactsForm = new ContactsForm();
                contactsForm.showBack();
            }
        });

        addAll(receiverUsernameField, messageField, addButton);

        Command backCommand = new Command("Retour") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ContactsForm contactsForm = new ContactsForm();
                contactsForm.showBack();
            }
        };
        setBackCommand(backCommand);
        getToolbar().addCommandToLeftBar(backCommand);
    }
}

