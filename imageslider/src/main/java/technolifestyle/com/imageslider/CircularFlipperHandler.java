package technolifestyle.com.imageslider;

import android.support.v4.view.ViewPager;

class CircularFlipperHandler implements ViewPager.OnPageChangeListener, FlipperLayout.CurrentPageListener {
    private ViewPager   mViewPager;
    private int         mCurrentPosition;
    private int         mScrollState;

    private FlipperLayout flipperLayout;

    CircularFlipperHandler(final ViewPager viewPager) {
        mViewPager = viewPager;
    }

    @Override
    public void onPageSelected(final int position) {
        mCurrentPosition = position;
        onCurrentPageChanged(mCurrentPosition);
    }

    @Override
    public void onPageScrollStateChanged(final int state) {
        handleScrollState(state);
        mScrollState = state;
    }

    private void handleScrollState(final int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            setNextItemIfNeeded();
        }
    }

    private void setNextItemIfNeeded() {
        if (!isScrollStateSettling()) {
            handleSetNextItem();
        }
    }

    private boolean isScrollStateSettling() {
        return mScrollState == ViewPager.SCROLL_STATE_SETTLING;
    }

    private void handleSetNextItem() {
        final int lastPosition = mViewPager.getAdapter().getCount() - 1;
        if(mCurrentPosition == 0) {
            mViewPager.setCurrentItem(lastPosition, false);
        } else if(mCurrentPosition == lastPosition) {
            mViewPager.setCurrentItem(0, false);
        }
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
    }

    @Override
    public int onCurrentPageChanged(int currentPosition) {
        flipperLayout.setCurrentPage(currentPosition);
        return currentPosition;
    }

    @Override
    public void setOnCurrentPageListener(FlipperLayout flipperLayout) {
        this.flipperLayout = flipperLayout;
    }
}
