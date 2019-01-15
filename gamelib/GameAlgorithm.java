package com.yongyong.gamelib;

import com.badlogic.gdx.Gdx;
import com.yongyong.cave.Cave;
import com.yongyong.objects.SavedGameData;

/**
 * Created by USER on 2017-03-27.
 */

public class GameAlgorithm{
    public static final int E_FIRST = 5; //곰이 지키고있는 동굴입구 발견
    public static final int E_SECOND = 5; //폭포절벽 발견 (물 1개 획득)
    public static final int E_THIRD = 5; //핸드폰신호가 닿는 구역
    public static final int C_DEADMAN = 10; //천조각을 소비해서 상처를 메워준다. X(죽어있는사람 파밍 또는 벌레에게 물려 감염),O(천조각소비하고 엔젤게이지 상승)
    public static final int C_SKULL = 10; //물건파밍 또는 아무것도없음
    public static final int C_RAT = 8; //쥐를 사냥한다.(나이프나 몽둥이가 있으면 사냥확률 업). O(사냥성공시 고기획득,실패 시 블러딩), X(엔젤게이지>10보내주고 엔젤게이지상승,else 물려서 상처입음)
    public static final int C_SNAKE = 8; //뱀을 사냥한다.(나이프나 몽둥이가 있으면 사냥확률 업), O(사냥성공시 고기 또는 아무것도못얻음), X(낮은확률로 로프획득, 엔젤게이지>14 보내주고 엔젤게이지상승,else 물려서 감염)
    public static final int C_DEKAYOIL = 6; //무조건 토치게이지를 풀로 채워줌
    public static final int C_CRAMPEDROAD = 9; //지나가면서 랜덤으로 물건을 흘림, 대신 1개이상의 아이템 파밍
    public static final int C_NOTHING = 20; //아무 상황도 일어나지 않음.
    public static final int C_ENEMY = 12; //강도를 만남

    private SavedGameData player;
    public com.yongyong.objects.Note note;

    public int textBatchNumber = 28;

    public GameAlgorithm(SavedGameData player){
        this.player = player;
        note = new com.yongyong.objects.Note();
    }
    public void setRandomCase(){
        //행운과 난이도에 따라 상황을 set해줌
        if(player.getDay() == 0)
            player.setStoryCase(0);
        else {
            int random = (int)(Math.random()*(E_FIRST+E_SECOND+E_THIRD+C_DEADMAN+C_SKULL+C_RAT+C_SNAKE+C_DEKAYOIL+C_CRAMPEDROAD+C_NOTHING));
            if (random >= 0 && random < E_FIRST)
                player.setStoryCase(1);
            else if(random >=E_FIRST && random < (E_FIRST+E_SECOND))
                player.setStoryCase(2);
            else if(random >=(E_FIRST+E_SECOND) && random < (E_FIRST+E_SECOND+E_THIRD))
                player.setStoryCase(3);
            else if(random >=(E_FIRST+E_SECOND+E_THIRD) && random < (E_FIRST+E_SECOND+E_THIRD+C_DEADMAN))
                player.setStoryCase(4);
            else if(random >=(E_FIRST+E_SECOND+E_THIRD+C_DEADMAN) && random < (E_FIRST+E_SECOND+E_THIRD+C_DEADMAN+C_SKULL))
                player.setStoryCase(5);
            else if(random >=(E_FIRST+E_SECOND+E_THIRD+C_DEADMAN+C_SKULL) && random < (E_FIRST+E_SECOND+E_THIRD+C_DEADMAN+C_SKULL+C_RAT))
                player.setStoryCase(6);
            else if(random >=(E_FIRST+E_SECOND+E_THIRD+C_DEADMAN+C_SKULL+C_RAT) && random < (E_FIRST+E_SECOND+E_THIRD+C_DEADMAN+C_SKULL+C_RAT+C_SNAKE))
                player.setStoryCase(7);
            else if(random >=(E_FIRST+E_SECOND+E_THIRD+C_DEADMAN+C_SKULL+C_RAT+C_SNAKE) && random < (E_FIRST+E_SECOND+E_THIRD+C_DEADMAN+C_SKULL+C_RAT+C_SNAKE+C_DEKAYOIL))
                player.setStoryCase(8);
            else if(random >=(E_FIRST+E_SECOND+E_THIRD+C_DEADMAN+C_SKULL+C_RAT+C_SNAKE+C_DEKAYOIL) && random < (E_FIRST+E_SECOND+E_THIRD+C_DEADMAN+C_SKULL+C_RAT+C_SNAKE+C_DEKAYOIL+C_CRAMPEDROAD))
                player.setStoryCase(9);
            else if(random >=(E_FIRST+E_SECOND+E_THIRD+C_DEADMAN+C_SKULL+C_RAT+C_SNAKE+C_DEKAYOIL+C_CRAMPEDROAD) && random < (E_FIRST+E_SECOND+E_THIRD+C_DEADMAN+C_SKULL+C_RAT+C_SNAKE+C_DEKAYOIL+C_CRAMPEDROAD+C_NOTHING))
                player.setStoryCase(10);
            if(player.getStoryCase()==1 && player.getSecondCase() == 1)
                setRandomCase();
            if(player.getBeforeStory() != player.getStoryCase())
                 player.setBeforeStory(player.getStoryCase());
            else
                setRandomCase();
        }
    }

