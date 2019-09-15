package technolifestyle.com.imageslider

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.flipper_view.view.*
import java.net.MalformedURLException

class FlipperView(context: Context) : View(context) {

    private var onFlipperClickListener: OnFlipperClickListener? = null

    private var description: String? = null

    var imageDrawable: Drawable? = null

    var imageUrl: String? = null

    var imageBitmap: Bitmap? = null

    private var scaleType: ImageView.ScaleType = ImageView.ScaleType.FIT_CENTER

    @SuppressLint("InflateParams")
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

    /**
     * This method is used to set and load image into the flipper view
     * @param image This image can be a String URL, a Bitmap, a drawable resource ID or a drawable itself
     * @param setFlipperImage This is a custom higher order method/interface which exposes flipperImageView to the
     * user so that they can set the image into the image view by whatever means they want
     * @throws MalformedURLException in case the image param is String but it does not resolve to a valid URL
     * @throws IllegalArgumentException in case the image param is neither of above defined types for image param
     * @throws IllegalStateException in case more than one type of image is set for a single flipper view
     */
    @Throws(MalformedURLException::class, IllegalArgumentException::class, IllegalStateException::class)
    fun setImage(image: Any,
                 setFlipperImage: (flipperImageView: ImageView) -> Unit): FlipperView {
        when (image) {
            is String -> {
                return this.setImageUrl(image, setFlipperImage)
            }

            is Drawable -> {
                return this.setImageDrawable(image, setFlipperImage)
            }

            is @DrawableRes Int -> {
                return this.setImageDrawable(
                        ContextCompat.getDrawable(context, image), setFlipperImage)
            }

            is Bitmap -> {
                return this.setImageBitmap(image, setFlipperImage)
            }

            else -> {
                throw IllegalArgumentException(context.getString(R.string.illegal_image_param))
            }
        }
    }

    /**
     * This method is used to set image url into the flipper image view
     *
     * @param imageUrl the String url of image location to be loaded into the flipperImageView
     * @param setFlipperImage This is a custom higher order method/interface which exposes flipperImageView to the
     * user so that they can set the image into the image view by whatever means they want
     * @throws MalformedURLException in case the imageUrl does not resolve to a valid URL
     * @throws IllegalStateException in case more than one type of image is set for a single flipper view
     */
    @Throws(IllegalStateException::class, MalformedURLException::class)
    fun setImageUrl(imageUrl: String,
                    setFlipperImage: (flipperImageView: ImageView) -> Unit): FlipperView {
        check(imageDrawable == null && imageBitmap == null) {
            context.getString(R.string.multiple_image_illegal_state_exception)
        }
        if (!URLUtil.isValidUrl(imageUrl))
            throw MalformedURLException(context.getString(R.string.malformed_url_exception_message))
        this.imageUrl = imageUrl
        setFlipperImage(autoSliderImageView)
        return this
    }

    /**
     * This method is used to set image url into the flipper image view
     *
     * @param imageDrawable the image drawable to be loaded into the flipperImageView
     * @param setFlipperImage This is a custom higher order method/interface which exposes flipperImageView to the
     * user so that they can set the image into the image view by whatever means they want
     * @throws IllegalStateException in case more than one type of image is set for a single flipper view
     */
    @Throws(IllegalStateException::class)
    fun setImageDrawable(imageDrawable: Drawable?,
                         setFlipperImage: (flipperImageView: ImageView) -> Unit): FlipperView {
        check(TextUtils.isEmpty(imageUrl) && imageBitmap == null) {
            context.getString(R.string.multiple_image_illegal_state_exception)
        }
        this.imageDrawable = imageDrawable
        setFlipperImage(autoSliderImageView)
        return this
    }

    /**
     * This method is used to set image url into the flipper image view
     *
     * @param imageBitmap the image bitmap to be loaded into the flipperImageView
     * @param setFlipperImage This is a custom higher order method/interface which exposes flipperImageView to the
     * user so that they can set the image into the image view by whatever means they want
     * @throws IllegalStateException in case more than one type of image is set for a single flipper view
     */
    @Throws(IllegalStateException::class)
    fun setImageBitmap(imageBitmap: Bitmap?,
                       setFlipperImage: (flipperImageView: ImageView) -> Unit): FlipperView {
        check(TextUtils.isEmpty(imageUrl) && imageDrawable == null) {
            context.getString(R.string.multiple_image_illegal_state_exception)
        }
        this.imageBitmap = imageBitmap
        setFlipperImage(autoSliderImageView)
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