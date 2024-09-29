package com.example.chess

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class ItemAdapter(private val context: Context, private val items: List<promotionsListItem>) : BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.promotions_list_item, parent, false)
        val item = items[position]

        val itemImage: ImageView = view.findViewById(R.id.item_image)
        val itemText: TextView = view.findViewById(R.id.item_text)

        itemImage.setImageResource(item.imageResId)
        itemText.text=item.piece

        return view
    }
}
