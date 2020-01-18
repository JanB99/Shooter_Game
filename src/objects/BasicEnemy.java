package objects;

import abstractObjects.LivingGameObject;
import abstractObjects.GameObject;
import com.company.Handler;
import com.company.Vector;

import java.awt.*;

public class BasicEnemy extends LivingGameObject {

    private Player player;
    private Handler handler;
    private float maxVel;

    public BasicEnemy(float x, float y, int w, int h, ID id, Player player, Handler handler) {
        super(x, y, w, h, id, 10, 2);
        this.player = player;
        this.handler = handler;
        maxVel = 1;
    }

    @Override
    public void tick() {
        Vector desired = new Vector(player.x, player.y);
        desired.sub(x, y);
        desired.setMag(maxVel);

        desired.sub(velX, velY);
        desired.limit(maxVel*2);

        velX += desired.x;
        velY += desired.y;

        x += velX;
        y += velY;

        for (int i = 0; i < handler.gameObjects.size(); i++){
            GameObject obj = handler.gameObjects.get(i);

            if (obj.id == ID.Wall){
                collisionsWall(obj);
            }

            if (obj.id == ID.Player){
                collisionsPlayer(obj);
            }
        }

        if (hp <= 0){
            isDead = true;
        }
    }

    public void collisionsWall(GameObject obj){
        if (getCollisionBoundX().intersects(obj.getCollisionBoundX())) {

            if (obj.velX < 0 && x < obj.x + obj.w/2){
                x = obj.x - w;
            } else if (obj.velX > 0 && x > obj.x + obj.w/2){
                x = obj.x + obj.w;
            }

            if (velX > 0) {
                velX = 0;
                x = obj.x - w;
            } else if (velX < 0) {
                velX = 0;
                x = obj.x + obj.w;
            }
        }

        if (getCollisionBoundY().intersects(obj.getCollisionBoundY())){

            if (obj.velY < 0 && y < obj.y + obj.h/2){
                y = obj.y - h;
            } else if (obj.velY > 0 && y > obj.y + obj.h/2){
                y = obj.y + obj.h;
            }

            if (velY > 0){
                velY = 0;
                y = obj.y - h;
            } else if (velY < 0){
                velY = 0;
                y = obj.y + obj.h;
            }
        }
    }

    public void collisionsPlayer(GameObject obj){
        if (getCollisionBoundX().intersects(obj.getCollisionBoundX())) {

            if (obj.velX < 0 && x < obj.x + obj.w/2){
                x = obj.x - w;
            } else if (obj.velX > 0 && x > obj.x + obj.w/2){
                x = obj.x + obj.w;
            }

            if (velX > 0) {
                velX = 0;
                x = obj.x - w;
            } else if (velX < 0) {
                velX = 0;
                x = obj.x + obj.w;
            }

            ((Player)obj).hp -= dmg;
        }

        if (getCollisionBoundY().intersects(obj.getCollisionBoundY())){

            if (obj.velY < 0 && y < obj.y + obj.h/2){
                y = obj.y - h;
            } else if (obj.velY > 0 && y > obj.y + obj.h/2){
                y = obj.y + obj.h;
            }

            if (velY > 0){
                velY = 0;
                y = obj.y - h;
            } else if (velY < 0){
                velY = 0;
                y = obj.y + obj.h;
            }

            ((Player)obj).hp -= dmg;
        }
    }

    public float map(float value, float start1, float stop1, float start2, float stop2){
        return ((value - start1)/(stop1 - start1)) * (stop2 - start2) + start2;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.blue);
        g2d.draw(getCollisionBoundX());
        g2d.setColor(Color.RED);
        g2d.draw(getCollisionBoundY());

        g.setColor(Color.black);
        g.drawString("HP: " + hp, 300, 20);

        g.setColor(Color.magenta);
        g.fillRect((int)x, (int)y, w, h);
    }

    @Override
    public Rectangle getCollisionBoundX() {
        return new Rectangle((int) (x + velX), (int)y + 4, (int)(w + velX/2), h - 8);
    }

    @Override
    public Rectangle getCollisionBoundY() {
        return new Rectangle((int) x + 4, (int)(y + velY), w - 8, (int)(h + velY/2));
    }
}
