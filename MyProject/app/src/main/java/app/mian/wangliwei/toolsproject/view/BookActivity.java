package app.mian.wangliwei.toolsproject.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import app.mian.wangliwei.toolsproject.R;
import app.mian.wangliwei.toolsproject.presenter.IBookPresenter;
import app.mian.wangliwei.toolsproject.presenter.BookPresenterImp;
import app.mian.wangliwei.toolsproject.utils.MessageEvent;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BookActivity extends AppCompatActivity implements IBookView {
    Unbinder unbinder;

    IBookPresenter iBookPresenter;

    @BindView(R.id.book_name)
    TextView bookName;

    @BindView(R.id.btn_search)
    Button btnSearch;

    @BindView(R.id.btn_post)
    Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        unbinder = ButterKnife.bind(this);

        iBookPresenter = new BookPresenterImp(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick(R.id.btn_search)
    public void searchBook() {
        iBookPresenter.getSearchBook("三国演义",null,1,0);
    }

    @OnClick(R.id.btn_post)
    public void eventPost() {
        EventBus.getDefault().post(new MessageEvent());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("book","book destroy");
        unbinder.unbind();

        /**
         * 离开页面时取消订阅，释放Avtivity引用
         */
        iBookPresenter.onDestroy();
    }

    @Override
    public void setBookText(String text) {
        btnSearch.setVisibility(View.GONE);
        bookName.setText(text);
    }
}
