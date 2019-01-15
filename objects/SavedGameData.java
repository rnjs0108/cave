package com.yongyong.objects;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.yongyong.cave.Cave;

import java.io.IOException;

/**
 * Created by USER on 2017-03-24.
 */

public class SavedGameData { //플레이중인 게임의 정보
    public com.yongyong.gamelib.GameAlgorithm gameManager;
    public GameData gameData;
    public int inven_num = 19; //아이템창에서 선택된 아이템의 배열숫자
    public boolean expOn = false; //아이템창에서 설명이 써져있나 안써져있나 확인하는 변수
    //saveData
    private String userGameData = "bin/data.txt"; //전체적인 게임 데이타 저장경로
    private String userData = "bin/game.txt"; //세이브된 게임의 저장경로
    private String userString = "bin/text.txt"; //세이브된 게임의 텍스트 저장경로
    //stage정보
    private int days = 0; //날짜 진행정보
    private int firstCase = 0; //탈출조건 진행정보(곰엔딩)
    private int secondCase = 0; //(절벽엔딩)
    private int thirdCase = 0; //(구조엔딩)
    private int CheckOX = 0; // 1=O,2=X
    private int storyCase=0; //랜덤 스테이지 상황
    private int beforeStory = 8;

    //0=첫시작,
    private int level = 0; //난이도

    //status
    private int pHealth = 10; //체력
    private int pHunger = 4; //배고픔
    private int pThirsty = 4; //목마름
    private int pTorchGage = 5; //횃불 게이지
    private int pAngel = 10; //선악 게이지
    private boolean pBlood = false; //피나는 상태
    private boolean pPoison = false; //독에걸린 상태
    private boolean pHurt = false; //이전에 다친적이 있는가
    private boolean pTorchOn = true; //불이 켜져있는가
    private boolean endingOn = false; //엔딩이 되었는가

    //item
    public Item[] inventory = new Item[20]; //아이템칸 20개
    private int lastItemNumber = 0; //마지막으로 가지고있는 아이템의 배열숫자

    //난이도설정
    private int disease = 30; //질병확률
    private int monster_damage = 3; //몬스터에게 입는 데미지
    private int lucky = 70; //행운
    private int water = 2; //물먹을 시 회복량
    private int food = 2; //음식먹을 시 회복량
    private int medikit = 8; //메디킷사용 시 회복량

    public SavedGameData(GameData gameData) { //생성자
        // TODO Auto-generated constructor stub
        this.gameData = gameData;
        gameManager = new com.yongyong.gamelib.GameAlgorithm(this);
        loadGameData();
        this.gameData.sound.setSound();
    }

    public void NewGameSetting(){//새로운 게임 셋팅
        storyCase = 0;
        days = 0;
        pHealth = 10;
        pHunger = 4;
        pThirsty = 4;
        pTorchGage = 5;
        pTorchOn = true;
        pAngel = 10;
        pBlood = false;
        pPoison = false;
        pHurt = false;
        pTorchOn = true;
        endingOn = false;
        firstCase = 0;
        secondCase = 0;
        thirdCase = 0;
        SetOption();
        inventory = null;
        inventory = new Item[20];
        inventory[0] = new Item(0,this);
        inventory[1] = new Item(1,this);
        inventory[2] = new Item(13,this);
        inventory[3] = new Item(3,this);
        lastItemNumber = 3;
        gameManager.note.ClearAllText();
        gameManager.SetFirstText();
        gameManager.SetSecondText();
        gameManager.SetThirdText();
        gameManager.note.setFirstText(gameManager.note.SortText(gameManager.note.getFirstText(),gameManager.textBatchNumber));
        gameManager.note.setSecondText(gameManager.note.SortText(gameManager.note.getSecondText(),gameManager.textBatchNumber));
        gameManager.note.setThirdText(gameManager.note.SortText(gameManager.note.getThirdText(),gameManager.textBatchNumber));
    }

