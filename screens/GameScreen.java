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
 * Created by USER on 2017-03-27.
 */

public class GameScreen extends AbstractScreen {
    private Texture bg;
    private Texture helpScreen;
    private Texture hand_base;
    private Texture hand_front;
    private Texture light;
    private Texture dark_on;
    private Texture dark_off;
    private Texture hand_poison;
    private Texture hand_hurt;
    private Texture hand_blood;
    private Texture torch;
    private Texture inven_pane;
    private Texture pause_pane;
    private Texture item_pane;
    private Texture need_item_pane;
    private Texture item_use_box_n;
    private Texture health_icon;
    private Texture health_gage;
    private Texture hunger_icon;
    private Texture hunger_gage;
    private Texture water_icon;
    private Texture water_gage;
    private Texture torch_icon;
    private Texture torch_gage;
    private Texture empty_gage;
    private Texture gage_r;
    private Texture gage_l;
    private Texture bloody_icon;
    private Texture poison_icon;
    private Texture menu_pane;
    private Texture menu_box;
    private Texture note_pane;
    private Texture snake;
    private Texture rat;
    private Texture skull;
    private Texture deadman;
    private Texture oil;
    private Texture cave02_water;
    private Texture cave02_back;
    private Texture cave03_front;
    private Texture cave05_light;
    private Texture ETgenerator;
    private Texture mix_pane;
    private Texture[] mix_item = new Texture[8];
    private int animal_animate = 0;
    private int water_y = 0;
    private Texture negative_box;
    private Texture plus;
    private Texture equals;
    private Texture item_plus0;
    private Texture item_plus1;
    private Texture mix_donot_icon;
    private Texture item_x;
    private Texture setting_pane;
    private Texture random_item;
    private Texture[] item_image = new Texture[19];

    private ImageButton Inven_icon;
    private ImageButton Mix_icon;
    private ImageButton Menu_icon;
    private ImageButton Note_icon;
    private ImageButton item_use_box;
    private ImageButton x_box;
    private ImageButton[] inven_box = new ImageButton[20];
    private ImageButton Back_icon;
    private ImageButton Setting_icon;
    private ImageButton Main_icon;
    private ImageButton Before_icon;
    private ImageButton Next_icon;
    private ImageButton DoIt_icon;
    private ImageButton DoNot_icon;
    private ImageButton GoNext_icon;
    private ImageButton Delete_icon;
    private ImageButton[] mix_box = new ImageButton[8];
    private ImageButton mix_do_icon;
    private ImageButton bgm_right_button;
    private ImageButton bgm_left_button;
    private ImageButton effect_right_button;
    private ImageButton effect_left_button;
    private ImageButton setting_ok_button;

    private BitmapFont mFont_text;
    private BitmapFont diary_text;
    private BitmapFont mFont_set_num;
    private BitmapFont mFont_set_text;

    private boolean IsBagOpen = false;
    private boolean IsMixOpen = false;
    private boolean IsMenuOpen = false;
    private boolean IsNoteOpen = false;
    private boolean IsSetOn = false;
    private boolean IsFirstGame = false;
    private int NotePage = 0;
    private int hand_y = 0;
    private int hand_animate = 0;
    private boolean hand_up = false;
    public int exp_pane_x = 300;
    public int need_item_x = -120;


