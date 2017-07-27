package technolifestyle.com.imageslider;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;
import me.relex.circleindicator.CircleIndicator;

/*
* FlipperLayout is a compound layout which consists of a View Pager and a View Pager Indicator
 */
public class FlipperLayout extends FrameLayout {

    /*
    *  Delay for Timer Task
     */
    private static final long DELAY_MS = 500;
    /*
    *   Flipping Pager Adapter
     */
    @Getter
    private static PagerAdapter mFlippingPagerAdapter;
    /*
    * Used for auto cycling to keep the count of current page
     */
    int currentPage = 0;
    /*
    * Flipping View Pager
     */
    private ViewPager mFlippingPager;
    /*
    * CircleIndicator which Indicates the View Pager
     */
    private CircleIndicator pagerIndicator;
    /*
    * Scroll Time in seconds
     */
    @Getter
    private int scrollTimeInSec = 3;
    /*
    * Handler for handling auto cycle
     */
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

    /*
    * This returns the current page position of view pager
     */
    public int getCurrentPagePosition() {
        if (getMFlippingPagerAdapter() != null) {
            return mFlippingPager.getCurrentItem() % mFlippingPagerAdapter.getCount();
        } else {
            throw new NullPointerException("Adapter not set");
        }
    }

    /*
    * Use for setting up of FlipperLayout, instantiating view pager, pager indicator
    * and binding the adapter with the view pager
    * @params context for Inflater
     */
    private void setLayout(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.flipper_layout, this, true);
        mFlippingPager = (ViewPager) view.findViewById(R.id.vp_flipper_layout);
        pagerIndicator = (CircleIndicator) view.findViewById(R.id.pager_indicator);

        mFlippingPagerAdapter = new FlipperAdapter(context);

        mFlippingPager.setAdapter(mFlippingPagerAdapter);

        //Starting auto cycle at the time of setting up of layout
        startAutoCycle();
    }

    /*
    * Add Flipper View to the pager adapter
    * @params flipperView is sent as the view to be added to the adapter
     */
    public void addFlipperView(FlipperView flipperView) {
        ((FlipperAdapter) mFlippingPagerAdapter).addFlipperView(flipperView);
        pagerIndicator.setViewPager(mFlippingPager);
    }

    /*
    * Setting up Scrolling Time for a page
    * @params time in second is sent
     */
    public void setScrollTimeInSec(int time) {
        scrollTimeInSec = time;
        startAutoCycle();
    }

    /*
    * Method to start Auto Cycle using Handler, Runnable and Timer
     */
    public void startAutoCycle() {
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == getMFlippingPagerAdapter().getCount()) {
                    currentPage = 0;
                }
                // true set for smooth transition between pager
                mFlippingPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer flippingTimer = new Timer();
        flippingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, scrollTimeInSec * 1000);
    }
}
