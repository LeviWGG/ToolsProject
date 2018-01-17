package app.mian.wangliwei.toolsproject.bean.Module;


import app.mian.wangliwei.toolsproject.bean.Dependent;
import app.mian.wangliwei.toolsproject.bean.Immutable;
import dagger.Module;
import dagger.Provides;


@Module
public class MainActivityModule {
    private String str;

    public MainActivityModule(String str) {
        this.str = str;
    }

    @Provides
    Immutable providerImmutable() {
        return new Immutable(str);
    }

    @Provides
    Dependent providerDependent(Immutable immutable) {
        Dependent dependent = new Dependent(immutable);
        dependent.setStr(immutable.getStr());
        return dependent;
    }

}
