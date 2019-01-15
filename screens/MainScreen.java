package com.yongyong.screens;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
 * Created by USER on 2017-03-24.
 */

public class MainScreen extends AbstractScreen {
    private Texture bg; //image
    private Texture pause_pane;
    private Sprite game_pane;
    private Sprite newGame_pane;
    private Sprite setting_pane;

    private ImageButton play_icon;
    private ImageButton setting_icon;
    private ImageButton exit_icon;
    private ImageButton newGame_icon;
    private ImageButton loadGame_icon;
    private ImageButton ok_button;
    private ImageButton cancel_button;
    private ImageButton setting_ok_button;
    private ImageButton bgm_right_button;
    private ImageButton bgm_left_button;
    private ImageButton effect_right_button;
    private ImageButton effect_left_button;
    private ImageButton lan_right_button;
    private ImageButton lan_left_button;

    private boolean IsLevelSet = false;
    private boolean IsPaneAllReady = false;
    private boolean IsNewGameStart = false;
    private boolean IsSetOn = false;
    private boolean IsQuitOn = false;
    private boolean NoDataMessage = false;

    private BitmapFont mFont_text;
    private BitmapFont mFont_set_num;
    private BitmapFont mFont_set_text;
    private BitmapFont ViewVersion_text;

    public MainScreen(Cave game, SavedGameData player) {
        super(game, player);
        Gdx.app.log(Cave.LOG, "메인 화면 생성: " + getName());
        bg = new Texture("gfx/background/mainImage.png");
        pause_pane = new Texture("gfx/object/pause_pan.png");
        game_pane = new Sprite(new Texture("gfx/object/game_pane.png"));
        game_pane.setSize(120,180);
        game_pane.setPosition(550,180);
        newGame_pane = new Sprite(new Texture("gfx/object/menu_pane.png"));
        newGame_pane.setPosition(200,140);
        setting_pane = new Sprite(new Texture("gfx/object/setting_pane.png"));
        setting_pane.setPosition(100,40);

        play_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/play_icon.png"))));
        setting_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/setting_icon.png"))));
        exit_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/exit_icon.png"))));
        newGame_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/newGame_icon.png"))));
        loadGame_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/loadGame_icon.png"))));
        ok_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/ok_button.png"))));
        cancel_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/cancel_button.png"))));
        setting_ok_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/ok_button.png"))));
        bgm_left_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/arrowLeft.png"))));
        bgm_right_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/arrowRight.png"))));
        effect_left_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/arrowLeft.png"))));
        effect_right_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/arrowRight.png"))));
        lan_left_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/arrowLeft.png"))));
        lan_right_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/arrowRight.png"))));
    }

