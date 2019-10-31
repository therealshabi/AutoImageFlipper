package technolifestyle.com.imageslider.utils

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

class UIUtils private constructor() {

    fun dpToPixels(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp,
                displayMetrics!!)
    }

    fun dpToPixels(dp: Int): Int {
        return dpToPixels(dp.toFloat()).toInt()
    }

    fun pixelsToDp(pixels: Float): Float {
        return pixels / dpToPixels(1)
    }

    companion object {
        private var instance: UIUtils? = null

        fun getInstance(context: Context?): UIUtils {
            if (instance == null) {
                instance = UIUtils()
            }
            displayMetrics = context?.resources?.displayMetrics
            return instance as UIUtils
        }

        var displayMetrics: DisplayMetrics? = null

    }
}