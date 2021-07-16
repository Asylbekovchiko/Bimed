package kg.sunrise.bimed.ui.auth.enter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import kg.sunrise.bimed.base.fragment.BaseFragmentWithVM
import kg.sunrise.bimed.databinding.FragmentEnterBinding
import kg.sunrise.bimed.ui.auth.AuthViewModel
import kg.sunrise.bimed.utils.constants.PHONE_NUMBER
import org.koin.androidx.viewmodel.ext.android.viewModel

class EnterFragment : BaseFragmentWithVM<FragmentEnterBinding, AuthViewModel>() {

    override val viewModel: AuthViewModel by viewModel()
    override val progressBar: ConstraintLayout by lazy {
        binding.inclProgress.clProgress
    }

    override fun makeRequests() {
        viewModel.checkInternetConnection()
    }

    override fun findTypeOfObject(data: Any?) {
        when (data) {
            PHONE_NUMBER -> {
                navigateToConfirmCode(binding.tivPhoneNumber.getPhoneNumber())
            }
        }
    }

    override fun successRequest() {
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEnterBinding {
        return FragmentEnterBinding.inflate(inflater)
    }

    override fun init() {
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            btnEnter.setOnClickListener {
                //todo: make check on phonenumber
                viewModel.enterWithPhoneNumber(binding.tivPhoneNumber.getPhoneNumber())
            }

            btnRegistration.setOnClickListener {
                navigateToRegistration()
            }
        }
    }

    private fun navigateToConfirmCode(phoneNumber: String) {
        val action = EnterFragmentDirections.actionEnterFragmentToConfirmCodeFragment(phoneNumber)
        findNavController().navigate(action)
    }

    private fun navigateToRegistration() {
        val action = EnterFragmentDirections.actionEnterFragmentToRegistrationFragment()
        findNavController().navigate(action)
    }
}