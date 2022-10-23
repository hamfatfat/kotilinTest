

package com.kotlintest.ui.dashboard
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.kotlintest.R
import com.squareup.picasso.Picasso


class ListItemAdapter(private val context: Context,
                    private val dataSource: ArrayList<ListItem>) : BaseAdapter() {

  private val inflater: LayoutInflater
      = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

  companion object {
    private val LABEL_COLORS = hashMapOf(
        "Low-Carb" to R.color.colorLowCarb,
        "Low-Fat" to R.color.colorLowFat,
        "Low-Sodium" to R.color.colorLowSodium,
        "Medium-Carb" to R.color.colorMediumCarb,
        "Vegetarian" to R.color.colorVegetarian,
        "Balanced" to R.color.colorBalanced
    )
  }

  override fun getCount(): Int {
    return dataSource.size
  }

  override fun getItem(position: Int): Any {
    return dataSource[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val view: View
    val holder: ViewHolder

    // 1
    if (convertView == null) {

      // 2
      view = inflater.inflate(R.layout.list_item, parent, false)

      // 3
      holder = ViewHolder()
      holder.thumbnailImageView = view.findViewById(R.id.item_list_thumbnail) as ImageView
      holder.titleTextView = view.findViewById(R.id.item_list_title) as TextView

      // 4
      view.tag = holder
    } else {
      // 5
      view = convertView
      holder = convertView.tag as ViewHolder
    }

    // 6
    val titleTextView = holder.titleTextView
    val thumbnailImageView = holder.thumbnailImageView

    val item = getItem(position) as ListItem

    titleTextView.text = item.title

    Picasso.get().load(item.imageUrl).into(thumbnailImageView)




    return view
  }

  private class ViewHolder {
    lateinit var titleTextView: TextView
    lateinit var thumbnailImageView: ImageView
  }
}
