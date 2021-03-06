package com.freeway.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;
import com.freeway.game.Scenes.Hud;

public class Player extends Thread implements Disposable {
    public Sprite sprite;
    private int baseVelocity;
    private boolean endGame;
    private int startY, keyUp, keyDown, scoreIndex, height;
    private Hud hud;
    public CollisionBuffer collisionBuffer;

    public Player(Texture texture, int x, int y, int vel, Hud hud, int scoreIndex){
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x, y);
        this.height = texture.getHeight();

        this.startY = y;

        this.baseVelocity = vel;
        this.endGame = false;

        this.keyUp = Input.Keys.W;
        this.keyDown = Input.Keys.S;
        this.hud = hud;
        this.scoreIndex = scoreIndex;

        this.collisionBuffer = new CollisionBuffer(texture.getWidth(), FreeWay.V_HEIGHT,  x);
    }

    public void setKeys(int up, int down){
        this.keyUp = up;
        this.keyDown= down;
    }


    public void update(){
        int y = (int) this.sprite.getY();
        int newPos, sig = 0;

        if(Gdx.input.isKeyPressed(keyUp))
            sig = 1;
        if(Gdx.input.isKeyPressed(keyDown))
            sig = -1;


        newPos = y + (sig) * this.baseVelocity;
        if ( newPos < startY )
            newPos = y;
        if (y > 174){
            newPos = startY;
            hud.score[this.scoreIndex] += 1;
        }

        if(collisionBuffer.checkCollision(newPos, newPos + height)){
            newPos = startY;
        }
        this.sprite.setPosition(this.sprite.getX(), newPos);
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
