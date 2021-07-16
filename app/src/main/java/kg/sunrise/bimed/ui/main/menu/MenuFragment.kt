package kg.sunrise.bimed.ui.main.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kg.sunrise.bimed.base.fragment.BaseFragmentWithVM
import kg.sunrise.bimed.databinding.FragmentMenuBinding
import kg.sunrise.bimed.ui.main.menu.adapter.MenuAdapter
import kg.sunrise.bimed.ui.main.menu.adapter.MenuAdapterDelegate
import kg.sunrise.bimed.ui.main.menu.adapter.MenuType
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : BaseFragmentWithVM<FragmentMenuBinding, MenuViewModel>(), MenuAdapterDelegate {

    override val viewModel: MenuViewModel by viewModel()

    override val progressBar: ConstraintLayout by lazy {
        binding.inclProgress.clProgress
    }

    private val menuAdapter = MenuAdapter(this)

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
        initAdapter()
    }

    private fun initAdapter() {
        menuAdapter.setData(viewModel.getMenuItems(true))
        binding.rvMenuItems.adapter = menuAdapter
    }

    override fun onItemClickListener(type: MenuType) {
        Toast.makeText(requireContext(), type.name, Toast.LENGTH_SHORT).show()
    }
}