package com.example.crypto.controllers;

import com.example.crypto.algorithmes.Caesar;
import com.example.crypto.utils.Flasher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CaesarController implements Initializable {

    @FXML
    public TextField input, output, keyField, ivField, input1, output1, key1, key2;
    @FXML
    public Label message1, error1,message2, error2;

    public void encrypt(){


        if(key1.getText().isEmpty()){

            Flasher.flash(error1, "Please enter a key to encrypt the message");
            return;
        }

        if(input.getText().isEmpty()){
            Flasher.flash(error1, "Please enter a message to encrypt");
            return;
        }

        int key = Integer.parseInt(key1.getText());

        output.setText(Caesar.encrypt(input.getText().toLowerCase(), key));

        Flasher.flash(message1, "Message encrypted successfully");
    }
    public void decrypt(){


        if(key2.getText().isEmpty()){

            Flasher.flash(error2, "Please enter a key to encrypt the message");
            return;
        }

        if(input1.getText().isEmpty()){
            Flasher.flash(error2, "Please enter a message to encrypt");
            return;
        }

        int key = Integer.parseInt(key2.getText());

        output1.setText(Caesar.encrypt(input1.getText().toLowerCase(), 26 - ((key - 2) % 26)));

        Flasher.flash(message2, "Message decrypted successfully");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // force the field to be numeric only
        key1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    key1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        key2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    key2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
