package com.example.crypto.controllers;

import com.example.crypto.algorithmes.Hill;
import com.example.crypto.utils.Flasher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HillController {

    @FXML
    public TextField input, output, input1, output1, key1, key2;
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

        String key = key1.getText();

        output.setText(Hill.hillCipherEncrypt(input.getText().toLowerCase(), key));

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

        String key = key2.getText();

        output1.setText(Hill.hillCipherDecrypt(input1.getText().toLowerCase(), key));

        Flasher.flash(message2, "Message decrypted successfully");

    }


}
