package app.mian.wangliwei.toolsproject.view.Adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import app.mian.wangliwei.toolsproject.R;
import app.mian.wangliwei.toolsproject.bean.Student;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Fragment fragment;
    private Unbinder unbinder;
    private LayoutInflater mInflater;
    private List<Student> datas;
    private MyViewHolder myViewHolder;

    public RecyclerViewAdapter(Context context,Fragment fragment,List<Student> datas) {
        this.context = context;
        this.fragment = fragment;
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
        myViewHolder.student = student;
        myViewHolder.name.setText(student.getName());
        Glide.with(fragment).load(student.getUrl()).into(myViewHolder.photo);
        myViewHolder.total.setText(" 总分："+student.getTotal());
        myViewHolder.itemView.setOnClickListener(onClickListener);
        myViewHolder.itemView.setTag(myViewHolder);

    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Student student;

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

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MyViewHolder myViewHolder = (MyViewHolder)view.getTag();
            Toast.makeText(view.getContext(),myViewHolder.student.getName()+"  数学:"+myViewHolder.student.getMath()
                    +"  语文:"+myViewHolder.student.getChinese()+"  英语:"
                    +myViewHolder.student.getEnglish(),Toast.LENGTH_SHORT).show();
        }
    };
}
