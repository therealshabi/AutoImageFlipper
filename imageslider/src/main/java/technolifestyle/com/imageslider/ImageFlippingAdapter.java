package technolifestyle.com.imageslider;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;


public class ImageFlippingAdapter extends PagerAdapter {

    private Context context;

    public ImageFlippingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