    //현재 속성 리턴
    public int getNowHp(){
        return pHealth;
    }
    public int getNowHunger(){
        return pHunger;
    }
    public int getNowWater(){
        return pThirsty;
    }
    public int getNowTorch(){
        return pTorchGage;
    }
    public int getNowAngel(){
        return pAngel;
    }
    public boolean getTorchOn(){
        return pTorchOn;
    }
    public int getCheckOX(){return CheckOX;}
    public int getStoryCase() {return storyCase;}
    public int getLastItemNumber(){return lastItemNumber;}
    public boolean getEndingOn(){return endingOn;}
    public String getGameData() {return userGameData;}
    public String getUserData() {return userData;}
    public String getUserString() {return userString;}
    public int getFirstCase(){return firstCase;}
    public int getSecondCase(){return secondCase;}
    public int getThirdCase(){return thirdCase;}
    //속성 변경
    public void setLevel(int i) { level = i; }
    public void setCheckOX(int i){
        CheckOX = i;
    }
    public void setStoryCase(int i){
        storyCase = i;
    }
    public void setEndingOn(boolean i){endingOn = i;}
    public void setpTorchOn(){
        if(pTorchOn == true)
            pTorchOn = false;
        else
            pTorchOn = true;
    }
    public void setBeforeStory(int i){beforeStory = i;}
    public void setFirstCase(int i){firstCase = i;}
    public void setSecondCase(int i){secondCase = i;}
    public void setThirdCase(int i){thirdCase = i;}

    //진행날짜 리턴
    public int getDay(){
        return days;
    }
    public int getLevel(){return level;}
    public int getBeforeStory(){return beforeStory;}

