package com.yongyong.objects;

/**
 * Created by USER on 2017-07-09.
 */

public class GameData {
    public com.yongyong.gamelib.GameSound sound;
    //설정

    private int language = 0; // 0 : KOP, 1 : ENG
    private boolean isitFirstGame = true; //튜토리얼
    private boolean isitPremium = false; //광고가 제거됐는지
    private boolean gameStartOn = false; //리워드광고후 게임시작

    public GameData(){
        sound = new com.yongyong.gamelib.GameSound();
    }

    //설정 리턴
    public int getLanguage(){return language;}
    public boolean getIsitFirstGame(){return isitFirstGame;}
    public boolean getIsitPremium(){return isitPremium;}

    public void setLanguage(int i){ language = i; }
    public void setIsitFirstGame(boolean i){
            isitFirstGame = i;
    }
    public void setIsitPremium(boolean i){
            isitPremium = i;
    }

    public void addLanguageNumber(int i){
        /*
        language += i;
        if(language > 1)
            language = 1;
        if(language < 0)
            language = 0;
            */
        //영어가 완성되면 변경가능으로
    }

    public boolean isGameStartOn(){
        return gameStartOn;
    }
    public void setGameStartOn(boolean gameStartOn){
        this.gameStartOn = gameStartOn;
    }
}
