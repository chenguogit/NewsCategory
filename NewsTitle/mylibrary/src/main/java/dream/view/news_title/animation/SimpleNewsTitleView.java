package dream.view.news_title.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dream.view.R;

public class SimpleNewsTitleView extends NewsTitleView implements NewsTitleView.Adapter {

    public interface OnItemSelectedListener {
        void onItemSelected(int index);
    }

    private List<String> data = new ArrayList<>();
    private LayoutInflater inflater;
    private OnItemSelectedListener listener;

    public SimpleNewsTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        setAdapter(this);
        ImageView view = getIndicateView();
        view.setBackgroundColor(0xff303F9F);
    }

    public void setData(List<String> data) {
        this.data = data;
        update();
    }

    public void setListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View createView() {
        return inflater.inflate(R.layout.news_title_item, null);
    }

    @Override
    public void bindData(View view, final int index, NewsTitleView parentView) {
        ((TextView) view).setText(data.get(index));
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectIndex(index);
                if (listener != null) {
                    listener.onItemSelected(index);
                }
            }
        });
    }
}
