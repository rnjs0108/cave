package com.yongyong.objects;

/**
 * Created by USER on 2017-03-24.
 */

public class Item {
    //0.can, 1.c_water, 2.d_water, 3.fabric, 4.first_aid_kit, 5.hook, 6.knife, 7.oil, 8.phone, 9.rope, 10.stick
    private int type;
    private boolean canUse = true;
    private SavedGameData player;

    private String name = "";
    private String exp= "";
    private String plus_exp= "";

    public Item(int t,SavedGameData player) {
        // TODO Auto-generated constructor stub
        type = t;
        this.player = player;
        if(type == 0){
            if(player.gameData.getLanguage()==0) {
                name = "통조림";
                exp = "좀 오래된 통조림이다.\n약간의 허기를 채울 수\n있을 것 같다.";
                plus_exp = "배고픔 +"+player.getFood()+"";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Can";
                exp="";
                plus_exp = "hunger +"+player.getFood()+"";
            }
            canUse = true;

        }
        else if(type == 1){
            if(player.gameData.getLanguage()==0){
                name = "깨끗한 물";
                exp = "먹어도 이상이 없을 것\n같은 생수이다.\n걱정말고 마시자.";
                plus_exp = "목마름 +"+player.getWater()+"";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Clean Water";
                exp = "";
                plus_exp = "water +"+player.getWater()+"";
            }
            canUse = true;
        }
        else if(type == 2){
            if(player.gameData.getLanguage()==0){
                name = "더러운 물";
                exp = "알 수 없는 부유물이 떠\n다니는 물이다.\n급하다면 먹어보자.";
                plus_exp = "목마름 +"+player.getWater()+"\n중독 +?";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Dirty Water";
                exp = "";
                plus_exp = "water +"+player.getWater()+"\npoison +?";
            }
            canUse = true;
        }
        else if(type == 3){
            if(player.gameData.getLanguage()==0){
                name = "천조각";
                exp = "헝겊 조각이다.\n횃불을 지피는데 도움이\n되겠다.";
                plus_exp = "횃불시간 +1";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Fabric";
                exp = "";
                plus_exp = "torch +1";
            }
            canUse = true;
        }
        else if(type == 4){
            if(player.gameData.getLanguage()==0){
                name = "구급키트";
                exp = "누군가 두고 간 비상\n구급약이다.\n다치면 얼른 사용하자.";
                plus_exp = "회복 +"+(player.getMedikit()+2)+"\n모든 질병치료";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "First aid kit";
                exp = "";
                plus_exp = "health +"+(player.getMedikit()+2)+"\ncure disease";
            }
            canUse = true;
        }
        else if(type == 5){
            if(player.gameData.getLanguage()==0){
                name = "갈고리";
                exp = "녹이 슨 갈고리이다.\n어딘가에 쓸 곳이 있을\n지 않을까?";
                plus_exp = "";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Hook";
                exp = "";
                plus_exp = "";
            }
            canUse = false;
        }
        else if(type == 6){
            if(player.gameData.getLanguage()==0){
                name = "나이프";
                exp = "날이 무딘 다용도 나이\n프이다.\n위험에서부터 나를 지\n켜줄 것 같다.";
                plus_exp = "";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Knife";
                exp = "";
                plus_exp = "";
            }
            canUse = false;
        }
        else if(type == 7){
            if(player.gameData.getLanguage()==0){
                name = "기름통";
                exp = "기름이 조금 들어있\n는 통이다.\n연료로 사용할 수 있다.\n";
                plus_exp = "횃불시간 +3";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Oil";
                exp = "";
                plus_exp = "torch +3";
            }
            canUse = true;
        }
        else if(type == 8){
            if(player.gameData.getLanguage()==0){
                name = "핸드폰";
                exp = "액정이 금이 간 핸드\n폰이다.\n배터리가 다 닳은건지\n켜지지 않는다.";
                plus_exp = "";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Phone";
                exp = "";
                plus_exp = "";
            }
            canUse = false;
        }
        else if(type == 9){
            if(player.gameData.getLanguage()==0){
                name = "밧줄";
                exp = "올이 엉성한 오래된\n밧줄이다.\n어딘가에 쓸 곳이\n있을 것 같다.";
                plus_exp = "";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Rope";
                exp = "";
                plus_exp = "";
            }
            canUse = false;
        }
        else if(type == 10){
            if(player.gameData.getLanguage()==0){
                name = "나뭇가지";
                exp = "굵직한 나뭇가지이다.\n횃불을 새로 만들거\n나, 무기로도 쓸 수\n있을 것 같다.";
                plus_exp = "횃불시간 +5";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Stick";
                exp = "";
                plus_exp = "torch +5";
            }
            canUse = true;
        }
        else if(type == 11){
            if(player.gameData.getLanguage()==0){
                name = "충전기";
                exp = "콘센트에 연결해 쓰는\n휴대폰 충전기이다.\n";
                plus_exp = "";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Charger";
                exp = "";
                plus_exp = "";
            }
            canUse = false;
        }
        else if(type == 12){
            if(player.gameData.getLanguage()==0){
                name = "날고기";
                exp = "알 수 없는 동물의 고\n기이다.\n먹으면 병에 걸릴 것\n같다.";
                plus_exp = "배고픔 +"+player.getFood()+"\n중독 +?";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Meat";
                exp = "";
                plus_exp = "hunger +"+player.getFood()+"\npoison +?";
            }
            canUse = true;
        }
        else if(type == 13){
            if(player.gameData.getLanguage()==0){
                name = "붕대";
                exp = "깨끗한 치료용 붕대이\n다. 다친 상처에 탁월\n할 것 같다.\n";
                plus_exp = "회복 +"+(player.getMedikit()/2)+"\n출혈 치료";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Bandage";
                exp = "";
                plus_exp = "health +"+(player.getMedikit()/2)+"\ncure bleed";
            }
            canUse = true;
        }
        else if(type == 14){
            if(player.gameData.getLanguage()==0){
                name = "해독제";
                exp = "라벨에 '해독제'라고 적\n혀 있다. 유통기한은\n신경쓰지 말자.\n";
                plus_exp = "회복 +"+(player.getMedikit()/2)+"\n중독 치료";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Antidote";
                exp = "라벨에 '해독제'라고 적\n혀 있다. 유통기한은\n신경쓰지 말자.\n";
                plus_exp = "health +"+(player.getMedikit()/2)+"\ncure poison";
            }
            canUse = true;
        }
        else if(type == 15){
            if(player.gameData.getLanguage()==0){
                name = "팔목보호대";
                exp = "임시로 만들어 낸 팔목\n보호대이다. 맹수의 공격\n한두번은 막아낼 것 같다.\n";
                plus_exp = "";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Armguard";
                exp = "";
                plus_exp = "";
            }
            canUse = false;
        }
        else if(type == 16){
            if(player.gameData.getLanguage()==0){
                name = "정화캡슐";
                exp = "여행용 물 정화캡슐이\n다. 약간의 해독효과도\n있다고 알고있다.\n";
                plus_exp = "";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Depurant";
                exp = "";
                plus_exp = "";
            }
            canUse = false;
        }
        else if(type == 17){
            if(player.gameData.getLanguage()==0){
                name = "구운고기";
                exp = "횃불로 구워낸 짐승의\n고기이다. 바싹 익혀서 식\n중독이 걸릴 염려는 없다.\n";
                plus_exp = "배고픔 +"+player.getFood();
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Grilled Meat";
                exp = "횃불로 구워낸 짐승의\n고기이다. 바싹 익혀서 식\n중독이 걸릴 염려는 없다.\n";
                plus_exp = "hunger +"+player.getFood();
            }
            canUse = true;
        }
        else if(type == 18){
            if(player.gameData.getLanguage()==0){
                name = "비상용 폭죽";
                exp = "비상용 폭죽이다.\n허름해 보여 제대로 작\n동이 될 지 의문이다.\n";
                plus_exp = "";
            }
            else if(player.gameData.getLanguage()==1) {
                name = "Fire Cracker";
                exp = "";
                plus_exp = "";
            }
            canUse = false;
        }
    }
    public void _Use(){
        if(type == 0){ //캔
            player.AddHunger(player.getFood());
        }
        else if(type == 1){ //깨끗한 물
            player.AddThirsty(player.getWater());
        }
        else if(type == 2){ //더러운 물
            player.AddThirsty(player.getWater());
            if((Math.random()*100)<=player.getDisease())
                player.GetPoison();
        }
        else if(type == 3){ //천조각 사용 시
            player.AddTorch(1);
        }
        else if(type == 4){ //구급약 사용 시
            player.AddHealth(player.getMedikit()+2);
            player.CureBlood();
            player.CurePoison();
        }
        else if(type == 7){ //기름 사용 시
            player.AddTorch(3);
        }
        else if(type == 10){ //나무스틱 사용 시
            player.AddTorch(5);
        }
        else if(type == 12){ //고기 사용 시
            player.AddHunger(player.getFood());
            if((Math.random() * 100)<=player.getDisease())
                player.GetPoison();
        }
        else if(type == 13){ //붕대 사용 시
            player.AddHealth((player.getMedikit()/2));
            player.CureBlood();
        }
        else if(type == 14){ //해독제 사용 시
            player.AddHealth((player.getMedikit()/2));
            player.CurePoison();
        }
        else if(type == 17){ //구운고기
            player.AddHunger(player.getFood());
        }
    }
    public boolean getCanUse(){
        return canUse;
    }
    public int getType(){
        return type;
    }
    public String getName(){return name;}
    public String getExp(){return exp;}
    public String getPlus_exp(){return plus_exp;}
}
