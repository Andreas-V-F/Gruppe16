/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Persistence;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensumboosted.GUI.FXMLDocumentController;

/**
 *
 * @author Alex
 */
public class Encryption {

    public Encryption() {
    }

    public String encryptString(String input) {
        StringBuffer sb = new StringBuffer();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());

            byte[] digest = md.digest();

            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString().toUpperCase();
    }
}
