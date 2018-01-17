package app.mian.wangliwei.toolsproject.bean;


public class Dependent {
    private Immutable immutable;
    private String str;

    public Dependent(Immutable immutable) {
        this.immutable = immutable;
    }

    public Immutable getImmutable() {
        return immutable;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
