/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
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
import entities.Contact;
import entities.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import services.MessageService;
import org.json.JSONObject;
import tn.esprit.jobtopia.entities.CurrentUser;

/**
 *
 * @author saddo
 */
public class MessageForm extends Form {

    public MessageForm(Contact contact, String Username) {
        setTitle(Username);

        Container messagesScrollContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container messagesContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        messagesScrollContainer.add(messagesContainer);
        messagesScrollContainer.setScrollableY(true);
        Container spinnerContainer = new Container(new FlowLayout(CENTER));
        spinnerContainer.add(new InfiniteProgress());
        messagesContainer.add(spinnerContainer);
        ConnectionRequest connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser parser = new JSONParser();
                Map<String, Object> parsed = parser.parseJSON(new InputStreamReader(input, "UTF-8"));
                List<Map<String, Object>> messagesList = (List<Map<String, Object>>) parsed.get("messages");
                System.out.println("messagesList: " + messagesList);
                messagesContainer.removeComponent(spinnerContainer);
                for (Map<String, Object> messageMap : messagesList) {
                    String messageText = (String) messageMap.get("contenu");
                    int receiverId = ((Map<String, Double>) messageMap.get("receiverid")).get("id").intValue();
                    String dateHeure = (String) messageMap.get("dateHeure");
                    Label messageLabel = new Label(messageText);
                    messageLabel.setUIID("MessageLabel"); 
                    Label dateLabel = new Label(dateHeure);
                    dateLabel.setUIID("DateLabel"); 
                    Container messageContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    messageContainer.add(messageLabel);
                    messageContainer.add(dateLabel);
                    if (receiverId != contact.getId()) {
                        messageLabel.getAllStyles().setAlignment(Component.LEFT);
                        messageLabel.getAllStyles().setBgColor(0xE6E6E6);
                        messageLabel.getAllStyles().setPadding(5, 5, 5, 5);
                        messageLabel.getAllStyles().setMargin(0, 10, 5, 0);
                        dateLabel.getAllStyles().setFgColor(0x777777);
                        dateLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
                        dateLabel.getAllStyles().setPadding(0, 5, 5, 5);
                        dateLabel.getAllStyles().setMargin(0, 0, 5, 0);
                        dateLabel.getAllStyles().setAlignment(Component.LEFT);
                    } else {
                        messageLabel.getAllStyles().setAlignment(Component.RIGHT);
                        messageLabel.getAllStyles().setBgColor(0x4da6ff);
                        messageLabel.getAllStyles().setPadding(5, 5, 5, 5);
                        messageLabel.getAllStyles().setMargin(0, 0, 5, 10);
                        dateLabel.getAllStyles().setFgColor(0x777777);
                        dateLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
                        dateLabel.getAllStyles().setPadding(0, 5, 5, 5);
                        dateLabel.getAllStyles().setMargin(0, 0, 5, 0);
                        dateLabel.getAllStyles().setAlignment(Component.RIGHT);
                    }
                    messagesContainer.add(messageContainer);
                }
                Component lastMessage = messagesContainer.getComponentAt(messagesContainer.getComponentCount() - 1);
                messagesContainer.scrollComponentToVisible(lastMessage);
                messagesContainer.revalidate();
            }

