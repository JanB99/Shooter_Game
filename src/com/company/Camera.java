package com.company;

import objects.Player;

public class Camera {

    private int x, y;
    private Player player;

    public Camera(Handler handler){
        player = handler.findPlayer();
    }

    public void tick(){
        x = (int) player.x;
        y = (int) player.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
