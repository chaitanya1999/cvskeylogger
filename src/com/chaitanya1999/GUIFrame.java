/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chaitanya1999;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Chaitanya V
 */
public class GUIFrame {
    JFrame frm = null;
    PipedInputStream inputStream;
    BufferedReader buff = null;
    TrayIcon trayIcon = null;
    public GUIFrame(PipedOutputStream outputStream) throws IOException {
        frm = new JFrame();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLayout(new BorderLayout());
        frm.setSize(320,240);
        frm.setTitle("Cv's KeyLogger");
        frm.setType(Window.Type.UTILITY);
        URL img = GUIFrame.class.getResource("/com/chaitanya1999/image/keylogger_icon.png");
        frm.setIconImage(Toolkit.getDefaultToolkit().getImage(img));
        trayIcon = new TrayIcon(frm.getIconImage(), "Cv's KeyLogger");
        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frm.toFront();
            }
        });
        
        try {
            SystemTray.getSystemTray().add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(GUIFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JTextArea text = new JTextArea();
        JScrollPane scroll = new JScrollPane(text);
        frm.add(scroll,BorderLayout.CENTER);
        text.setEditable(false);
        text.setLineWrap(true);
        frm.setVisible(true);
        inputStream = new PipedInputStream(outputStream);
        buff = new BufferedReader(new InputStreamReader(inputStream));
        Thread reader = new Thread(){
            @Override
            public void run(){
                while(true){
                    StringBuilder str = new StringBuilder();
                    try {
                        while(inputStream.available()>0){
                            str.append((char)inputStream.read());
                        }
                        text.append(str.toString());
                        str = new StringBuilder();
                    } catch (IOException ex) {
                        Logger.getLogger(GUIFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        reader.start();
    }
    
}
