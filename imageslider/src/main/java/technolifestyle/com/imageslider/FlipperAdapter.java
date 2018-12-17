package technolifestyle.com.imageslider;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

class FlipperAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<FlipperView> flipperViews = new ArrayList<>();

    FlipperAdapter(Context context) {
        this.context = context;
    }

    public void setFlipperViews(ArrayList<FlipperView> flipperViews) {
        this.flipperViews = flipperViews;
    }

    void addFlipperView(FlipperView view) {
        flipperViews.add(view);
        notifyDataSetChanged();
    }

    public void removeAllFlipperViews() {
        flipperViews.clear();
        notifyDataSetChanged();
    }

    public FlipperView getFlipperView(int position) {
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
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        FlipperView imageFlipperView = flipperViews.get(position);
        View v = imageFlipperView.getView();
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
