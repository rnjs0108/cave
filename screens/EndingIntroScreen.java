package com.yongyong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Timer;
import com.yongyong.cave.Cave;
import com.yongyong.gamelib.AbstractScreen;
import com.yongyong.objects.SavedGameData;

/**
 * Created by USER on 2017-07-03.
 */

public class EndingIntroScreen extends AbstractScreen {
    private Sprite bg; //image
    private Sprite whiteScreen; //image
    private Sprite cloud; //image

    private int cloud_x=0;
    private int ani_count=0;
    private float white_alpha = 1f;
    private boolean ViewText = false;
    public EndingIntroScreen(Cave game, SavedGameData player) {
        super(game, player);
        bg = new Sprite(new Texture("gfx/background/endingIntroScreen.png"));
        bg.setSize(stage.getWidth(),stage.getHeight());
        whiteScreen = new Sprite(new Texture("gfx/background/whiteScreen.png"));
        whiteScreen.setSize(stage.getWidth(),stage.getHeight());
        cloud = new Sprite(new Texture("gfx/background/ending_sky.png"));
        }
    @Override
    public void show() {
        super.show();
        player.gameData.sound.playEndingBGM();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/NanumGothic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 42;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        mFont = generator.generateFont(parameter);
        generator.dispose();
        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
            @Override
            public void run() {
                Gdx.app.log(Cave.LOG, "font view");
                ViewText = true;
            }
        }, 2f);
        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
            @Override
            public void run() {
                fadeOn = true;
            }
        }, 6f-screenDelay);
        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
            @Override
            public void run() {
                Gdx.app.log(Cave.LOG, "goto GameScreen");
                game.setScreen(new EndingScreen(game,player));
            }
        }, 6f);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        cloud.draw(batch);
        bg.draw(batch);
        whiteScreen.draw(batch);
        if(ViewText == true)
            mFont.draw(batch, "You survived.", 260, 400);
        batch.end();
        update();
    }

    public void update(){
        ani_count++;
        if(ani_count<200){
            white_alpha -= 0.0035f;
        }
        else
            white_alpha -= 0.0005f;
        if(ani_count%4 == 0)
            cloud_x--;
        cloud.setPosition(cloud_x,0);
        whiteScreen.setAlpha(white_alpha);
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
        whiteScreen = null;
        cloud = null;
    }
}
