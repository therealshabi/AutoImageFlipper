package technolifestyle.com.imageslider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import lombok.Getter;

class ImageFlipperView {

    @Getter
    private String description;

    @DrawableRes
    @Getter
    private int imageRes;

    @Getter
    private String imageUrl;

    @Getter
    private ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;

    private Context context;

    public ImageFlipperView(Context context) {
        this.context = context;
    }

    public ImageFlipperView setDescription(String description) {
        this.description = description;
        return this;
    }

    public ImageFlipperView setImageUrl(String imageUrl) {
        if (imageRes != 0) {
            throw new IllegalStateException("Can't set multiple images");
        }
        this.imageUrl = imageUrl;
        return this;
    }

    public ImageFlipperView setImageDrawable(int imageDrawable) {
        if (!TextUtils.isEmpty(imageUrl)) {
            throw new IllegalStateException("Can't set multiple images");
        }
        this.imageRes = imageDrawable;
        return this;
    }

    public ImageFlipperView setImageScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
        return this;
    }

    public View getView() {
        @SuppressLint("InflateParams")
        View v = LayoutInflater.from(context).inflate(R.layout.image_flipper_layout_item, null);
        ImageView autoSliderImage = (ImageView) v.findViewById(R.id.iv_auto_image_slider);
        TextView description = (TextView) v.findViewById(R.id.tv_auto_image_slider);
        description.setText(getDescription());
        bindData(autoSliderImage);
        return v;
    }

    private void bindData(ImageView autoSliderImage) {
        try {
            autoSliderImage.setScaleType(getScaleType());
            if (imageUrl != null) {
                Glide.with(context).load(imageUrl)
                        .thumbnail(0.1f)
                        .into(autoSliderImage);

            } else {
                Glide.with(context).load(imageRes)
                        .thumbnail(0.1f)
                        .into(autoSliderImage);
            }
        } catch (Exception exception) {
            Log.d("Exception", exception.getMessage());
        }

    }
}