            @Override
            protected void handleErrorResponseCode(int code, String message) {
                Dialog.show("Erreur", message, "OK", null);
            }
        };
        connectionRequest.setUrl("http://127.0.0.1:8000/messagesJson/" + contact.getId()+"?userid="+CurrentUser.getInstance().getId());
        connectionRequest.setPost(false);
        connectionRequest.addRequestHeader("Accept", "application/json");
        connectionRequest.addRequestHeader("Content-Type", "application/json");
        NetworkManager.getInstance().addToQueue(connectionRequest);
        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, messagesScrollContainer);
        messagesScrollContainer.getAllStyles().setPadding(10, 10, 10, 10);
        messagesScrollContainer.getAllStyles().setBgTransparency(255);
        messagesScrollContainer.getAllStyles().setBgColor(0xf2f2f2);
        Container inputContainer = new Container(new BorderLayout());
        TextField inputTextField = new TextField("", "Ecrire votre message ici ...");
        inputContainer.add(BorderLayout.CENTER, inputTextField);

        Button sendButton = new Button("Envoyer");
        sendButton.addActionListener(evt -> {
            String message = inputTextField.getText().trim();
            if (!message.isEmpty()) {
                
                ConnectionRequest sendRequest = new ConnectionRequest() { 
                    @Override
                    protected void handleErrorResponseCode(int code, String message) {
                        message="Ce message contient un mauvais mot!";
                        Dialog.show("Error", message, "OK", null);
                    }

                    @Override
                    protected void readResponse(InputStream input) throws IOException {

                        ConnectionRequest reloadRequest = new ConnectionRequest() {
                            @Override
                            protected void handleErrorResponseCode(int code, String message) {
                                Dialog.show("Error", message, "OK", null);
                            }

                            @Override
                            protected void readResponse(InputStream input) throws IOException {
                                JSONParser parser = new JSONParser();
                                Map<String, Object> parsed = parser.parseJSON(new InputStreamReader(input, "UTF-8"));
                                List<Map<String, Object>> messagesList = (List<Map<String, Object>>) parsed.get("messages");
                                System.out.println("messagesList: " + messagesList);
                                messagesContainer.removeAll();
                                for (Map<String, Object> messageMap : messagesList) {
                                    String messageText = (String) messageMap.get("contenu");
                                    int receiverId = ((Map<String, Double>) messageMap.get("receiverid")).get("id").intValue();
                                    String dateHeure = (String) messageMap.get("dateHeure");

                                    Label messageLabel = new Label(messageText);
                                    messageLabel.setUIID("MessageLabel"); 
                                    Label dateLabel = new Label(dateHeure);
                                    dateLabel.setUIID("DateLabel");
                                    Container messageContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                    messageContainer.add(messageLabel);
                                    messageContainer.add(dateLabel);

                                    if (receiverId != contact.getId()) {
                                        messageLabel.getAllStyles().setAlignment(Component.LEFT);
                                        messageLabel.getAllStyles().setBgColor(0xE6E6E6);
                                        messageLabel.getAllStyles().setPadding(5, 5, 5, 5);
                                        messageLabel.getAllStyles().setMargin(0, 10, 5, 0);
                                        dateLabel.getAllStyles().setFgColor(0x777777);
                                        dateLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
                                        dateLabel.getAllStyles().setPadding(0, 5, 5, 5);
                                        dateLabel.getAllStyles().setMargin(0, 0, 5, 0);
                                        dateLabel.getAllStyles().setAlignment(Component.LEFT);
                                    } else {
                                        messageLabel.getAllStyles().setAlignment(Component.RIGHT);
                                        messageLabel.getAllStyles().setBgColor(0x4da6ff);
                                        messageLabel.getAllStyles().setPadding(5, 5, 5, 5);
                                        messageLabel.getAllStyles().setMargin(0, 0, 5, 10);
                                        dateLabel.getAllStyles().setFgColor(0x777777);
                                        dateLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
                                        dateLabel.getAllStyles().setPadding(0, 5, 5, 5);
                                        dateLabel.getAllStyles().setMargin(0, 0, 5, 0);
                                        dateLabel.getAllStyles().setAlignment(Component.RIGHT);
                                    }
                                    messagesContainer.add(messageContainer);
                                }
                                messagesContainer.revalidate();
                            }
                        };
                        reloadRequest.setUrl("http://127.0.0.1:8000/messagesJson/" + contact.getId()+"?userid="+CurrentUser.getInstance().getId());
                        reloadRequest.setPost(false);
                        reloadRequest.addRequestHeader("Accept", "application/json");
                        reloadRequest.addRequestHeader("Content-Type", "application/json");
                        NetworkManager.getInstance().addToQueue(reloadRequest);
                        inputTextField.clear();
                    }
                };
                JSONObject postData = new JSONObject();
                sendRequest.setUrl("http://127.0.0.1:8000/message/send-from-codename-one?receiverId=" + contact.getId() + "&message=" + message+"&userid="+CurrentUser.getInstance().getId());
                sendRequest.setPost(true);
                sendRequest.addRequestHeader("Accept", "application/json");
                sendRequest.addRequestHeader("Content-Type", "application/json");
                NetworkManager.getInstance().addToQueue(sendRequest);
            }
        });

        inputContainer.add(BorderLayout.EAST, sendButton);
        add(BorderLayout.SOUTH, inputContainer);

        Command backCommand = new Command("Retour") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ContactsForm contactsForm = new ContactsForm();
                contactsForm.showBack();
            }
        };
        setBackCommand(backCommand);
        getToolbar().addCommandToLeftBar(backCommand);

        Image deleteIcon = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "Button", 6);
        Command deleteCommand = new Command("", deleteIcon) {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest deleteRequest = new ConnectionRequest() {
                    @Override
                    protected void handleErrorResponseCode(int code, String message) {
                        Dialog.show("Error", message, "OK", null);
                    }

                    @Override
                    protected void readResponse(InputStream input) throws IOException {
                    }
                };
                deleteRequest.setUrl("http://127.0.0.1:8000/message/deleteContact2/" + contact.getId()+"?userid="+CurrentUser.getInstance().getId());
                deleteRequest.setHttpMethod("DELETE");
                deleteRequest.addRequestHeader("Accept", "application/json");
                deleteRequest.addRequestHeader("Content-Type", "application/json");
                NetworkManager.getInstance().addToQueue(deleteRequest);

                new ContactsForm().show();
            }
        };
        getToolbar().addCommandToRightBar(deleteCommand);

    }

}
