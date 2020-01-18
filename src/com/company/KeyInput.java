package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    public boolean keys[] = new boolean[4];

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            keys[0] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S){
            keys[1] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A){
            keys[2] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D){
            keys[3] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            keys[0] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S){
            keys[1] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A){
            keys[2] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D){
            keys[3] = false;
        }
    }
}
