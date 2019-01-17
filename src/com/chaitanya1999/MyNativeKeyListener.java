/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chaitanya1999;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author Chaitanya V
 */
public class MyNativeKeyListener implements NativeKeyListener {
    GUIFrame frame = null;
    PipedOutputStream pos=null;
    PrintWriter pw = null;
    public MyNativeKeyListener() throws IOException {
        pos = new PipedOutputStream();
        pw = new PrintWriter(pos);
        frame = new GUIFrame(pos);
    }
       
    

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
        
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
        pw.print(NativeKeyEvent.getKeyText(nke.getKeyCode()) + " ");
        pw.flush();
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        
    }
    
}
