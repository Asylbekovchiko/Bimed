package kg.sunrise.bimed.ui.main.qrCode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import kg.sunrise.bimed.R
import kg.sunrise.bimed.base.fragment.BaseFragmentWithVM
import kg.sunrise.bimed.databinding.FragmentQrCodeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class QrCodeFragment : BaseFragmentWithVM<FragmentQrCodeBinding, QrCodeViewModel>() {

    override val viewModel: QrCodeViewModel by viewModel()
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
    ): FragmentQrCodeBinding {
        return FragmentQrCodeBinding.inflate(inflater)
    }

    override fun init() {
        setupQrCode("hello")
    }

    private fun setupQrCode(qrCode: String) {
        val bitmap = viewModel.generateQrBitmap(qrCode, resources.getDimensionPixelSize(R.dimen.qr_code_size))
        binding.ivQrCode.setImageBitmap(bitmap)
    }
}