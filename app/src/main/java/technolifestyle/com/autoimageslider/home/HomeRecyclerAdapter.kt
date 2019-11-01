package technolifestyle.com.autoimageslider.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.image_item.view.*
import technolifestyle.com.autoimageslider.R
import technolifestyle.com.imageslider.FlipperView

class HomeRecyclerAdapter(private val homeContentList: ArrayList<HomeActivity.HomeContents>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return (when (viewType) {
            0 -> HomeImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false))
            1 -> HomeImageFlipperViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false))
            else -> HomeImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false))
        })
    }

    override fun getItemCount(): Int {
        return homeContentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (homeContentList[position].type) {
            is HomeActivity.HomeType.Image -> 0
            is HomeActivity.HomeType.ImageFlipper -> 1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> (holder as HomeImageViewHolder).bind(homeContentList[position])
            1 -> (holder as HomeImageFlipperViewHolder).bind(homeContentList[position])
        }
    }

    internal class HomeImageViewHolder(private val homeImageItemView: View) :
            RecyclerView.ViewHolder(homeImageItemView) {
        fun bind(homeContents: HomeActivity.HomeContents) {
            Picasso.get().load(
                    (homeContents.type as HomeActivity.HomeType.Image).imageUrl)
                    .into(homeImageItemView.imageview)
            homeImageItemView.textView.text = homeContents.type.text
        }
    }

    internal class HomeImageFlipperViewHolder(private val homeFlipperItemView: View) :
            RecyclerView.ViewHolder(homeFlipperItemView) {
        fun bind(homeContents: HomeActivity.HomeContents) {
            homeFlipperItemView.flipper_layout.apply {
                removeAllFlipperViews()
                showInnerPagerIndicator()
//                setIndicatorBackgroundColor(Color.parseColor("#90000000"))
                setCircleIndicatorHeight(20)
                (homeContents.type as HomeActivity.HomeType.ImageFlipper).flipperList.forEach {
                    homeFlipperItemView.flipper_layout.addFlipperView(
                            FlipperView(homeFlipperItemView.context).setDescription(it.text)
                                    .setImageUrl(it.imageUrl) { flipperImageView, imageUrl ->
                                        Picasso.get()
                                                .load(imageUrl)
                                                .into(flipperImageView)
                                    }
                    )
                }
            }
        }
    }
}


