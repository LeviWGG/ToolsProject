package app.mian.wangliwei.toolsproject.bean.Component;

import app.mian.wangliwei.toolsproject.MainActivity;
import dagger.Component;


@Component
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
