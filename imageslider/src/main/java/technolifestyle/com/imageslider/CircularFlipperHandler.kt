package technolifestyle.com.imageslider

import androidx.viewpager.widget.ViewPager

internal class CircularFlipperHandler(private val mViewPager: ViewPager) : ViewPager.OnPageChangeListener {
    private var mCurrentPosition: Int = 0

    private var currentPageListener: CurrentPageListener? = null

    fun setCurrentPageListener(currentPageListener: CurrentPageListener) {
        this.currentPageListener = currentPageListener
    }

    override fun onPageSelected(position: Int) {
        mCurrentPosition = position
        currentPageListener!!.onCurrentPageChanged(mCurrentPosition)
    }

    override fun onPageScrollStateChanged(state: Int) {
        val currentPage = mViewPager.currentItem
        if (currentPage == mViewPager.adapter?.count?.minus(1) || currentPage == 0) {
            val previousState = mCurrentPosition
            mCurrentPosition = state
            if (previousState == 1 && mCurrentPosition == 0) {
                mViewPager.currentItem = if (currentPage == 0) mViewPager.adapter!!.count - 1 else 0
            }
        }
    }

    override fun onPageScrolled(
        position: Int, positionOffset: Float, positionOffsetPixels: Int
    ) {
    }

    internal interface CurrentPageListener {
        fun onCurrentPageChanged(currentPosition: Int)
    }
}
