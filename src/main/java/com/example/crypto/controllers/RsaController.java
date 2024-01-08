package com.example.crypto.controllers;

import com.example.crypto.algorithmes.RSA;
import com.example.crypto.utils.Flasher;
import com.example.crypto.utils.PemFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class RsaController {


    @FXML
    public TextField input, output, publicKey, privateKey = null;
    @FXML
    public Label message1;
    public Label error1;
    public Key previousPublic, previousPrivate;
    public Label decryptedMessage;
    public Label decryptedError;
    public TextField cipherText;
    public TextField decryptedCipher;

    @FXML
    public void encrypt() {

        try {


            String originalText = input.getText();

            if (originalText.isEmpty()) {

                Flasher.flash(error1, "Please enter a message to encrypt");

                return;
            }


            PublicKey _publicKey = null;
            if (publicKey.getText().isEmpty()) {
                KeyPair keyPair = RSA.generateKeyPair();
                _publicKey = keyPair.getPublic();
                publicKey.setText(Base64.getEncoder().encodeToString(_publicKey.getEncoded()));


                PrivateKey _privateKey = keyPair.getPrivate();
                privateKey.setText(Base64.getEncoder().encodeToString(_privateKey.getEncoded()));


                previousPrivate = _privateKey;


            }else {

                byte[] decodedKey;

                try {
                    decodedKey = Base64.getDecoder().decode(publicKey.getText());
                    _publicKey = parsePublic(decodedKey);

                } catch (Exception e) {
                    Flasher.flash(error1, "Invalid key!");
                    return;
                }
            }

            previousPublic = _publicKey;


            byte[] encryptedBytes = RSA.encrypt(originalText, _publicKey);


            output.setText(Base64.getEncoder().encodeToString(encryptedBytes));

            Flasher.flash(message1, "Message encrypted successfully");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void decrypt(){

        try {

            if (privateKey.getText().isEmpty()) {

                Flasher.flash(decryptedError, "Please enter a private key");
                return;
            }
            String cipher = cipherText.getText();

            if (cipher.isEmpty()) {

                Flasher.flash(decryptedError, "Please enter cipher text to decrypt");
                return;

            }


            byte[] decodedKey;

            try {
                decodedKey = Base64.getDecoder().decode(privateKey.getText());

            } catch (Exception e) {
                Flasher.flash(decryptedError, "Please enter a valid key");
                return;
            }

            PrivateKey pvt = null;
            String decryptedBytes;
            try {
                pvt = parsePrivate(decodedKey);
                decryptedBytes = RSA.decrypt(Base64.getDecoder().decode(cipher), pvt);

            } catch (Exception e) {
                Flasher.flash(decryptedError, "Invalid key!");
                return;

            }


            decryptedCipher.setText(decryptedBytes);

            Flasher.flash(decryptedMessage, "Cipher text decrypted successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PrivateKey parsePrivate(byte[] decodedKey) throws Exception{

        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(decodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(ks);

    }

    public PublicKey parsePublic(byte[] decodedKey) throws Exception{

        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(decodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(ks);

    }

}


