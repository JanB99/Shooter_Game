package abstractObjects;

import objects.ID;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    public float x, y;
    public float velX, velY;
    public int w, h;
    public ID id;
    public boolean isDead;

    public GameObject(float x, float y, int w, int h, ID id){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.velX = 0;
        this.velY = 0;
        this.id = id;
        this.isDead = false;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getCollisionBoundX();

    public abstract Rectangle getCollisionBoundY();


}
