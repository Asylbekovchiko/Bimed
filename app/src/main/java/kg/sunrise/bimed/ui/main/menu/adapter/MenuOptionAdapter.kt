package kg.sunrise.bimed.ui.main.menu.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import kg.sunrise.bimed.R
import kg.sunrise.bimed.base.adapter.BaseVH
import kg.sunrise.bimed.utils.extensions.setLocale
import kg.sunrise.bimed.utils.preference.LANGUAGE_KG
import kg.sunrise.bimed.utils.preference.LANGUAGE_RU
import kg.sunrise.bimed.utils.preference.getLocalization

class MenuOptionAdapter(val delegate: MenuOptionAdapterDelegate) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = ArrayList<MenuOption>()

    fun addData(items : ArrayList<MenuOption>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setData(items : ArrayList<MenuOption>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int) = when (items[position]) {
        is MenuOption.FeedItem -> R.layout.item_feed
        is MenuOption.WholeSalersItem -> R.layout.item_whole_salers
        is MenuOption.AppLangItem -> R.layout.item_app_lang
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_feed -> FeedOptionVH(parent, R.layout.item_feed)
            R.layout.item_whole_salers -> WholeSalersOptionVH(parent, R.layout.item_whole_salers)
            R.layout.item_app_lang -> AppLangOptionVH(parent, R.layout.item_app_lang)
            else -> throw Throwable("1435: Error creating View Holder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is FeedOptionVH -> holder.bind(item as MenuOption.FeedItem)
            is WholeSalersOptionVH -> holder.bind(item as MenuOption.WholeSalersItem)
            is AppLangOptionVH -> holder.bind(item as MenuOption.AppLangItem)
        }

    }

    inner class FeedOptionVH(
        parent: ViewGroup,
        @LayoutRes layoutResId: Int
    ): BaseVH<MenuOption.FeedItem>(parent, layoutResId) {

        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)

        override fun bind(item: MenuOption.FeedItem) {
            itemView.setOnClickListener { delegate.onFeedItemClick() }

            tvTitle.setText(item.title)
        }
    }

    inner class WholeSalersOptionVH(
        parent: ViewGroup,
        @LayoutRes layoutResId: Int
    ): BaseVH<MenuOption.WholeSalersItem>(parent, layoutResId) {

        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)

        override fun bind(item: MenuOption.WholeSalersItem) {
            tvTitle.setText(item.title)
        }
    }

    inner class AppLangOptionVH(
        parent: ViewGroup,
        @LayoutRes layoutResId: Int
    ): BaseVH<MenuOption.AppLangItem>(parent, layoutResId) {

        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        val tabLayout = itemView.findViewById<TabLayout>(R.id.l_tab)

        override fun bind(item: MenuOption.AppLangItem) {
            tvTitle.setText(item.title)

            val tab = tabLayout.newTab().setText(R.string.Rus)
            tabLayout.addTab(tab)
            val tab2 = tabLayout.newTab().setText(R.string.Eng)
            tabLayout.addTab(tab2)

            val lang = getLocalization(itemView.context)

            tabLayout.selectTab(if (lang == LANGUAGE_RU) tab else tab2)

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let {
                        val lang = if (it.position == 0) LANGUAGE_RU else LANGUAGE_KG
                        setLocale(itemView.context, lang)
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }
}

sealed class MenuOption(@StringRes val title: Int) {

    class FeedItem(
        @StringRes title: Int
    ): MenuOption(title)

    class WholeSalersItem(
        @StringRes title: Int,
    ): MenuOption(title)

    class AppLangItem(
        @StringRes title: Int,
    ): MenuOption(title)
}

interface MenuOptionAdapterDelegate {
    fun onFeedItemClick()
}

