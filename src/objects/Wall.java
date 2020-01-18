package objects;

import abstractObjects.GameObject;
import com.company.Game;

import java.awt.*;

public class Wall extends GameObject {

    public Wall(float x, float y, int w, int h, ID id, int velX, int velY) {
        super(x, y, w, h, id);
        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (x > Game.WIDTH - w || x < 0){
            velX *= -1;
        }
        if (y > Game.HEIGHT - h || y < 0){
            velY *= -1;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect((int) x, (int) y, w, h);
    }

    @Override
    public Rectangle getCollisionBoundX() {
        return new Rectangle((int)x, (int)y, w, h);
    }

    @Override
    public Rectangle getCollisionBoundY() {
        return new Rectangle((int)x, (int)y, w, h);
    }
}
