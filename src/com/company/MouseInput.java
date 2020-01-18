package com.company;

import objects.Bullet;
import objects.ID;
import objects.Player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private Player player = null;
    private Camera cam;

    public MouseInput(Handler handler){
        this.handler = handler;
//        this.cam = cam;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        player = handler.findPlayer();

        float angle = (float) Math.atan2(e.getY() - player.y - 10, e.getX() - player.x - 10);
        float dx = (float) (Math.cos(angle) * 10);
        float dy = (float) (Math.sin(angle) * 10);

        handler.addGameObject(new Bullet(player.x + player.w/2, player.y + player.h/2, 8, 8, ID.Bullet, dx, dy, player, handler));
    }
}
