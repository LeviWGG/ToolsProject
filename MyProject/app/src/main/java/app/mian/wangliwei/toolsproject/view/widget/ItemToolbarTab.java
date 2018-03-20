package app.mian.wangliwei.toolsproject.view.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.mian.wangliwei.toolsproject.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wlw on 2018/3/20.
 */

public class ItemToolbarTab {
    private int image;
    private int text;
    private Unbinder unbinder;
    private View view;

    @BindView(R.id.image_toolbar_tab)
    ImageView imageView;

    @BindView(R.id.text_toolbar_tab)
    TextView textView;

    public ItemToolbarTab(Context context, int image, int text) {
        view = LayoutInflater.from(context).inflate(R.layout.item_toolbar_tab,null);
        unbinder = ButterKnife.bind(this,view);
        this.image = image;
        this.text = text;
    }

    public View getView() {
        imageView.setImageResource(image);
        textView.setText(text);

        return view;
    }

    public void setPressState(int image,int color) {
        imageView.setImageResource(image);
        textView.setTextColor(color);
    }

    public void setNormalState(int image,int color) {
        imageView.setImageResource(image);
        textView.setTextColor(color);
    }
}
