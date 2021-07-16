package kg.sunrise.bimed.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import kg.sunrise.bimed.R
import kg.sunrise.bimed.base.activity.BaseActivity
import kg.sunrise.bimed.databinding.ActivityAuthBinding
import kg.sunrise.bimed.utils.extensions.transitionFade

class AuthActivity : BaseActivity<ActivityAuthBinding>() {

    override val navContainerId: Int = R.id.nav_container
    override val internetConnectionLayout: ConstraintLayout by lazy {
        binding.inclNoInternet.clNoInternet
    }

    override val bindingInflater: (LayoutInflater) -> ActivityAuthBinding = { it ->
        ActivityAuthBinding.inflate(it)
    }

    override fun onBackPressed() {
        val currentID = binding.navContainer.findNavController().currentDestination?.id
        val sourceID = R.id.registrationCompletedFragment

        if (currentID == sourceID) {
            return
        }

        super.onBackPressed()
        transitionFade()
    }
}