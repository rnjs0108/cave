package com.yongyong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import com.yongyong.cave.Cave;
import com.yongyong.gamelib.AbstractScreen;
import com.yongyong.objects.SavedGameData;

/**
 * Created by USER on 2017-03-31.
 */

public class IntroScreen extends AbstractScreen {
    private Sprite bg; //image

    public IntroScreen(Cave game, SavedGameData player) {
        super(game, player);
        bg = new Sprite(new Texture("gfx/background/YOUNGBOT.png"));
        bg.setSize(stage.getWidth(),stage.getHeight());
    }

    @Override
    public void show() {
        super.show();
        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
            @Override
            public void run() {
                Gdx.app.log(Cave.LOG, "GotoMainScreen");
                fadeOn = true;
            }
        }, 3f - screenDelay);
        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
            @Override
            public void run() {
                Gdx.app.log(Cave.LOG, "GotoMainScreen");
                game.setScreen(new MainScreen(game,player));
            }
        }, 3.0f);
    }
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        bg.draw(batch);
        fadeScreen.draw(batch);
        batch.end();
        update();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg = null;
    }
}
