package app.mian.wangliwei.toolsproject.view.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.mian.wangliwei.toolsproject.R;
import app.mian.wangliwei.toolsproject.bean.Student;

public class StudentAdapter extends ArrayAdapter<Student> {
    private int resourceId;

    public StudentAdapter(@NonNull Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Student student = getItem(position);
        View view;
        ViewHolder viewHolder;

        if(convertView == null) {

            view = LayoutInflater.from(getContext()).inflate(resourceId,null);

            viewHolder = new ViewHolder();
            viewHolder.student = student;
            viewHolder.photo = (ImageView)view.findViewById(R.id.photo);
            viewHolder.name = (TextView)view.findViewById(R.id.name);
            viewHolder.total = (TextView)view.findViewById(R.id.total);
            view.setTag(viewHolder);
        }else {
            view = convertView;

            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.photo.setImageResource(student.getPhotoId());
        viewHolder.name.setText(student.getName());
        viewHolder.total.setText("  总分：" + student.getTotal());
        view.setOnClickListener(onClickListener);

        return view;
    }

    class ViewHolder {
        Student student;
        ImageView photo;
        TextView name;
        TextView total;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ViewHolder viewHolder = (ViewHolder)view.getTag();
            Toast.makeText(view.getContext(),"数学:"+viewHolder.student.getMath()
                    +"  语文:"+viewHolder.student.getChinese()+"  英语:"
                    +viewHolder.student.getEnglish(),Toast.LENGTH_SHORT).show();
        }
    };
}
