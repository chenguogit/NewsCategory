package dream.news_title;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dream.view.news_title.animation.SimpleNewsTitleView;

public class Activity3 extends AppCompatActivity {

    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout3);

        final SimpleNewsTitleView newsTitleView = (SimpleNewsTitleView) findViewById(R.id.news_title);
        newsTitleView.setData(data);
        newsTitleView.setListener(new SimpleNewsTitleView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                Toast.makeText(Activity3.this, "index=" + index, Toast.LENGTH_SHORT).show();
            }
        });

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
                data.add("index-" + data.size());
                newsTitleView.update();
            }
        });

        findViewById(R.id.sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size() > 0) {
                    data.remove(0);
                }
                newsTitleView.update();
            }
        });
    }
}
