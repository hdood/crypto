package com.example.crypto.controllers;

import com.example.crypto.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NavigationController implements Initializable {

    @FXML
    Tab rsaTab, aesTab, cesarTab, affineTab, hillTab;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            AnchorPane rsa = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("rsa.fxml")));
            rsaTab.setContent(rsa);

            AnchorPane cesar = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("cesar.fxml")));
            cesarTab.setContent(cesar);

            AnchorPane aes = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("aes.fxml")));
            aesTab.setContent(aes);

            AnchorPane affine = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("affine.fxml")));
            affineTab.setContent(affine);

            AnchorPane hill = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("hill.fxml")));
            hillTab.setContent(hill);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
