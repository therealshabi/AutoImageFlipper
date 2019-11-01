package technolifestyle.com.autoimageslider.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import technolifestyle.com.autoimageslider.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val arrayList = ArrayList<HomeContents>()

        arrayList.add(HomeContents(HomeType.Image("https://blog.eap.ucop.edu/wp-content/uploads/2016/01/Julie-Huang-27.jpg", "Test Image 1")))
        arrayList.add(HomeContents(HomeType.ImageFlipper(ArrayList(listOf(
                HomeType.Image("https://blog.eap.ucop.edu/wp-content/uploads/2016/01/Julie-Huang-27.jpg", "Flipper Test Image 1"),
                HomeType.Image("https://picsum.photos/1200", "Flipper Test Image 2"),
                HomeType.Image("https://i.pinimg.com/originals/18/40/72/184072abb72399c23ab635faaa0a94ad.jpg", "Flipper Test Image 3")
        )))))
        arrayList.add(HomeContents(HomeType.Image("https://picsum.photos/1200", "Test Image 2")))
        arrayList.add(HomeContents(HomeType.Image("https://blog.eap.ucop.edu/wp-content/uploads/2016/01/Julie-Huang-27.jpg", "Test Image 3")))
        arrayList.add(HomeContents(HomeType.Image("https://i.pinimg.com/originals/18/40/72/184072abb72399c23ab635faaa0a94ad.jpg", "Test Image 4")))
        arrayList.add(HomeContents(HomeType.ImageFlipper(ArrayList(listOf(
                HomeType.Image("https://picsum.photos/1200", "Flipper Test Image 4"),
                HomeType.Image("https://i.pinimg.com/originals/18/40/72/184072abb72399c23ab635faaa0a94ad.jpg", "Flipper Test Image 5"),
                HomeType.Image("https://blog.eap.ucop.edu/wp-content/uploads/2016/01/Julie-Huang-27.jpg", "Flipper Test Image 6")
        )))))

        homeRecyclerView.layoutManager = LinearLayoutManager(this)
        homeRecyclerView.adapter = HomeRecyclerAdapter(arrayList)
    }

    data class HomeContents(val type: HomeType)

    sealed class HomeType {
        data class Image(val imageUrl: String, val text: String) : HomeType()
        data class ImageFlipper(val flipperList: ArrayList<Image>) : HomeType()
    }
}
