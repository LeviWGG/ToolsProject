package app.mian.wangliwei.toolsproject.bean;


import javax.inject.Inject;

public class Factory {
    private Product product;

    @Inject
    public Factory (Product product){
        this.product = product;
    }

    public Product getProduct(){
        return this.product;
    }

}
