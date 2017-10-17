package dream.view.news_title.handle_scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 实现跟随滑动
 */
public class NewsTitleView extends ViewGroup {

    public interface Adapter {
        int getCount();
        View createView();
        void bindData(View view, int index, NewsTitleView parentView);
    }

    private Adapter adapter;

    public NewsTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        update();
    }

    public void update() {

    }
}