    //회복량 리턴
    public int getFood(){
        return food;
    }
    public int getWater(){
        return water;
    }
    public int getMedikit(){
        return medikit;
    }
    //수치 조정
    public void AddDay(int i){
        days += i;
    }
    public void AddHealth(int i){ //회복
        pHealth += i;
        if(pHealth >10)
            pHealth =10;
        else if(pHealth <0)
            pHealth =0;
    }
    public void AddHunger(int i){ //배고픔조정
        pHunger += i;
        if(pHunger >4)
            pHunger =4;
        else if(pHunger <0)
            pHunger =0;
    }
    public void AddThirsty(int i){ //목마름조정
        pThirsty += i;
        if(pThirsty >4)
            pThirsty =4;
        else if(pThirsty <0)
            pThirsty =0;
    }
    public void AddTorch(int i){ //토치게이지 조정
        pTorchGage += i;
        if(pTorchGage >5)
            pTorchGage =5;
        else if(pTorchGage <0)
            pTorchGage =0;
        if(pTorchGage >0)
            pTorchOn = true;
        else
            pTorchOn = false;
    }
    public void AddAngel(int i){//선악게이지 조정
        pAngel += i;
        if(pAngel >20)
            pAngel = 20;
        else if(pAngel <0)
            pAngel = 0;
    }
    //상태이상 값 리턴
    public void GetPoison(){
        pPoison = true;
    } //중독증상을 얻는다
    public void CurePoison(){ //중독증상 치료
        pPoison = false;
        pHurt = true;
    }
    public boolean isitPoison(){
        return pPoison;
    } //중독이 되었는가
    public void GetBlood(){
        pBlood = true;
    } //출혈을 얻는다
    public void CureBlood(){ //출혈 치료
        pBlood = false;
        pHurt = true;
    }
    public boolean isitBlood(){
        return pBlood;
    } //출혈중인가
    public boolean isitHurt(){
        return pHurt;
    } //다친적이 있는가
    //난이도 값 리턴
    public int getDisease(){
        return disease;
    } //질병확률값을 받는다
    public int getMonsterDamage() { return monster_damage; }//몬스터데미지값을 받는다
    public int getLucky(){
        return lucky;
    } //행운값을 받는다
    public void SortInventory(){ //인벤토리 정렬함수
        for(int i=1;i<=lastItemNumber;i++) {
            if (inventory[i-1] == null && inventory[i] != null) {
                inventory[i-1] = inventory[i];
                inventory[i] = null;
            }
        }
        int i = 0;
        for(int j=0;j<20;j++){
            if(inventory[j] != null)
                i++;
        }
        lastItemNumber = i-1;
        Gdx.app.log(Cave.LOG, "라스트아이템 : "+lastItemNumber);
    }
    public void deleteItem(int itemNumber){ //입력받은 숫자 == 인벤토리 아이템배열숫자
        inventory[itemNumber] = null;
        SortInventory();
    }
    public void deleteItemNumber(int number) { //입력받은 숫자 == 아이템 고유번호
        int value = 20;
        for(int i =0;i<=lastItemNumber;i++) {
            if(inventory[i] != null)
            if(inventory[i].getType() == number)
                value = i;
        }
        if(value != 20) {
            inventory[value] = null;
            SortInventory();
        }
    }
    public int RandomlostItem(){ //랜덤으로 인벤안의 아이템을 잃고 해당 타입을 리턴해줌
        if(lastItemNumber < 0) {
            return 50;
        }
        else{
            int i = (int)Math.random()*lastItemNumber;
            int j = inventory[i].getType();
            deleteItem(i);
            return j;
        }
    }
    public void addItem(int type){ //입력받은 아이템고유번호에 맞는 아이템을 추가
        if(lastItemNumber <19){
            inventory[lastItemNumber+1] = new Item(type,this);
            lastItemNumber++;
            SortInventory();
        }
    }
    public void loadData(){ //데이터를 로드해줌
        FileHandle fh = Gdx.files.getFileHandle(getUserData(), Files.FileType.Local);
        if(fh.exists()) {
            String buffer = fh.readString();
            String[] split = buffer.split("\t");
            Gdx.app.log(Cave.LOG, "불러오기 준비완료");
            level = Integer.valueOf(split[0]);
            SetOption();
            days = Integer.valueOf(split[1]);
            firstCase = Integer.valueOf(split[2]);
            secondCase = Integer.valueOf(split[3]);
            thirdCase = Integer.valueOf(split[4]);
            pHealth = Integer.valueOf(split[5]);
            pHunger = Integer.valueOf(split[6]);
            pThirsty = Integer.valueOf(split[7]);
            pTorchGage = Integer.valueOf(split[8]);
            pAngel = Integer.valueOf(split[9]);
            pBlood = (Integer.valueOf(split[10]) == 0 ? true : false);
            pPoison = (Integer.valueOf(split[11]) == 0 ? true : false);
            pHurt = (Integer.valueOf(split[12]) == 0 ? true : false);
            pTorchOn = (Integer.valueOf(split[13]) == 0 ? true : false);
            storyCase = Integer.valueOf(split[14]);
            lastItemNumber = Integer.valueOf(split[15]);
            beforeStory = Integer.valueOf(split[16]);
            for (int k = 0; k <= lastItemNumber; k++) {
                inventory[k] = new Item(Integer.valueOf(split[17 + k]), this);
            }
        }
        gameManager.note.ClearAllText();
        FileHandle text = Gdx.files.getFileHandle(getUserString(), Files.FileType.Local);
        if(text.exists()) {
            String buffer_text = text.readString();
            String[] split_text = buffer_text.split("\t");
            gameManager.note.addFirstText(split_text[1]);
            gameManager.note.addSecondText(split_text[2]);
            gameManager.note.addThirdText(split_text[3]);
        }
    }
    public void saveData(){ //데이터를 저장해줌
        deleteData(); //지난 파일을 삭제
        FileHandle fh = Gdx.files.getFileHandle(getUserData(), Files.FileType.Local);
        try {
                        if(!fh.exists())  //만약 파일이 없다면
                            fh.file().createNewFile(); //생성해줌
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
        fh.writeString(level+"\t"+days+"\t"+firstCase+"\t"+secondCase+"\t"+thirdCase+"\t"+pHealth+"\t"+pHunger+"\t"
                +pThirsty+"\t"+pTorchGage+"\t"+pAngel+"\t"+(pBlood == true ? "0":"1")+"\t"+(pPoison == true ? "0":"1")+"\t"+(pHurt == true ? "0":"1")+"\t" +(pTorchOn == true ? "0":"1")+"\t"
                +storyCase+"\t"+lastItemNumber+"\t"+beforeStory+"\t",true);
        for(int k=0;k<=lastItemNumber;k++)
            fh.writeString(inventory[k].getType()+"\t",true);
        FileHandle text = Gdx.files.getFileHandle(getUserString(), Files.FileType.Local);
            if(!text.exists()) //만약 파일이 없다면
                try {
                    text.file().createNewFile(); //생성해줌
                } catch (IOException e) {
                    e.printStackTrace();
                }
                text.writeString(" \t"+gameManager.note.getFirstText()+"\t"+gameManager.note.getSecondText()+"\t"+gameManager.note.getThirdText()+"\t",true);
        saveGameData();
    }
    public void loadGameData(){ //데이터를 로드해줌
        FileHandle fh = Gdx.files.getFileHandle(getGameData(), Files.FileType.Local);
        if(fh.exists()) {
            String buffer = fh.readString();
            String[] split = buffer.split("\t");
            Gdx.app.log(Cave.LOG, "GameData 불러오기");
            gameData.sound.setBGMVolume(Integer.valueOf(split[0]));
            gameData.sound.setVolume(Integer.valueOf(split[1]));
            gameData.setLanguage(Integer.valueOf(split[2]));
            gameData.setIsitFirstGame(Integer.valueOf(split[3]) == 0 ? true : false);
            gameData.setIsitPremium(Integer.valueOf(split[4]) == 0 ? true : false);
        }
    }
    public void saveGameData(){ //데이터를 저장해줌
        deleteGameData(); //지난 파일을 삭제
        FileHandle fh = Gdx.files.getFileHandle(getGameData(), Files.FileType.Local);
        try {
            if(!fh.exists())  //만약 파일이 없다면
                fh.file().createNewFile(); //생성해줌
        } catch (IOException e) {
            e.printStackTrace();
        }
        fh.writeString((int)(gameData.sound.getBGMVolume())+"\t"+(int)(gameData.sound.getVolume())+"\t"+gameData.getLanguage()+"\t"+(gameData.getIsitFirstGame()? "0":"1")+"\t"+(gameData.getIsitPremium()? "0":"1")+"\t",true);
    }
    public void deleteData(){ //데이터를 삭제시킴
        FileHandle fh = Gdx.files.getFileHandle(getUserData(), Files.FileType.Local);
        FileHandle text = Gdx.files.getFileHandle(getUserString(), Files.FileType.Local);
        if(fh.exists())  //만약 파일이 있다면
            fh.file().delete();
        if(text.exists())
            text.file().delete();
        deleteGameData();
    }

    public void deleteGameData(){ //데이터를 삭제시킴
        FileHandle fh = Gdx.files.getFileHandle(getGameData(), Files.FileType.Local);
        if(fh.exists())  //만약 파일이 있다면
            fh.file().delete();
    }

    public void SetOption(){ //난이도 설정 시 셋팅
        if(this.level == 0){ //easy
            disease = 25; //질병확률
            monster_damage = 2; //몬스터 데미지
            lucky = 80; //행운확률
            water = 3; //물사용시 충전량
            food = 3; //음식사용시 충전량
            medikit = 6; //치료킷사용시 회복량
            pAngel = 10;
        }
        else if(this.level == 1){ //normal
            disease = 50;
            monster_damage = 3;
            lucky = 65;
            water = 3;
            food = 3;
            medikit = 5;
            pAngel = 10;
        }
        else if(this.level == 2){ //hard
            disease = 75;
            monster_damage = 4;
            lucky = 50;
            water = 2;
            food = 2;
            medikit = 4;
            pAngel = 10;
        }
    }
    public int getHaveItemNumber(int num){ //아이템 갯수 리턴
        int some = 0;
        for(int i=0; i<=lastItemNumber;i++) {
            if (inventory[i].getType() == num)//item 갯수
                some++;
        }
        return some;
    }
    public String getItemName(int num){
        Item some = new Item(num,this);
        return some.getName();
    }
    public String getItemExp(int num){
        Item some = new Item(num,this);
        return some.getExp();
    }
    public String getItemPExp(int num){
        Item some = new Item(num,this);
        return some.getPlus_exp();
    }
    public Item getInvenItem(int i){
        return inventory[i];
    }
    public boolean IsHaveItem(int num){ //해당 아이템을 소유하고있는지
        int some = 0;
        for(int i=0; i<=lastItemNumber;i++) {
            if (inventory[i].getType() == num)//item 갯수
                some = 1;
        }
        return (some == 1 ? true : false);
    }
}
