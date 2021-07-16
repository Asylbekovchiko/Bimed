package kg.sunrise.bimed.base.fragment

import androidx.viewbinding.ViewBinding
import kg.sunrise.bimed.base.viewModel.BaseViewModelPaging
import kotlinx.coroutines.Job

abstract class BaseFragmentWithPaging<Item: Any, Binding : ViewBinding, out VM : BaseViewModelPaging<Item>>
    : BaseFragmentWithVM<Binding, VM>() {

    protected var requestJob: Job? = null

    override fun makeRequests() {
        viewModel.clearPaging()
    }
}