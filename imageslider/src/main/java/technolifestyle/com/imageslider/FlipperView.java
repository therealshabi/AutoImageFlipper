package technolifestyle.com.imageslider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FlipperView {

    private FlipperView.OnFlipperClickListener onFlipperClickListener;

    private String description;

    @DrawableRes
    private int imageRes;

    private String imageUrl;

    private ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;
    private Context context;

    public FlipperView(Context context) {
        this.context = context;
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

    public View getView() {
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(R.layout.image_flipper_layout_item, null, true);
        ImageView autoSliderImage = view.findViewById(R.id.iv_auto_image_slider);
        TextView description = view.findViewById(R.id.tv_auto_image_slider);
        description.getBackground().setAlpha(80);
        description.setText(getDescription());
        bindData(view, autoSliderImage);
        return view;
    }

    public FlipperView setOnFlipperClickListener(FlipperView.OnFlipperClickListener l) {
        onFlipperClickListener = l;
        return this;
    }

    private void bindData(View v, ImageView autoSliderImage) {
        final FlipperView con = this;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onFlipperClickListener != null) {
                    onFlipperClickListener.onFlipperClick(con);
                }
            }
        });
        try {
            autoSliderImage.setScaleType(getScaleType());
            if (imageUrl != null) {
                Picasso.get().load(Uri.parse(imageUrl)).into(autoSliderImage);
            } else {
                Picasso.get().load(imageRes).into(autoSliderImage);
            }
        } catch (Exception exception) {
            Log.d("Exception", exception.getMessage());
        }
    }

    public interface OnFlipperClickListener {
        void onFlipperClick(FlipperView flipperView);
    }
}
