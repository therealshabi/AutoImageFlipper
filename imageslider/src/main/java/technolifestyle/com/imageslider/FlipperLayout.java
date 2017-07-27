package technolifestyle.com.imageslider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;
import me.relex.circleindicator.CircleIndicator;


public class FlipperLayout extends RelativeLayout {

    private static ViewPager mFlippingPager;
    @Getter
    private static PagerAdapter mFlippingPagerAdapter;
    private CircleIndicator pagerIndicator;
    @Getter
    private long scrollTime = 3;

    private Timer flippingTimer;
    private TimerTask flippingTask;

    private Handler handler = new FlipperHandler();

    public FlipperLayout(Context context) {
        super(context);
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(R.layout.flipper_layout, null);
        mFlippingPager = (ViewPager) view.findViewById(R.id.vp_flipper_layout);
        pagerIndicator = (CircleIndicator) view.findViewById(R.id.pager_indicator);

        mFlippingPagerAdapter = new ImageFlippingAdapter(getContext());

        mFlippingPager.setAdapter(mFlippingPagerAdapter);
        pagerIndicator.setViewPager(mFlippingPager);
        startAutoCycle();
    }

    private static void moveNextPosition() {
        mFlippingPager.setCurrentItem(
                (mFlippingPager.getCurrentItem() + 1) % mFlippingPagerAdapter.getCount(), true);
    }

    void addSlider(ImageFlipperView flipperView) {
        ((ImageFlippingAdapter) mFlippingPagerAdapter).addFlipperView(flipperView);
    }

    public void setScrollTime(long time) {
        scrollTime = time;
        startAutoCycle();
    }


    public void startAutoCycle() {
        if (flippingTimer != null) flippingTimer.cancel();
        if (flippingTask != null) flippingTask.cancel();
        flippingTimer = new Timer();
        flippingTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        flippingTimer.schedule(flippingTask, 2000, getScrollTime());
    }

    public void stopAutoCycle() {
        if (flippingTask != null) {
            flippingTask.cancel();
        }
        if (flippingTimer != null) {
            flippingTimer.cancel();
        }
    }

    private static class FlipperHandler extends Handler {

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            moveNextPosition();
        }
    }
}
