package kg.sunrise.bimed.ui.auth.registration

import android.view.LayoutInflater
import android.view.ViewGroup
import kg.sunrise.bimed.base.fragment.BaseFragment
import kg.sunrise.bimed.databinding.FragmentRegistrationCompletedBinding
import kg.sunrise.bimed.utils.extensions.navigateToMain


class RegistrationCompletedFragment : BaseFragment<FragmentRegistrationCompletedBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegistrationCompletedBinding {
        return FragmentRegistrationCompletedBinding.inflate(inflater)
    }

    override fun init() {
        initListeners()
    }

    private fun initListeners() {
        binding.btnMain.setOnClickListener {
            navigateToMain()
        }
    }
}