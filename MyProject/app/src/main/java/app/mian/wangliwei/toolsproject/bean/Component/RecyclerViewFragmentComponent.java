package app.mian.wangliwei.toolsproject.bean.Component;


import app.mian.wangliwei.toolsproject.bean.Module.ListViewFragmentModule;
import app.mian.wangliwei.toolsproject.view.fragment.RecyclerViewFragment;
import dagger.Component;

@Component(modules = ListViewFragmentModule.class)
public interface RecyclerViewFragmentComponent {
    void inject(RecyclerViewFragment recyclerViewFragment);
}
