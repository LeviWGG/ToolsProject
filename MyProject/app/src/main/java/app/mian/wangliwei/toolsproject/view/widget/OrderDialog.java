package app.mian.wangliwei.toolsproject.view.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.mian.wangliwei.toolsproject.R;
import app.mian.wangliwei.toolsproject.base.BitkerDialog;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wlw on 2018/3/22.
 */

public class OrderDialog extends BitkerDialog {
    private OnItemOnClickListener onItemOnClickListener;

    @BindView(R.id.text_dialog_title)
    TextView title;

    @BindView(R.id.text_dialog_content)
    TextView content;

    @BindView(R.id.text_dialog_left)
    TextView btnLeft;

    @BindView(R.id.text_dialog_right)
    TextView btnRight;

    @BindView(R.id.checkbox_dialog)
    CheckBox checkBox;

    @BindView(R.id.layout_button_two)
    LinearLayout layoutTwoBtn;

    @BindView(R.id.text_dialog_only_btn)
    TextView btnOnly;

    public OrderDialog(@NonNull Context context) {
        this(context, R.style.dialog);
    }

    public OrderDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected OrderDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void initView() {
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = (int)(display.getWidth() * 0.8);
        getWindow().setAttributes(lp);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_order;
    }

    @OnClick({R.id.text_dialog_only_btn,R.id.text_dialog_left,R.id.text_dialog_right})
    public void onClick(View view) {
        if (onItemOnClickListener == null)return;
        onItemOnClickListener.onItemOnClick(view,OrderDialog.this);
    }

    public OrderDialog setTitleCustom(String msg) {
        if (title.getVisibility() == View.GONE) {
            title.setVisibility(View.VISIBLE);
        }
        title.setText(msg);
        return this;
    }

    public OrderDialog setTitleCustom(int resId) {
        if (title.getVisibility() == View.GONE) {
            title.setVisibility(View.VISIBLE);
        }
        title.setText(resId);
        return this;
    }

    public OrderDialog setContent(String msg) {
        this.content.setText(msg);
        return this;
    }

    public OrderDialog setContent(int resId) {
        this.content.setText(resId);
        return this;
    }

    public OrderDialog setLeft(String msg) {
        if(layoutTwoBtn.getVisibility() == View.GONE) {
            layoutTwoBtn.setVisibility(View.VISIBLE);
            btnOnly.setVisibility(View.GONE);
        }
        btnLeft.setText(msg);
        return this;
    }

    public OrderDialog setLeft(int resId) {
        if(layoutTwoBtn.getVisibility() == View.GONE) {
            layoutTwoBtn.setVisibility(View.VISIBLE);
            btnOnly.setVisibility(View.GONE);
        }
        btnLeft.setText(resId);
        return this;
    }

    public OrderDialog setRight(String msg) {
        if(layoutTwoBtn.getVisibility() == View.GONE) {
            layoutTwoBtn.setVisibility(View.VISIBLE);
            btnOnly.setVisibility(View.GONE);
        }
        btnRight.setText(msg);
        return this;
    }

    public OrderDialog setRight(int resId) {
        if(layoutTwoBtn.getVisibility() == View.GONE) {
            layoutTwoBtn.setVisibility(View.VISIBLE);
            btnOnly.setVisibility(View.GONE);
        }
        btnRight.setText(resId);
        return this;
    }

    public OrderDialog setCheckBox(String msg) {
        if (checkBox.getVisibility() == View.GONE) {
            checkBox.setVisibility(View.VISIBLE);
        }
        checkBox.setText(msg);
        return this;
    }

    public OrderDialog setCheckBox(int resId) {
        if (checkBox.getVisibility() == View.GONE) {
            checkBox.setVisibility(View.VISIBLE);
        }
        checkBox.setText(resId);
        return this;
    }

    public OrderDialog setOnlyBtn(String msg) {
        if(btnOnly.getVisibility() == View.GONE) {
            btnOnly.setVisibility(View.VISIBLE);
            layoutTwoBtn.setVisibility(View.GONE);
        }
        btnOnly.setText(msg);
        return this;
    }

    public OrderDialog setOnlyBtn(int resId) {
        if(btnOnly.getVisibility() == View.GONE) {
            btnOnly.setVisibility(View.VISIBLE);
            layoutTwoBtn.setVisibility(View.GONE);
        }
        btnOnly.setText(resId);
        return this;
    }

    public interface OnItemOnClickListener {
        void onItemOnClick(View view, OrderDialog orderDialog);
    }

    public void setOnItemOnClickListener(OnItemOnClickListener onItemOnClickListener) {
        this.onItemOnClickListener = onItemOnClickListener;
    }
}
