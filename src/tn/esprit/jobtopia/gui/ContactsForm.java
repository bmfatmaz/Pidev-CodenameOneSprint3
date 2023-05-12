/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.jobtopia.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import entities.Contact;

import java.util.ArrayList;
import java.util.List;
import services.MessageService;

/**
 *
 * @author saddo
 */
public class ContactsForm extends Form {

    MessageService messageService = new MessageService();
    List<Contact> contacts;

    public ContactsForm() {
        setTitle("Liste Contacts");
        setLayout(new BorderLayout());

        ArrayList<Contact> contacts = messageService.getAllContacts();

        Container contactsScrollContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container contactsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contactsScrollContainer.add(contactsContainer);
        contactsScrollContainer.setScrollableY(true);

        for (Contact contact : contacts) {
            Container contactContainer = new Container(new BorderLayout());
            Container infoContainer = new Container(new FlowLayout(Component.LEFT, Component.CENTER));
            Label nameLabel = new Label(contact.getUsername());
            nameLabel.getStyle().setFgColor(0x000000);
            nameLabel.getStyle().setAlignment(CENTER);
            infoContainer.add(nameLabel);
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(getWidth() / 4, getWidth() / 4, 0xffffffff), true);
            String imagePath = contact.getImagePath();
            URLImage image = URLImage.createToStorage(placeholder, imagePath,
                    "http://localhost/" + imagePath,
                    URLImage.RESIZE_SCALE);

            Label pdpLabel = new Label(image);

            contactContainer.add(BorderLayout.WEST, pdpLabel);
            contactContainer.add(BorderLayout.CENTER, infoContainer);

            infoContainer.addPointerPressedListener((evt) -> {
                MessageForm messageForm = new MessageForm(contact,contact.getUsername());
                messageForm.show();
            });

            contactsContainer.add(contactContainer);
        }
        add(BorderLayout.CENTER, contactsScrollContainer);
        contactsScrollContainer.getAllStyles().setPadding(10, 10, 10, 10);
        contactsScrollContainer.getAllStyles().setBgTransparency(255);
        contactsScrollContainer.getAllStyles().setBgColor(0xf2f2f2);

        Button newContactButton = new Button("Ajouter un nouveau contact");
        newContactButton.addActionListener((evt) -> {
            NewContactForm newContactForm = new NewContactForm();
            newContactForm.show();
        });
        add(BorderLayout.SOUTH, newContactButton);

    }

}