    public GameScreen(Cave game, SavedGameData player) {
        super(game, player);
        Inven_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/bag_icon.png"))));
        Mix_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/mix_icon.png"))));
        Menu_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/menu_icon.png"))));
        Note_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/note_icon.png"))));
        for(int i=0;i<20;i++){
            inven_box[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/inventory_box.png"))));
            if(i<8)
            mix_box[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/mix_box.png"))));
        }
        item_use_box = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/exp_box.png"))));
        x_box = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/x.png"))));
        Back_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/back_icon.png"))));
        Setting_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/set_icon.png"))));
        Main_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/main_icon.png"))));
        Before_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/before_icon.png"))));
        Next_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/next_icon.png"))));
        DoIt_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/doIt_icon.png"))));
        DoNot_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/doNot_icon.png"))));
        GoNext_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/icon/goNext_icon.png"))));
        Delete_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/delete_button.png"))));
        mix_do_icon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/mix_do_icon.png"))));
        bgm_left_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/arrowLeft.png"))));
        bgm_right_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/arrowRight.png"))));
        effect_left_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/arrowLeft.png"))));
        effect_right_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/arrowRight.png"))));
        setting_ok_button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("gfx/object/ok_button.png"))));
    }

    @Override
    public void show() {
        super.show();
        if(player.getStoryCase() == 2)
            player.gameData.sound.playWaterSound();
        else if(player.getStoryCase() == 4)
            player.gameData.sound.playFlySound();
        Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
            @Override
            public void run() {
                game.showAD(true);
            }
        }, 1f);
        //폰트 배치
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/NanumGothic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 26;
        parameter.color = Color.BLACK;
        mFont = generator.generateFont(parameter);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/NanumGothic.ttf"));
        parameter.color = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.size = 48;
        mFont_set_num = generator.generateFont(parameter);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/NanumGothic.ttf"));
        parameter.color = Color.WHITE;
        parameter.borderWidth = 1;
        parameter.size = 46;
        mFont_set_text = generator.generateFont(parameter);
        mFont_text = new BitmapFont(Gdx.files.internal("font/hanguel_18.fnt"),
                Gdx.files.internal("font/hanguel_18.png"), false);
        mFont_text.setColor(Color.BLACK);
        diary_text = new BitmapFont(Gdx.files.internal("font/diary_text.fnt"),
                Gdx.files.internal("font/diary_text.png"), false);
        diary_text.setColor(Color.BLACK);
        generator.dispose();

        //이미지 설정
        bg = getBackgroundImage();
        helpScreen = new Texture("gfx/background/helpScreen.png");
        setting_pane = new Texture("gfx/object/game_setting_pane.png");
        cave02_water = new Texture("gfx/background/cave_02_water.png");
        cave02_back = new Texture("gfx/background/cave_02_back.png");
        cave03_front = new Texture("gfx/background/cave_03_front.png");
        cave05_light = new Texture("gfx/background/cave_05_light.png");
        hand_base = new Texture("gfx/object/hand_base.png");
        hand_front = new Texture("gfx/object/hand_front.png");
        hand_hurt = new Texture("gfx/object/hand_hurt.png");
        hand_poison = new Texture("gfx/object/hand_poison.png");
        hand_blood = new Texture("gfx/object/hand_blood.png");
        torch = new Texture("gfx/object/torch.png");
        light = new Texture("gfx/object/light.png");
        dark_on = new Texture("gfx/object/dark_b.png");
        dark_off = new Texture("gfx/object/dark_a.png");
        snake = new Texture("gfx/object/snake.png");
        rat = new Texture("gfx/object/rat.png");
        skull = new Texture("gfx/object/skull.png");
        deadman = new Texture("gfx/object/deadman.png");
        oil = new Texture("gfx/object/oil.png");
        ETgenerator = new Texture("gfx/object/generator.png");

        bloody_icon = new Texture("gfx/icon/bloody_icon.png");
        poison_icon = new Texture("gfx/icon/poison_icon.png");
        health_icon = new Texture("gfx/icon/health_icon.png");
        hunger_icon = new Texture("gfx/icon/hunger_icon.png");
        water_icon = new Texture("gfx/icon/water_icon.png");
        torch_icon = new Texture("gfx/icon/fire_icon.png");
        health_gage = new Texture("gfx/icon/gage_h.png");
        hunger_gage = new Texture("gfx/icon/gage_hu.png");
        water_gage = new Texture("gfx/icon/gage_w.png");
        torch_gage = new Texture("gfx/icon/gage_f.png");
        empty_gage = new Texture("gfx/icon/gage_e.png");
        gage_r = new Texture("gfx/icon/gageBar_r.png");
        gage_l = new Texture("gfx/icon/gageBar_l.png");
        note_pane = new Texture("gfx/object/note.png");

        inven_pane = new Texture("gfx/object/inventory_pan.png");
        need_item_pane = new Texture("gfx/object/need_item_pane.png");
        pause_pane = new Texture("gfx/object/pause_pan.png");
        item_pane = new Texture("gfx/object/exp_pane.png");
        item_use_box_n = new Texture("gfx/object/exp_box_n.png");
        menu_pane = new Texture("gfx/object/menu_pane.png");
        menu_box = new Texture("gfx/object/menu_box.png");
        mix_pane = new Texture("gfx/object/mix_pane.png");
        mix_item[0] = new Texture("gfx/item/bandage.png");
        mix_item[1] = new Texture("gfx/item/Antidote.png");
        mix_item[2] = new Texture("gfx/item/first_aid_kit.png");
        mix_item[3] = new Texture("gfx/item/armGuard.png");
        mix_item[4] = new Texture("gfx/item/clean_water.png");
        mix_item[5] = new Texture("gfx/item/meat_g.png");
        mix_item[6] = new Texture("gfx/item/fabric.png");
        mix_item[7] = new Texture("gfx/item/rope.png");
        negative_box = new Texture("gfx/object/negative_box.png");
        plus = new Texture("gfx/object/plus.png");
        equals = new Texture("gfx/object/equals.png");
        mix_donot_icon = new Texture("gfx/object/mix_donot_icon.png");
        item_x = new Texture("gfx/object/item_x.png");
        random_item = new Texture("gfx/object/randomItem.png");
        item_image[0] = new Texture("gfx/item/can.png");
        item_image[1] = new Texture("gfx/item/clean_water.png");
        item_image[2] = new Texture("gfx/item/dirty_water.png");
        item_image[3] = new Texture("gfx/item/fabric.png");
        item_image[4] = new Texture("gfx/item/first_aid_kit.png");
        item_image[5] = new Texture("gfx/item/hook.png");
        item_image[6] = new Texture("gfx/item/knife.png");
        item_image[7] = new Texture("gfx/item/oil.png");
        item_image[8] = new Texture("gfx/item/phone.png");
        item_image[9] = new Texture("gfx/item/rope.png");
        item_image[10] = new Texture("gfx/item/stick.png");
        item_image[11] = new Texture("gfx/item/charger.png");
        item_image[12] = new Texture("gfx/item/meat.png");
        item_image[13] = new Texture("gfx/item/bandage.png");
        item_image[14] = new Texture("gfx/item/Antidote.png");
        item_image[15] = new Texture("gfx/item/armGuard.png");
        item_image[16] = new Texture("gfx/item/depurant.png");
        item_image[17] = new Texture("gfx/item/meat_g.png");
        item_image[18] = new Texture("gfx/item/fireCracker.png");


        //아이콘 액티브 설정
        inven_box[0].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[0] != null){
                    player.expOn = true;
                    player.inven_num = 0;
                }
            }
        });
        inven_box[1].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[1] != null){
                    player.expOn = true;
                    player.inven_num = 1;
                }
            }
        });
        inven_box[2].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[2] != null){
                    player.expOn = true;
                    player.inven_num = 2;
                }
            }
        });
        inven_box[3].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[3] != null){
                    player.expOn = true;
                    player.inven_num = 3;
                }
            }
        });
        inven_box[4].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[4] != null){
                    player.expOn = true;
                    player.inven_num = 4;
                }
            }
        });
        inven_box[5].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[5] != null){
                    player.expOn = true;
                    player.inven_num = 5;
                }
            }
        });
        inven_box[6].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[6] != null){
                    player.expOn = true;
                    player.inven_num = 6;
                }
            }
        });
        inven_box[7].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[7] != null){
                    player.expOn = true;
                    player.inven_num = 7;
                }
            }
        });
        inven_box[8].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[8] != null){
                    player.expOn = true;
                    player.inven_num = 8;
                }
            }
        });
        inven_box[9].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[9] != null){
                    player.expOn = true;
                    player.inven_num = 9;
                }
            }
        });
        inven_box[10].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[10] != null){
                    player.expOn = true;
                    player.inven_num = 10;
                }
            }
        });
        inven_box[11].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[11] != null){
                    player.expOn = true;
                    player.inven_num = 11;
                }
            }
        });
        inven_box[12].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[12] != null){
                    player.expOn = true;
                    player.inven_num = 12;
                }
            }
        });
        inven_box[13].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[13] != null){
                    player.expOn = true;
                    player.inven_num = 13;
                }
            }
        });
        inven_box[14].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[14] != null){
                    player.expOn = true;
                    player.inven_num = 14;
                }
            }
        });
        inven_box[15].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[15] != null){
                    player.expOn = true;
                    player.inven_num = 15;
                }
            }
        });
        inven_box[16].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[16] != null){
                    player.expOn = true;
                    player.inven_num = 16;
                }
            }
        });
        inven_box[17].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[17] != null){
                    player.expOn = true;
                    player.inven_num = 17;
                }
            }
        });
        inven_box[18].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[18] != null){
                    player.expOn = true;
                    player.inven_num = 18;
                }
            }
        });
        inven_box[19].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 클릭 시 작동
                if(player.inventory[19] != null){
                    player.expOn = true;
                    player.inven_num = 19;
                }
            }
        });
        for(int i=0;i<20;i++)
            inven_box[i].setPosition(30+(96*(i%5)),336-(96*(i/5)));

        mix_box[0].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //붕대 조합
                item_plus0 = new Texture("gfx/item/fabric.png");
                    player.expOn = true;
                    player.inven_num = 0;
            }
        });
        mix_box[1].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //해독제 조합
                item_plus0 = new Texture("gfx/item/clean_water.png");
                item_plus1 = new Texture("gfx/item/depurant.png");
                player.expOn = true;
                player.inven_num = 1;
            }
        });
        mix_box[2].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //치료킷 조합
                item_plus0 = new Texture("gfx/item/bandage.png");
                item_plus1 = new Texture("gfx/item/Antidote.png");
                player.expOn = true;
                player.inven_num = 2;
            }
        });
        mix_box[3].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //손목아머 조합
                item_plus0 = new Texture("gfx/item/fabric.png");
                item_plus1 = new Texture("gfx/item/stick.png");
                player.expOn = true;
                player.inven_num = 3;
            }
        });
        mix_box[4].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //물 조합
                item_plus0 = new Texture("gfx/item/dirty_water.png");
                item_plus1 = new Texture("gfx/item/depurant.png");
                player.expOn = true;
                player.inven_num = 4;
            }
        });
        mix_box[5].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //고기 조합
                item_plus0 = new Texture("gfx/item/meat.png");
                item_plus1 = new Texture("gfx/icon/fire_icon.png");
                player.expOn = true;
                player.inven_num = 5;
            }
        });
        mix_box[6].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //천조각 조합
                item_plus0 = new Texture("gfx/item/rope.png");
                player.expOn = true;
                player.inven_num = 6;
            }
        });
        mix_box[7].addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //로프 조합
                item_plus0 = new Texture("gfx/item/bandage.png");
                player.expOn = true;
                player.inven_num = 7;
            }
        });
        for(int i=0;i<8;i++)
            mix_box[i].setPosition(36+(96*(i%2)),314-(96*(i/2)));
        mix_do_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //use버튼 클릭시
                if(player.expOn == true){
                    Gdx.app.log(Cave.LOG, "keyPressed : Use_icon");
                    if(player.inven_num==0) { //붕대
                        if (player.getHaveItemNumber(3) >= 3) {
                            player.deleteItemNumber(3);
                            player.deleteItemNumber(3);
                            player.deleteItemNumber(3);
                            player.addItem(13);
                        }
                    }
                    else if(player.inven_num==1) { //해독제
                        if (player.getHaveItemNumber(1) >= 1 && player.getHaveItemNumber(16) >= 1) {
                            player.deleteItemNumber(1);
                            player.deleteItemNumber(16);
                            player.addItem(14);
                        }
                    }
                    else if(player.inven_num==2) { //치료킷
                        if (player.getHaveItemNumber(13) >= 1 && player.getHaveItemNumber(14) >= 1) {
                            player.deleteItemNumber(13);
                            player.deleteItemNumber(14);
                            player.addItem(4);
                        }
                    }
                    else if(player.inven_num==3) { //손목아머
                        if (player.getHaveItemNumber(3) >= 3 && player.getHaveItemNumber(10) >= 1) {
                            player.deleteItemNumber(3);
                            player.deleteItemNumber(3);
                            player.deleteItemNumber(3);
                            player.deleteItemNumber(10);
                            player.addItem(15);
                        }
                    }
                    else if(player.inven_num==4) { //깨끗한 물
                        if (player.getHaveItemNumber(2) >= 1 && player.getHaveItemNumber(16) >= 1) {
                            player.deleteItemNumber(2);
                            player.deleteItemNumber(16);
                            player.addItem(1);
                        }
                    }
                    else if(player.inven_num==5) { //구운 고기
                        if (player.getHaveItemNumber(12) >= 1 && player.getNowTorch() > 0) {
                            player.deleteItemNumber(12);
                            player.AddTorch(-1);
                            player.addItem(17);
                        }
                    }
                    else if(player.inven_num==6) { //천조각
                        if (player.getHaveItemNumber(9) >= 1)
                        {
                            player.deleteItemNumber(9);
                            player.addItem(3);
                            player.addItem(3);
                            player.addItem(3);
                        }
                    }
                    else if(player.inven_num==7) { //로프
                        if (player.getHaveItemNumber(13) >= 2)
                        {
                            player.deleteItemNumber(13);
                            player.deleteItemNumber(13);
                            player.addItem(9);
                        }
                    }
                    player.saveData();
                }}
        });
        mix_do_icon.setPosition(650,210);

        x_box.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //x버튼 클릭시
                Gdx.app.log(Cave.LOG, "keyPressed : X_icon");
                if(IsBagOpen) {
                    if (player.expOn) {
                        player.expOn = false;
                    } else {
                        IsBagOpen = false;
                        exp_pane_x = 300;
                        AddActor(0);
                        ClearActor(1);
                    }
                }
                if(IsNoteOpen) {
                    player.gameData.sound.playCloseNoteSound();
                    ClearActor(3);
                    Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                        @Override
                        public void run() {
                            IsNoteOpen = false;
                            player.setCheckOX(0);
                            need_item_x = -120;
                            DoIt_icon.setSize(96,96);
                            DoIt_icon.setPosition(224,80);
                            DoNot_icon.setSize(96,96);
                            DoNot_icon.setPosition(480,80);
                            AddActor(0);
                        }
                    }, 0.2f);
                }
                if(IsMixOpen) {
                    IsMixOpen = false;
                    player.expOn = false;
                    AddActor(0);
                    ClearActor(4);
                }
                if(IsFirstGame) {
                    IsFirstGame = false;
                    player.gameData.setIsitFirstGame(false);
                    AddActor(0);
                    stage.getRoot().removeActor(x_box);
                }
            }
        });
        x_box.setSize(48,48);
        x_box.setPosition(750,430);
        item_use_box.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //use버튼 클릭시
                if(player.expOn == true){
                    Gdx.app.log(Cave.LOG, "keyPressed : Use_icon");
                if(player.inventory[player.inven_num].getCanUse() == true){
                    player.inventory[player.inven_num]._Use();
                    if(player.inventory[player.inven_num].getType() == 0) //사운드 동작
                        player.gameData.sound.playCanSound();
                    else if(player.inventory[player.inven_num].getType() == 1 || player.inventory[player.inven_num].getType() == 2)
                        player.gameData.sound.playDrinkingSound();
                    else if(player.inventory[player.inven_num].getType() == 12 || player.inventory[player.inven_num].getType() == 17)
                        player.gameData.sound.playEatingSound();
                    player.deleteItem(player.inven_num);
                    player.SortInventory();
                    player.saveData();
                    player.inven_num = 19;
                    player.expOn=false;
                }
            }}
        });
        item_use_box.setPosition(592+exp_pane_x,60);
        Delete_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //use버튼 클릭시
                if(player.expOn == true){
                    Gdx.app.log(Cave.LOG, "keyPressed : Delete_icon");
                        player.deleteItem(player.inven_num);
                        player.SortInventory();
                    player.saveData();
                        player.expOn=false;
                }}
        });
        Delete_icon.setPosition(615+exp_pane_x,10);
        Delete_icon.setScale(1f,0.5f);
        Inven_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 작동
                if(IsBagOpen == false) {
                    Gdx.app.log(Cave.LOG, "keyPressed : Inven_icon");
                    player.gameData.sound.playBagOpenSound();
                    ClearActor(0);
                    Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                        @Override
                        public void run() {
                            IsBagOpen = true;
                            AddActor(1);
                        }
                    }, 0.25f);
                }
            }
        });
        Inven_icon.setPosition(30,30);
        Inven_icon.setSize(96,96);
        stage.addActor(Inven_icon);
        Mix_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //가방아이콘 작동
                if(IsMixOpen == false) {
                    Gdx.app.log(Cave.LOG, "keyPressed : Mix_icon");
                    player.gameData.sound.playMixOpenSound();
                    ClearActor(0);
                    Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                        @Override
                        public void run() {
                            IsMixOpen = true;
                            AddActor(4);
                        }
                    }, 0.05f);
                }
            }
        });
        Mix_icon.setPosition(30, 150);
        Mix_icon.setSize(96,96);
        stage.addActor(Mix_icon);
        Note_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //노트아이콘 작동
                    Gdx.app.log(Cave.LOG, "keyPressed : Note_icon");
                player.gameData.sound.playNoteSound();
                ClearActor(0);
                Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                    @Override
                    public void run() {
                        IsNoteOpen=true;
                        AddActor(3);
                        player.setCheckOX(0);
                        if(player.getDay() == 0 || player.getStoryCase() == 8 || player.getStoryCase() == 10)
                            player.setCheckOX(1);
                    }
                }, 0.15f);

            }
        });
        Note_icon.setPosition(640,20);
        stage.addActor(Note_icon);
        Menu_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //메뉴아이콘 작동
                Gdx.app.log(Cave.LOG, "keyPressed : Menu_icon");
                player.gameData.sound.playClickSound();
                ClearActor(0);
                AddActor(2);
                IsMenuOpen = true;
            }
        });
        Menu_icon.setPosition(680,370);
        stage.addActor(Menu_icon);
        Back_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //back버튼 클릭시
                Gdx.app.log(Cave.LOG, "keyPressed : Back_icon");
                if(IsMenuOpen == true){
                    player.gameData.sound.playClickSound();
                    ClearActor(2);
                    AddActor(0);
                    IsMenuOpen = false;
                }}
        });
        Back_icon.setPosition(250,192);
        Setting_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //set버튼 클릭시
                Gdx.app.log(Cave.LOG, "keyPressed : Set_icon");
                if(IsMenuOpen == true){
                    player.gameData.sound.playClickSound();
                    IsSetOn = true;
                    ClearActor(2);
                    AddActor(5);
                }
                }}
        );
        Setting_icon.setPosition(350,192);
        Main_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //main버튼 클릭시
                Gdx.app.log(Cave.LOG, "keyPressed : Main_icon");
                if(IsMenuOpen == true){
                    fadeOn = true;
                    player.gameData.sound.playClickSound();
                    game.showAD(false);
                    ClearActor(0);
                    ClearActor(1);
                    ClearActor(2);
                    Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                        @Override
                        public void run() {
                            player.gameData.sound.stopGameBGM();
                            if(player.getStoryCase() == 2)
                                player.gameData.sound.stopWaterSound();
                            game.setScreen(new MainScreen(game,player));
                        }
                    }, screenDelay);
                }}
        });
        Main_icon.setPosition(450,192);
        Next_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //Next버튼 클릭 시
                Gdx.app.log(Cave.LOG, "keyPressed : Next_icon");
                player.gameData.sound.playPageSound();
                Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                    @Override
                    public void run() {
                        if(IsNoteOpen == true){
                            if(NotePage <2) {
                                NotePage++;
                                stage.getRoot().addActor(Before_icon);
                            }
                            if(NotePage == 2)
                                stage.getRoot().removeActor(Next_icon);
                        }
                    }
                }, 0.1f);
                }
        });
        Next_icon.setSize(48,48);
        Next_icon.setPosition(620,80);
        Before_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //Before버튼 클릭 시
                    Gdx.app.log(Cave.LOG, "keyPressed : Before_icon");
                player.gameData.sound.playPageSound();
                Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                    @Override
                    public void run() {
                        if(IsNoteOpen == true){
                            if(NotePage > 0){
                                NotePage--;
                                stage.getRoot().addActor(Next_icon);
                            }
                            if(NotePage == 0)
                                stage.getRoot().removeActor(Before_icon);
                        }
                    }
                }, 0.1f);
                    }
        });
        Before_icon.setSize(48,48);
        Before_icon.setPosition(132,80);
        DoIt_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //O버튼 클릭시
                    Gdx.app.log(Cave.LOG, "keyPressed : O_icon");
                    if(IsNoteOpen == true){
                        DoIt_icon.setSize(96,96);
                        DoIt_icon.setPosition(224,80);
                        DoNot_icon.setSize(72,72);
                        DoNot_icon.setPosition(496,96);
                        player.setCheckOX(1);
                }}
        });
        DoIt_icon.setSize(72,72);
        DoIt_icon.setPosition(236,96);
        DoNot_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //X버튼 클릭시
                    Gdx.app.log(Cave.LOG, "keyPressed : X_icon");
                    if(IsNoteOpen == true){
                        DoIt_icon.setSize(72,72);
                        DoIt_icon.setPosition(236,96);
                        DoNot_icon.setSize(96,96);
                        DoNot_icon.setPosition(480,80);
                        player.setCheckOX(2);
                }}
        });
        DoNot_icon.setSize(72,72);
        DoNot_icon.setPosition(496,96);
        GoNext_icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) { //Go버튼 클릭시
                Gdx.app.log(Cave.LOG, "keyPressed : GoNext_icon");
                if(IsNoteOpen==true && player.getCheckOX() >0){ //다음스테이지로 이동
                    fadeOn = true;
                    game.showAD(false);
                    Timer.schedule(new Timer.Task() { //뒤에 초만큼 딜레이를 줌
                        @Override
                        public void run() {
                            if(player.getStoryCase() == 2)
                                player.gameData.sound.stopWaterSound();
                            else if(player.getStoryCase() == 9)
                                player.gameData.sound.playBoxSound();
                            player.gameManager.NextDay();
                            player.saveData();
                            game.setScreen(new DayScreen(game,player));
                        }
                    }, screenDelay);
                }
            }
        });
        GoNext_icon.setPosition(650,192);
        setting_ok_button.addListener(new ClickListener() { //게임종료
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(Cave.LOG, "keyPressed : ok_button");
                player.gameData.sound.playClickSound();
                if(IsSetOn == true) {
                    IsSetOn = false;
                    player.saveGameData();
                    ClearActor(5);
                    AddActor(2);
                }
            }
        });
        setting_ok_button.setSize(180,60);
        setting_ok_button.setPosition(310,105);
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
        bgm_left_button.setPosition(380,280);
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
        bgm_right_button.setPosition(545,280);
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
        effect_left_button.setPosition(380,180);
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
        effect_right_button.setPosition(545,180);
        if(player.getDay() == 0) {
            IsFirstGame = true;
            ClearActor(0);
            stage.getRoot().addActor(x_box);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.getBatch().begin();
        if(player.getStoryCase() == 2){
            stage.getBatch().draw(cave02_back,0,0);
            stage.getBatch().draw(cave02_water,0,water_y-hand_animate*4);
            stage.getBatch().draw(cave02_water,0,480+water_y-hand_animate*4);
        }
        stage.getBatch().draw(bg,0,0);
        if(player.getStoryCase() == 1)
            stage.getBatch().draw(cave05_light,0,0);
        else if(player.getStoryCase() == 3) {
            if (player.getThirdCase() == 0)
                stage.getBatch().draw(ETgenerator, 0, 0);
        }
        else if(player.getStoryCase() == 4)
            stage.getBatch().draw(deadman,505,12);
        else if(player.getStoryCase() == 5)
            stage.getBatch().draw(skull,505,12);
        else if(player.getStoryCase() == 6)
            stage.getBatch().draw(rat,-100+animal_animate*8,12);
        else if(player.getStoryCase() == 7)
            stage.getBatch().draw(snake,-100+animal_animate*8,12);
        else if(player.getStoryCase() == 8)
            stage.getBatch().draw(oil,260,-25);
        if(player.getStoryCase() == 6 || player.getStoryCase() == 7)
            stage.getBatch().draw(cave03_front,0,0);
        stage.getBatch().draw(hand_base,320,hand_y);
        stage.getBatch().draw(torch,320,hand_y);
        stage.getBatch().draw(hand_front,320,hand_y);
        if(player.isitPoison() ==true)
            stage.getBatch().draw(hand_poison,320,hand_y);
        if(player.isitHurt() == true)
            stage.getBatch().draw(hand_hurt,320,hand_y);
        if(player.isitBlood() ==true)
            stage.getBatch().draw(hand_blood,320,hand_y);
        if(player.getTorchOn() == true) {
            stage.getBatch().draw(light, 320, hand_y);
            if(player.getStoryCase()!=2 && player.getStoryCase()!=3 && player.getStoryCase()!=1)
            stage.getBatch().draw(dark_off,0,hand_y-100);
        }
        else{
            if(player.getStoryCase()!=1 && player.getStoryCase()!=2 && player.getStoryCase()!=3)
            stage.getBatch().draw(dark_on,0,0);
        }
        Note_icon.draw(stage.getBatch(),1);
        Menu_icon.draw(stage.getBatch(),1);
        Inven_icon.draw(stage.getBatch(),1);
        Mix_icon.draw(stage.getBatch(),1);
        if(player.isitBlood() == true)
        stage.getBatch().draw(bloody_icon,50,320,24,24);
        if(player.isitPoison() == true)
        stage.getBatch().draw(poison_icon,80,320,24,24);
        stage.getBatch().draw(health_icon,20,440,24,24);
        stage.getBatch().draw(hunger_icon,20,410,24,24);
        stage.getBatch().draw(water_icon,20,380,24,24);
        stage.getBatch().draw(torch_icon,20,350,24,24);
        stage.getBatch().draw(gage_l, 47, 440, 12, 24);
        stage.getBatch().draw(gage_l, 47, 410, 12, 24);
        stage.getBatch().draw(gage_l, 47, 380, 12, 24);
        stage.getBatch().draw(gage_l, 47, 350, 12, 24);
        stage.getBatch().draw(gage_r, 190, 440, 12, 24);
        stage.getBatch().draw(gage_r, 112, 410, 12, 24);
        stage.getBatch().draw(gage_r, 112, 380, 12, 24);
        stage.getBatch().draw(gage_r, 125, 350, 12, 24);
        for(int i=0;i<10;i++)
            if (i < player.getNowHp())
                stage.getBatch().draw(health_gage, 60 + (i * 13), 440, 12, 24);
            else
                stage.getBatch().draw(empty_gage, 60 + (i * 13), 440, 12, 24);
        for(int i=0;i<4;i++)
            if(i<player.getNowHunger())
                stage.getBatch().draw(hunger_gage,60+(i*13),410,12,24);
            else
                stage.getBatch().draw(empty_gage, 60 + (i * 13), 410, 12, 24);
        for(int i=0;i<4;i++)
            if(i<player.getNowWater())
                stage.getBatch().draw(water_gage,60+(i*13),380,12,24);
            else
                stage.getBatch().draw(empty_gage, 60 + (i * 13), 380, 12, 24);
        for(int i=0;i<5;i++)
            if(i<player.getNowTorch())
                stage.getBatch().draw(torch_gage,60+(i*13),350,12,24);
            else
                stage.getBatch().draw(empty_gage, 60 + (i * 13), 350, 12, 24);
            if(IsBagOpen == true){ //가방이 열렸을때
                stage.getBatch().draw(pause_pane,0,0);
                stage.getBatch().draw(inven_pane,20,40);
                for(int i=0;i<20;i++)
                    inven_box[i].draw(stage.getBatch(),1);
            for(int i=0; i<20;i++) {
                if(player.inventory[i] != null)
                    stage.getBatch().draw(item_image[player.inventory[i].getType()], 30+(96*(i%5)),336-(96*(i/5)));
            }
                stage.getBatch().draw(item_pane, 530+exp_pane_x,0);
                if(player.inventory[player.inven_num] != null) {
                    if(player.inventory[player.inven_num].getCanUse() != true)
                        stage.getBatch().draw(item_use_box_n, 592+exp_pane_x,60);
                    else
                        item_use_box.draw(stage.getBatch(),1);
                    Delete_icon.draw(stage.getBatch(),1);
                    stage.getBatch().draw(item_image[player.getInvenItem(player.inven_num).getType()], 550 + exp_pane_x, 360);
                    mFont_text.draw(stage.getBatch(), player.inventory[player.inven_num].getName(), 650 + exp_pane_x, 410);
                    mFont_text.draw(stage.getBatch(), player.inventory[player.inven_num].getExp(), 580 + exp_pane_x, 340);
                    mFont_text.draw(stage.getBatch(), player.inventory[player.inven_num].getPlus_exp(), 570 + exp_pane_x, 200);
                }
                x_box.draw(stage.getBatch(),1);
        }
        else if(IsMenuOpen == true){
                stage.getBatch().draw(pause_pane,0,0);
                stage.getBatch().draw(menu_pane,200,140);
                stage.getBatch().draw(menu_box,250,192);
                stage.getBatch().draw(menu_box,350,192);
                stage.getBatch().draw(menu_box,450,192);
                Back_icon.draw(stage.getBatch(),1);
                Setting_icon.draw(stage.getBatch(),1);
                Main_icon.draw(stage.getBatch(),1);
                if(IsSetOn == true){
                    stage.getBatch().draw(setting_pane,150,90);
                    mFont_set_text.draw(stage.getBatch(),"BGM",220,331);
                    mFont_set_text.draw(stage.getBatch(),"EFFECT",200,230);
                    mFont_set_num.draw(stage.getBatch(),""+(int)(player.gameData.sound.getBGMVolume()),(player.gameData.sound.getBGMVolume()<10?485:468),331);
                    mFont_set_num.draw(stage.getBatch(),""+(int)(player.gameData.sound.getVolume()),(player.gameData.sound.getVolume()<10?485:468),230);
                    bgm_left_button.draw(stage.getBatch(),1f);
                    bgm_right_button.draw(stage.getBatch(),1f);
                    effect_left_button.draw(stage.getBatch(),1f);
                    effect_right_button.draw(stage.getBatch(),1f);
                    setting_ok_button.draw(stage.getBatch(),1f);
                }
            }
        else if(IsNoteOpen==true){
                stage.getBatch().draw(note_pane,0,0,800,480);
                x_box.draw(stage.getBatch(),1);
                if(NotePage <= 1)
                    Next_icon.draw(stage.getBatch(),1);
                if(NotePage >= 1)
                    Before_icon.draw(stage.getBatch(),1);
                if(NotePage == 2){
                    if(player.getDay() != 0 && player.getStoryCase() != 8 && player.getStoryCase() != 10 && player.getStoryCase() != 11) {
                        DoIt_icon.draw(stage.getBatch(), 1);
                        DoNot_icon.draw(stage.getBatch(), 1);
                    }
                    if(player.getCheckOX() >0)
                        GoNext_icon.draw(stage.getBatch(),1);
                }
                if(NotePage == 0){
                    diary_text.draw(stage.getBatch(), player.gameManager.note.getFirstText(), 190, 360);
                }
                else if(NotePage == 1)
                    diary_text.draw(stage.getBatch(), player.gameManager.note.getSecondText(), 190, 360);
                else if(NotePage == 2)
                    diary_text.draw(stage.getBatch(), player.gameManager.note.getThirdText(), 190, 360);
                showNeedItem();
            }
        else if(IsMixOpen == true){ //조합 열렸을때
                stage.getBatch().draw(pause_pane,0,0);
                stage.getBatch().draw(mix_pane,10,20);
                for(int i=0;i<8;i++)
                    mix_box[i].draw(stage.getBatch(),1);
                for(int i=0; i<8;i++) {
                    stage.getBatch().draw(mix_item[i], 36 + (96 * (i % 2)), 314 - (96 * (i / 2)));
                    if(i==0) { //붕대
                        if (player.getHaveItemNumber(3) < 3)
                            stage.getBatch().draw(negative_box, 36 + (96 * (i % 2)), 314 - (96 * (i / 2)));
                    }
                    else if(i==1) { //해독제
                        if (player.getHaveItemNumber(1) < 1 || player.getHaveItemNumber(16) < 1)
                            stage.getBatch().draw(negative_box, 36 + (96 * (i % 2)), 314 - (96 * (i / 2)));
                    }
                    else if(i==2) { //치료킷
                        if (player.getHaveItemNumber(13) < 1 || player.getHaveItemNumber(14) < 1)
                            stage.getBatch().draw(negative_box, 36 + (96 * (i % 2)), 314 - (96 * (i / 2)));
                    }
                    else if(i==3) { //손목아머
                        if (player.getHaveItemNumber(3) < 3 || player.getHaveItemNumber(10) < 1)
                            stage.getBatch().draw(negative_box, 36 + (96 * (i % 2)), 314 - (96 * (i / 2)));
                    }
                    else if(i==4) { //깨끗한 물
                        if (player.getHaveItemNumber(2) < 1 || player.getHaveItemNumber(16) < 1)
                            stage.getBatch().draw(negative_box, 36 + (96 * (i % 2)), 314 - (96 * (i / 2)));
                    }
                    else if(i==5) { //구운 고기
                        if (player.getHaveItemNumber(12) < 1 || player.getNowTorch() == 0)
                            stage.getBatch().draw(negative_box, 36 + (96 * (i % 2)), 314 - (96 * (i / 2)));
                    }
                    else if(i==6) { //천조각
                        if (player.getHaveItemNumber(9) < 1)
                            stage.getBatch().draw(negative_box, 36 + (96 * (i % 2)), 314 - (96 * (i / 2)));
                    }
                    else if(i==7) { //로프
                        if (player.getHaveItemNumber(13) < 2)
                            stage.getBatch().draw(negative_box, 36 + (96 * (i % 2)), 314 - (96 * (i / 2)));
                    }
                }
                if(player.expOn == true){
                    mix_do_icon.draw(stage.getBatch(),1);
                    stage.getBatch().draw(mix_item[player.inven_num], 280,270,128,128);
                    if(player.inven_num == 0) {
                        mFont_text.draw(stage.getBatch(), player.getItemName(13), 460, 340);
                        mFont_text.draw(stage.getBatch(), player.getItemExp(13), 440, 280);
                        mFont_text.draw(stage.getBatch(), player.getItemPExp(13), 290, 250);
                        //stage.getBatch().draw(plus, 360,72 ,72 ,72);
                        stage.getBatch().draw(item_plus0, 320,60);
                        mFont_text.draw(stage.getBatch(), "X 3", 440, 110);
                        stage.getBatch().draw(equals, 500,60);
                        stage.getBatch().draw(mix_item[player.inven_num], 600,60);
                        if (player.getHaveItemNumber(3) < 3) {
                            stage.getBatch().draw(item_x, 320, 60);
                            stage.getBatch().draw(mix_donot_icon, 650, 210);
                        }
                    }
                    else if(player.inven_num == 1) {
                        mFont_text.draw(stage.getBatch(), player.getItemName(14), 460, 340);
                        mFont_text.draw(stage.getBatch(), player.getItemExp(14), 440, 280);
                        mFont_text.draw(stage.getBatch(), player.getItemPExp(14), 290, 250);
                        stage.getBatch().draw(item_plus0, 260,60);
                        stage.getBatch().draw(item_plus1, 440,60);
                        stage.getBatch().draw(plus, 360,72 ,72 ,72);
                        stage.getBatch().draw(equals, 540,60);
                        stage.getBatch().draw(mix_item[player.inven_num], 640,60);
                        if (player.getHaveItemNumber(1) < 1 || player.getHaveItemNumber(16) < 1) {
                            if (player.getHaveItemNumber(1) < 1)
                                stage.getBatch().draw(item_x, 260, 60);
                            if(player.getHaveItemNumber(16) < 1)
                                stage.getBatch().draw(item_x, 440, 60);
                            stage.getBatch().draw(mix_donot_icon, 650, 210);
                        }
                    }
                    else if(player.inven_num == 2) { //치료킷
                        mFont_text.draw(stage.getBatch(), player.getItemName(4), 460, 340);
                        mFont_text.draw(stage.getBatch(), player.getItemExp(4), 440, 280);
                        mFont_text.draw(stage.getBatch(), player.getItemPExp(4), 290, 250);
                        stage.getBatch().draw(item_plus0, 260,60);
                        stage.getBatch().draw(item_plus1, 440,60);
                        stage.getBatch().draw(plus, 360,72 ,72 ,72);
                        stage.getBatch().draw(equals, 540,60);
                        stage.getBatch().draw(mix_item[player.inven_num], 640,60);
                        if (player.getHaveItemNumber(13) < 1 || player.getHaveItemNumber(14) < 1) {
                            if (player.getHaveItemNumber(13) < 1)
                                stage.getBatch().draw(item_x, 260, 60);
                            if(player.getHaveItemNumber(14) < 1)
                                stage.getBatch().draw(item_x, 440, 60);
                            stage.getBatch().draw(mix_donot_icon, 650, 210);
                        }
                    }
                    else if(player.inven_num == 3) { //손목
                        mFont_text.draw(stage.getBatch(), player.getItemName(15), 460, 340);
                        mFont_text.draw(stage.getBatch(), player.getItemExp(15), 440, 280);
                        mFont_text.draw(stage.getBatch(), player.getItemPExp(15), 290, 250);
                        stage.getBatch().draw(item_plus0, 240,60);
                        mFont_text.draw(stage.getBatch(), "X 3", 340, 110);
                        stage.getBatch().draw(item_plus1, 450,60);
                        stage.getBatch().draw(plus, 380,72 ,72 ,72);
                        stage.getBatch().draw(equals, 540,60);
                        stage.getBatch().draw(mix_item[player.inven_num], 640,60);
                        if (player.getHaveItemNumber(3) < 3 || player.getHaveItemNumber(10) < 1) {
                            if (player.getHaveItemNumber(3) < 3)
                                stage.getBatch().draw(item_x, 240, 60);
                            if(player.getHaveItemNumber(10) < 1)
                                stage.getBatch().draw(item_x, 450, 60);
                            stage.getBatch().draw(mix_donot_icon, 650, 210);
                        }
                    }
                    else if(player.inven_num == 4) {
                        mFont_text.draw(stage.getBatch(), player.getItemName(1), 460, 340);
                        mFont_text.draw(stage.getBatch(), player.getItemExp(1), 440, 280);
                        mFont_text.draw(stage.getBatch(), player.getItemPExp(1), 290, 250);
                        stage.getBatch().draw(item_plus0, 260,60);
                        stage.getBatch().draw(item_plus1, 440,60);
                        stage.getBatch().draw(plus, 360,72 ,72 ,72);
                        stage.getBatch().draw(equals, 540,60);
                        stage.getBatch().draw(mix_item[player.inven_num], 640,60);
                        if (player.getHaveItemNumber(2) < 1 || player.getHaveItemNumber(16) < 1) {
                            if (player.getHaveItemNumber(2) < 1)
                                stage.getBatch().draw(item_x, 260, 60);
                            if(player.getHaveItemNumber(16) < 1)
                                stage.getBatch().draw(item_x, 440, 60);
                            stage.getBatch().draw(mix_donot_icon, 650, 210);
                        }
                    }
                    else if(player.inven_num == 5) {
                        mFont_text.draw(stage.getBatch(), player.getItemName(17), 460, 340);
                        mFont_text.draw(stage.getBatch(), player.getItemExp(17), 440, 280);
                        mFont_text.draw(stage.getBatch(), player.getItemPExp(17), 290, 250);
                        stage.getBatch().draw(item_plus0, 260,60);
                        stage.getBatch().draw(item_plus1, 430,60,96,96);
                        mFont_text.draw(stage.getBatch(), "X 1", 510, 110);
                        stage.getBatch().draw(plus, 360,72 ,72 ,72);
                        stage.getBatch().draw(equals, 540,60);
                        stage.getBatch().draw(mix_item[player.inven_num], 640,60);
                        if (player.getHaveItemNumber(12) < 1 || player.getNowTorch() == 0) {
                            if (player.getHaveItemNumber(12) < 1)
                                stage.getBatch().draw(item_x, 260, 60);
                            if(player.getNowTorch() == 0)
                                stage.getBatch().draw(item_x, 430, 60);
                            stage.getBatch().draw(mix_donot_icon, 650, 210);
                        }
                    }
                    else if(player.inven_num == 6) { //천조각
                        mFont_text.draw(stage.getBatch(), player.getItemName(3), 460, 340);
                        mFont_text.draw(stage.getBatch(), player.getItemExp(3), 440, 280);
                        mFont_text.draw(stage.getBatch(), player.getItemPExp(3), 290, 250);
                        stage.getBatch().draw(item_plus0, 340,60);
                        stage.getBatch().draw(equals, 460,60);
                        stage.getBatch().draw(mix_item[player.inven_num], 580,60);
                        mFont_text.draw(stage.getBatch(), "X 3", 690, 110);
                        if (player.getHaveItemNumber(9) < 1) {
                            stage.getBatch().draw(item_x, 340, 60);
                            stage.getBatch().draw(mix_donot_icon, 650, 210);
                        }
                    }
                    else if(player.inven_num == 7) { //로프
                        mFont_text.draw(stage.getBatch(), player.getItemName(9), 460, 340);
                        mFont_text.draw(stage.getBatch(), player.getItemExp(9), 440, 280);
                        mFont_text.draw(stage.getBatch(), player.getItemPExp(9), 290, 250);
                        stage.getBatch().draw(item_plus0, 320,60);
                        mFont_text.draw(stage.getBatch(), "X 2", 430, 110);
                        stage.getBatch().draw(equals, 460,60);
                        stage.getBatch().draw(mix_item[player.inven_num], 580,60);
                        if (player.getHaveItemNumber(13) < 2) {
                            stage.getBatch().draw(item_x, 320, 60);
                            stage.getBatch().draw(mix_donot_icon, 650, 210);
                        }
                    }
            }
            x_box.draw(stage.getBatch(),1);
        }
        if(IsFirstGame){
            stage.getBatch().draw(helpScreen,0,0,800,480);
            x_box.draw(stage.getBatch(),1);
        }
        fadeScreen.draw(batch);
        stage.getBatch().end();
        update();
    }

    public void update() {
        super.update();
        hand_animate++;
        animal_animate++;
        if(animal_animate > 300)
            animal_animate = 0;
        if(!hand_up){
            if(hand_animate%10 == 0)
            hand_y--;
            if(hand_animate > 120) {
                hand_animate = 0;
                hand_up = true;
            }
        }
        else{
            if(hand_animate%10 == 0)
                hand_y++;
            if(hand_animate > 120) {
                hand_animate = 0;
                hand_up = false;
            }
        }
        if(IsBagOpen){
            if(player.expOn){
                if(exp_pane_x >= 0)
                    exp_pane_x -= 12;
            }
            else{
                if(exp_pane_x <= 300)
                    exp_pane_x += 12;
            }
            Delete_icon.setPosition(615+exp_pane_x,10);
            item_use_box.setPosition(592+exp_pane_x,60);
        }
        if(IsNoteOpen){
            if(NotePage == 2){
                if(need_item_x <= 0)
                    need_item_x += 10;
            }
            else {
                if (need_item_x >= -120)
                    need_item_x -= 10;
            }
        }
    }
    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        helpScreen.dispose();
        hand_base.dispose();
        hand_front.dispose();
        light.dispose();
        dark_on.dispose();
        dark_off.dispose();
        hand_poison.dispose();
        hand_hurt.dispose();
        hand_blood.dispose();
        torch.dispose();
        inven_pane.dispose();
        pause_pane.dispose();
        item_pane.dispose();
        for(int i=0;i<20;i++) {
            inven_box[i].remove();
            if(i<8) {
                mix_item[i].dispose();
                mix_box[i].remove();
            }
        }
        item_use_box_n.dispose();
        health_icon.dispose();
        health_gage.dispose();
        hunger_icon.dispose();
        hunger_gage.dispose();
        water_icon.dispose();
        water_gage.dispose();
        torch_icon.dispose();
        torch_gage.dispose();
        bloody_icon.dispose();
        poison_icon.dispose();
        menu_pane.dispose();
        menu_box.dispose();
        note_pane.dispose();
        snake.dispose();
        rat.dispose();
        skull.dispose();
        deadman.dispose();
        oil.dispose();
        cave02_water.dispose();
        cave02_back.dispose();
        cave03_front.dispose();
        cave05_light.dispose();
        Inven_icon.remove();
        Menu_icon.remove();
        Note_icon.remove();
        item_use_box.remove();
        x_box.remove();
        Back_icon.remove();
        Setting_icon.remove();
        Main_icon.remove();
        Before_icon.remove();
        Next_icon.remove();
        DoIt_icon.remove();
        DoNot_icon.remove();
        GoNext_icon.remove();
        Delete_icon.remove();
        Mix_icon.remove();
        mix_pane.dispose();
        mix_do_icon.remove();
        negative_box.dispose();
        equals.dispose();
        plus.dispose();
        setting_pane.dispose();
        bgm_left_button.remove();
        bgm_right_button.remove();
        effect_right_button.remove();
        effect_left_button.remove();
        setting_ok_button.remove();
        for(int i=10;i<19;i++)
            item_image[i].dispose();
        need_item_pane.dispose();
        item_x.dispose();
        random_item.dispose();
        mFont_text.dispose();
        mFont_set_num.dispose();
        mFont_set_text.dispose();
    }

    public Texture getBackgroundImage(){
        Texture backgroundImage;
        if(player.getStoryCase() == 0)
            backgroundImage = new Texture("gfx/background/cave_00.png");
        else if(player.getStoryCase() == 4 || player.getStoryCase() == 5)
            backgroundImage = new Texture("gfx/background/cave_01.png");
        else if(player.getStoryCase() == 1)
            backgroundImage = new Texture("gfx/background/cave_05_base.png");
        else if(player.getStoryCase() == 2)
            backgroundImage = new Texture("gfx/background/cave_02_base.png");
        else if(player.getStoryCase() == 3)
            if(player.getThirdCase() == 0)
                backgroundImage = new Texture("gfx/background/cave_07.png");
            else
                backgroundImage = new Texture("gfx/background/cave_09.png");
        else if(player.getStoryCase() == 6 || player.getStoryCase() == 7)
            backgroundImage = new Texture("gfx/background/cave_03_base.png");
        else if(player.getStoryCase() == 8)
            backgroundImage = new Texture("gfx/background/cave_06.png");
        else if(player.getStoryCase() == 9)
            backgroundImage = new Texture("gfx/background/cave_04.png");
        else if(player.getStoryCase() == 10)
            backgroundImage = new Texture("gfx/background/cave_08.png");
        else if(player.getStoryCase() == 11)
            backgroundImage = new Texture("gfx/background/cave_10.png");
        else
            backgroundImage = new Texture("gfx/background/cave_08.png");
        return backgroundImage;
    }
    public void ClearActor(int i){
        //0=게임화면,1=인벤토리,2=메뉴,3=Note, 4=Mix, 5=Setting
        switch(i){
            case 0:
                stage.getRoot().removeActor(Inven_icon);
                stage.getRoot().removeActor(Menu_icon);
                stage.getRoot().removeActor(Note_icon);
                stage.getRoot().removeActor(Mix_icon);
                break;
            case 1:
                stage.getRoot().removeActor(item_use_box);
                stage.getRoot().removeActor(Delete_icon);
                stage.getRoot().removeActor(x_box);
                for(int j=0;j<20;j++)
                stage.getRoot().removeActor(inven_box[j]);
                break;
            case 2:
                stage.getRoot().removeActor(Back_icon);
                stage.getRoot().removeActor(Setting_icon);
                stage.getRoot().removeActor(Main_icon);
                break;
            case 3:
                stage.getRoot().removeActor(x_box);
                stage.getRoot().removeActor(Before_icon);
                stage.getRoot().removeActor(Next_icon);
                stage.getRoot().removeActor(DoIt_icon);
                stage.getRoot().removeActor(DoNot_icon);
                stage.getRoot().removeActor(GoNext_icon);
                break;
            case 4:
                stage.getRoot().removeActor(x_box);
                for(int j=0;j<8;j++)
                    stage.getRoot().removeActor(mix_box[j]);
                stage.getRoot().removeActor(mix_do_icon);
                break;
            case 5:
                stage.getRoot().removeActor(setting_ok_button);
                stage.getRoot().removeActor(bgm_left_button);
                stage.getRoot().removeActor(bgm_right_button);
                stage.getRoot().removeActor(effect_left_button);
                stage.getRoot().removeActor(effect_right_button);
                break;
        }
    }
    public void AddActor(int i){
        //0=게임화면,1=인벤토리,2=메뉴,3=Note, 4=Mix, 5=Setting
        switch(i){
            case 0:
                stage.getRoot().addActor(Inven_icon);
                stage.getRoot().addActor(Menu_icon);
                stage.getRoot().addActor(Note_icon);
                stage.getRoot().addActor(Mix_icon);
                break;
            case 1:
                stage.getRoot().addActor(item_use_box);
                stage.getRoot().addActor(Delete_icon);
                stage.getRoot().addActor(x_box);
                for(int j=0;j<20;j++)
                    stage.getRoot().addActor(inven_box[j]);
                break;
            case 2:
                stage.getRoot().addActor(Back_icon);
                stage.getRoot().addActor(Setting_icon);
                stage.getRoot().addActor(Main_icon);
                break;
            case 3:
                stage.getRoot().addActor(x_box);
                stage.getRoot().addActor(Before_icon);
                stage.getRoot().addActor(Next_icon);
                stage.getRoot().addActor(DoIt_icon);
                stage.getRoot().addActor(DoNot_icon);
                stage.getRoot().addActor(GoNext_icon);
                break;
            case 4:
                stage.getRoot().addActor(x_box);
                for(int j=0;j<8;j++)
                    stage.getRoot().addActor(mix_box[j]);
                stage.getRoot().addActor(mix_do_icon);
                break;
            case 5:
                stage.getRoot().addActor(setting_ok_button);
                stage.getRoot().addActor(bgm_left_button);
                stage.getRoot().addActor(bgm_right_button);
                stage.getRoot().addActor(effect_left_button);
                stage.getRoot().addActor(effect_right_button);
                break;
        }
    }
    public void showNeedItem(){
        if(player.getStoryCase() == 1){
            stage.getBatch().draw(need_item_pane,-20+need_item_x,40);
            if(player.getFirstCase() == 0){
                stage.getBatch().draw(item_image[12],-5+need_item_x,330);
                if(!player.IsHaveItem(12))
                    stage.getBatch().draw(item_x,-5+need_item_x,330);
                mFont_text.draw(stage.getBatch(),"X1",50+need_item_x,325);
                stage.getBatch().draw(item_image[10],-5+need_item_x,200);
                if(!player.IsHaveItem(10))
                    stage.getBatch().draw(item_x,-5+need_item_x,200);
                mFont_text.draw(stage.getBatch(),"X1",50+need_item_x,195);
                stage.getBatch().draw(item_image[9],-5+need_item_x,70);
                if(!player.IsHaveItem(9))
                    stage.getBatch().draw(item_x,-5+need_item_x,70);
                mFont_text.draw(stage.getBatch(),"X1",50+need_item_x,65);
            }
            else{
                stage.getBatch().draw(item_image[6],-5+need_item_x,330);
                if(!player.IsHaveItem(6))
                    stage.getBatch().draw(item_x,-5+need_item_x,330);
                mFont_text.draw(stage.getBatch(),"X1",50+need_item_x,325);
                stage.getBatch().draw(item_image[15],-5+need_item_x,200);
                if(!player.IsHaveItem(15))
                    stage.getBatch().draw(item_x,-5+need_item_x,200);
                mFont_text.draw(stage.getBatch(),"X1",50+need_item_x,195);
            }
        }
        else if(player.getStoryCase() == 2){
            stage.getBatch().draw(need_item_pane,-20+need_item_x,40);
            stage.getBatch().draw(item_image[9],-5+need_item_x,330);
            if(player.getHaveItemNumber(9) < 2)
                stage.getBatch().draw(item_x,-5+need_item_x,330);
            mFont_text.draw(stage.getBatch(),"X2",50+need_item_x,325);
            stage.getBatch().draw(item_image[5],-5+need_item_x,200);
            if(!player.IsHaveItem(5))
                stage.getBatch().draw(item_x,-5+need_item_x,200);
            mFont_text.draw(stage.getBatch(),"X1",50+need_item_x,195);
        }
        else if(player.getStoryCase() == 3){
            stage.getBatch().draw(need_item_pane,-20+need_item_x,40);
            if(player.getFirstCase() == 0){
                stage.getBatch().draw(item_image[8],-5+need_item_x,330);
                if(!player.IsHaveItem(8))
                    stage.getBatch().draw(item_x,-5+need_item_x,330);
                mFont_text.draw(stage.getBatch(),"X1",50+need_item_x,325);
                stage.getBatch().draw(item_image[11],-5+need_item_x,200);
                if(!player.IsHaveItem(11))
                    stage.getBatch().draw(item_x,-5+need_item_x,200);
                mFont_text.draw(stage.getBatch(),"X1",50+need_item_x,195);
                stage.getBatch().draw(item_image[7],-5+need_item_x,70);
                if(!player.IsHaveItem(7))
                    stage.getBatch().draw(item_x,-5+need_item_x,70);
                mFont_text.draw(stage.getBatch(),"X1",50+need_item_x,65);
            }
            else{
                stage.getBatch().draw(item_image[18],-5+need_item_x,330);
                if(!player.IsHaveItem(18))
                    stage.getBatch().draw(item_x,-5+need_item_x,330);
                mFont_text.draw(stage.getBatch(),"X1",50+need_item_x,325);
            }
        }
        else if(player.getStoryCase() == 4){
            stage.getBatch().draw(need_item_pane,-20+need_item_x,40);
            stage.getBatch().draw(random_item,-5+need_item_x,330,96,96);
            mFont_text.draw(stage.getBatch(),"+1",50+need_item_x,325);
        }
        else if(player.getStoryCase() == 5){
            stage.getBatch().draw(need_item_pane,-20+need_item_x,40);
            stage.getBatch().draw(random_item,-5+need_item_x,330,96,96);
            mFont_text.draw(stage.getBatch(),"+1",50+need_item_x,325);
        }
        else if(player.getStoryCase() == 6){
            stage.getBatch().draw(need_item_pane,-20+need_item_x,40);
            stage.getBatch().draw(item_image[6],-5+need_item_x,330);
            if(!player.IsHaveItem(6))
                stage.getBatch().draw(item_x,-5+need_item_x,330);
            mFont_text.draw(stage.getBatch(),"OR",50+need_item_x,325);
            stage.getBatch().draw(item_image[10],-5+need_item_x,200);
            if(!player.IsHaveItem(10))
                stage.getBatch().draw(item_x,-5+need_item_x,200);
        }
        else if(player.getStoryCase() == 7){
            stage.getBatch().draw(need_item_pane,-20+need_item_x,40);
            stage.getBatch().draw(item_image[6],-5+need_item_x,330);
            if(!player.IsHaveItem(6))
                stage.getBatch().draw(item_x,-5+need_item_x,330);
            mFont_text.draw(stage.getBatch(),"OR",50+need_item_x,325);
            stage.getBatch().draw(item_image[10],-5+need_item_x,200);
            if(!player.IsHaveItem(10))
                stage.getBatch().draw(item_x,-5+need_item_x,200);
        }
        else if(player.getStoryCase() == 9){
            stage.getBatch().draw(need_item_pane,-20+need_item_x,40);
            stage.getBatch().draw(random_item,-5+need_item_x,330,96,96);
            mFont_text.draw(stage.getBatch(),"-1",50+need_item_x,325);
            stage.getBatch().draw(random_item,-5+need_item_x,200,96,96);
            mFont_text.draw(stage.getBatch(),"+1",50+need_item_x,195);
        }
    }

}
