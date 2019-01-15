package com.yongyong.gamelib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.yongyong.cave.Cave;

/**
 * Created by USER on 2017-07-06.
 */

public class GameSound {
    private Music intro_BGM;
    private Music game_BGM;
    private Music ending_BGM;

    private Sound bagOpen_sound;
    private Sound DiaryOpen_sound;
    private Sound DiaryClose_sound;
    private Sound mixOpen_sound;
    private Sound click_sound;
    private Sound canOpen_sound;
    private Sound pageTurn_sound;
    private Sound rat_sound;
    private Sound water_sound;
    private Sound snake_sound;
    private Sound mic_sound;
    private Sound box_sound;
    private Sound fly_sound;
    private Sound eating_sound;
    private Sound drinking_sound;
    private Sound dead_sound;

    private int BGMVolume = 5; //배경음악 볼륨
    private int Volume = 5; //효과음 볼륨

    public GameSound(){

    }
    public float getBGMVolume(){
        return BGMVolume;
    }
    public float getVolume(){
        return Volume;
    }

    public void setBGMVolume(int i){ BGMVolume = i; }
    public void setVolume(int i){ Volume = i; }

    public void addVolume(int i){
        Volume += i;
        if(Volume < 0)
            Volume = 0;
        if(Volume > 10)
            Volume = 10;
    }
    public void addBGMVolume(int i){
        BGMVolume += i;
        if(BGMVolume < 0)
            BGMVolume = 0;
        if(BGMVolume > 10)
            BGMVolume = 10;
        intro_BGM.setVolume(BGMVolume*0.1f);
        game_BGM.setVolume(BGMVolume*0.15f);
        ending_BGM.setVolume(BGMVolume*0.1f);
    }
    public void playMainBGM(){
        intro_BGM.setLooping(true);
        intro_BGM.play();
    }
    public void stopMainBGM(){
        intro_BGM.stop();
    }
    public void playGameBGM(){
        game_BGM.setLooping(true);
        game_BGM.play();
    }
    public void stopGameBGM(){
        game_BGM.stop();
    }
    public void playEndingBGM(){
        ending_BGM.setLooping(true);
        ending_BGM.play();
    }
    public void stopEndingBGM(){
        ending_BGM.stop();
    }
    public void playClickSound(){
        click_sound.play(Volume*0.1f);
    }
    public void playBagOpenSound(){
        bagOpen_sound.play(Volume*0.1f);
    }
    public void playNoteSound(){
        DiaryOpen_sound.play(Volume*0.1f);
    }
    public void playCloseNoteSound(){
        DiaryClose_sound.play(Volume*0.1f);
    }
    public void playPageSound(){
        pageTurn_sound.play(Volume*0.1f);
    }
    public void playFlySound(){
        fly_sound.play(Volume*0.03f);
    }
    public void playCanSound(){
        canOpen_sound.play(Volume*0.1f);
    }
    public void playMixOpenSound(){
        mixOpen_sound.play(Volume*0.1f);
    }
    public void playEatingSound(){
        eating_sound.play(Volume*0.1f);
    }
    public void playBoxSound(){
        box_sound.play(Volume*0.1f);
    }
    public void playDeadSound(){
        dead_sound.play(Volume*0.1f);
    }
    public void playDrinkingSound(){
        drinking_sound.play(Volume*0.1f);
    }
    public void playWaterSound(){
        long id = water_sound.play(Volume*0.03f);
        water_sound.setPitch(id, 0.4f);
    }
    public void stopWaterSound(){
        water_sound.stop();
    }

    public void setSound(){
        intro_BGM = Gdx.audio.newMusic(Gdx.files.internal("mfx/opening_BGM.mp3"));
        game_BGM = Gdx.audio.newMusic(Gdx.files.internal("mfx/Question,Why.mp3"));
        ending_BGM = Gdx.audio.newMusic(Gdx.files.internal("mfx/Ashes of Dreams_BGM.mp3"));
        box_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/theBox_open.mp3"));
        mic_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/mic_click_on.mp3"));
        fly_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/BeeSound.mp3"));
        click_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/mouse_click.mp3"));
        water_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/Water Noise-Sound.mp3"));
        bagOpen_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/zipper_sound.mp3"));
        DiaryOpen_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/open_book.mp3"));
        DiaryClose_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/book_close.mp3"));
        canOpen_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/open_can.mp3"));
        pageTurn_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/page_turn.mp3"));
        mixOpen_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/mixBox_open.mp3"));
        eating_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/eating_meat.mp3"));
        drinking_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/drinking-sound.mp3"));
        dead_sound = Gdx.audio.newSound(Gdx.files.internal("mfx/dead_sound.mp3"));

        intro_BGM.setVolume(BGMVolume*0.1f);
        game_BGM.setVolume(BGMVolume*0.15f);
        ending_BGM.setVolume(BGMVolume*0.1f);
        Gdx.app.log(Cave.LOG, "사운드 로딩");
    }
    public void dispose(){
        intro_BGM.dispose();
        game_BGM.dispose();
        ending_BGM.dispose();
        box_sound.dispose();
        mic_sound.dispose();
        fly_sound.dispose();
        click_sound.dispose();
        water_sound.dispose();
        bagOpen_sound.dispose();
        DiaryOpen_sound.dispose();
        DiaryClose_sound.dispose();
        canOpen_sound.dispose();
        pageTurn_sound.dispose();
        mixOpen_sound.dispose();
    }
}