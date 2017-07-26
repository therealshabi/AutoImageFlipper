package technolifestyle.com.imageslider;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;


public class AutoFlippingPager extends ViewPager {

    public AutoFlippingPager(Context context) {
        super(context);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
    }
}