    public void NextDay(){ //다음날 진행
        note.ClearAllText(); //모든 텍스트 초기화
        player.AddDay(1); //날짜를 하나 더함
        SetFirstText(); //첫번째 텍스트(전날 일어난 상황에 대한 설명)
        SetSecondText(); //두번째 텍스트(현재의 상태)
        if(player.getNowHunger() > 0) //배고픔 달기
            player.AddHunger(-1);
        else
            player.AddHealth(-1);
        if(player.getNowWater() > 0) //목마름 달기
            player.AddThirsty(-1);
        else
            player.AddHealth(-1);
        if(player.getNowTorch() > 0) //토치 게이지 달기
            player.AddTorch(-1);
        else {
            if(player.getTorchOn() == true)
            player.setpTorchOn();
        }
        if(player.isitPoison() == true)
            if(Math.random()*100 < player.getLucky())
                player.AddHealth(-((player.getLevel()/2)+1));
        if(player.isitBlood() == true)
            if(Math.random()*100 < player.getLucky())
                player.AddHealth(-((player.getLevel()/2)+1));
        if(player.getNowHp() > 0 ||  player.getEndingOn() != true)
            NextDaySet();
    }
    public void NextDaySet(){
        setRandomCase(); //랜덤으로 새로운 케이스 부여
        SetThirdText(); //세번째 텍스트 및 상황부여
        note.setFirstText(note.SortText(note.getFirstText(),textBatchNumber));
        note.setSecondText(note.SortText(note.getSecondText(),textBatchNumber));
        note.setThirdText(note.SortText(note.getThirdText(),textBatchNumber));
    }
    public void SetFirstText() { //지난 사건에 대한 결과 텍스트
        note.addFirstText(" "+(player.getDay()+1)+"일 째,\n");
        if (player.getDay() == 0) {
            note.addFirstText("벌레가 지나다니는 소리에 번뜩 정신이 들었다. ");
            note.addFirstText("주위를 둘러보니 온통 바위와 벌레들밖에 없다. ");
            note.addFirstText("아마 갑작스런 지진으로 지면의 약한 부분에 ");
            note.addFirstText("균열이 생겨 구덩이가 생긴 것 같다. 천장에 뚫");
            note.addFirstText("린 구멍은 너무나도 높아 올라갈 수는 없을 것 ");
            note.addFirstText("같다. 결국 출구를 찾아 나아가는 수 밖에 없다.");
        } else {
            if (player.getStoryCase() == 0) {
                note.addFirstText("신기하게도 동굴 안은 마치 미로처럼 막힌 부분");
                note.addFirstText("이 없이 이어져 있었다. 하지만 마치 이게 게임의 ");
                note.addFirstText("초반부분인 듯 아무 일도, 아무 것도 나타나지 않");
                note.addFirstText("았다. 확실한 것은 나는 이렇게 아무도 찾아주지 ");
                note.addFirstText("않는 무덤에서 묻히고 싶지는 않다는 점이다. 내 ");
                note.addFirstText("체력이 다 할때까지 걸어가보자.");
            }
            else if (player.getStoryCase() == 1) {
                Gdx.app.log(Cave.LOG, "엔딩 시작");
                if(player.getFirstCase() == 0) {
                    int random01 = (int) (Math.random() * 100);
                    int meat = 0;
                    int wood = 0;
                    int rope = 0;
                    for (int j = 0; j <= player.getLastItemNumber(); j++) { //아이템 조건 찾기
                        if (player.inventory[j] != null)
                            if (player.inventory[j].getType() == 12) //고기 갯수
                                meat = 1;
                            else if (player.inventory[j].getType() == 10)
                                wood = 1;
                            else if (player.inventory[j].getType() == 9)
                                rope = 1;
                    }
                    if (player.getCheckOX() == 1) { //O를 눌렀을 경우
                        Gdx.app.log(Cave.LOG, "고기:" +meat);
                        if (meat == 1 && wood == 1 && rope == 1) {
                            note.addFirstText("단단히 동여맨 밧줄과 나무로 엉성하지만 나름 ");
                            note.addFirstText("훌륭한 덫을 만들어냈다. 이 덫에 곰의 다리가 ");
                            note.addFirstText("걸린다면 한동안 그를 쇠약하게 또는 흉폭하게 ");
                            note.addFirstText("만들 수 있을 것이다. 덫 주위에 고기를 뿌려 ");
                            note.addFirstText("놓은 뒤, 눈부신 햇살을 한번 보고는 다시 동");
                            note.addFirstText("굴 안으로 들어갔다.");
                            player.deleteItemNumber(12);
                            player.deleteItemNumber(10);
                            player.deleteItemNumber(9);
                            player.setFirstCase(1);
                        } else { //곰잡기 실패
                            note.addFirstText("나름 기발한 아이디어였지만, 가방안을 살펴");
                            note.addFirstText("보니 덫을 만들 마땅한 재료가 보이질 않는다. ");
                            note.addFirstText("차라리 다른 길을 찾아보는 것이 더 나을 것 ");
                            note.addFirstText("같다고 생각하며 나는 조용히 발길을 돌렸다. ");
                        }
                    } else { //X를 눌렀을 경우
                        if (meat >= 2 && wood == 1 && rope == 1) {
                            note.addFirstText("덫을 놓을 재료가 없는 것도 아니고, 덫을 만");
                            note.addFirstText("들어낼 자신이 없는 것도 아니다. 이것은 맹수");
                            note.addFirstText("에 대한 인간의 원초적 공포감에 따른 행동일 ");
                            note.addFirstText("수 밖에 없다. 이 근처에는 얼씬도 말자!");
                        } else {
                            note.addFirstText("아무리 내가 제정신이 아닌 상태지만 똥오줌");
                            note.addFirstText("정도는 가릴 줄 알고, 맹수와 나와 대적한다");
                            note.addFirstText("면 난 한끼 식사 정도밖에 되지 않는다는 것");
                            note.addFirstText("도 잘 안다. 이건 겁쟁이 짓이 아니고 현명한 ");
                            note.addFirstText("선택이다.");
                        }
                    }
                }
                else { //두번째 상황
                    int random01 = (int) (Math.random() * 100);
                    if (player.getCheckOX() == 1) { //O를 눌렀을 경우
                        int guard = 0;
                        int knife = 0;
                        for (int j = 0; j <= player.getLastItemNumber(); j++) { //아이템 조건 찾기
                            if (player.inventory[j] != null)
                                if (player.inventory[j].getType() == 15) { //천조각 갯수
                                    guard = 1;
                                } else if (player.inventory[j].getType() == 6) //나이프
                                    knife = 1;
                        }
                        if (random01 < ((player.getLucky() / 2) + (guard * 30) + (knife * 40))) {
                            //엔딩
                            player.setEndingOn(true);
                        } else { //곰잡기 실패
                            Gdx.app.log(Cave.LOG, "실패");
                            note.addFirstText("그 놈을 잡기엔 너무 재빠르고 강했다. ");
                            note.addFirstText("내가 곰을 잡기 위해 사용한 장비들은 모두 걸레짝이 되어 버렸다.\n");
                            player.deleteItemNumber(15);
                            player.deleteItemNumber(6);
                            Gdx.app.log(Cave.LOG, "아이템 삭제 완료");
                            if ((int) (Math.random() * 100) < player.getLucky()) { //운이 좋았을 경우
                                Gdx.app.log(Cave.LOG, "운좋음");
                                player.AddHealth(-2 - player.getLevel());
                                note.addFirstText("잠시 방심한 틈을 타 달아나서 크게 다친 곳은 없었다. ");
                                note.addFirstText("불행 중 다행인 것인가? 마음이 진정되지가 않는다.");
                            } else { //운이 나쁠 경우
                                Gdx.app.log(Cave.LOG, "운나쁨");
                                player.AddHealth(-4 - player.getLevel());
                                player.GetBlood();
                                note.addFirstText("격렬하게 공격을 막다 도망치는 도중 심하게 다쳤다. ");
                                note.addFirstText("젠장...배에선 피가 멈추질 않는다. 어떡하지?");
                            }
                        }
                    } else { //X를 눌렀을 경우
                        note.addFirstText("그래! 저런 무식한 곰과 싸우겠다는 건 미친 짓이야! ");
                        note.addFirstText("좀 더 돌아다니다 보면 다른 길이 있을거야. 확신해! ");
                        note.addFirstText("어디 근처에 엽총이라도 떨어져있더라면...");
                    }
                }
            } else if (player.getStoryCase() == 2) { //폭포절벽 엔딩(아이템) 로프 2개,후크 1개
                int random01 = (int) (Math.random() * 100);
                    if (player.getCheckOX() == 1) { //O를 눌렀을 경우
                        int rope = 0;
                        int hook = 0;
                        for (int j = 0; j <= player.getLastItemNumber(); j++) { //아이템 조건 찾기
                            if(player.inventory[j] != null)
                                if (player.inventory[j].getType() == 5) { //갈고리 갯수
                                    hook=1;
                                } else if (player.inventory[j].getType() == 9) //로프 갯수
                                    rope++;
                        }
                        if(rope > 2)
                            rope = 2;
                        if (random01 < (player.getLucky()/2 + (hook * 30) + (rope * 15))) {
                            Gdx.app.log(Cave.LOG, "엔딩완료");
                            if(player.getSecondCase() == 0) { //첫번째 상황
                                player.setSecondCase(1);
                                for(int i=0;i<rope;i++)
                                    player.deleteItemNumber(9);
                                if(hook == 1)
                                    player.deleteItemNumber(5);
                                note.addFirstText((rope > 0 ? "밧줄을 타고 조심스럽게 내려가자, " : "바위에 난 풀들을 잡고 천천히 내려가자, ")
                                        +"발에 뭔가 닿는다. 그 곳은 좀 더 아래쪽으로 향하는 동굴이었다. 다시는 동굴입구를 지키는 곰을 볼 수 없겠지만" +
                                        " 어찌보면 이 것이 더 나은 선택일지도 모른다. 앞으로 한번 정도만 더 내려가면 폭포 아래로 떨어져도 살 수 있을 것 같다.");
                            }
                            else
                                player.setEndingOn(true);
                        } else { //절벽탈출 실패
                            note.addFirstText("내려가려 하던 도중" + (hook > 0 ? "아슬아슬히 버티던 줄이 끊어져 " : "대충 바위에 묶어둔 줄이 풀려 "));
                            note.addFirstText("나는 간신히 튀어나온 바위를 붙잡고 가까스로 기어 올라왔다.\n");
                            for(int i=0;i<rope;i++)
                                player.deleteItemNumber(9);
                            if(hook == 1)
                                player.deleteItemNumber(5);
                            if ((int) (Math.random() * 100) < player.getLucky()) { //운이 좋았을 경우
                                player.AddHealth(-1 - player.getLevel());
                                note.addFirstText("순간적으로 살기위한 초능력이 발휘된 것일까? ");
                                note.addFirstText("손목이 조금 삐끗한 것 말고는 크게 다친 곳이 없다.");
                            } else{ //운이 나쁠 경우
                                player.AddHealth(-2 - player.getLevel());
                                player.GetBlood();
                                note.addFirstText("젠장! 올라오던 도중 팔뚝이 날카로운 곳에 베인 ");
                                note.addFirstText("모양이다. 손으로 급하게 막아보지만 피가 멈추질 않는다.");
                            }
                        }
                    } else { //X를 눌렀을 경우
                        player.addItem(1);
                        note.addFirstText("뭐? 내가 이 아래로 내려간다고? 세상에 어떤 미친 ");
                        note.addFirstText("놈이 그런 짓을 할까? 차라리 짐승한테 뜯기면 뜯겼지, ");
                        note.addFirstText("난 그렇게는 못하겠다. 온 김에 깨끗한 물이라도 가져가자.");
                    }
            }
            else if (player.getStoryCase() == 3) { //핸드폰 구조신호 엔딩 (아이템)핸드폰, 충전기
                int random01 = (int) (Math.random() * 100);
                if(player.getThirdCase() == 0){
                    int phone = 0;
                    int charger = 0;
                    int oil = 0;
                    for (int j = 0; j <= player.getLastItemNumber(); j++) { //아이템 조건 찾기
                        if(player.inventory[j] != null)
                            if (player.inventory[j].getType() == 8) //핸드폰 갯수
                                phone=1;
                            else if (player.inventory[j].getType() == 11) //충전기 갯수
                                charger=1;
                            else if (player.inventory[j].getType() == 7) //기름 갯수
                                oil=1;
                    }
                    if (player.getCheckOX() == 1) { //O를 눌렀을 경우
                        if(phone == 1 && charger == 1 && oil == 1){ //조건이 가능할 경우
                            player.setSecondCase(1);
                            note.addFirstText("조금 있던 기름을 들이붓고 발전기휠을 돌리자 핸드폰 ");
                            note.addFirstText("전원에 드디어 불이 들어온다. 급하게 119를 누른 뒤 ");
                            note.addFirstText("들려오는 반가운 사람의 목소리에 \"살려주세요! 여기");
                            note.addFirstText("는 ...산에 있는 ...동굴...\"이라 말하는 순간 핸드폰이 픽 꺼져버린다. ");
                            note.addFirstText("다급하게 발전기휠을 돌리며 다시 핸드폰 전원을 눌러보았");
                            note.addFirstText("지만 핸드폰은 더이상 켜지지 않았다...");
                            player.deleteItemNumber(8);
                            player.deleteItemNumber(11);
                            player.deleteItemNumber(7);
                            player.setThirdCase(1);
                        }
                        else{
                            note.addFirstText("언제 다시 올지 모르는 상황에 나는 다급하게 발전기로 ");
                            note.addFirstText("갔지만 "+(phone == 1 ? (oil == 1 ? "충전기" : "기름"):"휴대폰")+"도 없는데 어떻게 연락");
                            note.addFirstText("을 한단 말인가? 아무래도 다른 경로를 찾아보는 것이 ");
                            note.addFirstText("나을 것 같다는 생각을 하며 아쉬운 마을을 뒤로 한 채 ");
                            note.addFirstText("발걸음을 돌렸다.");
                        }
                    } else { //X를 눌렀을 경우
                        if(phone == 1 && charger == 1 && oil == 1){
                            note.addFirstText("액정에 금이 간 핸드폰을 만지작 대다가 이내 결심했다. ");
                            note.addFirstText("동굴에 쳐박힌 먼지 쌓인 발전기에 기름을 부어봤자 작");
                            note.addFirstText("동이 될 리가 없잖아? 이건 아까운 기름만 낭비하는 행동일 뿐이다. ");
                            note.addFirstText("좀 더 돌아다니다 보면 제대로 된 콘센트라도 있겠지?");
                        }
                        else{
                            note.addFirstText("뭘 하고 싶어도 제대로 된 준비가 된 상태여야 한다고 ");
                            note.addFirstText("생각한다. 이건 바로 나한테 하는 혼잣말이다. 그리고 ");
                            note.addFirstText("이런 동굴안에 있는 발전기가 제대로 작동할 리가 없잖");
                            note.addFirstText("아? 차라리 다른 길을 찾아보는 게 더 마음 편할 것 같다.");
                        }
                    }
                }
                else{ //두번째 상황
                    int firecracker = 0;
                    for (int j = 0; j <= player.getLastItemNumber(); j++) { //아이템 조건 찾기
                        if(player.inventory[j] != null)
                            if (player.inventory[j].getType() == 18) //폭죽 갯수
                                firecracker=1;
                    }
                    if (player.getCheckOX() == 1) { //O를 눌렀을때
                        if (random01 < (player.getLucky()/2 + firecracker*70)) {
                            Gdx.app.log(Cave.LOG, "핸드폰구조엔딩");
                            //엔딩
                            player.setEndingOn(true);
                        } else { //실패할 경우
                            Gdx.app.log(Cave.LOG, "실패");
                            if(firecracker == 1){
                                note.addFirstText("하늘위로 쏘아올린 폭죽이 펑 하고 터져야 하나, 맙소사! ");
                                note.addFirstText("조준을 잘 못했는지 천장에 난 구멍 끄트머리에 맞고 나한");
                                note.addFirstText("테 튕겨져 돌아온다. 폭죽은 이윽고 내 팔뚝에 부딫힌 뒤 ");
                                note.addFirstText("구석을 나뒹군다. 재수도 없어라... 헬기는 이런 나를 무시");
                                note.addFirstText("하고 무심히 지나가버린다.");
                                player.AddHealth(player.getLevel()/2+1);
                            }
                            else{
                                note.addFirstText("무작정 손을 흔들고 무작정 소리를 질렀다. \"사람 살려!\" ");
                                note.addFirstText("한참을 소리치고 난리를 쳤으나, 헬기는 무심히 내 위를 지");
                                note.addFirstText("나쳐 버렸다. 괜히 목만 더 마르고 배고파졌다. 제발 나를 ");
                                note.addFirstText("찾는 것을 멈추지 않아야 할텐데...");
                                player.AddHunger(-1);
                                player.AddThirsty(-1);
                            }
                        }
                    }
                    else{ //X를 눌렀을때
                        note.addFirstText("잠시 망설이는 사이 헬리콥터는 무심하게 나를 지나쳐버렸다. ");
                        note.addFirstText("그래도 괜히 신경쓰인다. 팔짝팔짝 뛰며 뭐라도 해 볼 것을 ");
                        note.addFirstText("그랬나...? 에이, 지나간 일은 신경쓰지 말자. ");
                        note.addFirstText("앞으로 더 체력을 보충해서 살아 나가면 모두 해결될 일이다. ");
                    }
                }
            }
            else if (player.getStoryCase() == 4) { //죽은 시체 발견
                int random01 = (int) (Math.random() * (110+(100-player.getLucky())));
                if (player.getCheckOX() == 1) { //O를 눌렀을 경우
                    note.addFirstText("두 손으로 시체 구석구석을 뒤져봤더니 ");
                    player.AddAngel(-2);
                    if(player.getLastItemNumber() < 19) {
                        if (random01 < 15) {
                            player.addItem(0);
                            note.addFirstText("주머니에서 통조림 하나가 나왔다.\n");
                        } else if (random01 >= 15 && random01 < 30) {
                            player.addItem(1);
                            note.addFirstText("가방에서 잘 밀봉되어있는 생수가 나왔다.\n");
                        } else if (random01 >= 30 && random01 < 50) {
                            player.addItem(3);
                            note.addFirstText("가져갈만한 것은 천조각 밖에 없었다.\n");
                        } else if (random01 >= 50 && random01 < 55) {
                            player.addItem(4);
                            note.addFirstText("시체 밑에 구급약이 깔려있었다!\n");
                        } else if (random01 >= 55 && random01 < 60) {
                            player.addItem(5);
                            note.addFirstText("시체의 배에 갈고리가 꽂혀있었다.\n");
                        } else if (random01 >= 60 && random01 < 65) {
                            player.addItem(6);
                            note.addFirstText("허리춤에서 무딘 사냥용 나이프가 나왔다.\n");
                        } else if (random01 >= 65 && random01 < 70) {
                            player.addItem(8);
                            note.addFirstText("주머니에 부서진 핸드폰이 있었다.\n");
                        } else if (random01 >= 70 && random01 < 75) {
                            player.addItem(11);
                            note.addFirstText("주머니에서 핸드폰 충전기가 나왔다.\n");
                        } else if (random01 >= 75 && random01 < 90) {
                            player.addItem(16);
                            note.addFirstText("가방에서 비상용 물정화제가 나왔다.\n");
                        } else if (random01 >= 90 && random01 < 100) {
                            player.addItem(13);
                            note.addFirstText("품안에서 깨끗한 붕대가 나왔다.\n");
                        } else if (random01 >= 100 && random01 < 110) {
                            player.addItem(14);
                            note.addFirstText("주머니 안에서 비상용 폭죽이 있었다.\n");
                        } else
                            note.addFirstText("먼지 한톨 나오지 않았다.\n");
                    }
                    else
                        note.addFirstText("별로 가져갈만한 것은 없었다. 그리고 이미 가방이 가득차서 무언가 가져가기에는 무리이다.");
                    note.addFirstText("뒤늦게 썩어가는 시체를 바라보고 헛구역질 올라왔다. ");
                    note.addFirstText("어서 이 자리를 벗어나야 할 것 같다.");
                }
                else{ //X를 눌렀을 때
                    int fabric = 0;
                    for (int j = 0; j <= player.getLastItemNumber(); j++) //아이템 조건 찾기
                        if (player.inventory[j].getType() == 3) //천조각 갯수
                            fabric=1;
                    if(fabric == 1) {
                        player.AddAngel(4);
                        player.deleteItemNumber(3);
                        note.addFirstText("나는 시체의 얼굴에 천조각을 덮어주었다.\n");
                    }
                    else{
                        player.AddAngel(3);
                        note.addFirstText("나는 손을 뻗어 고인의 눈을 감겨주었다.\n");
                    }
                    note.addFirstText("아무리 내가 죽을듯 살듯한 상황이지만 ");
                    note.addFirstText("죽은 사람의 짐을 함부로 뒤지");
                    note.addFirstText("는 것은 천벌받을 행동이다. 그리고 ");
                    note.addFirstText("뒤진다 하더라도 뭔가 나올 것 같지도 않다. ");
                    note.addFirstText("나보다 먼저 이 곳에 도착한 선배님께 명복을 빌어주고 떠나자.");
                }
            }
            else if (player.getStoryCase() == 5) { //해골 발견
                int random01 = (int) (Math.random() * (100+(80-player.getLucky())));
                if (player.getCheckOX() == 1) { //O를 눌렀을 경우
                    if(player.getLastItemNumber() <19) {
                        note.addFirstText("해골 옆에 먼지가 가득 쌓인 보자기를 풀어봤다.\n 그 안에는 ");
                        player.AddAngel(-2);
                        if (random01 < 15) {
                            player.addItem(0);
                            note.addFirstText("통조림 하나가 덩그러니 놓여있다.\n");
                        } else if (random01 >= 15 && random01 < 30) {
                            player.addItem(1);
                            note.addFirstText("잘 밀봉되어있는 생수가 나왔다.\n");
                        } else if (random01 >= 30 && random01 < 50) {
                            player.addItem(3);
                            note.addFirstText("묶여있는 천조각 밖에 없었다.\n");
                        } else if (random01 >= 50 && random01 < 55) {
                            player.addItem(13);
                            note.addFirstText("붕대가 들어있었다!\n");
                        } else if (random01 >= 55 && random01 < 60) {
                            player.addItem(5);
                            note.addFirstText("갈고리가 나왔다.\n");
                        } else if (random01 >= 60 && random01 < 65) {
                            player.addItem(6);
                            note.addFirstText("무딘 사냥용 나이프가 나왔다.\n");
                        } else if (random01 >= 65 && random01 < 70) {
                            player.addItem(8);
                            note.addFirstText("부서진 핸드폰이 있었다.\n");
                        } else if (random01 >= 70 && random01 < 75) {
                            player.addItem(11);
                            note.addFirstText("핸드폰 충전기가 나왔다.\n");
                        } else if (random01 >= 75 && random01 < 90) {
                            player.addItem(16);
                            note.addFirstText("물 정화용 캡슐이 나왔다.\n");
                        } else if (random01 >= 90 && random01 < 100) {
                            player.addItem(18);
                            note.addFirstText("비상용 폭죽이 나왔다.\n");
                        } else {
                            note.addFirstText("먼지밖에 보이지 않았다.\n");
                        }
                    }
                    else{
                        note.addFirstText("아무것도 없었다. 그리고 이미 내 가방은 ");
                        note.addFirstText("가득 차 있는지 오래다.\n");
                    }
                    note.addFirstText("어쩃든 살 사람은 살아야 되는거니깐 ");
                    note.addFirstText("너무 죄책감가지지말도록 하자. ");
                }
                else{ //X를 눌렀을 때
                    player.AddAngel(3);
                    note.addFirstText("아무래도 죽은사람의 짐을 뒤진다는 건 ");
                    note.addFirstText("불쾌한 일이다. ");
                    note.addFirstText("내가 저렇게 되지 않기를 바라며 ");
                    note.addFirstText("다시 앞으로 걸어가자. ");
                }
            }
            else if (player.getStoryCase() == 6) { //쥐 발견
                int random01 = (int) (Math.random() * (100+(100-player.getLucky())));
                if (player.getCheckOX() == 1) { //O를 눌렀을 경우
                    int knife = 0;
                    int stick = 0;
                    for (int j = 0; j <= player.getLastItemNumber(); j++) { //아이템 조건 찾기
                        if(player.inventory[j] != null)
                        if (player.inventory[j].getType() == 6) //나이프 갯수
                            knife = 1;
                        else if (player.inventory[j].getType() == 10) //stick 갯수
                            stick = 1;
                    }
                    if(knife == 1)
                        note.addFirstText("나는 칼을 쥐고 조심스럽게 쥐에게 다가갔다. ");
                    else if(stick == 1)
                        note.addFirstText("나는 몽둥이를 움켜쥔 채 쥐에게 다가갔다. ");
                    else {
                        note.addFirstText("무방비하게 나를 쳐다보고 있는 쥐를 향해 한걸음 다가갔다. ");
                    }
                    int end = (int)Math.random()*100;
                    if(end < player.getLucky() + (knife == 1 ? 40 : stick*15)){ //쥐잡기 성공
                        if(knife == 1){
                            note.addFirstText("가만히 서있는 쥐를 향해 눈을 질끈 감고 칼을 ");
                            note.addFirstText("내리 찍자, 찍 하는 소리와 함께 부르르 떨리던 ");
                            note.addFirstText("칼의 진동이 서서히 멈춘다.\n");
                        }
                        else if(stick == 1){
                            note.addFirstText("무슨 냄새를 맡고 있는 지 코를 열심히 킁킁대");
                            note.addFirstText("는 쥐의 머리를 향해 몽둥이를 힘껏 들어 휘두");
                            note.addFirstText("르자, 쥐의 몸이 파르르 떨리다 픽 쳐진다.\n");
                            }
                        else{
                            note.addFirstText("손을 조심스레 뻗다가 쥐의 목을 양손으로 움");
                            note.addFirstText("켜쥐자, 쥐는 끽끽대며 벗어나려 발버둥을 친");
                            note.addFirstText("다. 결국 쥐는 혀를 내민채 축 쳐졌다.\n");
                            }
                        if(player.getNowAngel() > 12)
                            note.addFirstText("미안하다, 쥐야... ");
                        else
                            note.addFirstText("살이 좀 오동통 했으면 좋을텐데. ");
                        if(player.getLastItemNumber() < 19) {
                            player.addItem(12);
                            note.addFirstText("나는 평소에 몸을 씻고 다녔는 지 의심스러운 쥐");
                            note.addFirstText("의 시체를 조심히 가방속에 넣었다. ");
                            player.AddAngel(-2);
                        }else{
                            note.addFirstText("하지만 가방 안에는 더 이상 무언가 넣을 만한 공간이 ");
                            note.addFirstText("없다. 그냥 이대로 두고 가면 누군가 처리하겠지? ");
                            player.AddAngel(-4);
                        }
                    }
                    else{ //쥐잡기 실패
                        note.addFirstText("쥐는 멍하니 있다가 갑자기 빠르게 움직이며 ");
                        if(Math.random()*90 > player.getLucky() + (3-player.getLevel()) * 10){
                            note.addFirstText("뻗고있던 나의 팔을 물어버리고는 도망친다. ");
                            note.addFirstText("혈관이 잘린 것인지 팔에서는 피가 멈추질 않는다.\n");
                            player.AddHealth(-player.getMonsterDamage());
                            player.GetBlood();
                        }
                        else
                            note.addFirstText("동굴 벽 구멍안으로 도망쳐 버렸다.\n");
                        if(player.getNowAngel() > 12){
                            note.addFirstText("그래, 작은 동물의 생명을 뺏느니 차라리 ");
                            note.addFirstText("굶는게 낫다. 멀리멀리 가렴. ");
                        }
                        else
                            note.addFirstText("저 쥐새끼...다음에 만나면 온 몸을 갈기갈기 찢어버릴테다.\n");
                        player.AddAngel(-1);
                    }
                }
                else{ //X를 눌렀을 때
                    note.addFirstText("나는 잠시나마 잔혹한 생각을 했던 나를 꾸짖");
                    note.addFirstText("으며 손을 휘저어 쥐를 쫓아냈다.\n");
                    if(player.getNowAngel() > 15){
                        if(Math.random()*100 < player.getLucky()/5 + player.getNowAngel()*2){
                            if(player.getLastItemNumber() < 19) {
                                note.addFirstText("그러자 쥐는 바위뒤로 천천히 걸어가다 뒤를 자꾸");
                                note.addFirstText("돌아보며 나를 쳐다본다. 나는 뭔가 이상한 느낌이 들");
                                note.addFirstText("어 쥐의 뒤를 쫓아갔다. 그 바위 뒤 자리에는 ");
                                int random02 = (int) Math.random() * (60 + (50 - player.getLucky() / 2));
                                if (random02 < 15) {
                                    player.addItem(0);
                                    note.addFirstText("통조림 하나가 덩그러니 놓여있었다.\n");
                                } else if (random02 >= 15 && random02 < 30) {
                                    player.addItem(1);
                                    note.addFirstText("잘 밀봉되어있는 생수가 굴러다닌다.\n");
                                } else if (random02 >= 30 && random02 < 40) {
                                    player.addItem(3);
                                    note.addFirstText("사람의 옷가지로 보이는 천조각이 있었다.\n");
                                } else if (random02 >= 40 && random02 < 45) {
                                    player.addItem(5);
                                    note.addFirstText("녹이 슨 갈고리가 박혀있었다.\n");
                                } else if (random02 >= 45 && random02 < 50) {
                                    player.addItem(16);
                                    note.addFirstText("비상용 물 정화제가 있었다.\n");
                                } else if (random02 >= 50 && random02 < 53) {
                                    player.addItem(14);
                                    note.addFirstText("알 수 없는 해독제가 있었다.\n");
                                } else if (random02 >= 53 && random02 < 56) {
                                    player.addItem(13);
                                    note.addFirstText("먼지 쌓인 붕대가 있었다.\n");
                                } else if (random02 >= 56 && random02 < 58) {
                                    player.addItem(18);
                                    note.addFirstText("구조용 폭죽이 하나 있었다.\n");
                                } else {
                                    note.addFirstText("먼지밖에 보이지 않았다.\n");
                                }
                                note.addFirstText("얼떨떨한 표정으로 난 쥐가 있던 자리를 보았으나 쥐의 흔적은 찾아볼 수 없었다. ");
                            }else{
                                note.addFirstText("그러자 쥐는 바위뒤로 천천히 걸어가다 뒤를 ");
                                note.addFirstText("돌아 나를 쳐다본다. 뭔가 이상한 느낌이 들");
                                note.addFirstText("었지만 가방도 무겁고 이미 지쳐있던 나는 쥐");
                                note.addFirstText("를 무시하고 다시 길을 나섰다. ");
                            }
                        }
                        else {
                            note.addFirstText("쥐는 같이 놀고 싶은지 한참을 주위를 맴돌");
                            note.addFirstText("다 이내 어둠속으로 사라졌다.");
                            note.addFirstText("내가 이렇게 한 착한 행동들이 언젠가는 나에");
                            note.addFirstText("게 돌아올 것이라 굳게 믿으며 미소를 지었다. ");
                        }
                    }
                    else{
                        note.addFirstText("쥐는 나를 그 새빨간 눈으로 한참을 노려보다 후다닥 어둠 속으");
                        note.addFirstText("로 사라져 버렸다. 뭔가 이상하고 섬뜩한 기");
                        note.addFirstText("분이 들어 더 짜증난다. ");
                        if(player.getNowAngel() <= 5)
                            note.addFirstText("하찮은 미물따위가 감히...\n");
                        else
                            note.addFirstText("\n");
                    }
                    player.AddAngel(3);
                }
            }
            else if (player.getStoryCase() == 7) { //뱀 발견
                int random01 = (int) (Math.random() * (100 + (100 - player.getLucky())));
                if (player.getCheckOX() == 1) { //O를 눌렀을 경우
                    int knife = 0;
                    int stick = 0;
                    for (int j = 0; j <= player.getLastItemNumber(); j++) { //아이템 조건 찾기
                        if (player.inventory[j] != null)
                            if (player.inventory[j].getType() == 6) //나이프 갯수
                                knife = 1;
                            else if (player.inventory[j].getType() == 10) //stick 갯수
                                stick = 1;
                    }
                    if (knife == 1)
                        note.addFirstText("나는 칼을 쥐고 조심스럽게 뱀에게 다가갔다. ");
                    else if (stick == 1)
                        note.addFirstText("나는 몽둥이를 움켜쥔 채 뱀에게 다가갔다. ");
                    else {
                        note.addFirstText("나를 보며 혀를 날름대는 뱀을 향해 조심스레 ");
                        note.addFirstText("한걸음 다가갔다. ");
                    }
                    int end = (int) Math.random() * 100;
                    if (end < player.getLucky() + (knife == 1 ? 40 : stick * 15)) { //쥐잡기 성공
                        if (knife == 1) {
                            note.addFirstText("가만히 서있는 뱀을 향해 눈을 질끈 감고 칼을 ");
                            note.addFirstText("내리 찍자, 찍 하는 소리와 함께 칼의 진동이 ");
                            note.addFirstText("멈춘다.\n");
                        } else if (stick == 1) {
                            note.addFirstText("천천히 몽둥이를 들어올려 가만히 뱀의 머리가 ");
                            note.addFirstText("멈추길 기다린다. 그리고 그 떄를 맞춰 정확히 ");
                            note.addFirstText("내리치자, 뱀은 온몸이 꼬이다가 멈춰버린다.\n");
                        } else {
                            note.addFirstText("조심스레 손을 뻗자, 뱀은 혀를 날름대며 경계 ");
                            note.addFirstText("하기 시작한다. 한참을 가만히 있자 뱀은 관심");
                            note.addFirstText("을 잃었는지 멈춰선다. 그 때를 맞춰 뱀의 머리");
                            note.addFirstText("를 붙잡아 세게 누르자 뱀은 꿈틀대다 멈춘다.\n");
                        }
                        if (player.getNowAngel() > 5)
                            note.addFirstText("이런 나를 용서해다오, 뱀아.");
                        else
                            note.addFirstText("뱀고기는 먹기가 좀 불편하긴 하지만 다행이다.\n");
                        if(player.getLastItemNumber()<19) {
                            player.addItem(12);
                            note.addFirstText("나는 기다란 뱀의 시체를 돌돌 말아서 조심스레 ");
                            note.addFirstText("가방안으로 넣었다. ");
                            player.AddAngel(-2);
                        }
                        else{
                            note.addFirstText("이런, 가방은 이미 가득 차서 고기를 넣을 수 ");
                            note.addFirstText("없을 것 같다. 그냥 내버려두고 가야겠다. ");
                            player.AddAngel(-4);
                        }
                    } else { //쥐잡기 실패
                        note.addFirstText("뱀은 혀를 날름대다 갑자기 재빠르게 움직여 ");
                        if (Math.random() * 90 > player.getLucky() + (3 - player.getLevel()) * 10) {
                            note.addFirstText("뻗고있던 나의 팔을 물어버리고는 도망친다. ");
                            note.addFirstText("몇 분 지나지 않아 팔에 이상한 기포가 올라");
                            note.addFirstText("오더니 피부가 붓고 간지러워지기 시작했다.\n");
                            player.AddHealth(-player.getMonsterDamage());
                            player.GetPoison();
                        } else
                            note.addFirstText("동굴 벽 구멍안으로 도망쳐 버렸다.\n");
                        if (player.getNowAngel() <= 5) {
                            note.addFirstText("차라리 잘된 일인지도 모른다. 되도록 다시는 ");
                            note.addFirstText("나의 눈에 띄지 않았으면 좋겠다. ");
                        } else
                            note.addFirstText("망할 뱀새끼...꼭 잡아서 꼬치를 만들테다! ");
                        player.AddAngel(-2);
                    }
                } else { //X를 눌렀을 때
                    note.addFirstText("나는 잠시나마 잔혹한 생각을 했던 나를 꾸짖");
                    note.addFirstText("으며 천천히 손을 휘저어 뱀을 쫓아냈다.\n");
                    if (player.getNowAngel() > 15) {
                        if(Math.random()*100 < player.getLucky()/5 + player.getNowAngel()*4){
                            if(player.getLastItemNumber() < 19) {
                                note.addFirstText("뱀이 아무런 미동도 없자, 의구심이 든 나는 ");
                                note.addFirstText("조심스럽게 뱀에게 다가갔다. 불빛을 비춰보자 그것은 뱀이 아닌 ");
                                int random02 = (int) Math.random() * (60 + (50 - player.getLucky() / 2));
                                if (random02 < 20) {
                                    player.addItem(9);
                                    note.addFirstText("밧줄이라는 것을 알았다.\n");
                                } else if (random02 >= 20 && random02 < 35) {
                                    player.addItem(10);
                                    note.addFirstText("굵은 나뭇가지라는 것을 알았다.\n");
                                } else if (random02 >= 35 && random02 < 42) {
                                    player.addItem(11);
                                    note.addFirstText("핸드폰 충전기라는 것을 알았다.\n");
                                } else {
                                    note.addFirstText("바위의 그림자라는 것을 알았다.\n");
                                }
                                note.addFirstText("이상한 환각을 본 것인지 얼떨떨한 표정으로 나는 ");
                                note.addFirstText("챙길 것을 챙긴 후 다시 길을 나섰다. ");
                            }
                            else{
                                note.addFirstText("뱀은 휘젓는 손을 피해 이리저리 고개를 흔");
                                note.addFirstText("들다가 바위 틈사이로 스르르 사라졌다. ");
                                note.addFirstText("이렇게 한 착한 행동들이 언젠가는 나에게 ");
                                note.addFirstText("언젠가는 돌아올 것이라 굳게 믿는다. ");
                            }
                        }
                        else {
                            note.addFirstText("뱀은 휘젓는 손을 피해 이리저리 고개를 흔");
                            note.addFirstText("들다가 바위 틈사이로 스르르 사라졌다. ");
                            note.addFirstText("이렇게 한 착한 행동들이 언젠가는 나에게 ");
                            note.addFirstText("언젠가는 돌아올 것이라 굳게 믿는다. ");
                        }
                    } else {
                        note.addFirstText("뱀은 혀를 스르르 내밀며 한참을 나를 보며 쉬이익 대다가 ");
                        note.addFirstText("천천히 사라져 버렸다. 뭔가 이상하고 섬뜩한 기");
                        note.addFirstText("분이 들어 더 짜증난다. ");
                        if(player.getNowAngel() <= 5)
                            note.addFirstText("하찮은 뱀따위가...\n");
                        else
                            note.addFirstText("\n");
                    }
                    player.AddAngel(3);
                }
            }
            else if (player.getStoryCase() == 8) { //기름 발견
                note.addFirstText("횃불을 잠시 끈 뒤, 기름웅덩이 안에 듬뿍 적셨다. ");
                note.addFirstText("라이터로 조심히 불을 붙이자, 횃불이 활활 타오른다. ");
                note.addFirstText("이 많은 기름을 담아갈 수 없다는게 아쉽지만 운이 ");
                note.addFirstText("좋았다고 생각하자. ");
                player.AddTorch(5);
                if(player.getTorchOn() == false)
                    player.setpTorchOn();
            }
            else if (player.getStoryCase() == 9) { //좁은 길 발견
                int random01 = (int) (Math.random() * (180+(100-player.getLucky())));
                if (player.getCheckOX() == 1) { //O를 눌렀을 경우
                    int lost = player.RandomlostItem();
                    if(lost == 50){
                        note.addFirstText("좁은길을 지나오며 가방문이 열려");
                        note.addFirstText("버렸지만 가방안은 이미 텅텅 비어");
                        note.addFirstText("있어, 흘릴 것이 없었다. ");
                    }
                    else {
                        note.addFirstText("제길, 좁은 길을 지나오면서 모르고 ");
                        if (lost == 0)
                            note.addFirstText("통조림을 흘려버렸다. ");
                        else if (lost == 1)
                            note.addFirstText("깨끗한 생수를 흘려버렸다. ");
                        else if (lost == 2)
                            note.addFirstText("더러운 생수를 흘려버렸다. ");
                        else if (lost == 3)
                            note.addFirstText("헝겊조각을 흘려버렸다. ");
                        else if (lost == 4)
                            note.addFirstText("구급약을 흘려버렸다. ");
                        else if (lost == 5)
                            note.addFirstText("갈고리를 흘려버렸다. ");
                        else if (lost == 6)
                            note.addFirstText("나이프를 흘려버렸다. ");
                        else if (lost == 7)
                            note.addFirstText("기름통을 흘려버렸다. ");
                        else if (lost == 8)
                            note.addFirstText("핸드폰을 흘려버렸다. ");
                        else if (lost == 9)
                            note.addFirstText("밧줄을 흘려버렸다. ");
                        else if (lost == 10)
                            note.addFirstText("나무막대를 흘려버렸다. ");
                        else if (lost == 11)
                            note.addFirstText("충전기를 흘려버렸다. ");
                        else if (lost == 12)
                            note.addFirstText("고기를 흘려버렸다. ");
                        else if (lost == 13)
                            note.addFirstText("붕대를 흘려버렸다. ");
                        else if (lost == 14)
                            note.addFirstText("해독제를 흘려버렸다. ");
                        else if (lost == 15)
                            note.addFirstText("팔목보호대를 흘려버렸다. ");
                        else if (lost == 16)
                            note.addFirstText("정화캡슐을 흘려버렸다. ");
                        else if (lost == 17)
                            note.addFirstText("구운고기를 흘려버렸다. ");
                        else if (lost == 18)
                            note.addFirstText("비상용 폭죽을 흘려버렸다. ");
                        else
                            note.addFirstText("무언가를 흘려버렸다. ");
                    }
                    note.addFirstText("그 환한 불빛의 원인은 다름 아닌 녹슨 상자의 ");
                    note.addFirstText("뚜껑에서 나오고 있었다. 천천히 상자를 열자, ");
                    if(random01 < 15){
                        player.addItem(8);
                        note.addFirstText("액정이 부숴진 핸드폰이 있었다. ");
                    }
                    else if(random01 >= 15 && random01 < 30){
                        player.addItem(11);
                        note.addFirstText("핸드폰전용 충전기가 놓여있었다. ");
                    }
                    else if(random01 >= 30 && random01 < 45){
                        player.addItem(6);
                        note.addFirstText("날이 빠진 사냥용 나이프가 있었다. ");
                    }
                    else if(random01 >= 45 && random01 < 60){
                        player.addItem(4);
                        note.addFirstText("비상 산악용 구급키트가 있었다. ");
                    }
                    else if(random01 >= 60 && random01 < 80){
                        player.addItem(18);
                        note.addFirstText("비상용 폭죽이 있었다. ");
                    }
                    else if(random01 >= 80 && random01 < 100){
                        player.addItem(10);
                        note.addFirstText("녹 슨 산악용 갈고리가 있었다. ");
                    }
                    else if(random01 >= 100 && random01 < 125){
                        player.addItem(13);
                        note.addFirstText("깨끗한 붕대가 놓여있었다. ");
                    }
                    else if(random01 >= 125 && random01 < 150){
                        player.addItem(14);
                        note.addFirstText("해독제가 있었다. ");
                    }
                    else if(random01 >= 150 && random01 < 180){
                        player.addItem(16);
                        note.addFirstText("물 정화제가 하나 놓여있었다. ");
                    }
                    else{
                        note.addFirstText("자욱한 먼지들이 나를 반겼다. 제길, 다시 뒤로 돌아갈 수는 없을 것 같다. ");
                    }
                }
                else{ //X를 눌렀을 떄
                    note.addFirstText("길도 좁고 가방도 낑겨서 들어가기가 ");
                    note.addFirstText("쉽지 않아보인다. 괜히 궁금증에 못 이겨 ");
                    note.addFirstText("들어가다가 다치지 말고 안전하게 위험해보이는 곳은 들어가지 말도록 하자. ");
                }
            }
            else if (player.getStoryCase() == 10) { //아무일도 일어나지않음
                int j = (int) (Math.random() * 4);
                if(j==0) {
                    note.addFirstText(" 고요한 동굴안은 가끔씩 쥐나 알 수 없는 ");
                    note.addFirstText("짐승의 울음소리만 날 뿐이다. 바닥에는 ");
                    note.addFirstText("벌레들이 기어다니고 땅의 진동이 가끔씩 ");
                    note.addFirstText("느껴지기도 한다. 무슨 일이라도 벌어진다");
                    note.addFirstText("면 이렇게 지루하진 않았을 것 같다. ");
                }
                else if(j==1) {
                    note.addFirstText(" 오늘도 죽고 싶은 충동을 이겨냈다. 내일은 ");
                    note.addFirstText("어떻게 이겨낼 지 아직 잘 모르겠다. 가끔은 ");
                    note.addFirstText("처음 있던 곳으로 돌아가서 따스한 햇살이라");
                    note.addFirstText("도 한웅큼 쬐고 싶다. 하지만 그러기에는 너");
                    note.addFirstText("무 멀리 와버렸다. ");
                }
                else if(j==2) {
                    note.addFirstText("고요한 동굴안은 가끔씩 쥐나 알 수 없는 ");
                    note.addFirstText("짐승의 울음소리만 날 뿐이다. 바닥에는 ");
                    note.addFirstText("벌레들이 기어다니고 뱀의 진동이 가끔씩 ");
                    note.addFirstText("느껴지기도 한다. 무슨 일이라도 벌어진다");
                    note.addFirstText("면 이렇게 지루하진 않았을 것 같다. ");
                }
                else if(j==3) {
                    note.addFirstText("고요한 동굴안은 가끔씩 쥐나 알 수 없는 ");
                    note.addFirstText("짐승의 울음소리만 날 뿐이다. 바닥에는 ");
                    note.addFirstText("벌레들이 기어다니고 뱀의 진동이 가끔씩 ");
                    note.addFirstText("느껴지기도 한다. 무슨 일이라도 벌어진다");
                    note.addFirstText("면 이렇게 지루하진 않았을 것 같다. ");
                }
                else if(j==4) {
                    note.addFirstText("고요한 동굴안은 가끔씩 쥐나 알 수 없는 ");
                    note.addFirstText("짐승의 울음소리만 날 뿐이다. 바닥에는 ");
                    note.addFirstText("벌레들이 기어다니고 뱀의 진동이 가끔씩 ");
                    note.addFirstText("느껴지기도 한다. 무슨 일이라도 벌어진다");
                    note.addFirstText("면 이렇게 지루하진 않았을 것 같다. ");
                }
                else{
                    note.addFirstText("고요한 동굴안은 가끔씩 쥐나 알 수 없는 ");
                    note.addFirstText("짐승의 울음소리만 날 뿐이다. 바닥에는 ");
                    note.addFirstText("벌레들이 기어다니고 뱀의 진동이 가끔씩 ");
                    note.addFirstText("느껴지기도 한다. 무슨 일이라도 벌어진다");
                    note.addFirstText("면 이렇게 지루하진 않았을 것 같다. ");
                }
            }
            else if (player.getStoryCase() == 11) { //적이 나타남(결과텍스트) ////////////////////////////////////////////////////////////////////////////////
                if (player.getCheckOX() == 1) { //O를 눌렀을 경우
                    int i = (int) (Math.random() * (130+(50 - player.getLucky()/2)));
                    note.addFirstText("아무리 비슷한 처지라도 내 것을 빼앗길 수는 없다. ");
                    if (player.IsHaveItem(6)) { //칼을 소유중
                        note.addFirstText("");
                    }
                    else if (player.IsHaveItem(10)) { //몽둥이를 소유중
                    }
                    else { //아무것도 안가지고 있을 때
                        note.addFirstText("나름 기발한 아이디어였지만, 가방안을 살펴");
                        note.addFirstText("보니 덫을 만들 마땅한 재료가 보이질 않는다. ");
                        note.addFirstText("차라리 다른 길을 찾아보는 것이 더 나을 것 ");
                        note.addFirstText("같다고 생각하며 나는 조용히 발길을 돌렸다. ");
                    }
                } else { //X를 눌렀을 경우
                    if (player.IsHaveItem(6)) { //칼을 소유중
                    }
                    else { //아무것도 안가지고 있을 때
                        note.addFirstText("나름 기발한 아이디어였지만, 가방안을 살펴");
                        note.addFirstText("보니 덫을 만들 마땅한 재료가 보이질 않는다. ");
                        note.addFirstText("차라리 다른 길을 찾아보는 것이 더 나을 것 ");
                        note.addFirstText("같다고 생각하며 나는 조용히 발길을 돌렸다. ");
                    }
                }
            }
        }
    }

