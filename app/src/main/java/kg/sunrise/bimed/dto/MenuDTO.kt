package kg.sunrise.bimed.dto

import kg.sunrise.bimed.ui.main.menu.adapter.MenuItem
import kg.sunrise.bimed.ui.main.menu.adapter.MenuOption

data class MenuDTO (
    val menuItems: ArrayList<MenuItem>,
    val menuOptions: ArrayList<MenuOption>
)