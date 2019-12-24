package ca.acsea.funstop;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;

@IgnoreExtraProperties
public class User implements Serializable {

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

    public User(String email, String uid) {
        init();
        this.email = email;
        this.uid = uid;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public long getQuizCutoff() {
        return quizCutoff;
    }

    public void setQuizCutoff(long quizCutoff) {
        this.quizCutoff = quizCutoff;
    }

    public HashMap<String, Boolean> getVag() {
        return vag;
    }

    public void setVag(HashMap<String, Boolean> vag) {
        this.vag = vag;
    }

    public HashMap<String, Boolean> getOakridge() {
        return oakridge;
    }

    public void setOakridge(HashMap<String, Boolean> oakridge) {
        this.oakridge = oakridge;
    }

    public HashMap<String, Boolean> getJackpool() {
        return jackpool;
    }

    public void setJackpool(HashMap<String, Boolean> jackpool) {
        this.jackpool = jackpool;
    }

    public HashMap<String, Boolean> getLot10() {
        return lot10;
    }

    public void setLot10(HashMap<String, Boolean> lot10) {
        this.lot10 = lot10;
    }
}