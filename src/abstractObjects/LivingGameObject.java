package abstractObjects;

import objects.ID;

public abstract class LivingGameObject extends GameObject{

    public int hp, dmg;

    public LivingGameObject(float x, float y, int w, int h, ID id, int hp, int dmg) {
        super(x, y, w, h, id);
        this.hp = hp;
        this.dmg = dmg;
    }
}
