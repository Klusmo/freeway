package com.freeway.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freeway.game.Carro;
import com.freeway.game.FreeWay;
import com.freeway.game.Scenes.Hud;

public class GameScreen implements Screen {
    private FreeWay game;
    Texture texture;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Hud hud;
    private Array<Carro> carros;
    private Sprite player1, player2;

    public GameScreen(FreeWay game){
        this.game = game;
        
        // Background
        texture = new Texture("Road.png");

        // Camara && ViewPort
        camera = new OrthographicCamera();
        camera.position.set(FreeWay.V_WIDTH /2f, FreeWay.V_HEIGHT/2f, 0);
        viewport = new FitViewport(FreeWay.V_WIDTH, FreeWay.V_HEIGHT, camera);

        hud = new Hud(game.batch);


        // Carros
        carros = new Array<>();
        carros.add(new Carro(new Texture("Car1.png"), 0,  19, 1));
        carros.add(new Carro(new Texture("Car2.png"), 0,  36, 2));
        carros.add(new Carro(new Texture("Car3.png"), 0,  53, 3));
        carros.add(new Carro(new Texture("Car4.png"), 0,  69, 4));
        carros.add(new Carro(new Texture("Car5.png"), 0,  84, 5));
        carros.add(new Carro(new Texture("Car6.png"), 0, 101, -5));
        carros.add(new Carro(new Texture("Car7.png"), 0, 116, -4));
        carros.add(new Carro(new Texture("Car8.png"), 0, 133, -3));
        carros.add(new Carro(new Texture("Car9.png"), 0, 149, -2));
        carros.add(new Carro(new Texture("Car10.png"), 0, 165, -1));

        player1 = new Sprite(new Texture("ChickenStop.png"));
        player1.setPosition(58, 6);

        player2 = new Sprite(new Texture("ChickenStop.png"));
        player2.setPosition(200, 6);
    }


    @Override
    public void show() {

    }

    public void update(){

        for (Carro car: carros) {
            car.update();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        update();

        game.batch.begin();
        game.batch.draw(texture, 0, 0);

        for (Carro car: carros) {
            car.sprite.draw(game.batch);
        }

        player1.draw(game.batch);
        player2.draw(game.batch);

        game.batch.end();



        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
