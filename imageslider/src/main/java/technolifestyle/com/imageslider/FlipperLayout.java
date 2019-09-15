package technolifestyle.com.imageslider;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * FlipperLayout is a compound layout which consists of a View Pager and a View Pager Indicator
 */
public class FlipperLayout extends FrameLayout implements
        CircularFlipperHandler.CurrentPageListener {

    private View flipperLayout;

    /**
     * Delay for Timer Task
     */
    private static final long DELAY_MS = 500;

    /**
     * Flipping Pager Adapter
     */
    private static FlipperAdapter mFlippingPagerAdapter;

    /**
     * Used for auto cycling to keep the count of current page
     */
    int currentPage = 0;

    CircularFlipperHandler circularFlipperHandler;

    /**
     * Flipping View Pager
     */
    private ViewPager mFlippingPager;

    /**
     * Runnable to update the current page
     */
    private final Runnable update = new Runnable() {
        public void run() {
            if (currentPage == getFlippingPagerAdapter().getCount()) {
                currentPage = 0;
            }
            // true set for smooth transition between pager
            mFlippingPager.setCurrentItem(currentPage, true);
            currentPage += 1;
        }
    };

    /**
     * CircleIndicator implementation which Indicates the View Pager
     */
    private TabLayout circularPagerIndicator;
    /*
     * Scroll Time in seconds
     */
    private int scrollTimeInSec = 3;

    /**
     * Handler for handling auto cycle
     */
    private Handler handler = new Handler();

    /**
     * Scheduler for auto-flipping
     */
    private Timer flippingTimer;

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

    /**
     * Getter for FlippingPagerAdapter
     */
    private static PagerAdapter getFlippingPagerAdapter() {
        return mFlippingPagerAdapter;
    }

    /**
     * Getter for ScrollTime
     */
    public int getScrollTimeInSec() {
        return scrollTimeInSec;
    }

    /**
     * Setting up Scrolling Time for a page
     *
     * @param time in second is sent
     */
    public void setScrollTimeInSec(int time) {
        scrollTimeInSec = time;
        startAutoCycle();
    }

    /**
     * @param width {int} width of circularPagerIndicator
     */
    public void setCircleIndicatorWidth(int width) {
        ViewGroup.LayoutParams params = circularPagerIndicator.getLayoutParams();
        params.width = width;
        circularPagerIndicator.setLayoutParams(params);
    }

    /**
     * @param height {int} height of circularPagerIndicator
     */
    public void setCircleIndicatorHeight(int height) {
        ViewGroup.LayoutParams params = circularPagerIndicator.getLayoutParams();
        params.height = height;
        circularPagerIndicator.setLayoutParams(params);
    }

    /**
     * @param width  {int} width of circularPagerIndicator
     * @param height {int} height of circularPagerIndicator
     */
    public void setCircularIndicatorLayoutParams(int width, int height) {
        ViewGroup.LayoutParams params = circularPagerIndicator.getLayoutParams();
        params.height = height;
        params.width = width;
        circularPagerIndicator.setLayoutParams(params);
    }

    /**
     * Method to remove circlePagerIndicator from viewFlipper
     */
    public void removeCircleIndicator() {
        circularPagerIndicator.removeAllTabs();
    }

    /**
     * Method to show circlePagerIndicator for viewFlipper
     */
    public void showCircleIndicator() {
        circularPagerIndicator.setupWithViewPager(mFlippingPager, true);
    }

    /**
     * This returns the current page position of view pager
     */
    public int getCurrentPagePosition() {
        if (getFlippingPagerAdapter() != null) {
            return mFlippingPager.getCurrentItem() % mFlippingPagerAdapter.getCount();
        } else {
            throw new NullPointerException("Adapter not set");
        }
    }

    /**
     * Use for setting up of FlipperLayout, instantiating view pager, pager indicator
     * and binding the adapter with the view pager
     *
     * @param context for Inflater
     */
    private void setLayout(Context context) {
        flipperLayout = LayoutInflater.from(context).inflate(
                R.layout.flipper_layout, this, true);
        mFlippingPager = flipperLayout.findViewById(R.id.vp_flipper_layout);
        circularPagerIndicator = flipperLayout.findViewById(R.id.tabLayout);

        mFlippingPagerAdapter = new FlipperAdapter(context);
        mFlippingPager.setAdapter(mFlippingPagerAdapter);
        circularPagerIndicator.setupWithViewPager(mFlippingPager, true);

        // Handler for onPageChangeListener
        circularFlipperHandler = new CircularFlipperHandler(mFlippingPager);
        circularFlipperHandler.setCurrentPageListener(this);
        mFlippingPager.addOnPageChangeListener(circularFlipperHandler);

        //Starting auto cycle at the time of setting up of layout
        startAutoCycle();
    }

    /**
     * Add Flipper View to the pager adapter
     *
     * @param flipperView is sent as the view to be added to the adapter
     */
    public void addFlipperView(FlipperView flipperView) {
        flipperView.setViewHeight(flipperLayout.getLayoutParams().height);
        mFlippingPagerAdapter.addFlipperView(flipperView);
//        pagerIndicator.setViewPager(mFlippingPager);
    }

    /**
     * Method to start Auto Cycle using Handler, Runnable and Timer
     */
    private void startAutoCycle() {
        if (flippingTimer != null) {
            flippingTimer.cancel();
            flippingTimer.purge();
        }
        flippingTimer = new Timer();
        flippingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, scrollTimeInSec * 1000);
    }

    @Override
    public void onCurrentPageChanged(int currentPosition) {
        this.currentPage = currentPosition;
    }


    /**
     * @param reverseDrawingOrder true if the supplied PageTransformer requires page views
     *                            to be drawn from last to first instead of first to last.
     * @param transformer         PageTransformer that will modify each page's animation properties
     * @link {https://developer.android.com/training/animation/screen-slide.html#kotlin}
     * Method to add Page transformer into the Flipper layout
     */
    public void addPageTransformer(boolean reverseDrawingOrder,
                                   @Nullable ViewPager.PageTransformer transformer) {
        mFlippingPager.setPageTransformer(reverseDrawingOrder, transformer);
    }
}
