package technolifestyle.com.imageslider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import me.relex.circleindicator.CircleIndicator;


public class FlipperLayout extends RelativeLayout {

    private ViewPager mFlippingPager;

    private CircleIndicator pagerIndicator;

    private PagerAdapter mFlippingPagerAdapter;
    
    public FlipperLayout(Context context) {
        super(context);
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(R.layout.flipper_layout, null);
        mFlippingPager = (ViewPager) view.findViewById(R.id.vp_flipper_layout);
        pagerIndicator = (CircleIndicator) view.findViewById(R.id.pager_indicator);

        mFlippingPagerAdapter = new ImageFlippingAdapter(getContext());

        mFlippingPager.setAdapter(mFlippingPagerAdapter);
        pagerIndicator.setViewPager(mFlippingPager);
    }

    public void startFlipping() {

    }


}
