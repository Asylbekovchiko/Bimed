package kg.sunrise.bimed.ui.auth.registration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import kg.sunrise.bimed.base.fragment.BaseFragmentWithVM
import kg.sunrise.bimed.databinding.FragmentRegistrationBinding
import kg.sunrise.bimed.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : BaseFragmentWithVM<FragmentRegistrationBinding, AuthViewModel>() {

    override val viewModel: AuthViewModel by viewModel()
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
    ): FragmentRegistrationBinding {
        return FragmentRegistrationBinding.inflate(inflater)
    }

    override fun init() {
        initListeners()
    }

    private fun initListeners() {
        binding.btnRegister.setOnClickListener {
            navigateToSuccess()
        }

        binding.tvEnter.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun navigateToSuccess() {
        val action = RegistrationFragmentDirections.actionRegistrationFragmentToRegistrationCompletedFragment()
        findNavController().navigate(action)
    }
}