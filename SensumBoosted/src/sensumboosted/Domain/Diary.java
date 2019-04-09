/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.Domain;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Diary {

    public void createDiary() {
        String fileName = getFileName("Enter output file name.");

        File output = new File(fileName);

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(output, true));
                 Scanner scan = new Scanner(System.in)) {
            String line = "";
            while (!line.equalsIgnoreCase("SAVE")) {
                System.out.println("Write your diary (SAVE to end the log):");
                line = scan.nextLine();
                pw.println(line);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Diary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String getFileName(String prompt) {
        Scanner keyboard = new Scanner(System.in);
        String fileName = null;
        System.out.println(prompt);
        fileName = keyboard.next();

        return fileName;
    }
}
