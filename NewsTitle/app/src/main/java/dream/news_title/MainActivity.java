package dream.news_title;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * 3. 实现跟随滑动
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ListView) findViewById(R.id.list)).setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, Activity1.class));
                break;
            case 1:
                startActivity(new Intent(this, Activity2.class));
                break;
            case 2:
                startActivity(new Intent(this, Activity3.class));
                break;
            case 3:
                startActivity(new Intent(this, Activity4.class));
                break;
        }
    }
}
