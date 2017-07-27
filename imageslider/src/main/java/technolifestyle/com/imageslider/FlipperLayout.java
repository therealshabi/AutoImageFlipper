package technolifestyle.com.imageslider;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;
import me.relex.circleindicator.CircleIndicator;


public class FlipperLayout extends LinearLayout {

    private static final long DELAY_MS = 500;
    @Getter
    private static PagerAdapter mFlippingPagerAdapter;
    int currentPage = 0;
    private ViewPager mFlippingPager;
    private CircleIndicator pagerIndicator;
    @Getter
    private long scrollTime = 3;

    private Handler handler = new Handler();

    public FlipperLayout(Context context) {
        super(context);
        setLayout(context);
    }

    public FlipperLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayout(context);
    }

    public FlipperLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayout(context);
    }

    private void setLayout(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.flipper_layout, this, true);
        mFlippingPager = (ViewPager) view.findViewById(R.id.vp_flipper_layout);
        pagerIndicator = (CircleIndicator) view.findViewById(R.id.pager_indicator);

        mFlippingPagerAdapter = new ImageFlippingAdapter(context);

        mFlippingPager.setAdapter(mFlippingPagerAdapter);
        startAutoCycle();
    }

    private void moveNextPosition() {
        mFlippingPager.setCurrentItem(
                (mFlippingPager.getCurrentItem() + 1), true);
    }

    public void addSlider(ImageFlipperView flipperView) {
        ((ImageFlippingAdapter) mFlippingPagerAdapter).addFlipperView(flipperView);
        pagerIndicator.setViewPager(mFlippingPager);
    }

    public void setScrollTime(long time) {
        scrollTime = time;
        startAutoCycle();
    }

    public void startAutoCycle() {
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == getMFlippingPagerAdapter().getCount()) {
                    currentPage = 0;
                }
                mFlippingPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer flippingTimer = new Timer();
        flippingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, scrollTime * 1000);
    }
}
