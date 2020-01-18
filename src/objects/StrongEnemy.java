package objects;

import abstractObjects.LivingGameObject;

import java.awt.*;

public class StrongEnemy extends LivingGameObject {

    public StrongEnemy(float x, float y, int w, int h, ID id, int hp, int dmg) {
        super(x, y, w, h, id, hp, dmg);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Rectangle getCollisionBoundX() {
        return null;
    }

    @Override
    public Rectangle getCollisionBoundY() {
        return null;
    }
}
