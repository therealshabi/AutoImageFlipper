package technolifestyle.com.imageslider

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import java.util.*

internal class FlipperAdapter : PagerAdapter() {
    private var flipperViews = ArrayList<FlipperView>()

    fun setFlipperViews(flipperViews: ArrayList<FlipperView>) {
        this.flipperViews = flipperViews
    }

    fun addFlipperView(view: FlipperView) {
        flipperViews.add(view)
    }

    fun removeAllFlipperViews() {
        flipperViews.clear()
        notifyDataSetChanged()
    }

    fun getFlipperView(position: Int): FlipperView? {
        return if (flipperViews.isEmpty() || position >= flipperViews.size) {
            null
        } else flipperViews[position]
    }

    override fun getCount(): Int {
        return flipperViews.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageFlipperView = flipperViews[position]
        val view = imageFlipperView.getView()
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun addAllFlipperViews(flipperViewList: List<FlipperView>) {
        flipperViews.addAll(flipperViewList)
        notifyDataSetChanged()
    }
}
