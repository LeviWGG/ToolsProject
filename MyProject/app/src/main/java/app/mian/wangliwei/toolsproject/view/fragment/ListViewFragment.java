package app.mian.wangliwei.toolsproject.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.mian.wangliwei.toolsproject.R;
import app.mian.wangliwei.toolsproject.bean.Component.DaggerListViewFragmentComponent;
import app.mian.wangliwei.toolsproject.bean.Module.ListViewFragmentModule;
import app.mian.wangliwei.toolsproject.bean.Student;
import app.mian.wangliwei.toolsproject.view.Adapter.StudentAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {
    private View view;
    private List<Student> list = new ArrayList<>();
    private Unbinder unBinder;

    @Inject
    Student stuA;

    @Inject
    Student stuB;

    @Inject
    Student stuC;

    @Inject
    Student stuD;

    @BindView(R.id.list_student)
    ListView listView;


    public ListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            // Inflate the layout for this fragment
            view =  inflater.inflate(R.layout.fragment_listview, container, false);
        }
        unBinder = ButterKnife.bind(this,view);

        DaggerListViewFragmentComponent.builder().listViewFragmentModule(new ListViewFragmentModule())
                .build().inject(this);

        initStudent();

        StudentAdapter studentAdapter = new StudentAdapter(getActivity(),R.layout.student_item,list);
        listView.setAdapter(studentAdapter);

        return view;
    }

    private void initStudent() {
//        Student stuA = new Student(R.mipmap.ic_launcher,"小明",90,85,91);
//        Student stuB = new Student(R.mipmap.ic_launcher,"小红",80,90,97);

        list.add(stuA);
        list.add(stuB);
        list.add(stuC);
        list.add(stuD);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }
}
