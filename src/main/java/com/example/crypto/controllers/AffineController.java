package com.example.crypto.controllers;

import com.example.crypto.algorithmes.Affine;
import com.example.crypto.utils.Flasher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AffineController implements Initializable {

    @FXML
    public TextField input, output, input1, output1, key1_1, key1_2, key2_1, key2_2;
    @FXML
    public Label message1, error1,message2, error2;

    public void encrypt (){

        if(key1_1.getText().isEmpty()) {
            Flasher.flash(error1, "Please enter the first key");
            return;
        }
        if(key1_2.getText().isEmpty()) {
            Flasher.flash(error1, "Please enter the second key");
            return;
        }

        if(input.getText().isEmpty()){
            Flasher.flash(error1, "Please enter a message to encrypt");
            return;
        }

        output.setText(Affine.encryptMessage(input.getText().toUpperCase().toCharArray(), Integer.parseInt(key1_1.getText()), Integer.parseInt(key1_2.getText())).toLowerCase());
        Flasher.flash(message1, "message encrypted successfully");

    }

    public void decrypt () throws Exception {

        if(key2_1.getText().isEmpty()) {
            Flasher.flash(error2, "Please enter the first key");
            return;
        }
        if(key2_2.getText().isEmpty()) {
            Flasher.flash(error2, "Please enter the second key");
            return;
        }

        if(input1.getText().isEmpty()){
            Flasher.flash(error2, "Please enter a cipher text to decrypt");
            return;
        }


        try{
            output1.setText(Affine.decryptCipher(input1.getText().toUpperCase(), Integer.parseInt(key2_1.getText()), Integer.parseInt(key2_2.getText())).toLowerCase());

        }catch (Exception e){
                Flasher.flash(error2, "the key you entered is inversible");
                return;
        }
        Flasher.flash(message2, "message encrypted successfully");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        key1_1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    key1_1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        key1_2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    key1_2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        key2_1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    key2_1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        key2_2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    key2_2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

}
