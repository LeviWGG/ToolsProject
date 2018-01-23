package app.mian.wangliwei.toolsproject.presenter;


public class RecyclerViewPresenterImp implements IRecyclerViewPresenter {

    public RecyclerViewPresenterImp(){}

    @Override
    public int getClass(int pos) {
        //view标志的获取方法
        return pos/10;
    }
}
