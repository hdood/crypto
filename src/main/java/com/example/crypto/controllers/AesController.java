package com.example.crypto.controllers;

import com.example.crypto.algorithmes.Aes;
import com.example.crypto.utils.Flasher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.ResourceBundle;

public class AesController  implements Initializable {


    @FXML
    public Label message1, error1,message2, error2;
    @FXML
    public TextField input, output, keyField, ivField, input1, output1;
    @FXML
    public ComboBox<String> modeInput;

    @FXML
    public VBox ivWrapper;
    public SecretKey storedKey;

    public  byte[] storedIv;
    public void encrypt () throws Exception {


        if(input.getText().isEmpty()){
            Flasher.flash(error1, "Please enter a message to encrypt");
            return;
        }
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(192);
        SecretKey key = keyGenerator.generateKey();
        String cipher = Aes.update("AES/" + modeInput.getValue() +  "/PKCS5Padding", key, iv, Cipher.ENCRYPT_MODE, input.getText());
        output.setText(cipher);
        keyField.setText(Base64.getEncoder().encodeToString(key.getEncoded()));
        ivField.setText(Base64.getEncoder().encodeToString(iv));
        Flasher.flash(message1, "Message encrypted successfully");
        storedKey = key;
        storedIv = iv;

    }

    public void decrypt () throws Exception {

        if (keyField.getText().isEmpty()) {
            Flasher.flash(error2, "Please enter a key to decrypt the message");
            return;
        }

        if (ivField.getText().isEmpty()) {
            Flasher.flash(error2, "Please enter an initialization vector to decrypt the message");
            return;
        }

        if (input1.getText().isEmpty()) {
            Flasher.flash(error2, "Please enter a cipher text to decrypt");
            return;
        }


        byte[] decodedKey;
        
        try {
            decodedKey = Base64.getDecoder().decode(keyField.getText());

        } catch (Exception e) {
            Flasher.flash(error2, "Please enter a valid key");
            return;
        }


        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        byte[] iv = Base64.getDecoder().decode(ivField.getText());

        output1.setText(Aes.decode("AES/" + modeInput.getValue() +  "/PKCS5Padding", originalKey, iv, Cipher.DECRYPT_MODE, input1.getText()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modeInput.setItems(FXCollections.observableArrayList("ECB", "CBC"));
        modeInput.getSelectionModel().selectFirst();

        modeInput.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                System.out.println("new value is : " + newValue);

                ivWrapper.setVisible(!newValue.equals("ECB"));

            }
        });



    }
}
