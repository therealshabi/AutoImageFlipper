package technolifestyle.com.imageslider;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


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
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        FlipperView imageFlipperView = flipperViews.get(position);
        View v = imageFlipperView.getView();
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
