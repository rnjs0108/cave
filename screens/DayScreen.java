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
 * Created by USER on 2017-03-27.
 */

public class DayScreen extends AbstractScreen {
    private Sprite bg; //image

    public DayScreen(Cave game, SavedGameData player) {
        super(game, player);
        bg = new Sprite(new Texture("gfx/background/dayScreen.png"));
        bg.setSize(stage.getWidth(),stage.getHeight());
    }

    @Override
    public void show() {
        super.show();
        player.gameData.sound.playGameBGM();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Typo_SsangmunDongB.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 68;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 3;
        mFont = generator.generateFont(parameter);
        generator.dispose();
        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
            @Override
            public void run() {
                fadeOn = true;
            }
        }, 3f-screenDelay);
        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
            @Override
            public void run() {
                Gdx.app.log(Cave.LOG, "goto GameScreen");
                if(player.getNowHp() == 0) {
                    player.gameData.sound.stopGameBGM();
                    game.setScreen(new GameOverScreen(game, player));
                }
                else {
                    if(player.getEndingOn() == true) {
                        player.gameData.sound.stopGameBGM();
                        game.setScreen(new EndingIntroScreen(game, player));
                    }
                    else
                    game.setScreen(new com.yongyong.screens.GameScreen(game, player));
                }
            }
        }, 3f);
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
        mFont.draw(batch, (player.getDay()+1+" day"), 290, 240);
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
