package com.freeway.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freeway.game.Carro;
import com.freeway.game.CollisionBuffer;
import com.freeway.game.FreeWay;
import com.freeway.game.Player;
import com.freeway.game.Scenes.Hud;

public class GameScreen implements Screen {
    private FreeWay game;
    Texture background;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Hud hud;
    private Array<Carro> carros;
    private Player player1, player2;

    private CollisionBuffer collisionP1, collisionP2;

    public GameScreen(FreeWay game){
        this.game = game;

        background = new Texture("Road.png");

        // Camera && ViewPort
        camera = new OrthographicCamera();
        camera.position.set(FreeWay.V_WIDTH /2f, FreeWay.V_HEIGHT/2f, 0);
        viewport = new FitViewport(FreeWay.V_WIDTH, FreeWay.V_HEIGHT, camera);

        hud = new Hud(game.batch);

        carros = new Array<>();
        carros.add(new Carro(new Texture( "Car1.png"),   0,  19,  1));
        carros.add(new Carro(new Texture( "Car2.png"),   0,  36,  2));
        carros.add(new Carro(new Texture( "Car3.png"),   0,  53,  3));
        carros.add(new Carro(new Texture( "Car4.png"),   0,  69,  4));
        carros.add(new Carro(new Texture( "Car5.png"),   0,  84,  5));
        carros.add(new Carro(new Texture( "Car6.png"), 272, 101, -5));
        carros.add(new Carro(new Texture( "Car7.png"), 272, 116, -4));
        carros.add(new Carro(new Texture( "Car8.png"), 272, 133, -3));
        carros.add(new Carro(new Texture( "Car9.png"), 272, 149, -2));
        carros.add(new Carro(new Texture("Car10.png"), 272, 165, -1));

        player1 = new Player(new Texture("ChickenStop.png"),  64, 6, 3, hud, 0);
        player1.setKeys(Input.Keys.W, Input.Keys.S);
        player2 = new Player(new Texture("ChickenStop.png"), 208, 6 , 3, hud, 1);
        player2.setKeys(Input.Keys.UP, Input.Keys.DOWN);


        // Start em cada thread dos carros
        for (Carro car: carros) {
            car.registerCollisionBuffer(player1.collisionBuffer);
            car.registerCollisionBuffer(player2.collisionBuffer);

            car.start();
        }

        player1.start();
        player2.start();
    }

    @Override
    public void show() {

    }

    public void handleInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        handleInput();

        game.batch.begin();

        game.batch.draw(background, 0, 0);

        // Draw cars
        for (Carro car: carros) {
            car.sprite.draw(game.batch);
        }

        player1.sprite.draw(game.batch);
        player2.sprite.draw(game.batch);

        game.batch.end();
        hud.update();
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        for(Carro car: carros){
            car.dispose();
        }
        player1.dispose();
        player2.dispose();
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
