package app.mian.wangliwei.toolsproject.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.mian.wangliwei.toolsproject.R;
import app.mian.wangliwei.toolsproject.bean.Student;
import app.mian.wangliwei.toolsproject.view.Adapter.RecyclerViewAdapter;
import app.mian.wangliwei.toolsproject.view.widget.CategoryItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {
    private View view;
    private Unbinder unbinder;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private CategoryItemDecoration categoryItemDecoration;

    @BindView(R.id.recycler_student)
    RecyclerView recyclerView;


    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(view == null) {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_recycler_view,container,false);
        }
        unbinder = ButterKnife.bind(this,view);

        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        categoryItemDecoration = new CategoryItemDecoration(Color.WHITE);
        recyclerView.addItemDecoration(categoryItemDecoration);

        adapter = new RecyclerViewAdapter(getActivity(),initData());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Student> initData() {
        List<Student> datas = new ArrayList<>();
        boolean difference = false;
        int id = 0;

        for (int i = 0; i < 50; i++) {
            if (!difference) {
                difference = true;
                id++;
                datas.add(new Student(R.mipmap.ic_launcher, "小明" + id, 90, 85, 91));
            } else {
                difference = false;
                datas.add(new Student(R.mipmap.ic_launcher, "小红" + id, 80, 90, 97));
            }
        }

        return datas;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}


