package kg.sunrise.bimed.ui.main.qrCode

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kg.sunrise.bimed.R

class QrCodeFragment : Fragment() {

    companion object {
        fun newInstance() = QrCodeFragment()
    }

    private lateinit var viewModel: QrCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_qr_code, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QrCodeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}