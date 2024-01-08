package com.example.crypto.algorithmes;


public class Affine {

    public static String encryptMessage(char[] msg, int a, int b)
    {
        String cipher = "";
        for (int i = 0; i < msg.length; i++)
        {
            if (msg[i] != ' ')
            {
                cipher = cipher
                        + (char) ((((a * (msg[i] - 'A')) + b) % 26) + 'A');
            } else
            {
                cipher += msg[i];
            }
        }
        return cipher;
    }

    public static String decryptCipher(String cipher, int a, int b) throws Exception {
        String msg = "";
        int a_inv = 0;
        int flag = 0;

        for (int i = 0; i < 26; i++)
        {
            flag = (a * i) % 26;

            if (flag == 1)
            {
                a_inv = i;
            }
        }

        if(a_inv == 0){
            throw  new Exception("key is not inversible");


        }

        for (int i = 0; i < cipher.length(); i++)
        {
            if (cipher.charAt(i) != ' ')
            {
                msg = msg + (char) (((a_inv *
                        ((cipher.charAt(i) + 'A' - b)) % 26)) + 'A');
            }
            else
            {
                msg += cipher.charAt(i);
            }
        }

        return msg;
    }

}
