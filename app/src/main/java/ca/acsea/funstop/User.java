package ca.acsea.funstop;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

@IgnoreExtraProperties
public class User {

    public String uid;
    public String email;
    public long point;
    public long quizCutoff;
    public HashMap<String, Boolean> vag;
    public HashMap<String, Boolean> oakridge;
    public HashMap<String, Boolean> jackpool;
    public HashMap<String, Boolean> lot10;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email) {
        init();
        this.email = email;
    }

    @Exclude
    public HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("email", email);
        result.put("point", point);
        result.put("quizCutoff", quizCutoff);
        result.put("vag", vag);
        result.put("oakridge", oakridge);
        result.put("jackpool", jackpool);
        result.put("lot10", lot10);

        return result;
    }

    public void init(){
        point = 0;
        quizCutoff = 0;
        initVag();
        initOak();
        initJack();
        initLot();
    }

    public void initVag(){
        vag = new HashMap<>();
        vag.put("station1", false);
        vag.put("station2", false);
        vag.put("station3", false);
        vag.put("station4", false);
        vag.put("station5", false);
        vag.put("station6", false);
        vag.put("station7", false);
        vag.put("station8", false);
        vag.put("station9", false);
        vag.put("station10", false);
        vag.put("station11", false);
        vag.put("station12", false);
    }

    public void initOak(){
        oakridge = new HashMap<>();
        oakridge.put("korean", false);
        oakridge.put("taiwanese", false);
        oakridge.put("chinese", false);
        oakridge.put("vietnamese", false);
    }

    public void initJack(){
        jackpool = new HashMap<>();
        jackpool.put("salishSea1", false);
        jackpool.put("salishSea2", false);
        jackpool.put("loneWolf1", false);
        jackpool.put("loneWolf2", false);
        jackpool.put("redFawn1", false);
        jackpool.put("redFawn2", false);
        jackpool.put("protector1", false);
        jackpool.put("protector2", false);
    }

    public void initLot(){
        lot10 = new HashMap<>();
        lot10.put("ladyHao", false);
    }
}