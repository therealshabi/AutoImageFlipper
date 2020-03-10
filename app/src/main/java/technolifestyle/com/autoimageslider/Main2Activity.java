package technolifestyle.com.autoimageslider;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final FlipperLayout flipperLayout = findViewById(R.id.flipperLayoutTwo);

        String[] url = {"https://blog.eap.ucop.edu/wp-content/uploads/2016/01/Julie-Huang-27.jpg",
                "https://source.unsplash.com/random",
                "https://i.pinimg.com/originals/18/40/72/184072abb72399c23ab635faaa0a94ad.jpg"
        };
        for (int i = 0; i < url.length; i++) {
            FlipperView view = new FlipperView(getBaseContext());
            view.setDescription("Cool" + (i + 1))
                    .setDescriptionBackgroundColor(Color.TRANSPARENT)
                    .resetDescriptionTextView();
            flipperLayout.setCircleIndicatorHeight(60);
            flipperLayout.setCircleIndicatorWidth(200);
            flipperLayout.removeCircleIndicator();
            flipperLayout.showCircleIndicator();
            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(@NotNull FlipperView flipperView) {
                    Toast.makeText(getBaseContext(),
                            "Here " + (flipperLayout.getCurrentPagePosition() + 1), Toast.LENGTH_SHORT).show();
                }
            });
            try {
                view.setImage(url[i], new Function2<ImageView, Object, Unit>() {
                    @Override
                    public Unit invoke(ImageView imageView, Object image) {
                        Picasso.get().load((String) image).into(imageView);
                        return Unit.INSTANCE;
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            flipperLayout.addFlipperView(view);
        }
    }
}
