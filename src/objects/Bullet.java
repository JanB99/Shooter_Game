package objects;

import abstractObjects.LivingGameObject;
import abstractObjects.GameObject;
import com.company.Handler;

import java.awt.*;

public class Bullet extends GameObject {

    public float velX, velY;
    private Handler handler;
    private GameObject owner;

    public Bullet(float x, float y, int w, int h, ID id, float velX, float velY, GameObject owner, Handler handler) {
        super(x, y,w, h, id);
        this.velX = velX;
        this.velY = velY;
        this.owner = owner;
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        for (int i = handler.gameObjects.size() - 1; i >= 0; i--){

            GameObject obj = handler.gameObjects.get(i);

            if (owner.id == ID.Player && obj.id == ID.Enemy && collision(obj)){
                isDead = true;
            }

            if (owner.id == ID.Enemy && obj.id == ID.Player && collision(obj)){
                isDead = true;
            }

            if (obj.id == ID.Wall){
                if (getCollisionBoundX().intersects(obj.getCollisionBoundX()) || getCollisionBoundY().intersects(obj.getCollisionBoundY())){
                    isDead = true;
                }
            }
        }
    }

    public boolean collision(GameObject obj){
        if (getCollisionBoundX().intersects(obj.getCollisionBoundX()) || getCollisionBoundY().intersects(obj.getCollisionBoundY())) {
            ((LivingGameObject)obj).hp -= ((LivingGameObject)owner).dmg;
            return true;
        }

        return false;
    }

    @Override
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.blue);
        g2d.draw(getCollisionBoundX());
        g2d.setColor(Color.RED);
        g2d.draw(getCollisionBoundY());

        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, w, h);
    }

    @Override
    public Rectangle getCollisionBoundX() {
        return new Rectangle((int) (x + velX), (int)y + 1, (int)(w + velX/2), h - 2);
    }

    @Override
    public Rectangle getCollisionBoundY() {
        return new Rectangle((int) x + 1, (int)(y + velY), w - 2, (int)(h + velY/2));
    }
}
