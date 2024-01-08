package com.example.crypto.algorithmes;

public class Caesar {

    public static String encrypt(String message, int key) {

        StringBuilder result = new StringBuilder();

        for (char character : message.toCharArray()) {

            if (Character.isLetter(character)) {

                int originalAlphabetPosition = character - 'a';

                int newAlphabetPosition = (originalAlphabetPosition + (key - 1)) % 26;

                char newCharacter = (char) ('a' + newAlphabetPosition);

                result.append(newCharacter);

            } else {
                result.append(character);
            }
        }
        return result.toString();
    }


    public static String decrypt(String message, int key) {

        StringBuilder result = new StringBuilder();

        for (char character : message.toCharArray()) {

            if (Character.isLetter(character)) {

                int originalAlphabetPosition = character - 'a';

                int newAlphabetPosition = (originalAlphabetPosition - (key - 1)) % 26;

                char newCharacter = (char) ('a' + newAlphabetPosition);

                result.append(newCharacter);

            } else {
                result.append(character);
            }
        }
        return result.toString();
    }


}
