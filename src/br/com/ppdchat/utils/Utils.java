package br.com.ppdchat.utils;

/**
 * Created by Roland on 9/29/16.
 */
import javax.swing.JOptionPane;
import java.awt.Component;

/**
 * Created by Roland on 7/17/16.
 */
public class Utils {
    public static void log(String s) {
        System.out.printf(s + "\n");
    }

    public static void displayDialog(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message);
    }

}
