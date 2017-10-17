package dream.news_title;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import dream.view.news_title.adjust_margin.NewsTitleView;

public class Activity2 extends AppCompatActivity {

    private int count = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        final NewsTitleView newsTitleView = (NewsTitleView) findViewById(R.id.news_title);
        newsTitleView.getIndicateView().setBackgroundColor(0xff303F9F);
        newsTitleView.setAdapter(new NewsTitleView.Adapter() {
            @Override
            public int getCount() {
                return count;
            }

            @Override
            public View createView() {
                return View.inflate(Activity2.this, R.layout.news_item, null);
            }

            @Override
            public void bindData(View view, int index, NewsTitleView parentView) {
                ((TextView)view).setText("index-" + index);
            }
        });
        newsTitleView.setSelectIndex(5);

        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsTitleView.setSelectIndex(newsTitleView.getSelectIndex() + 1);
            }
        });

        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsTitleView.setSelectIndex(newsTitleView.getSelectIndex() - 1);
            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                newsTitleView.update();
            }
        });

        findViewById(R.id.sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                newsTitleView.update();
            }
        });
    }
}
