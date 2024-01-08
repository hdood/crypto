module com.example.crypto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires jdk.jsobject;
    requires bcprov.jdk15on;

    opens com.example.crypto to javafx.fxml;
    exports com.example.crypto;
    exports com.example.crypto.controllers;
    opens com.example.crypto.controllers to javafx.fxml;
}