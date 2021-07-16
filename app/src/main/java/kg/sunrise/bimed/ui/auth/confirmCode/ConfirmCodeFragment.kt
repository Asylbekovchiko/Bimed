package kg.sunrise.bimed.ui.auth.confirmCode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kg.sunrise.bimed.base.fragment.BaseFragmentWithVM
import kg.sunrise.bimed.databinding.FragmentConfirmCodeBinding
import kg.sunrise.bimed.ui.auth.AuthViewModel
import kg.sunrise.bimed.utils.extensions.navigateToMain
import org.koin.androidx.viewmodel.ext.android.viewModel


class ConfirmCodeFragment : BaseFragmentWithVM<FragmentConfirmCodeBinding, AuthViewModel>() {

    override val viewModel: AuthViewModel by viewModel()
    override val progressBar: ConstraintLayout by lazy {
        binding.inclProgress.clProgress
    }

    private val navArgs by navArgs<ConfirmCodeFragmentArgs>()

    override fun makeRequests() {
        viewModel.checkInternetConnection()
    }

    override fun findTypeOfObject(data: Any?) {
    }

    override fun successRequest() {
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConfirmCodeBinding {
        return FragmentConfirmCodeBinding.inflate(inflater)
    }

    override fun init() {
        initUI()
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            tvInvalidPhoneNumber.setOnClickListener {
                navigateBack()
            }

            btnConfirm.setOnClickListener {
                navigateToMain()
            }
        }
    }

    private fun initUI() {
        binding.tvPhoneNumber.text = navArgs.phoneNumber
    }

    private fun navigateBack() {
        findNavController().navigateUp()
    }
}