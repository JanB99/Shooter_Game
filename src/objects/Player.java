package objects;
import abstractObjects.GameObject;
import abstractObjects.LivingGameObject;
import com.company.Handler;
import com.company.KeyInput;

import java.awt.*;

public class Player extends LivingGameObject {

    private KeyInput keyInput;
    private float acc = 1f, dacc = 0.5f;
    private Handler handler;

    public Player(float x, float y, int w, int h, ID id, KeyInput keyInput, Handler handler){
        super(x, y, w, h, id, 200, 5);
        this.keyInput = keyInput;
        this.handler = handler;
    }

    @Override
    public void tick() {
        if (keyInput.keys[0]){
            velY -= acc;
        } else if (keyInput.keys[1]){
            velY += acc;
        } else if (!keyInput.keys[0] && !keyInput.keys[1]){
            if (velY > 0){
                velY -= dacc;
            } else if (velY < 0){
                velY += dacc;
            }
        }

        if (keyInput.keys[2]){
            velX -= acc;
        } else if (keyInput.keys[3]){
            velX += acc;
        } else if (!keyInput.keys[2] && !keyInput.keys[3]){
            if (velX > 0){
                velX -= dacc;
            } else if (velX < 0){
                velX += dacc;
            }
        }

        x+=velX;
        y+=velY;

        velX = limit(velX, -5, 5);
        velY = limit(velY, -5, 5);

        for (int i = 0; i < handler.gameObjects.size(); i++){

            GameObject obj = handler.gameObjects.get(i);

            if (obj.id == ID.Wall){
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
            } else if (obj.id == ID.Enemy &&
                    (getCollisionBoundX().intersects(obj.getCollisionBoundX()) || getCollisionBoundY().intersects(obj.getCollisionBoundY()))){
                this.hp -= ((LivingGameObject)obj).dmg;
            }
        }

        if (hp <= 0){
            isDead = true;
            handler.stop();
        }
    }

    private float limit(float value, int min, int max) {
        if (value > max){value = max;}
        if (value < min){value = min;}
        return value;
    }

//    private boolean collide(GameObject object){
//        if (object.id == ID.Player){
//            return false;
//        }
//        if (
//                (x > object.x && x < object.x + object.w || x + w > object.x && x + w < object.x + object.w) &&
//                (y > object.y && y < object.y + object.h || y + h > object.y && y + h < object.y + object.h)){
//            return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public Rectangle getCollisionBoundX() {
        return new Rectangle((int) (x + velX), (int)y + 2, (int)(w + velX/2), h - 4);
    }

    @Override
    public Rectangle getCollisionBoundY() {
        return new Rectangle((int) x + 2, (int)(y + velY), w - 4, (int)(h + velY/2));
    }

    @Override
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.blue);
        g2d.draw(getCollisionBoundX());
        g2d.setColor(Color.RED);
        g2d.draw(getCollisionBoundY());

        g.setColor(Color.black);
        g.drawString("HP: " + hp, 20, 20);

        g.setColor(Color.black);
        g.fillRect((int)x, (int)y , w, h);
    }
}
