package technolifestyle.com.imageslider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

public class FlipperView extends View {

    private FlipperView.OnFlipperClickListener onFlipperClickListener;

    private String description;

    @DrawableRes
    private int imageRes;

    private String imageUrl;

    private ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;
    private Context context;

    private TextView descriptionTextView;

    private View view;
    private ImageView autoSliderImage;

    @SuppressLint("InflateParams")
    public FlipperView(Context context) {
        super(context);
        this.context = context;
        view = LayoutInflater.from(context)
                .inflate(R.layout.image_flipper_layout_item,
                        null,
                        true);
        autoSliderImage = view.findViewById(R.id.iv_auto_image_slider);
        descriptionTextView = view.findViewById(R.id.tv_auto_image_slider);
        descriptionTextView.getBackground().setAlpha(80);
    }

    private String getDescription() {
        return description;
    }

    public FlipperView setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public FlipperView setImageUrl(String imageUrl) {
        if (imageRes != 0) {
            throw new IllegalStateException("Can't set multiple images");
        }
        this.imageUrl = imageUrl;
        return this;
    }

    private ImageView.ScaleType getScaleType() {
        return scaleType;
    }

    public FlipperView setImageDrawable(int imageDrawable) {
        if (!TextUtils.isEmpty(imageUrl)) {
            throw new IllegalStateException("Can't set multiple images");
        }
        this.imageRes = imageDrawable;
        return this;
    }

    public FlipperView setImageScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
        return this;
    }

    View getView() {
        descriptionTextView.setText(getDescription());
        bindData(view, autoSliderImage);
        return view;
    }

    public void setOnFlipperClickListener(OnFlipperClickListener l) {
        onFlipperClickListener = l;
    }

    /**
     * Set the background alpha for descriptionTextView
     *
     * @param alpha {float}
     * @return FlipperView
     */
    public FlipperView setDescriptionBackgroundAlpha(float alpha) {
        descriptionTextView.setBackground(null);
        descriptionTextView.setAlpha(alpha);
        return this;
    }

    /**
     * Set the background for descriptionTextView
     *
     * @param color {int}
     * @param alpha {float}
     * @return FlipperView
     */
    public FlipperView setDescriptionBackground(@ColorInt int color, float alpha) {
        descriptionTextView.setBackground(null);
        descriptionTextView.setBackgroundColor(color);
        descriptionTextView.setAlpha(alpha);
        return this;
    }


    /**
     * set background of descriptionText
     *
     * @param color {int} color
     * @return FlipperView
     */
    public FlipperView setDescriptionBackgroundColor(@ColorInt int color) {
        descriptionTextView.setBackground(null);
        descriptionTextView.setBackgroundColor(color);
        return this;
    }

    /**
     * set background of descriptionText
     *
     * @param drawable {int} drawable resource id
     * @return FlipperView
     */
    public FlipperView setDescriptionBackgroundDrawable(@DrawableRes int drawable) {
        descriptionTextView.setBackground(ContextCompat.getDrawable(context, drawable));
        return this;
    }

    /**
     * Reset DescriptionTextView back to default
     *
     * @return FlipperView
     */
    public FlipperView resetDescriptionTextView() {
        descriptionTextView.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_overlay));
        descriptionTextView.getBackground().setAlpha(80);
        descriptionTextView.setTextColor(Color.WHITE);
        return this;
    }

    /**
     * @param color {int} text color for description text view
     * @return FlipperView
     */
    public FlipperView setDescriptionTextColor(@ColorInt int color) {
        descriptionTextView.setTextColor(color);
        return this;
    }

    private void bindData(View view, ImageView autoSliderImage) {
        final FlipperView flipperView = this;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onFlipperClickListener != null) {
                    onFlipperClickListener.onFlipperClick(flipperView);
                }
            }
        });
        try {
            autoSliderImage.setScaleType(getScaleType());
            if (imageUrl != null) {
                Picasso.with(context).load(Uri.parse(imageUrl))
                        .into(autoSliderImage);
            } else {
                Picasso.with(context).load(imageRes).into(autoSliderImage);
            }
        } catch (Exception exception) {
            Log.d("Exception", exception.getMessage());
        }
    }

    public interface OnFlipperClickListener {
        void onFlipperClick(FlipperView flipperView);
    }
}
