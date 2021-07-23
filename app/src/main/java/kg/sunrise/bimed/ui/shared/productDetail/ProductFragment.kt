package kg.sunrise.bimed.ui.shared.productDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import kg.sunrise.bimed.base.fragment.BaseFragmentWithVM
import kg.sunrise.bimed.databinding.FragmentProductBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : BaseFragmentWithVM<FragmentProductBinding, ProductViewModel>() {

    override val viewModel: ProductViewModel by viewModel()

    override val progressBar: ConstraintLayout by lazy {
        binding.inclProgress.clProgress
    }

    override fun makeRequests() {
    }

    override fun findTypeOfObject(data: Any?) {
    }

    override fun successRequest() {
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProductBinding {
        return FragmentProductBinding.inflate(inflater)
    }

    override fun init() {
    }
}