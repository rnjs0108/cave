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
 * Created by USER on 2017-05-22.
 */

public class GameOverScreen extends AbstractScreen {
    private Texture bg;
    private Texture BlackScreen;

    private int B0_y = -480;
    private int B1_y = 480;
    private int ani_count = 1;

    private ImageButton next_icon;
    private boolean buttonOn = false;

    private BitmapFont mFont_text;
    private BitmapFont mFont;

    private String DeadText = "";

    public GameOverScreen(Cave game, SavedGameData player) {
        super(game, player);
        bg = new Texture("gfx/background/deadScreen.png");
        BlackScreen = new Texture("gfx/background/blackScreen.png");
        next_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/next_icon.png"))));
    }

    public void show() {
        super.show();
        player.gameData.sound.playDeadSound();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/DecoSolidSlash.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 96;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        mFont = generator.generateFont(parameter);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/DecoSolid.ttf"));
        parameter.color = Color.RED;
        parameter.borderWidth = 1;
        parameter.size = 14;
        mFont_text = new BitmapFont(Gdx.files.internal("font/hanguel_18.fnt"),
                Gdx.files.internal("font/hanguel_18.png"), false);
        mFont_text.setColor(Color.WHITE);
        generator.dispose();

        next_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : next_icon");
                fadeOn = true;
                Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                    @Override
                    public void run() {
                        player.deleteData();
                        game.setScreen(new MainScreen(game, player));
                        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                            @Override
                            public void run() {
                                game.showForward(true);
                            }
                        }, 2f);
                    }
                }, screenDelay);
            }
        });
        next_icon.setPosition(680, 20);
        if(player.getStoryCase() == 1)
            DeadText = "\"야생곰은 무서웠다. 그 뿐이다...\"";
        else if(player.getStoryCase() == 2)
            DeadText = "\"섣부른 판단은 죽음만을 부를 뿐.\"";
        else if(player.getStoryCase() == 3)
            DeadText = "\"왜 나는 핸드폰에 집착한 것인가.\"";
        else if(player.getStoryCase() == 6)
            DeadText = "\"난 아마 쥐들의 좋은 식량이 되겠지.\"";
        else if(player.getStoryCase() == 7)
            DeadText = "\"다음 생에는 뱀으로 태어나고 싶다.\"";
        else
            DeadText = "   \"정말 후회스러운 삶이었다.\"";

        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
            @Override
            public void run() {
                Gdx.app.log(Cave.LOG, "iconUpdate");
                buttonOn = true;
                stage.addActor(next_icon);
            }
        }, 2.5f);
    }
    public void render(float delta) {
        super.render(delta);
        stage.getBatch().begin();
        stage.getBatch().draw(bg,0,0);
        stage.getBatch().draw(BlackScreen,0,B0_y);
        stage.getBatch().draw(BlackScreen,0, B1_y);
        if(buttonOn == true) {
            mFont.draw(stage.getBatch(), "Game Over", 210, 320);
            next_icon.draw(stage.getBatch(), 1f);
            mFont_text.draw(stage.getBatch(), DeadText, 280, 160);
        }
        fadeScreen.draw(batch);
        stage.getBatch().end();
        update();
    }
    public void update(){
        super.update();
        if(ani_count >0){
            ani_count++;
            if(ani_count % 2 == 0){
                B0_y += 4;
                B1_y -= 4;
                if(B1_y <= 240)
                    ani_count = 0;
            }
        }
    }
    public void dispose() {
        super.dispose();
        bg.dispose();
        BlackScreen.dispose();
        next_icon.remove();
        mFont.dispose();
        mFont_text.dispose();
    }
}
