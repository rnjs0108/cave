package com.yongyong.objects;

import java.util.ArrayList;

/**
 * Created by USER on 2017-03-30.
 */

public class Note{
    private ArrayList<String> firstText; //지난 상황에 대한 결과
    private ArrayList<String> secondText; //현재의 상태
    private ArrayList<String> thirdText; //이벤트 발생 텍스트
    public Note(){
        firstText = new ArrayList<String>();
        secondText = new ArrayList<String>();
        thirdText = new ArrayList<String>();
        ClearAllText();
    }
    public void addFirstText(String text){ //하나씩 셋 시켜줌
        firstText.add(text);
    }
    public String getFirstText(){
        String text="";
        for(int i=0;i<firstText.size();i++)
            text += firstText.get(i);
        return text;
    }
    public void setFirstText(String text){ //하나씩 셋 시켜줌
        String[] split = text.split("|");
        firstText.clear();
        for(int i=0;i<split.length;i++)
            firstText.add(split[i]);
    }
    public void addSecondText(String text){ //하나씩 셋 시켜줌
        secondText.add(text);
    }
    public String getSecondText(){
        String text="";
        for(int i=0;i<secondText.size();i++)
            text += secondText.get(i);
        return text;
    }
    public void setSecondText(String text){ //하나씩 셋 시켜줌
        String[] split = text.split("|");
        secondText.clear();
        for(int i=0;i<split.length;i++)
            secondText.add(split[i]);
    }
    public void addThirdText(String text){ //하나씩 셋 시켜줌
        thirdText.add(text);
    }
    public String getThirdText(){
        String text="";
        for(int i=0;i<thirdText.size();i++)
            text += thirdText.get(i);
        return text;
    }
    public void setThirdText(String text){ //하나씩 셋 시켜줌
        String[] split = text.split("|");
        thirdText.clear();
        for(int i=0;i<split.length;i++)
            thirdText.add(split[i]);
    }
    public void ClearAllText(){
        firstText.clear();
        secondText.clear();
        thirdText.clear();
    }
    public String SortText(String s, int num){
        String[] split = s.split("|");
        int value = 0;
        for(int i = 0; i<split.length; i++){
            if(split[i].intern() == "\n"){
                value = 0;
            }else{
                if (value%(num-1) == 0 && value != 0) {
                    split[i] += "\n";
                    value = -1;
                }
                value++;
            }
        }
        String turn = "";
        for(int i = 0; i<split.length; i++){
            turn+=split[i];
        }
        s = turn;
        return s;
    }
}
