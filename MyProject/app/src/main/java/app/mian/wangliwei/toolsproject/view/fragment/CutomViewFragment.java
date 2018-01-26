package app.mian.wangliwei.toolsproject.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.mian.wangliwei.toolsproject.R;
import app.mian.wangliwei.toolsproject.view.widget.CircleBarView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CutomViewFragment extends Fragment {
    private View view;
    private Unbinder unbinder;

    @BindView(R.id.view_circlebar)
    CircleBarView circleBarView;

    @BindView(R.id.text_progress)
    TextView textProgress;

    @BindView(R.id.text_progress_title)
    TextView textProgressTitle;


    public CutomViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_custom, container, false);
        }

        unbinder = ButterKnife.bind(this,view);
        circleBarView.setProcessTime(100,5000);
        circleBarView.setTextView(textProgress);

        textProgressTitle.setTextColor(Color.DKGRAY);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
