package com.company;

import objects.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 600, HEIGHT = 600;
    private Thread thread;
    private boolean running;

    private Handler handler;
    private KeyInput keyInput;
    private MouseInput mouseInput;
    private Spawn spawner;
//    private Camera cam;

    public Game(){
        keyInput = new KeyInput();
        this.addKeyListener(keyInput);

        handler = new Handler(this);
        handler.addGameObject(new Player(WIDTH/2, HEIGHT/2, 16, 16, ID.Player, keyInput, handler));
        handler.addGameObject(new Wall(100, 150, 100,100, ID.Wall, 1, 0));
        handler.addGameObject(new Wall(30, 300, 20,150, ID.Wall, 0, 1));
        handler.addGameObject(new BasicEnemy(200, 200, 20, 20, ID.Enemy, handler.findPlayer(), handler, 20, 5, EnemyID.Strong));

//        cam = new Camera(handler);
        spawner = new Spawn(handler);

        mouseInput = new MouseInput(handler);
        this.addMouseListener(mouseInput);

        new Window("Shooter game", HEIGHT, WIDTH, this);
    }

    public static void main(String[] args) {
	    new Game();
    }

    public synchronized void start() {
        this.thread = new Thread(this);
        this.thread.start();
        this.running = true;
    }

    public synchronized void stop() {
        try{
            this.thread.join();
            this.running = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running){
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
//        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

//        g2d.translate(-cam.getX() + WIDTH/2, -cam.getY() + HEIGHT/2);

        handler.render(g);

//        g2d.translate(cam.getX() + WIDTH/2, cam.getY() + HEIGHT/2);

        g.dispose();
        bs.show();
    }

    private void tick() {
        handler.tick();
        spawner.tick();
//        cam.tick();
    }
}
