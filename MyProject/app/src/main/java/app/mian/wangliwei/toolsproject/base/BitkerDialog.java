package app.mian.wangliwei.toolsproject.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wlw on 2018/3/17.
 */

public abstract class BitkerDialog extends Dialog {
    private Context mContext;
    private Unbinder unbinder;

    public BitkerDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public BitkerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    protected BitkerDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext,getLayoutId(),null);
        setContentView(view);

        unbinder = ButterKnife.bind(this,view);
        initView();
    }

    public abstract void initView();

    public abstract int getLayoutId();
}
