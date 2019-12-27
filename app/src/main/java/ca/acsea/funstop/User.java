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
    public int index;
    public long quizCutoff;
    public HashMap<String, Boolean> vag;
    public HashMap<String, Boolean> oakridge;
    public HashMap<String, Boolean> jackpool;

    public HashMap<String, Boolean> Toronto1;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String uid) {
        init();
        this.index = 0;
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
        result.put("Toronto1", Toronto1);

        return result;
    }

    public void init(){
        this.point = 0;
        this.quizCutoff = 0;
        initVag();
        initOak();
        initJack();

        initToronto1();
    }

    public void initVag(){
        this.vag = new HashMap<>();
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
        this.oakridge = new HashMap<>();
        oakridge.put("korean", false);
        oakridge.put("taiwanese", false);
        oakridge.put("chinese", false);
        oakridge.put("vietnamese", false);
    }

    public void initJack(){
        this.jackpool = new HashMap<>();
        jackpool.put("salishSea1", false);
        jackpool.put("salishSea2", false);
        jackpool.put("loneWolf1", false);
        jackpool.put("loneWolf2", false);
        jackpool.put("redFawn1", false);
        jackpool.put("redFawn2", false);
        jackpool.put("protector1", false);
        jackpool.put("protector2", false);
    }

    public void initToronto1() {
        this.Toronto1 = new HashMap<>();
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

    public HashMap<String, Boolean> getToronto1() {
        return Toronto1;
    }

    public void setToronto1 (HashMap<String, Boolean> Toronto1) {
        this.Toronto1 = Toronto1;
    }
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void increaseIndex(){
        ++this.index;
    }
}