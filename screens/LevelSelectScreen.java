package com.yongyong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.yongyong.cave.Cave;
import com.yongyong.gamelib.AbstractScreen;
import com.yongyong.objects.SavedGameData;

/**
 * Created by USER on 2017-04-27.
 */

public class LevelSelectScreen extends AbstractScreen {
    private Texture bg;
    private Texture exp_pane;
    private Texture pause_pane;

    private ImageButton before_icon;
    private ImageButton easy_icon;
    private ImageButton normal_icon;
    private ImageButton hard_icon;
    private ImageButton start_icon;
    private ImageButton ok_button;
    private ImageButton cancel_button;

    private BitmapFont mFont_text;
    private BitmapFont mFont;

    private int selectLevel = 0;
    private boolean exp_Open = false;

    public LevelSelectScreen(Cave game, SavedGameData player) {
        super(game, player);
        Gdx.app.log(Cave.LOG, "레벨 화면 생성: " + getName());
        bg = new Texture("gfx/background/level_select_screen.png");
        exp_pane = new Texture("gfx/object/menu_pane.png");
        pause_pane = new Texture("gfx/object/pause_pan.png");

        before_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/back_icon.png"))));
        easy_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/easy_icon.png"))));
        normal_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/normal_icon.png"))));
        hard_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/hard_icon.png"))));
        start_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/next_icon_g.png"))));
        ok_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/ok_button.png"))));
        cancel_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/cancel_button.png"))));

    }

