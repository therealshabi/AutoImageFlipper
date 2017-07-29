package technolifestyle.com.autoimageslider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class MainActivity extends AppCompatActivity {

    FlipperLayout flipperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flipperLayout = (FlipperLayout) findViewById(R.id.flipper_layout);

        setLayout();
    }

    private void setLayout() {
        String url[] = new String[]{"https://theluxurytravelexpert.files.wordpress.com/2014/01/scenery.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTy4OeOJDZj7W8J2Ll4W43Hv6WfwXXR-Xby-ZCG6MbfNYXpRkgM",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSu5QxN89NiqAMmKuBfYXDd1t0j5UXFuZS0vmLG9D4iMyCyfCZv"};
        for (int i = 0; i < 3; i++) {
            FlipperView view = new FlipperView(getBaseContext());
            view.setImageUrl(url[i])
                    .setDescription("Cool" + (i + 1));
            flipperLayout.addFlipperView(view);
            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {
                    Toast.makeText(MainActivity.this
                            , "Here " + (flipperLayout.getCurrentPagePosition() + 1)
                            , Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
