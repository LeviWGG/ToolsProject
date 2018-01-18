package app.mian.wangliwei.toolsproject.bean.Component;


import app.mian.wangliwei.toolsproject.bean.Module.ListViewFragmentModule;
import app.mian.wangliwei.toolsproject.view.fragment.ListViewFragment;
import dagger.Component;

@Component(modules = ListViewFragmentModule.class)
public interface ListViewFragmentComponent {
    void inject(ListViewFragment listViewFragment);
}
