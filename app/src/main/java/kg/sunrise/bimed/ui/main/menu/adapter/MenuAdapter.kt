package kg.sunrise.bimed.ui.main.menu.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import kg.sunrise.bimed.R
import kg.sunrise.bimed.base.adapter.BaseAdapter
import kg.sunrise.bimed.base.adapter.BaseVH

class MenuAdapter(
    val delegate: MenuAdapterDelegate
): BaseAdapter<MenuItem, MenuAdapter.MenuVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuVH {
        return MenuVH(parent, R.layout.item_menu)
    }

    inner class MenuVH(
        parent: ViewGroup,
        @LayoutRes layoutResId: Int
    ): BaseVH<MenuItem>(parent, layoutResId) {

        private val tvTitle: TextView = itemView.findViewById<TextView>(R.id.tv_title)
        private val ivIcon: ImageView = itemView.findViewById<ImageView>(R.id.iv_icon)

        override fun bind(item: MenuItem) {
            item.apply {
                tvTitle.setText(title)

                Glide.with(itemView).load(icon).into(ivIcon)

                itemView.setOnClickListener {
                    delegate.onItemClickListener(type)
                }
            }
        }
    }
}

data class MenuItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val type: MenuType
)

enum class MenuType {
    ENTER,
    PROFILE,
    FAVOURITES,
    ORDERS_HISTORY,
    FILIALS,
    NEWS,
    ABOUT_COMPANY,
    HELP,
    CONTACTS
}

interface MenuAdapterDelegate {
    fun onItemClickListener(type: MenuType)
}