    public void show() {
        super.show();
        game.gameData.setGameStartOn(false);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Dynalight-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 56;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        //parameter.shadowOffsetX = 3;
        //parameter.shadowOffsetY = 3;
        mFont = generator.generateFont(parameter);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/NanumGothic.ttf"));
        parameter.color = Color.WHITE;
        parameter.borderWidth = 1;
        parameter.size = 14;
        mFont_text = new BitmapFont(Gdx.files.internal("font/hanguel_18.fnt"),
                Gdx.files.internal("font/hanguel_18.png"), false);
        mFont_text.setColor(Color.WHITE);
        generator.dispose();

        before_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : before_icon");
                player.gameData.sound.playClickSound();
                fadeOn = true;
                Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                    @Override
                    public void run() {
                        game.setScreen(new MainScreen(game, player));
                    }
                }, screenDelay);
            }
        });
        before_icon.setPosition(20, 380);
        stage.addActor(before_icon);
        iconReset();
        easy_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : easy_icon");
                if(selectLevel != 1){
                    player.gameData.sound.playClickSound();
                    selectLevel = 1;
                    player.setLevel(0);
                    iconReset();
                    easy_icon.setPosition(80, 150);
                }
            }
        });
        easy_icon.setPosition(80, 160);
        stage.addActor(easy_icon);

        normal_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : normal_icon");
                if(selectLevel != 2){
                    player.gameData.sound.playClickSound();
                    selectLevel = 2;
                    player.setLevel(1);
                    iconReset();
                    normal_icon.setPosition(300, 150);
                }
            }
        });
        normal_icon.setPosition(300, 160);
        stage.addActor(normal_icon);

        hard_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : hard_icon");
                if(selectLevel != 3){
                    player.gameData.sound.playClickSound();
                    selectLevel = 3;
                    player.setLevel(2);
                    iconReset();
                    hard_icon.setPosition(520, 150);
                }
            }
        });
        hard_icon.setPosition(520, 160);
        stage.addActor(hard_icon);
        start_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : start_icon");
                    exp_Open = true;
                    stage.getRoot().addActor(ok_button);
                    stage.getRoot().addActor(cancel_button);
                    stage.getRoot().removeActor(before_icon);
                    stage.getRoot().removeActor(easy_icon);
                    stage.getRoot().removeActor(normal_icon);
                    stage.getRoot().removeActor(hard_icon);
                    stage.getRoot().removeActor(start_icon);
            }
        });
        start_icon.setPosition(650, 30);
        start_icon.setScale(1.2f);
        stage.addActor(start_icon);
        ok_button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                exp_Open = false;
                stage.getRoot().removeActor(ok_button);
                stage.getRoot().removeActor(cancel_button);
                stage.getRoot().addActor(before_icon);
                stage.getRoot().addActor(easy_icon);
                stage.getRoot().addActor(normal_icon);
                stage.getRoot().addActor(hard_icon);
                stage.getRoot().addActor(start_icon);
                game.showReward(true);
            }
        });
        ok_button.setSize(180,60);
        ok_button.setPosition(220,145);
        cancel_button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : start_icon");
                exp_Open = false;
                stage.getRoot().removeActor(ok_button);
                stage.getRoot().removeActor(cancel_button);
                stage.getRoot().addActor(before_icon);
                stage.getRoot().addActor(easy_icon);
                stage.getRoot().addActor(normal_icon);
                stage.getRoot().addActor(hard_icon);
                stage.getRoot().addActor(start_icon);
            }
        });
        cancel_button.setSize(180,60);
        cancel_button.setPosition(400,145);
    }
    public void render(float delta) {
        super.render(delta);
        stage.getBatch().begin();
        stage.getBatch().draw(bg,0,0);
        mFont.draw(stage.getBatch(), "Select Level", 300, 460);
        before_icon.draw(stage.getBatch(),1f);
        easy_icon.draw(stage.getBatch(),1f);
        normal_icon.draw(stage.getBatch(),1f);
        hard_icon.draw(stage.getBatch(),1f);
        if(selectLevel == 1){
            mFont_text.draw(stage.getBatch(), "꾸준한 운동으로 단련된 생존전문가.\n그에게는 항상 행운이 뒤따른다.", 100, 100);
            mFont_text.draw(stage.getBatch(), "행운 80%\n질병확률 25%\n음식 회복량 75%", 450, 120);
        }
        else if(selectLevel == 2){
            mFont_text.draw(stage.getBatch(), "등산을 좋아하는 한 청년.\n긍정적인 마인드로 버텨내 보자.", 100, 100);
            mFont_text.draw(stage.getBatch(), "행운 65%\n질병확률 50%\n음식 회복량 75%", 450, 120);
        }
        else if(selectLevel == 3){
            mFont_text.draw(stage.getBatch(), "혼자 무언가를 해본 적 없는 불쌍한 청년.\n아마 오래 버티기는 힘들 것 같다..", 100, 100);
            mFont_text.draw(stage.getBatch(), "행운 50%\n질병확률 75%\n음식 회복량 50%", 450, 120);
        }
        if(selectLevel != 0)
            start_icon.draw(stage.getBatch(),1f);
        if(exp_Open) {
            stage.getBatch().draw(pause_pane,0,0);
            stage.getBatch().draw(exp_pane,200,140);
            mFont_text.draw(stage.getBatch(), "간단한 동영상 광고를 시청해야 새로운\n      게임을 시작하실 수 있습니다.\n        영상을 시청하시겠습니까?", 240, 300);
            ok_button.draw(stage.getBatch(),1);
            cancel_button.draw(stage.getBatch(),1);
        }
        fadeScreen.draw(batch);
        stage.getBatch().end();
        update();
    }
    public void update(){
        super.update();
        if(game.gameData.isGameStartOn()){
            if(selectLevel != 0) {
                game.gameData.setGameStartOn(false);
                startGame();
            }
        }
    }
    private void iconReset(){
        easy_icon.setPosition(80, 160);
        easy_icon.setScale(1.0f);
        normal_icon.setPosition(300, 160);
        normal_icon.setScale(1.0f);
        hard_icon.setPosition(520, 160);
        hard_icon.setScale(1.0f);
    }
    public void startGame(){
        fadeOn = true;
        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
            @Override
            public void run() {
                Gdx.app.log(Cave.LOG, "GotoMainScreen");
                player.gameData.sound.stopMainBGM();
                player.NewGameSetting();
                player.saveData(); //새로 저장
                game.setScreen(new DayScreen(game, player));
            }
        }, screenDelay);
    }
    public void dispose() {
        super.dispose();
        bg.dispose();
        easy_icon.remove();
        normal_icon.remove();
        hard_icon.remove();
        before_icon.remove();
        mFont.dispose();
        mFont_text.dispose();
    }
}
