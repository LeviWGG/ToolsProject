package app.mian.wangliwei.toolsproject.bean.Component;

import app.mian.wangliwei.toolsproject.MainActivity;
import app.mian.wangliwei.toolsproject.bean.Module.MainActivityModule;
import dagger.Component;


@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
