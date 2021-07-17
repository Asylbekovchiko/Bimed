package kg.sunrise.bimed.ui.main.menu

import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import kg.sunrise.bimed.base.fragment.BaseFragmentWithVM
import kg.sunrise.bimed.databinding.FragmentMenuBinding
import kg.sunrise.bimed.ui.auth.AuthActivity
import kg.sunrise.bimed.ui.main.menu.adapter.*
import kg.sunrise.bimed.utils.extensions.navigateToAuth
import kg.sunrise.bimed.utils.extensions.navigateToMain
import kg.sunrise.bimed.utils.preference.isAuthorized
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment
    : BaseFragmentWithVM<FragmentMenuBinding, MenuViewModel>(),
    MenuAdapterDelegate,
    MenuOptionAdapterDelegate {

    override val viewModel: MenuViewModel by viewModel()

    override val progressBar: ConstraintLayout by lazy {
        binding.inclProgress.clProgress
    }

    private val menuItemsAdapter = MenuAdapter(this)
    private val menuOptionAdapter = MenuOptionAdapter(this)

    override fun makeRequests() {
    }

    override fun findTypeOfObject(data: Any?) {
    }

    override fun successRequest() {
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentMenuBinding {
        return FragmentMenuBinding.inflate(inflater)
    }

    override fun init() {
        initAdapters()
    }

    private fun initAdapters() {
        val menuDTO = viewModel.getMenuDTO(isAuthorized(requireContext()))
        menuItemsAdapter.setData(menuDTO.menuItems)
        binding.rvMenuItems.adapter = menuItemsAdapter

        menuOptionAdapter.setData(menuDTO.menuOptions)
        binding.rvMenuOptions.adapter = menuOptionAdapter
    }

    override fun onItemClickListener(type: MenuType) {
        when (type) {
            MenuType.CONTACTS -> navigateToContacts()
            MenuType.HELP -> navigateToHelp()
            MenuType.ABOUT_COMPANY -> navigateToCompanyInfo()
            MenuType.NEWS -> navigateToNews()
            MenuType.FILIALS -> navigateToFilials()
            MenuType.ORDERS_HISTORY -> navigateToOrdersHistory()
            MenuType.FAVOURITES -> navigateToFavourites()
            MenuType.PROFILE -> navigateToProfile()
            MenuType.ENTER -> requireActivity().navigateToAuth()
        }
    }

    private fun navigateToProfile() {

    }

    private fun navigateToFavourites() {

    }

    private fun navigateToOrdersHistory() {
    }

    private fun navigateToFilials() {

    }

    private fun navigateToNews() {

    }

    private fun navigateToCompanyInfo() {

    }

    private fun navigateToHelp() {

    }

    private fun navigateToContacts() {

    }

    override fun onFeedItemClick() {
        val direction = MenuFragmentDirections.actionMenuFragmentToFeedbackFragment()
        findNavController().navigate(direction)
    }


}