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

    private int p1Score, p2Score;

    Label p1Label, p2Label;


    public Hud(SpriteBatch batch){
        p1Score = p2Score = 0;

        viewport = new FitViewport(FreeWay.V_WIDTH, FreeWay.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Label.LabelStyle style = new Label.LabelStyle( new BitmapFont(), Color.YELLOW);
        p1Label = new Label(String.format("%02d", p1Score), style);
        p2Label = new Label(String.format("%02d", p2Score), style);

        table.add(p1Label).expandX();
        table.add(p2Label).expandX();

        stage.addActor(table);
    }


}