    public void SetSecondText(){ //현재 상태에 대한 텍스트
        if(player.getDay() == 0){
            note.addSecondText("등산용 비상물품을 챙겨왔던 가방의 입은 벌어져 이미 ");
            note.addSecondText("가져온 것들의 대부분은 사라져 있었다. 그나마 남아있");
            note.addSecondText("는 것은 붕대와 통조림캔, 물정도 밖에 없었다. 다행히 ");
            note.addSecondText("주머니 안의 라이터는 깨지지 않고 살아있다. 같이 떨어");
            note.addSecondText("진 적당한 나뭇가지에 찣어진 겉옷을 감싼 채 불을 붙여 ");
            note.addSecondText("앞을 밝혔다.\n");
        }
        else{
            note.addSecondText(" -현재 상태 체크\n");
                int i = (int) (Math.random() * (130+(50 - player.getLucky()/2)+(player.getTorchOn()==true?0:150-player.getLucky())));
                    if (player.getNowTorch() >= 3)
                        note.addSecondText("횃불은 정신없이 활활 타오르고 있다.\n");
                    else if (player.getNowTorch() < 3 && player.getNowTorch() > 0)
                        note.addSecondText("불꽃이 점점 약해진다. 금방이라도 꺼질 것 같다.\n");
                    else
                        note.addSecondText("횃불은 이미 차갑게 식어버린지 오래다.\n");
            if(player.getLastItemNumber() <19){
                if(i<=20){
                    player.addItem(0);
                    note.addSecondText("구석에서 먼지쌓인 캔을 주웠다! ");
                }
                else if(i>20 && i<=40){
                    player.addItem(1);
                    note.addSecondText("먹을 수 있는 깨끗한 물을 발견했다! ");
                }
                else if(i>40 && i<=50){
                    player.addItem(2);
                    note.addSecondText("정화되지 않은 물웅덩이에서 물을 챙겼다. ");
                }
                else if(i>50 && i<=60){
                    player.addItem(3);
                    note.addSecondText("걷다가 발에 헝겊조각이 차여 주워냈다. ");
                }
                else if(i>60 && i<=70){
                    player.addItem(12);
                    note.addSecondText("갓 죽은듯한 박쥐에게서 고기를 얻어냈다. ");
                }
                else if(i>70 && i<=80){
                    player.addItem(7);
                    note.addSecondText("기름이 조금 담긴 연료통을 발견했다! ");
                }
                else if(i>80 && i<=90){
                    player.addItem(9);
                    note.addSecondText("가다가 허름한 밧줄을 주웠다! ");
                }
                else if(i>90 && i<=100){
                    player.addItem(10);
                    note.addSecondText("타다만듯한 나무막대를 발견했다! ");
                }
                else if(i>100 && i<=110){
                    player.addItem(13);
                    note.addSecondText("꽤 쓸만한 붕대를 발견했다! ");
                }
                else if(i>110 && i<=120){
                    player.addItem(14);
                    note.addSecondText("먼지쌓인 해독제통을 발견했다! ");
                }
                else if(i>120 && i<=130){
                    player.addItem(16);
                    note.addSecondText("오래된 비상용 물정화제를 찾았다! ");
                }
                else if(i>130) {
                    int j = (int) (Math.random() * 4);
                    if (player.getTorchOn() == true) {
                        if (j == 0)
                            note.addSecondText("주변에는 머리카락 한올도 있지 않았다... ");
                        else if (j == 1)
                            note.addSecondText("오늘은 아무런 소득이 없었다... ");
                        else if (j == 2)
                            note.addSecondText("바닥에는 쥐똥만 굴러다닌다... ");
                        else if (j == 3)
                            note.addSecondText("오늘 얻은 것은 몸의 피로뿐이다. ");
                        else
                            note.addSecondText("동물의 흔적조차 찾아볼 수 없다. ");
                    } else {
                        if (j == 0)
                            note.addSecondText("어두워서 무언가를 찾기에는 어려워보인다. ");
                        else if (j == 1)
                            note.addSecondText("앞이 보이지 않아... ");
                        else if (j == 2)
                            note.addSecondText("한 치 앞도 막막하다. ");
                        else if (j == 3)
                            note.addSecondText("어서 횃불을 켤만한 무언가를 찾아보자. ");
                        else
                            note.addSecondText("횃불이 꺼져서 아무것도 찾을 수 없다. ");
                    }
                }
                }
                else{
                note.addSecondText("가방이 가득차서 무언가 찾을 필요는 없어보인다... ");
            }

            if(player.isitBlood() == true && player.isitPoison() == true)
                note.addSecondText("피부는 간지럽고, 흐르는 피는 멈추질 않는다. ");
            else if(player.isitBlood() == false && player.isitPoison() == true)
                note.addSecondText("무얼 잘못 먹었는지 정신이 어지럽고 피부가 간지럽다. ");
            else if(player.isitBlood() == true && player.isitPoison() == false)
                note.addSecondText("벌어진 상처는 아물 생각이 없는지 계속 피를 토해낸다. ");
            else {
                if(player.isitHurt() == true)
                    note.addSecondText("상처가 아른거리지만 아픈 곳은 다행히 없다. ");
                else
                    note.addSecondText("한 군데도 다치지 않은 것이 불행 중 다행이다. ");
            }
            if(player.getNowHp() >=7) {
                int j= (int)(Math.random()*4);
                if(j == 0)
                    note.addSecondText("아직까지 체력은 버틸만하다. ");
                else if(j==1)
                    note.addSecondText("체력은 아직 팔팔하다. ");
                else if(j==2)
                    note.addSecondText("몸이 아직까지는 제 기능을 해주고 있다. ");
                else if(j==3)
                    note.addSecondText("건강에는 아무 이상이 없는 것 같다. ");
                else
                    note.addSecondText("몸상태는 아직 괜찮다. ");
            }
            else if(player.getNowHp() <7 && player.getNowHp() >=4){
                note.addSecondText("체력이 떨어져가고 있는게 느껴진다. ");
            }
            else{
                note.addSecondText("금방이라도 쓰러져 죽어버릴 것만 같다. ");
            }
            if(player.getNowHunger() >=3){
                note.addSecondText("지금 상황에 가장 행복한 것은 배가 부르다는 것이다. ");
            }
            else if(player.getNowHunger() <3 && player.getNowHunger() >=1){
                note.addSecondText("슬슬 무언가 먹을 때가 된 것 같다. 뭐라도 먹을게... ");
            }
            else{
                note.addSecondText("이대로 가다간 굶어서 먼저 죽지 않을까...? ");
            }
            if(player.getNowWater()>=3){
                note.addSecondText("칼칼한 물을 들이키고 싶지만 아직까지는 괜찮다. ");
            }
            else if(player.getNowWater() <3 && player.getNowHunger() >=1){
                note.addSecondText("목이 매마르기 시작했다. 바닥에 고인 물이라도 핥고싶다. ");
            }
            else{
                note.addSecondText("가도가도 물방울 하나 보이질않아...목이 타들어간다. ");
            }

        }
    }
    public void SetThirdText(){
        if(player.getDay() == 0){
            note.addThirdText("자세히 살펴보자, 사람이 겨우 지나갈만한 틈이 보인다. ");
            note.addThirdText("여기서 가만히 있는다고 나를 구해러 올 사람은 없겠지? ");
            note.addThirdText("돌아다니다 보면 언젠가 동굴의 끝에 도달할 것이다. ");
            note.addThirdText("앞으로 무슨 일이 생기던 당황하지말고 대처해야할 것이다. ");
            note.addThirdText("제발 이 긴 여정이 짧았으면 하고 기도해본다. ");
        }
        else{
            if(player.getStoryCase() == 1){ //동굴입구에서 곰 만났을 상황
                if(player.getFirstCase() == 0){
                    note.addThirdText("점점 주변이 밝아지더니 저 끝에 태양이 보인다. ");
                    note.addThirdText("아니, 태양빛 사이로 거대한 그림자가 보인다. ");
                    note.addThirdText("아마 이 동굴의 주인이지 않을까 생각이 든다. ");
                    note.addThirdText("번뜩이는 아이디어가 생각났다. 고기 한덩이와 ");
                    note.addThirdText("로프와 나무를 이용하여 덫을 놓으면 어느정도 ");
                    note.addThirdText("이 괴물의 체력을 빼놓을 수 있지 않을까? ");
                }
                else {
                    note.addThirdText("빛이 보인다. 그리고 낮게 크르릉대는 흉포한 소리, ");
                    note.addThirdText("아마 내가 쳐둔 덫에 걸린 그 거대한 곰일테다. ");
                    note.addThirdText("발소리를 죽이고 멀리서 바라본 그의 모습은 몇일을 ");
                    note.addThirdText("굶은 듯, 전보다 작아진 덩치에 눈은 혈빛으로 휩싸");
                    note.addThirdText("여 있다. 아무리 상태가 안좋다 하더라도 그는 곰이");
                    note.addThirdText("다. 해치우고 지나가려면 적어도 나이프와 어정쩡한 ");
                    note.addThirdText("방어구라도 필요할 것 같다. ");
                }
            }
            else if(player.getStoryCase() == 2){ //폭포절벽 발견
                note.addThirdText("저 앞에 밝은 빛이 보여 허겁지겁 달려갔다. 헐떡");
                note.addThirdText("대며 도착해보니 시원한 물 소리와 함께 아래로 쏟");
                note.addThirdText("아지는 물줄기가 보인다. 혹시나해서 머리를 들이 밀");
                note.addThirdText("어 아래를 쳐다보니 까마득한 절벽밖에 보이질 않는");
                note.addThirdText("다. 하지만 조금 멀리 떨어진 곳에 무슨 용도일지 모");
                note.addThirdText("를 사다리가 설치되어 있다. 저기 까지 간다면 밑으로 ");
                note.addThirdText("내려갈 수 있어! 그러기 위해서는 로프와 그것을 묶을만한... ");
            }
            else if(player.getStoryCase() == 3){ //발전기 발견
                if(player.getThirdCase() == 0){
                    note.addThirdText("오늘은 신기한 물건을 발견했다. 얼마만에 보는 건");
                    note.addThirdText("지 모를 기계덩어리이다. 주유구로 보이는 원통에는 ");
                    note.addThirdText("썩은 기름냄새만 가득하고 옆구리에는 돌리는 것으로 ");
                    note.addThirdText("보이는 손잡이가 보인다. 무엇에 쓰는 용도인고 하다 ");
                    note.addThirdText("옆을 보니 익숙한 250V짜리 콘센트가 달려있다. 나에");
                    note.addThirdText("게 핸드폰과 충전기, 그리고 소량의 기름이 있다면 ");
                    note.addThirdText("어딘가에 구조요청이라도 할 수 있을텐데...");
                }
                else {
                    note.addThirdText("이 곳은 마치 내가 처음으로 도착했던 곳 같다. 아마 알 수 없는" +
                            " 다른 사람들도 이 곳에서 떨어졌겠지? 한참을 감성에 빠져있다가 작게 들리던" +
                            " 쇳소리가 점점 커지는 것을 느낀다. 그것은 헬기 프로펠러 소리이다! 어떡하지? " +
                            "이 곳에 내가 있다는 신호를 줄 수 있다면 그들이 나를 도와줄텐데...");
                }
            }
            else if(player.getStoryCase() == 4){ //죽은 사람 발견
                note.addThirdText("안으로 들어갈 수록 뭔가 썩은내가 진동을 하더니 ");
                if(player.getTorchOn() == true)
                    note.addThirdText("횃불 불빛 사이로 누워있는 사람 형체가 보인다. ");
                else
                    note.addThirdText("바닥에 흐릿하게 사람 형태가 보인다. ");
                note.addThirdText("가까이서 보니 죽은 지 얼마 되지않은 사람이었다. ");
                note.addThirdText("굶어죽은 듯 삐쩍 말라있는 시체는 억울했는지 눈도 ");
                note.addThirdText("감지 못한 채 구석에 쓰러져 있다. 뭔가 나의 미래");
                note.addThirdText("를 본 느낌이라 구역질이 올라왔지만 살아남기 위해");
                note.addThirdText("선 뭐라도 뒤져봐야 하겠지? ");
            }
            else if(player.getStoryCase() == 5){ //해골 발견
                note.addThirdText("들어가도 들어가도 끝이 없는 동굴안을 들어가다가 ");
                note.addThirdText("무언가 발에 채였다. 그것은 다름 아닌 사람의 해골");
                note.addThirdText("이었다. 얼마나 많은 사람들이 나처럼 이곳에 빠져서 ");
                note.addThirdText("죽게 된 것일까? 해골 옆에는 낡은 천에 싸인 보자기");
                note.addThirdText("가 보였다. 혹시 지금 나에게 필요한 뭐라도 있지 않");
                note.addThirdText("을까? 어디 한번 열어볼까? ");
            }
            else if(player.getStoryCase() == 6){ //쥐 발견
                note.addThirdText("아까부터 찍찍대는 소리가 뒤에서 나를 따라다닌다. ");
                note.addThirdText("아마 이 안에서 길잃은 외로운 쥐 한마리일테다. ");
                note.addThirdText("의심을 푼 것인지 내 바로 뒤까지 쫓아와서 두리번");
                note.addThirdText("대고 있다. 이 귀여운 쥐에게 인간의 무서움을 보여");
                note.addThirdText("줄 때인가? 삭막한 이 곳에서 살아남기 위해서라면 쥐고기라도 먹으면서" +
                        " 버텨야 되지 않을까?");
            }
            else if(player.getStoryCase() == 7){ //뱀 발견
                note.addThirdText("계속해서 스르륵거리는 소리가 주변을 맴돈다. ");
                note.addThirdText("그러더니 얼마 안가 내 앞에 혀를 날름대며 뱀 ");
                note.addThirdText("한마리가 또아리를 튼다. 마치 내 앞길을 막는 듯이 가만히 있는 뱀을 ");
                note.addThirdText("보니 조금씩 유혹에 빠진다. 양이 그렇게 많지는 않지만 먹을 수 있는 ");
                note.addThirdText("고기가 아닌가? 잡아볼까? ");
            }
            else if(player.getStoryCase() == 8){ //썩은 기름 발견
                note.addThirdText("한참을 걷다 오른쪽 발이 무언가 물컹한 것을 밟았다. 그 것은 오래된 동물의 뼈가 둥둥 떠다니는 기름웅덩이였다. ");
                note.addThirdText("이정도 양의 기름이면 한참 동안 횃불을 밝히는 데 도움이 될 것 같다. ");
                note.addThirdText("이게 왠 횡재냐? ");
            }
            else if(player.getStoryCase() == 9){ //좁은 길 발견
                note.addThirdText("바위의 갈라진 틈 사이로 무언가 빛나는 물체가 휘황찬란하게 빛나고 ");
                note.addThirdText("있다. 하지만 그 틈은 겨우 마른 사람 한명 정도가 들어갈 만한 ");
                note.addThirdText("공간밖에 없었다. 혹시 저 안에 무언가 보물이 있지 않을까? ");
                note.addThirdText("한번 어거지로 들어가볼까? ");
            }
            else if(player.getStoryCase() == 10){ //아무 일도 일어나지않음
                int j = (int) (Math.random() * 4);
                if(j==0) {
                    note.addThirdText("걸어도 걸어도 이 동굴의 끝이 보이질 않는다. ");
                    note.addThirdText("다리는 후들거리고 정신은 아득해지지만 발걸음");
                    note.addThirdText("을 멈출 순 없다. 왜냐하면 멈추는 순간 나는 주");
                    note.addThirdText("저앉아서 모든 것을 포기해버릴 것 같기 떄문이다. ");
                }
                else if(j==1) {
                    note.addThirdText("가끔씩 옷을 길게 찣은 천으로 목을 거세게 졸라서 목뼈를 부러뜨려 죽으면 이 악몽");
                    note.addThirdText("에서 깰 수 있지 않을까 하는 생각이 든다. ");
                    note.addThirdText("안돼...밖에서 나를 기다리는 사람들이 있잖아? ");
                    note.addThirdText("여기 오기 전날 엄청 싸웠던 그 친구가 보고싶다. ");
                }
                else if(j==2) {
                    note.addThirdText("오늘은 아무 일도 일어나지 않았다. 동물도, ");
                    note.addThirdText("벌레도, 사람도, 괴물도...아무것도 나타나지 ");
                    note.addThirdText("않았다. 너무 외롭다. 괴물이 나타나 내 몸을 ");
                    note.addThirdText("갈기갈기 찣어줬으면 좋겠다. ");
                }
                else if(j==3) {
                    note.addThirdText("이상하리만큼 조용한 동굴안은 나를 미치게 ");
                    note.addThirdText("만든다. 차라리 주변에서 총알이 날아다니고 ");
                    note.addThirdText("칼이 여기저기 휘둘러지는 공간이 더 아늑할 ");
                    note.addThirdText("것 같다는 생각이 든다. 물론 진짜 그런 상황");
                    note.addThirdText("이 된다면 지금에 감사하게 되겠지만 말이다. ");
                }
                else if(j==4) {
                    note.addThirdText("내가 이렇게 있는 동안 누가 구조요청이라도 ");
                    note.addThirdText("해줬을까? 혹시 모두가 나를 잊고 살아가는 것");
                    note.addThirdText("은 아닐까? 그게 사실이라면 나는 지금 여기서 ");
                    note.addThirdText("바위에 머리를 부딫혀 죽는 것이 낫다고 생각");
                    note.addThirdText("한다. 물론 그게 아니라는 일말의 희망이 나를 ");
                    note.addThirdText("계속 걷게 만든다. ");
                }
                else{
                    note.addThirdText("내가 이렇게 있는 동안 누가 구조요청이라도 ");
                    note.addThirdText("해줬을까? 혹시 모두가 나를 잊고 살아가는 것");
                    note.addThirdText("은 아닐까? 그게 사실이라면 나는 지금 여기서 ");
                    note.addThirdText("바위에 머리를 부딫혀 죽는 것이 낫다고 생각");
                    note.addThirdText("한다. 물론 그게 아니라는 일말의 희망이 나를 ");
                    note.addThirdText("계속 걷게 만든다. ");
                }
            }
            else if(player.getStoryCase() == 11){ //강도를 만남
                note.addThirdText("영원히 나 혼자뿐일 것 같던 동굴안에서 나와 같");
                note.addThirdText("은 처지의 사람을 만났다! 오랜만에 만나는 사람");
                note.addThirdText("이라 반가웠던 것도 잠시, 그 남자는 나와 같이 ");
                note.addThirdText("살아남기 싫었는지 날이 선 흉기를 꺼내더니 가");
                note.addThirdText("지고 있는 것을 다 꺼내라는 진부한 대사를 한다. ");
                note.addThirdText("어떡하지? 이 사람에게서 내 것을 지키려는 작은 ");
                note.addThirdText("반항을 해야할까? ");
            }
        }
    }
}
