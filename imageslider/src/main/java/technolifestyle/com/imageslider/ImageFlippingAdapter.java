package technolifestyle.com.imageslider;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

import lombok.Setter;


class ImageFlippingAdapter extends PagerAdapter {

    private Context context;
    @Setter
    private ArrayList<ImageFlipperView> flipperViews = new ArrayList<>();

    ImageFlippingAdapter(Context context) {
        this.context = context;
    }

    public void addFlipperView(ImageFlipperView view) {
        flipperViews.add(view);
        notifyDataSetChanged();
    }

    public void removeAllFlipperViews() {
        flipperViews.clear();
        notifyDataSetChanged();
    }

    public ImageFlipperView getFlipperView(int position) {
        if (flipperViews.isEmpty() || position >= flipperViews.size()) {
            return null;
        }
        return flipperViews.get(position);
    }

    @Override
    public int getCount() {
        return flipperViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
