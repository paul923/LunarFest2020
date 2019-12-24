package ca.acsea.funstop;

/*
A singleton class to handle a global variable: mapCode
 */
public class Globals {

    private static int mapCode = 0;
    public int getData()
    {
        return mapCode;
    }
    public void setData(int code)
    {
        this.mapCode = code;
    }
    private static Globals instance;

    public static synchronized Globals getInstance(){
        if(null == instance){
            instance = new Globals();
        }
        return instance;
    }
}