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

public class EndingScreen extends AbstractScreen {
    private Texture bg;

    private Texture endingAni00;
    private Texture endingAni01;
    private Texture endingAni02;
    private Texture endingAni03;
    private Texture endingAni04;
    private Texture circle;

    private String[] endingText;

    private ImageButton before_icon;
    private ImageButton next_icon;
    private ImageButton skip_icon;

    private BitmapFont mFont_text;
    private BitmapFont mFont;

    private int page_num = 0;
    private int endingCase = 0; //0:트루엔딩, 1:일반엔딩, 2:베드엔딩
    private int textBatchNumber = 30;

    public EndingScreen(Cave game, SavedGameData player) {
        super(game, player);
        Gdx.app.log(Cave.LOG, "엔딩 화면 생성: " + getName());
        endingText = new String[5];
        circle = new Texture("gfx/endingSource/endingCircle.png");
        before_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/before_icon.png"))));
        next_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/next_icon.png"))));
        skip_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/skip_icon.png"))));
    }

    public void show() {
        super.show();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Dynalight-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 56;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        mFont = generator.generateFont(parameter);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/NanumGothic.ttf"));
        parameter.color = Color.WHITE;
        parameter.borderWidth = 1;
        parameter.size = 14;
        mFont_text = new BitmapFont(Gdx.files.internal("font/hanguel_18.fnt"),
                Gdx.files.internal("font/hanguel_18.png"), false);
        mFont_text.setColor(Color.WHITE);
        generator.dispose();
        player.setStoryCase(3);
        player.AddAngel(-20);

        if(player.getStoryCase() == 1)
            endingText[0] = " 비틀거리며 밖으로 나왔다. 날카로운 햇빛이 내 눈을 " +
                    "찌른다. 두 손에는 이미 곰의 것인지 내 것인지 모를 피가 " +
                    "흥건해 있다. 달려드는 모기떼가 반가울 정도이다.";
        else if(player.getStoryCase() == 2)
            endingText[0] = " 가까스로 물밖으로 나와 누워서 숨을 몰아쉰다. 주변" +
                    "에서는 지저분한 바위가 아닌 산뜻한 풀냄새들이 나를 반겨준다. " +
                    "결국 나는 해내고야 만 것이다!";
        else if(player.getStoryCase() == 3)
            endingText[0] = " 헬리콥터 소리가 귀를 때린다. 주변에서는 나에게 링거" +
                    "를 놔주며 연신 괜찮냐고 물어본다. 나는 고개를 돌려 어찌보면 " +
                    "정들은 동굴 구멍안을 들여다봤다. 잘있어.";
        else
            endingText[0] = " 비틀거리며 밖으로 나왔다. 날카로운 햇빛이 내 눈을 " +
                    "찌른다. 두 손에는 이미 곰의 것인지 내 것인지 모를 피가 " +
                    "흥건해 있다. 달려드는 모기떼가 반가울 정도이다.";
        endingText[1] = " 가물가물하지만 저 집은 내 집이다. 부모님은 날 얼" +
                "마나 걱정하실까? 친구들은? 내가 아끼는 사람들 모두 잘 있" +
                "을까? 어서 달려가서 보고싶다.";
        if(player.getNowAngel() <5) //0:트루엔딩, 1:일반엔딩, 2:베드엔딩
            endingCase = 2;
        else if(player.getNowAngel() >= 20)
            endingCase = 0;
        else
            endingCase = 1;
        if(endingCase == 0) {
            bg = new Texture("gfx/endingSource/endingBG01.png");
            if(player.getStoryCase() == 1)
            endingAni00 = new Texture("gfx/endingSource/ending_bear_true.png");
            else if(player.getStoryCase() == 2)
                endingAni00 = new Texture("gfx/endingSource/ending_rope_true.png");
            else if(player.getStoryCase() == 3)
                endingAni00 = new Texture("gfx/endingSource/ending_119_true.png");
            else
                endingAni00 = new Texture("gfx/endingSource/ending_bear_true.png");
            endingAni01 = new Texture("gfx/endingSource/ending_true_00.png");
            endingAni02 = new Texture("gfx/endingSource/ending_true_01.png");
            endingAni03 = new Texture("gfx/endingSource/ending_true_02.png");
            endingAni04 = new Texture("gfx/endingSource/ending_true_03.png");
            endingText[2] = " 집에 들어가려는 찰라 누군가 나를 거칠게 안는다. " +
                    "\"아버지!!\" 아버지는 나보다 더 초쵀한 몰골을 하고 말" +
                    "없이 흐느낀다. 옆에서 어머니가 말씀하셨다. \"아무말도 말으렴. 돌아와서 고맙다.\"";
            endingText[3] = " 집에는 이미 내가 아는 모든 사람들이 와있었다. " +
                    "\"드디어 왔구나!\" 태연한 척 장난을 치지만, 누구보다 걱정" +
                    "스러운 표정을 하고 있다. 나도 그들을 보며 이내 웃고만다.";
            endingText[4] = " 악몽같은 기억들이지만 내가 겪었던 모든 일들이 " +
                    "어찌보면 좀 더 나를 성장시켜주는 감사한 밑거름이 되었다. " +
                    "아무리 살아가는게 어렵더라도 동굴안에서 혼자 사는 것 만할까? 나는 오늘도 두 손을 모아 감사한다.";
        }
        else if(endingCase == 1) {
            bg = new Texture("gfx/endingSource/endingBG02.png");
            if(player.getStoryCase() == 1)
                endingAni00 = new Texture("gfx/endingSource/ending_bear_normal.png");
            else if(player.getStoryCase() == 2)
                endingAni00 = new Texture("gfx/endingSource/ending_rope_normal.png");
            else if(player.getStoryCase() == 3)
                endingAni00 = new Texture("gfx/endingSource/ending_119_normal.png");
            else
                endingAni00 = new Texture("gfx/endingSource/ending_bear_normal.png");
            endingAni01 = new Texture("gfx/endingSource/ending_normal_00.png");
            endingAni02 = new Texture("gfx/endingSource/ending_normal_01.png");
            endingAni03 = new Texture("gfx/endingSource/ending_normal_02.png");
            endingAni04 = new Texture("gfx/endingSource/ending_normal_03.png");
            endingText[2] = " 집 문을 열자, 전화를 하던 아버지는 깜짝 놀라더니 나에게 달려온다. " +
                    "\"정말 다행이구나, 얘야.\" 아버지의 한 손에는 내 얼굴이 그려진 전단지가 있었다. " +
                    "나를 이만큼이나 찾고 다니신거구나... 어머니는 나를 껴안고 한참을 우셨다.";
            endingText[3] = " 내가 돌아왔다는 소식을 듣고 친구들이 하나 둘 집으로 찾아왔다. 너가 없어서 심심했다는 둥, 태연하게 말을 하지만 모두가" +
                    " 나를 걱정해줬다는 게 너무 감사하다.";
            endingText[4] = " 분명 동굴안은 다신 돌아가기 싫을만큼 끔찍했다. 하지만 가끔씩 삶이 힘들어질 때, 한번쯤 찾아가고 싶은 마음이 든다. 물론 마음만 말이다.";
        }
        else {
            bg = new Texture("gfx/endingSource/endingBG03.png");
            if(player.getStoryCase() == 1)
                endingAni00 = new Texture("gfx/endingSource/ending_bear_bad.png");
            else if(player.getStoryCase() == 2)
                endingAni00 = new Texture("gfx/endingSource/ending_rope_bad.png");
            else if(player.getStoryCase() == 3)
                endingAni00 = new Texture("gfx/endingSource/ending_119_bad.png");
            else
                endingAni00 = new Texture("gfx/endingSource/ending_bear_bad.png");
            endingAni01 = new Texture("gfx/endingSource/ending_bad_00.png");
            endingAni02 = new Texture("gfx/endingSource/ending_bad_01.png");
            endingAni03 = new Texture("gfx/endingSource/ending_bad_02.png");
            endingAni04 = new Texture("gfx/endingSource/ending_bad_03.png");
            endingText[2] = " 집문을 열자, 시끄러운 TV소리가 깔깔대며 나를 반긴다. 아버지는 술을 진탕 마셨는지, 바닥까지 병이 굴러다닌다. " +
                    "늘 그렇듯 나는 평범한 일상속에 돌아왔다.";
            endingText[3] = " 사실 나에게 친구는 없다. 어릴 적 있었던 친구들은 나와 싸우고 하나 둘 사라졌기 때문이다." +
                    " 너무 피곤하다. 여전히 노린내 나는 내 침대에 누워 잠을 청해본다.";
            endingText[4] = " 다음 날, 나는 기억을 더듬어 내가 떨어졌었던 구멍을 찾아왔다. 저 곳이다 포근한 엄마 품... 셋을 세면 저 안으로 다시 들어가는거야. 하나, 둘...";
        }

        before_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : before_icon");
                stage.getRoot().removeActor(before_icon);
                stage.getRoot().removeActor(next_icon);
                stage.getRoot().removeActor(skip_icon);
                fadeOn = true;
                Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                    @Override
                    public void run() {
                        if(page_num > 0){
                            page_num--;
                            stage.getRoot().addActor(before_icon);
                            stage.getRoot().addActor(next_icon);
                            stage.getRoot().addActor(skip_icon);
                            if(page_num == 0)
                                stage.getRoot().removeActor(before_icon);
                        }
                    }
                }, screenDelay);
            }
        });
        before_icon.setPosition(20, 20);
        stage.addActor(before_icon);

        next_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : normal_icon");
                stage.getRoot().removeActor(before_icon);
                stage.getRoot().removeActor(next_icon);
                stage.getRoot().removeActor(skip_icon);
                fadeOn = true;
                Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                    @Override
                    public void run() {
                        if(page_num <4){
                            page_num++;
                            stage.getRoot().addActor(before_icon);
                            stage.getRoot().addActor(next_icon);
                            stage.getRoot().addActor(skip_icon);
                        }
                        else if(page_num == 4) {
                            player.deleteData();
                            player.gameData.sound.stopEndingBGM();
                            game.setScreen(new MainScreen(game, player));
                            Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                                @Override
                                public void run() {
                                    game.showForward(true);
                                }
                            }, 1f);
                        }
                    }
                }, screenDelay);

            }
        });
        next_icon.setPosition(684, 20);
        stage.addActor(next_icon);
        skip_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : normal_icon");
                fadeOn = true;
                Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                    @Override
                    public void run() {
                        player.deleteData();
                        player.gameData.sound.stopEndingBGM();
                        game.setScreen(new MainScreen(game, player));
                        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                            @Override
                            public void run() {
                                game.showForward(true);
                            }
                        }, 1f);
                    }
                }, screenDelay);
            }
        });
        skip_icon.setPosition(600, 370);
        skip_icon.setSize(190, 108);
        stage.addActor(skip_icon);
        for(int i=0;i<5;i++)
            endingText[i] = player.gameManager.note.SortText(endingText[i], textBatchNumber);
    }
    public void render(float delta) {
        super.render(delta);
        stage.getBatch().begin();
        stage.getBatch().draw(bg,272,200);
        if(page_num == 0){
            stage.getBatch().draw(endingAni00,272,200);
            mFont_text.draw(stage.getBatch(), endingText[0], 180, 150);
        }
        else if(page_num == 1){
            stage.getBatch().draw(endingAni01,272,200);
            mFont_text.draw(stage.getBatch(), endingText[1], 180, 150);
        }
        else if(page_num== 2){
            stage.getBatch().draw(endingAni02,272,200);
            mFont_text.draw(stage.getBatch(), endingText[2], 180, 150);
        }
        else if(page_num== 3){
            stage.getBatch().draw(endingAni03,272,200);
            mFont_text.draw(stage.getBatch(), endingText[3], 180, 150);
        }
        else if(page_num== 4){
            stage.getBatch().draw(endingAni04,272,200);
            mFont_text.draw(stage.getBatch(), endingText[4], 180, 150);
        }
        stage.getBatch().draw(circle,272,200);
        fadeScreen.draw(batch);
        if(page_num > 0)
            before_icon.draw(stage.getBatch(),1f);
        if(page_num < 5)
            next_icon.draw(stage.getBatch(),1f);
        skip_icon.draw(stage.getBatch(),1);
        stage.getBatch().end();
        update();
    }
    public void update(){
        super.update();
    }
    public void dispose() {
        super.dispose();
        bg.dispose();
        endingAni00.dispose();
        endingAni01.dispose();
        endingAni02.dispose();
        endingAni03.dispose();
        endingAni04.dispose();
        before_icon.remove();
        next_icon.remove();
        skip_icon.remove();
        mFont.dispose();
        mFont_text.dispose();
    }

}