    @Override
    public void show() {
        super.show();
        player.gameData.sound.playMainBGM();
        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
            @Override
            public void run() {
                game.showAD(true);
            }
        }, 1f);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Dynalight-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 56;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        mFont = generator.generateFont(parameter);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/NanumGothic.ttf"));
        parameter.color = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.size = 52;
        mFont_set_num = generator.generateFont(parameter);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/NanumGothic.ttf"));
        parameter.color = Color.WHITE;
        parameter.borderWidth = 1;
        parameter.size = 50;
        mFont_set_text = generator.generateFont(parameter);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/NanumGothic.ttf"));
        parameter.color = Color.WHITE;
        parameter.borderWidth = 1;
        parameter.size = 14;
        ViewVersion_text = generator.generateFont(parameter);
        generator.dispose();
        mFont_text = new BitmapFont(Gdx.files.internal("font/hanguel_18.fnt"),
                Gdx.files.internal("font/hanguel_18.png"), false);
        mFont_text.setColor(Color.WHITE);
        play_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : play_icon");
                if(IsLevelSet == false) {
                    player.gameData.sound.playClickSound();
                    IsLevelSet = true;
                    IsSetOn = false;
                    IsQuitOn = false;
                    game_pane.setSize(0, 0);
                    Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                        @Override
                        public void run() {
                            IsPaneAllReady = true;
                        }
                    }, 0.385f);
                    AddActor(1);
                }
            }
        });
        play_icon.setPosition(680,270);
        stage.addActor(play_icon);
        setting_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : setting_icon");
                //셋팅창 오픈
                if(IsSetOn == false) {
                    player.gameData.sound.playClickSound();
                    IsLevelSet = false;
                    IsSetOn = true;
                    IsQuitOn = false;
                    IsPaneAllReady = false;
                    ClearActor(1);
                    AddActor(2);
                }
            }
        });
        setting_icon.setPosition(680,160);
        stage.addActor(setting_icon);
        exit_icon.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : exit_icon");
                if(IsQuitOn == false) {
                    player.gameData.sound.playClickSound();
                    IsLevelSet = false;
                    IsSetOn = false;
                    IsQuitOn = true;
                    IsPaneAllReady = false;
                    ClearActor(1);
                    AddActor(4);
                }
            }
        });
        exit_icon.setPosition(680,50);
        stage.addActor(exit_icon);
        newGame_icon.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : new_icon");
                if(IsLevelSet == true) {
                    player.gameData.sound.playClickSound();
                    FileHandle fh = Gdx.files.getFileHandle("bin/game.txt", Files.FileType.Local);
                    try{
                        if(fh.exists() && fh.length() > 8) {
                            IsNewGameStart = true;
                            ClearActor(1);
                            AddActor(4);
                        }
                        else {
                            fadeOn = true;
                            game.showAD(false);
                            Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                                @Override
                                public void run() {
                                    game.setScreen(new LevelSelectScreen(game, player));
                                }
                            }, screenDelay);
                        }
                    }
                    catch(Exception e){}
                }
            }
        });
        newGame_icon.setSize(72,72);
        newGame_icon.setPosition(568,275);
        loadGame_icon.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : load_icon");
                if(IsLevelSet == true) {
                    player.gameData.sound.playClickSound();
                    FileHandle fh = Gdx.files.getFileHandle("bin/game.txt", Files.FileType.Local);
                    try{
                        if(fh.exists() && fh.length() > 8) {
                            fadeOn = true;
                            game.showAD(false);
                            Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                                @Override
                                public void run() {
                                    player.gameData.sound.stopMainBGM();
                                    player.loadData();
                                    Gdx.app.log(Cave.LOG, "Now level : "+player.getLevel());
                                    game.setScreen(new DayScreen(game, player));
                                }
                            }, screenDelay);
                        }
                        else {
                            NoDataMessage = true;
                            ok_button.setPosition(310,145);
                            ClearActor(1);
                            AddActor(3);
                        }
                    }
                    catch(Exception e){}

                }
            }
        });
        loadGame_icon.setSize(72,72);
        loadGame_icon.setPosition(568,190);
        ok_button.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : ok_button");
                player.gameData.sound.playClickSound();
                if(IsNewGameStart == true) {
                    fadeOn = true;
                    game.showAD(false);
                    Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                        @Override
                        public void run() {
                            game.setScreen(new LevelSelectScreen(game, player));
                        }
                    }, screenDelay);
                }
                else if(IsQuitOn == true){
                    fadeOn = true;
                    game.showAD(false);
                    Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                        @Override
                        public void run() {
                            player.gameData.sound.stopMainBGM();
                            game.dispose();
                            System.exit(0);
                        }
                    }, screenDelay);
                }
                else if(NoDataMessage == true){
                    NoDataMessage = false;
                    ok_button.setPosition(220,145);
                    ClearActor(3);
                    AddActor(1);
                }
            }
        });
        ok_button.setSize(180,60);
        ok_button.setPosition(220,145);
        cancel_button.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : cancel_button");
                player.gameData.sound.playClickSound();
                if(IsNewGameStart == true) {
                    IsNewGameStart = false;
                    ClearActor(4);
                    AddActor(1);
                }
                else if(IsQuitOn = true){
                    IsQuitOn = false;
                    ClearActor(4);
                    AddActor(1);
                }
            }
        });
        cancel_button.setSize(180,60);
        cancel_button.setPosition(400,145);
        setting_ok_button.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : ok_button");
                player.gameData.sound.playClickSound();
                if(IsSetOn == true) {
                    IsSetOn = false;
                    player.saveGameData();
                    ClearActor(2);
                    AddActor(1);
                }
            }
        });
        setting_ok_button.setSize(180,60);
        setting_ok_button.setPosition(310,60);
        bgm_left_button.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : ok_button");
                player.gameData.sound.playClickSound();
                if(IsSetOn == true) {
                    player.gameData.sound.addBGMVolume(-1);
                }
            }
        });
        bgm_left_button.setSize(72,72);
        bgm_left_button.setPosition(420,335);
        bgm_right_button.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : ok_button");
                player.gameData.sound.playClickSound();
                if(IsSetOn == true) {
                    player.gameData.sound.addBGMVolume(1);
                }
            }
        });
        bgm_right_button.setSize(72,72);
        bgm_right_button.setPosition(570,335);
        effect_left_button.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : ok_button");
                if(IsSetOn == true) {
                    player.gameData.sound.addVolume(-1);
                    player.gameData.sound.playClickSound();
                }
            }
        });
        effect_left_button.setSize(72,72);
        effect_left_button.setPosition(420,255);
        effect_right_button.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : ok_button");
                if(IsSetOn == true) {
                    player.gameData.sound.addVolume(1);
                    player.gameData.sound.playClickSound();
                }
            }
        });
        effect_right_button.setSize(72,72);
        effect_right_button.setPosition(570,255);
        lan_left_button.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : ok_button");
                player.gameData.sound.playClickSound();
                if(IsSetOn == true) {
                    player.gameData.addLanguageNumber(-1);
                }
            }
        });
        lan_left_button.setSize(72,72);
        lan_left_button.setPosition(180,160);
        lan_right_button.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : ok_button");
                player.gameData.sound.playClickSound();
                if(IsSetOn == true) {
                    player.gameData.addLanguageNumber(1);
                }
            }
        });
        lan_right_button.setSize(72,72);
        lan_right_button.setPosition(540,160);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.getBatch().begin();
        stage.getBatch().draw(bg,0,0);
        mFont.draw(stage.getBatch(), Cave.TITLE, 500, 420);
        ViewVersion_text.draw(stage.getBatch(), "© 2017. YoungBot All Rights Reserved. "+Cave.TITLE+"  "+Cave.VERSION, 170, 20);
        play_icon.draw(stage.getBatch(),1f);
        setting_icon.draw(stage.getBatch(),1f);
        exit_icon.draw(stage.getBatch(),1f);
        if (IsLevelSet == true) {
            game_pane.draw(stage.getBatch(),1);
            if(IsPaneAllReady == true) {
                newGame_icon.draw(stage.getBatch(), 1);
                loadGame_icon.draw(stage.getBatch(), 1);
            }
        }
        else if (IsSetOn == true) {
            stage.getBatch().draw(pause_pane,0,0);
            setting_pane.draw(stage.getBatch(),1);
            bgm_left_button.draw(stage.getBatch(),1f);
            bgm_right_button.draw(stage.getBatch(),1f);
            effect_left_button.draw(stage.getBatch(),1f);
            effect_right_button.draw(stage.getBatch(),1f);
            lan_left_button.draw(stage.getBatch(),1f);
            lan_right_button.draw(stage.getBatch(),1f);
            mFont_set_text.draw(stage.getBatch(),"BGM",210,390);
            mFont_set_text.draw(stage.getBatch(),"EFFECT",180,310);
            mFont_set_num.draw(stage.getBatch(),""+(int)(player.gameData.sound.getBGMVolume()),(player.gameData.sound.getBGMVolume()<10?515:495),390);
            mFont_set_num.draw(stage.getBatch(),""+(int)(player.gameData.sound.getVolume()),(player.gameData.sound.getVolume()<10?515:495),310);
            mFont_set_num.draw(stage.getBatch(),player.gameData.getLanguage()==0?"KOREAN":(player.gameData.getLanguage()==1?"ENGLISH":""),280,220);
            setting_ok_button.draw(stage.getBatch(),1);
        }
        else if (IsQuitOn == true) {
            stage.getBatch().draw(pause_pane,0,0);
            newGame_pane.draw(stage.getBatch(),1);
            mFont_text.draw(stage.getBatch(), " 게임을 종료하시겠습니까?", 280, 270);
            ok_button.draw(stage.getBatch(),1);
            cancel_button.draw(stage.getBatch(),1);
        }
        if(IsNewGameStart == true){
            stage.getBatch().draw(pause_pane,0,0);
            newGame_pane.draw(stage.getBatch(),1);
            mFont_text.draw(stage.getBatch(), "저장된 데이터가 삭제됩니다.\n  계속 진행하시겠습니까?", 280, 280);
            ok_button.draw(stage.getBatch(),1);
            cancel_button.draw(stage.getBatch(),1);
        }
        if(NoDataMessage == true){
            stage.getBatch().draw(pause_pane,0,0);
            newGame_pane.draw(stage.getBatch(),1);
            mFont_text.draw(stage.getBatch(), "저장된 데이터가 없습니다.", 300, 260);
            ok_button.draw(stage.getBatch(),1);
        }
        fadeScreen.draw(batch);
        stage.getBatch().end();
        update();
    }
    public void update(){
        super.update();
        if(game_pane.getWidth() <120)
            game_pane.setSize(game_pane.getWidth() + 4.8f, game_pane.getHeight());
        if(game_pane.getHeight() <180)
            game_pane.setSize(game_pane.getWidth(),game_pane.getHeight()+7.2f);
        game_pane.setPosition(670-game_pane.getWidth(),360-game_pane.getHeight());
    }
    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
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
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg = null;
        pause_pane.dispose();
        game_pane = null;
        newGame_pane = null;
        setting_pane=null;
        play_icon.remove();
        setting_icon.remove();
        exit_icon.remove();
        newGame_icon.remove();
        loadGame_icon.remove();
        ok_button.remove();
        cancel_button.remove();
        bgm_left_button.remove();
        bgm_right_button.remove();
        effect_left_button.remove();
        effect_right_button.remove();
        lan_left_button.remove();
        lan_right_button.remove();
        setting_ok_button.remove();
        mFont_text.dispose();
        mFont_set_text.dispose();
        mFont_set_num.dispose();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public void ClearActor(int i){
        //0=게임화면,1=selecctGame,2=Setting,3=Exit,4=warning_pane
        switch(i){
            case 0:
                stage.getRoot().removeActor(play_icon);
                stage.getRoot().removeActor(setting_icon);
                stage.getRoot().removeActor(exit_icon);
                break;
            case 1:
                stage.getRoot().removeActor(newGame_icon);
                stage.getRoot().removeActor(loadGame_icon);
                break;
            case 2:
                stage.getRoot().removeActor(bgm_left_button);
                stage.getRoot().removeActor(bgm_right_button);
                stage.getRoot().removeActor(effect_left_button);
                stage.getRoot().removeActor(effect_right_button);
                stage.getRoot().removeActor(lan_left_button);
                stage.getRoot().removeActor(lan_right_button);
                stage.getRoot().removeActor(setting_ok_button);
                break;
            case 3:
                stage.getRoot().removeActor(ok_button);
                break;
            case 4:
                stage.getRoot().removeActor(ok_button);
                stage.getRoot().removeActor(cancel_button);
                break;
        }
    }
    public void AddActor(int i){
        //0=게임화면,1=selecctGame,2=Setting,3=Exit,4=warning_pane
        switch(i){
            case 0:
                stage.getRoot().addActor(play_icon);
                stage.getRoot().addActor(setting_icon);
                stage.getRoot().addActor(exit_icon);
                break;
            case 1:
                stage.getRoot().addActor(newGame_icon);
                stage.getRoot().addActor(loadGame_icon);
                break;
            case 2:
                stage.getRoot().addActor(bgm_left_button);
                stage.getRoot().addActor(bgm_right_button);
                stage.getRoot().addActor(effect_left_button);
                stage.getRoot().addActor(effect_right_button);
                stage.getRoot().addActor(lan_left_button);
                stage.getRoot().addActor(lan_right_button);
                stage.getRoot().addActor(setting_ok_button);
                break;
            case 3:
                stage.getRoot().addActor(ok_button);
                break;
            case 4:
                stage.getRoot().addActor(ok_button);
                stage.getRoot().addActor(cancel_button);
                break;
        }
    }
}
