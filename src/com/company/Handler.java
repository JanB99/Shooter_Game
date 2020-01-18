package com.company;

import abstractObjects.GameObject;
import objects.Bullet;
import objects.ID;
import objects.Player;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> gameObjects;
    private Game game;

    public Handler(Game game){

        gameObjects = new LinkedList<>();
        this.game = game;
    }

    public void render(Graphics g){
        for (int i = 0; i < gameObjects.size(); i++){
            gameObjects.get(i).render(g);
        }
    }

    public void tick(){
        for (int i = gameObjects.size() - 1; i >= 0 ; i--){
            gameObjects.get(i).tick();
            if (gameObjects.get(i).isDead){
                gameObjects.remove(i);
            }
        }
    }

    public Player findPlayer(){
        for (int i = 0; i < gameObjects.size(); i++){
            if (gameObjects.get(i).id == ID.Player){
                return (Player) gameObjects.get(i);
            }
        }
        return null;
    }

    public int getNumOfBasics(){
        int n = 0;
        for (int i = 0; i < gameObjects.size(); i++){
            if (gameObjects.get(i).id == ID.Enemy){
                n++;
            }
        }
        return n;
    }

    public void addGameObject(GameObject object){
        this.gameObjects.add(object);
    }

    public void stop(){
        game.stop();
    }

    public int amountOf(ID id){
        int count = 0;
        for (int i = 0; i < gameObjects.size(); i++){
            if (gameObjects.get(i).id == id){
                count++;
            }
        }

        return count;
    }
}
