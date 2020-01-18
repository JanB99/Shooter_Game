package com.company;

import objects.BasicEnemy;
import objects.EnemyID;
import objects.ID;

import java.util.*;

public class Spawn {

    private Handler handler;
    private Random r = new Random();
    private int score, numOfBasics;

    public Spawn(Handler handler){
        this.handler = handler;
        score = 0;
        numOfBasics = 0;
    }

    public void tick(){
        score++;

        numOfBasics = handler.getNumOfBasics();

//        if (score % 50 == 0 && numOfBasics < 10){
//            handler.addGameObject(
//                    new BasicEnemy(
//                            r.nextInt(Game.WIDTH - 16),
//                            r.nextInt(Game.HEIGHT - 16),
//                            16, 16,
//                            ID.Enemy, handler.findPlayer(), handler, 10, 2, EnemyID.Basic));
//        }
    }
}
