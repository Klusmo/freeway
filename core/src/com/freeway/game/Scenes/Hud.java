package com.freeway.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freeway.game.FreeWay;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    public int[] score;

    Label p1Label, p2Label;


    public Hud(SpriteBatch batch){
        score = new int[2];
        score[0] = 0;
        score[1] = 0;

        viewport = new FitViewport(FreeWay.V_WIDTH, FreeWay.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Label.LabelStyle style = new Label.LabelStyle( new BitmapFont(), Color.YELLOW);
        p1Label = new Label(String.format("%02d", score[0]), style);
        p2Label = new Label(String.format("%02d", score[1]), style);

        table.add(p1Label).expandX();
        table.add(p2Label).expandX();

        stage.addActor(table);
    }

    public void update() {
        p1Label.setText(String.format("%02d", score[0]));
        p2Label.setText(String.format("%02d", score[1]));
    }

}
