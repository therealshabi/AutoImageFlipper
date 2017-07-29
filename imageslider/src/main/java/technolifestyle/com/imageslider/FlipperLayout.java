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

import me.relex.circleindicator.CircleIndicator;

/*
* FlipperLayout is a compound layout which consists of a View Pager and a View Pager Indicator
 */
public class FlipperLayout extends FrameLayout implements CircularFlipperHandler.CurrentPageListener {

    /*
    *  Delay for Timer Task
     */
    private static final long DELAY_MS = 500;

    /*
    *   Flipping Pager Adapter
     */
    private static PagerAdapter mFlippingPagerAdapter;
    /*
        * Used for auto cycling to keep the count of current page
         */
    int currentPage = 0;
    CircularFlipperHandler circularFlipperHandler;
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
    private int scrollTimeInSec = 2;
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
    * Getter for FlippingPagerAdapter
     */
    public static PagerAdapter getFlippingPagerAdapter() {
        return mFlippingPagerAdapter;
    }

    /*
    *   Getter for ScrollTime
     */
    public int getScrollTimeInSec() {
        return scrollTimeInSec;
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
    * This returns the current page position of view pager
     */
    public int getCurrentPagePosition() {
        if (getFlippingPagerAdapter() != null) {
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

        circularFlipperHandler = new CircularFlipperHandler(mFlippingPager);
        circularFlipperHandler.setCurrentPageListener(this);

        mFlippingPager.addOnPageChangeListener(circularFlipperHandler);

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
    * Method to start Auto Cycle using Handler, Runnable and Timer
     */
    public void startAutoCycle() {
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == getFlippingPagerAdapter().getCount()) {
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

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public void onCurrentPageChanged(int currentPosition) {
        this.currentPage = currentPosition;
    }
}
