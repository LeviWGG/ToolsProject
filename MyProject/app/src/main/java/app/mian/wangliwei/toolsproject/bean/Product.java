package app.mian.wangliwei.toolsproject.bean;


import javax.inject.Inject;

public class Product {
    private String str;
    @Inject
    public Product() {
        this.str = "This is from Dagger";
    }

    public String getStr() {
        return str;
    }
}
