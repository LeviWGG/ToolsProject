package app.mian.wangliwei.toolsproject.bean.Module;


import app.mian.wangliwei.toolsproject.R;
import app.mian.wangliwei.toolsproject.bean.Student;
import dagger.Module;
import dagger.Provides;

@Module
public class ListViewFragmentModule {
    private boolean difference = false;

    public ListViewFragmentModule() {}

    @Provides
    public Student providerStudent() {
        if (!difference) {
            this.difference = true;
            return new Student(R.mipmap.ic_launcher,"小明",90,85,91);
        }
        this.difference = false;
        return new Student(R.mipmap.ic_launcher,"小红",80,90,97);
    }

}
