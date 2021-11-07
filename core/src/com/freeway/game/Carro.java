package com.freeway.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Carro extends Thread{
    public Sprite sprite;
    private int velocity;
    private boolean endGame;

    public Carro(Texture texture, int x, int y, int vel){
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x,y);
        if (vel > 0)
            this.sprite.rotate(180);

        this.velocity = vel;
        this.endGame = false;
    }

    public void update(){
        int x = (int) this.sprite.getX();
        int newPos;

        if (this.velocity > 0)
            newPos = (x > FreeWay.V_WIDTH) ? -20: x + this.velocity;
        else
            newPos = (x < -20) ? FreeWay.V_WIDTH + 20: x + this.velocity;
        this.sprite.setPosition(newPos, this.sprite.getY());
    }

    public void run(){
        while(!endGame){
            try {
                update();
                Thread.sleep(20);
            }catch (Exception ex){
                System.out.println("Erro: " + ex);
            }
        }
    }

    public void dispose(){
        this.endGame = true;
    }

}