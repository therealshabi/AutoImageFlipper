package technolifestyle.com.imageslider

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

class FlipperView @SuppressLint("InflateParams")
constructor(context: Context) : View(context) {

    private var onFlipperClickListener: OnFlipperClickListener? = null

    private var description: String? = null

    @DrawableRes
    var imageRes: Int = 0
        private set

    private var imageUrl: String? = null

    private var scaleType: ImageView.ScaleType = ImageView.ScaleType.CENTER_CROP

    private val descriptionTextView: TextView

    private val view: View
    private val autoSliderImage: ImageView

    init {
        view = LayoutInflater.from(context)
                .inflate(R.layout.image_flipper_layout_item, null,
                        true)
        autoSliderImage = view.findViewById(R.id.iv_auto_image_slider)
        descriptionTextView = view.findViewById(R.id.tv_auto_image_slider)
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

    internal fun getView(): View {
        descriptionTextView.text = getDescription()
        bindData(view, autoSliderImage)
        return view
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
     * @param color {int} text color for description text view
     * @return FlipperView
     */
    fun setDescriptionTextColor(@ColorInt color: Int): FlipperView {
        descriptionTextView.setTextColor(color)
        return this
    }

    private fun bindData(view: View, autoSliderImage: ImageView) {
        val flipperView = this
        view.setOnClickListener {
            if (onFlipperClickListener != null) {
                onFlipperClickListener!!.onFlipperClick(flipperView)
            }
        }
        try {
            autoSliderImage.scaleType = scaleType
            if (imageUrl != null) {
                Picasso.with(context).load(Uri.parse(imageUrl))
                        .into(autoSliderImage)
            } else {
                Picasso.with(context).load(imageRes).into(autoSliderImage)
            }
        } catch (exception: Exception) {
            Log.d("Exception", exception.message)
        }

    }

    interface OnFlipperClickListener {
        fun onFlipperClick(flipperView: FlipperView)
    }
}
