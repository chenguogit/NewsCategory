package dream.view.news_title.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 利用控件的卷动和动画
 * 没有跟随滑动的功能
 */
public class NewsTitleView extends HorizontalScrollView {

    public interface Adapter {
        int getCount();

        View createView();

        void bindData(View view, int index, NewsTitleView parentView);
    }

    private Adapter adapter;
    private LinearLayout containerView;
    private ImageView indicateView;
    private int selectIndex;
    private boolean isLayout;

    public NewsTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setHorizontalScrollBarEnabled(false);
        setFillViewport(true);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams lp1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        addViewInLayout(linearLayout, 0, lp1);

        containerView = new LinearLayout(context);
        containerView.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 0);
        lp2.weight = 1;
        linearLayout.addView(containerView, lp2);

        indicateView = new ImageView(context);
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(0, (int) (3 * getResources().getDisplayMetrics().density));
        linearLayout.addView(indicateView, lp3);
    }

    public LinearLayout getContainerView() {
        return containerView;
    }

    public ImageView getIndicateView() {
        return indicateView;
    }

    public void setSelectIndex(int index) {
        if (isLayout) {
            layoutIndicateView(index);
        }
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        update();
    }

    public void update() {
        if (adapter == null) {
            return;
        }

        int needCount = adapter.getCount();
        if (needCount < 1) {
            return;
        }
        int currentCount = containerView.getChildCount();
        for (int i = 0; i < needCount; i++) {
            View view;
            if (i < currentCount) {
                view = containerView.getChildAt(i);
            } else {
                view = adapter.createView();
                containerView.addView(view, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
            }
            adapter.bindData(view, i, this);
        }
        for (int i = currentCount - 1; i >= needCount; i--) {
            containerView.removeViewAt(i);
        }

        if (isLayout) {
            layoutIndicateView(selectIndex);
        }
    }

    private void layoutIndicateView(int index) {
        int count = containerView.getChildCount();
        if (count < 1) {
            return;
        }
        if (index < 0) {
            index = 0;
        } else if (index >= count) {
            index = count - 1;
        }
        int left = containerView.getChildAt(index).getLeft();
        int right = containerView.getChildAt(index).getRight();

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) indicateView.getLayoutParams();
        int width = right - left;
        if (lp.width != width) {
            lp.width = width;
            indicateView.requestLayout();
        }

        final int offset = (right + left) / 2 - (getWidth() / 2);
        smoothScrollTo(offset, 0);

        if (index != selectIndex) {
            int startLeft;
            if (selectIndex >= count) {
                startLeft = containerView.getChildAt(count - 1).getRight();
            } else {
                startLeft = containerView.getChildAt(selectIndex).getLeft();
                containerView.getChildAt(selectIndex).setSelected(false);
            }
            TranslateAnimation animation = new TranslateAnimation(startLeft, left, 0f, 0f);
            animation.setFillBefore(true);
            animation.setFillAfter(true);
            animation.setDuration(300);
            indicateView.startAnimation(animation);

            selectIndex = index;
        }
        containerView.getChildAt(index).setSelected(true);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!isLayout) {
            isLayout = true;
            layoutIndicateView(selectIndex);
        }
    }
}
