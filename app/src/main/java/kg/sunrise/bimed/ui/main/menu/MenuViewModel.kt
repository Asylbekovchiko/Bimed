package kg.sunrise.bimed.ui.main.menu

import kg.sunrise.bimed.R
import kg.sunrise.bimed.base.viewModel.BaseViewModel
import kg.sunrise.bimed.ui.main.menu.adapter.MenuItem
import kg.sunrise.bimed.ui.main.menu.adapter.MenuType

class MenuViewModel : BaseViewModel() {

    fun getMenuItems(isAuthorized: Boolean): ArrayList<MenuItem> {
        val items =
            if (isAuthorized)
                getAuthorizedMenuItems()
            else
                arrayListOf(MenuItem(R.string.Enter2, R.drawable.ic_menu_profile, MenuType.ENTER))
        return ArrayList(items + getDefaultMenuItems())
    }

    private fun getAuthorizedMenuItems(): ArrayList<MenuItem> {
        return arrayListOf(
            MenuItem(R.string.Profile, R.drawable.ic_menu_profile, MenuType.PROFILE),
            MenuItem(R.string.Favourites, R.drawable.ic_menu_favourites, MenuType.FAVOURITES),
            MenuItem(R.string.Orders_history, R.drawable.ic_menu_history, MenuType.ORDERS_HISTORY)
        )
    }

    private fun getDefaultMenuItems(): ArrayList<MenuItem> {
        return arrayListOf(
            MenuItem(R.string.Filials, R.drawable.ic_menu_filials, MenuType.FILIALS),
            MenuItem(R.string.News, R.drawable.ic_menu_news, MenuType.NEWS),
            MenuItem(R.string.About_company, R.drawable.ic_menu_info, MenuType.ABOUT_COMPANY),
            MenuItem(R.string.Help, R.drawable.ic_menu_help, MenuType.HELP),
            MenuItem(R.string.Contacts, R.drawable.ic_menu_contacts, MenuType.CONTACTS)
        )
    }
}