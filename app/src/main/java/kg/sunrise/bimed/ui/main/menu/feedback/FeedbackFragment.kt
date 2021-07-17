package kg.sunrise.bimed.ui.main.menu.feedback

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import kg.sunrise.bimed.base.fragment.BaseFragmentWithVM
import kg.sunrise.bimed.databinding.FragmentFeedbackBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedbackFragment : BaseFragmentWithVM<FragmentFeedbackBinding, FeedbackViewModel>() {
    override val viewModel: FeedbackViewModel by viewModel()

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
    ): FragmentFeedbackBinding {
        return FragmentFeedbackBinding.inflate(inflater)
    }

    override fun init() {
    }
}