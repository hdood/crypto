package com.example.crypto.utils;

import javafx.scene.control.Label;

public class Flasher {


    public static void flash(Label label, String message){

        label.setText(message);
        label.setVisible(true);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        label.setVisible(false);
                    }
                },
                2000
        );

    }
}
