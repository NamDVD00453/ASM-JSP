package com.assignmentjavaweb.security;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Security {
    public String endcodeMd5(String string){
        String endcode = "MD5";
        String error = "Endcode fail";
        try {
            MessageDigest md = MessageDigest.getInstance(endcode);
            md.update(string.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return myHash;
        } catch (NoSuchAlgorithmException e) {
            return error;
        }
    }

    public String eandomString(){
        byte[] array = new byte[8];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
    public void writeLog(String string){
        String pathname = "log.txt";
        File file = new File(pathname);
        String status = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            if (file.createNewFile()){
                status = dateFormat.format(new Date()) + " - LOG : File " + pathname + " was created!";
            }else {
                status = dateFormat.format(new Date()) + " - LOG : File " + pathname + " has exists ! Ready to write new log !";
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(status);
            writer.newLine();
            writer.append(dateFormat.format(new Date()) + " - LOG : " + string);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
