package com.mathcity.myapplication.Utills;

import android.content.Context;

import com.mathcity.myapplication.model.parents;
import com.mathcity.myapplication.model.profile;

public class Common {
    public static final String DEVELOPER_KEY ="AIzaSyAioBxg0vH_-iYwkFjBv2n_XJ7Jazuls1s" ;
    Context context;
    public Common(Context context) {
        this.context = context;
    }
    public void SetSigning(String chk){
        context.getSharedPreferences("login",Context.MODE_PRIVATE).edit().putString("chl",chk).apply();

    }
    public String GetSigning(){
       return context.getSharedPreferences("login",Context.MODE_PRIVATE).getString("chl","false");

    }


    public void SetTotalScore(int sc){
        context.getSharedPreferences("ScoreX",Context.MODE_PRIVATE).edit().putInt("score",sc).apply();

    }
    public int GetTotalScore(){
        return context.getSharedPreferences("ScoreX",Context.MODE_PRIVATE).getInt("score",0);

    }

    public void SetBonusScore(int sc){
        context.getSharedPreferences("BonusScoreX",Context.MODE_PRIVATE).edit().putInt("Bscore",sc).apply();

    }
    public int GetBonusScore(){
        return context.getSharedPreferences("BonusScoreX",Context.MODE_PRIVATE).getInt("Bscore",0);

    }

    public void SetTotalAttempts(int TotalAttempts){
        context.getSharedPreferences("TotalAttemptsX",Context.MODE_PRIVATE).edit().putInt("TotalAttempts",TotalAttempts).apply();

    }
    public int GetTotalAttempts(){
        return context.getSharedPreferences("TotalAttemptsX",Context.MODE_PRIVATE).getInt("TotalAttempts",0);

    }


    public void SetTotalAttemptsSUBJECT(int TotalAttempts,String Topic){
        context.getSharedPreferences("TotalAttemptsSUBJECTX",Context.MODE_PRIVATE).edit().putInt("TotalAttempts"+Topic,TotalAttempts).apply();

    }
    public int GetTotalAttemptsSUBJECT(String Topic){
        return context.getSharedPreferences("TotalAttemptsSUBJECTX",Context.MODE_PRIVATE).getInt("TotalAttempts"+Topic,0);

    }


    public void SetTotalAttemptsPASS(int TotalAttemptsPASS){
        context.getSharedPreferences("TotalAttemptsPASSX",Context.MODE_PRIVATE).edit().putInt("TotalAttemptsPASS",TotalAttemptsPASS).apply();

    }
    public int GetTotalAttemptsPASS(){
        return context.getSharedPreferences("TotalAttemptsPASSX",Context.MODE_PRIVATE).getInt("TotalAttemptsPASS",0);

    }
    public void SetTotalAttemptsPASSSUBJECT(int TotalAttemptsPASS,String Topic){
        context.getSharedPreferences("TotalAttemptsPASSSUBJECTX",Context.MODE_PRIVATE).edit().putInt("TotalAttemptsPASS"+Topic,TotalAttemptsPASS).apply();

    }
    public int GetTotalAttemptsPASSSUBJECT(String Topic){
        return context.getSharedPreferences("TotalAttemptsPASSSUBJECTX",Context.MODE_PRIVATE).getInt("TotalAttemptsPASS"+Topic,0);

    }


    public void SetTotalAttemptsFAILL(int TotalAttemptsFAILL){
        context.getSharedPreferences("TotalAttemptsFAILLX",Context.MODE_PRIVATE).edit().putInt("TotalAttemptsFAILL",TotalAttemptsFAILL).apply();

    }
    public int GetTotalAttemptsFAILL(){
        return context.getSharedPreferences("TotalAttemptsFAILLX",Context.MODE_PRIVATE).getInt("TotalAttemptsFAILL",0);

    }
    public void SetTotalAttemptsFAILLSUBJECT(int TotalAttemptsFAILL,String Topic){
        context.getSharedPreferences("TotalAttemptsFAILLSUBJECTX",Context.MODE_PRIVATE).edit().putInt("TotalAttemptsFAILL"+Topic,TotalAttemptsFAILL).apply();

    }
    public int GetTotalAttemptsFAILLSUBJECT(String Topic){
        return context.getSharedPreferences("TotalAttemptsFAILLSUBJECTX",Context.MODE_PRIVATE).getInt("TotalAttemptsFAILL"+Topic,0);

    }



    public void SetWeakAreas(String WeakAreas){
        context.getSharedPreferences("WeakAreasX",Context.MODE_PRIVATE).edit().putString("WeakAreas",WeakAreas).apply();

    }
    public String GetWeakAreas(){
        return context.getSharedPreferences("WeakAreasX",Context.MODE_PRIVATE).getString("WeakAreas","");

    }


    public void SetStrongAreas(String StrongAreas){
        context.getSharedPreferences("StrongAreasX",Context.MODE_PRIVATE).edit().putString("StrongAreas",StrongAreas).apply();

    }
    public String GetStrongAreas(){
        return context.getSharedPreferences("StrongAreassX",Context.MODE_PRIVATE).getString("StrongAreas","");

    }




    public void SetProfile(profile item){
        context.getSharedPreferences("ProfileX",Context.MODE_PRIVATE).edit().putString("nameProfileX",item.getName()).apply();
        context.getSharedPreferences("ProfileX",Context.MODE_PRIVATE).edit().putString("emailProfileX",item.getEmail()).apply();
        context.getSharedPreferences("ProfileX",Context.MODE_PRIVATE).edit().putString("UIDProfileX",item.getUid()).apply();
        context.getSharedPreferences("ProfileX",Context.MODE_PRIVATE).edit().putString("DPURLProfileX",item.getPic()).apply();


    }


    public profile GetProfile(){
        profile item=new profile();
       item.setName( context.getSharedPreferences("ProfileX",Context.MODE_PRIVATE).getString("nameProfileX",item.getName()));
       item.setEmail( context.getSharedPreferences("ProfileX",Context.MODE_PRIVATE).getString("emailProfileX",item.getEmail()));
      item.setUid(  context.getSharedPreferences("ProfileX",Context.MODE_PRIVATE).getString("UIDProfileX",item.getUid()));
        item.setPic(context.getSharedPreferences("ProfileX",Context.MODE_PRIVATE).getString("DPURLProfileX",item.getPic()));
return item;

    }
    public void Setparentsdetais(parents item){
        context.getSharedPreferences("parentsdetaisX",Context.MODE_PRIVATE).edit().putString("nameparentsdetaisX",item.getName()).apply();
        context.getSharedPreferences("parentsdetaisX",Context.MODE_PRIVATE).edit().putString("emailparentsdetaisX",item.getEmail()).apply();

    }
    public parents Getparentsdetais() {
        parents item = new parents();
        item.setName(context.getSharedPreferences("parentsdetaisX", Context.MODE_PRIVATE).getString("nameparentsdetaisX", item.getName()));
        item.setEmail(context.getSharedPreferences("parentsdetaisX", Context.MODE_PRIVATE).getString("emailparentsdetaisX", item.getEmail()));
    return item;
    }




    }
