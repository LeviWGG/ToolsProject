package app.mian.wangliwei.myproject.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.mian.wangliwei.myproject.R;
import app.mian.wangliwei.myproject.presenter.IBookPresenter;
import app.mian.wangliwei.myproject.presenter.BookPresenterImp;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        unbinder = ButterKnife.bind(this);

        iBookPresenter = new BookPresenterImp(this);
    }

    @OnClick(R.id.btn_search)
    public void searchBook() {
        iBookPresenter.getSearchBook("三国演义",null,1,0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("book","book destroy");
        unbinder.unbind();
    }

    @Override
    public void setBookText(String text) {
        btnSearch.setVisibility(View.GONE);
        bookName.setText(text);
    }
}
