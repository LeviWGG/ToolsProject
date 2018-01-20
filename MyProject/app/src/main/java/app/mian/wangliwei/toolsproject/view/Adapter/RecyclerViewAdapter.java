package app.mian.wangliwei.toolsproject.view.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.mian.wangliwei.toolsproject.R;
import app.mian.wangliwei.toolsproject.bean.Student;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Unbinder unbinder;
    private LayoutInflater mInflater;
    private List<Student> datas;
    private MyViewHolder myViewHolder;

    public RecyclerViewAdapter(Context context,List<Student> datas) {
        this.context = context;
        this.datas = datas;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.student_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        myViewHolder = (MyViewHolder) holder;
        Student student = datas.get(position);
        myViewHolder.name.setText(student.getName());
        myViewHolder.photo.setImageResource(student.getPhotoId());
        myViewHolder.total.setText(" 总分："+student.getTotal());

    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.photo)
        ImageView photo;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.total)
        TextView total;

        public MyViewHolder(View view) {
            super(view);
            unbinder = ButterKnife.bind(this,view);
        }
    }
}
