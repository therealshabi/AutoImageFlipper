package technolifestyle.com.imageslider

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.flipper_view.view.*

class FlipperView(context: Context) : View(context) {

    private var onFlipperClickListener: OnFlipperClickListener? = null

    private var description: String? = null

    @DrawableRes
    var imageRes: Int = 0

    private var imageUrl: String? = null

    private var scaleType: ImageView.ScaleType = ImageView.ScaleType.FIT_CENTER

    private val flipperView: View = LayoutInflater.from(context)
            .inflate(R.layout.flipper_view, null, true)

    private val descriptionTextView: TextView
    private val autoSliderImageView: ImageView


    init {
        autoSliderImageView = flipperView.autoSliderImageView
        descriptionTextView = flipperView.descriptionTextView
        descriptionTextView.background.alpha = 80
    }

    private fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String): FlipperView {
        this.description = description
        return this
    }

    fun getImageUrl(): String? {
        return imageUrl
    }

    fun setImageUrl(imageUrl: String): FlipperView {
        check(imageRes == 0) { "Can't set multiple images" }
        this.imageUrl = imageUrl
        return this
    }

    fun setImageDrawable(imageDrawable: Int): FlipperView {
        check(TextUtils.isEmpty(imageUrl)) { "Can't set multiple images" }
        this.imageRes = imageDrawable
        return this
    }

    fun setImageScaleType(scaleType: ImageView.ScaleType): FlipperView {
        this.scaleType = scaleType
        return this
    }

    fun getView(): View {
        descriptionTextView.text = getDescription()
        bindData(flipperView, autoSliderImageView)
        return flipperView
    }

    fun setOnFlipperClickListener(l: OnFlipperClickListener) {
        onFlipperClickListener = l
    }

    /**
     * Set the background alpha for descriptionTextView
     *
     * @param alpha {float}
     * @return FlipperView
     */
    fun setDescriptionBackgroundAlpha(alpha: Float): FlipperView {
        descriptionTextView.background = null
        descriptionTextView.alpha = alpha
        return this
    }

    /**
     * Set the background for descriptionTextView
     *
     * @param color {int}
     * @param alpha {float}
     * @return FlipperView
     */
    fun setDescriptionBackground(@ColorInt color: Int, alpha: Float): FlipperView {
        descriptionTextView.background = null
        descriptionTextView.setBackgroundColor(color)
        descriptionTextView.alpha = alpha
        return this
    }


    /**
     * set background of descriptionText
     *
     * @param color {int} color
     * @return FlipperView
     */
    fun setDescriptionBackgroundColor(@ColorInt color: Int): FlipperView {
        descriptionTextView.background = null
        descriptionTextView.setBackgroundColor(color)
        return this
    }

    /**
     * set background of descriptionText
     *
     * @param drawable {int} drawable resource id
     * @return FlipperView
     */
    fun setDescriptionBackgroundDrawable(@DrawableRes drawable: Int): FlipperView {
        descriptionTextView.background = ContextCompat.getDrawable(context, drawable)
        return this
    }

    /**
     * Reset DescriptionTextView back to default
     *
     * @return FlipperView
     */
    fun resetDescriptionTextView(): FlipperView {
        descriptionTextView.background = ContextCompat.getDrawable(context, R.drawable.bg_overlay)
        descriptionTextView.background.alpha = 80
        descriptionTextView.setTextColor(Color.WHITE)
        return this
    }

    /**
     * @param color {int} text color for description text flipperView
     * @return FlipperView
     */
    fun setDescriptionTextColor(@ColorInt color: Int): FlipperView {
        descriptionTextView.setTextColor(color)
        return this
    }

    fun setViewHeight(height: Int) {
        this.autoSliderImageView.layoutParams.height = height
    }

    private fun bindData(view: View, autoSliderImage: ImageView) {
        val flipperView = this
        view.setOnClickListener {
            if (onFlipperClickListener != null) {
                onFlipperClickListener?.onFlipperClick(flipperView)
            }
        }
        autoSliderImage.scaleType = scaleType
    }

    interface OnFlipperClickListener {
        fun onFlipperClick(flipperView: FlipperView)
    }
}