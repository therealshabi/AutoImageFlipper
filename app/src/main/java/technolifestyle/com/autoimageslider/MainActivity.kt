package technolifestyle.com.autoimageslider

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import technolifestyle.com.imageslider.FlipperLayout
import technolifestyle.com.imageslider.FlipperView
import technolifestyle.com.imageslider.pagetransformers.ZoomOutPageTransformer

class MainActivity : AppCompatActivity() {

    private lateinit var flipperLayout: FlipperLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flipperLayout = findViewById(R.id.flipper_layout)
        flipperLayout.addPageTransformer(false, ZoomOutPageTransformer())

        // Uncomment to add your custom scroll time (default is 3 seconds)
        // flipperLayout.setScrollTimeInSec(5);

        setLayout()
    }

    private fun setLayout() {
        val url =
                arrayOf("https://blog.eap.ucop.edu/wp-content/uploads/2016/01/Julie-Huang-27.jpg",
                        "https://picsum.photos/300",
                        "https://i.pinimg.com/originals/18/40/72/184072abb72399c23ab635faaa0a94ad.jpg")

        val flipperViewList: ArrayList<FlipperView> = ArrayList()
        for (i in url.indices) {
            val view = FlipperView(baseContext)
            view.setDescription("Cool" + (i + 1))
                    .setDescriptionBackgroundColor(Color.TRANSPARENT)
                    .resetDescriptionTextView()
                    .setImage(url[i]) { flipperImageView, image ->
                        Picasso.get().load(image as String).into(flipperImageView)
                    }
            view.setOnFlipperClickListener(object : FlipperView.OnFlipperClickListener {
                override fun onFlipperClick(flipperView: FlipperView) {
                    Toast.makeText(this@MainActivity, "Here " + (flipperLayout.currentPagePosition + 1), Toast.LENGTH_SHORT).show()
                }
            })
            flipperViewList.add(view)
        }

        flipperLayout.addFlipperViewList(flipperViewList)
        flipperLayout.removeCircleIndicator()
        flipperLayout.showCircleIndicator()
        val view = FlipperView(baseContext)
        view.setDescription("This is Black Panther II from new Marvel Movies")
        view.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
        view.setImage(R.drawable.error) { imageView, image ->
            imageView.setImageDrawable(image as Drawable)
        }
        flipperLayout.addFlipperView(view)
    }
}