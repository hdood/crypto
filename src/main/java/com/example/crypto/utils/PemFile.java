package com.example.crypto.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.Key;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

public class PemFile {

    private PemObject pemObject;

    public PemFile (Key key, String description) {
        this.pemObject = new PemObject(description, key.getEncoded());
    }

    public void write(String filename) throws IOException {

        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));

        try {

            pemWriter.writeObject(this.pemObject);

        }catch (Exception e){

            System.err.println(e);

        }
        finally {

            pemWriter.close();
            
        }
    }
}

