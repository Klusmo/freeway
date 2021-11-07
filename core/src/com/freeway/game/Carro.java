package com.freeway.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Carro extends Thread implements Disposable {
    public Sprite sprite;
    private int velocity, width, height;
    private boolean endGame;
    private Array<CollisionBuffer> collisionBufferArray;

    public Carro(Texture texture, int x, int y, int vel){
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x,y);
        if (vel > 0)
            this.sprite.rotate(180);

        this.velocity = vel;
        this.endGame = false;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.collisionBufferArray = new Array<>();
    }

    public void registerCollisionBuffer(CollisionBuffer collisionBuffer){
        collisionBufferArray.add(collisionBuffer);
    }

    public void update(){
        int x = (int) this.sprite.getX();
        int newPos;

        if (this.velocity > 0)
            newPos = (x > FreeWay.V_WIDTH) ? -20: x + this.velocity;
        else
            newPos = (x < -20) ? FreeWay.V_WIDTH + 20: x + this.velocity;

        // check collisions
        for (CollisionBuffer cb: collisionBufferArray){
            if ((newPos + width > cb.refX) && (newPos <= cb.refXEnd)){
                cb.inserir((int) sprite.getY(), (int) sprite.getY() + height, cb.refX);
            }

            if (this.velocity > 0){
                if ((newPos > cb.refXEnd)){
                    cb.retirar((int) sprite.getY(), (int) sprite.getY() + height, cb.refX);
                }
            } else {
                if ((newPos + width < cb.refX)){
                    cb.retirar((int) sprite.getY(), (int) sprite.getY() + height, cb.refX);
                }
            }
        }

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