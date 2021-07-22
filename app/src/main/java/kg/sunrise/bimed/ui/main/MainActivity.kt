package kg.sunrise.bimed.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kg.sunrise.bimed.R
import kg.sunrise.bimed.base.activity.BaseActivity
import kg.sunrise.bimed.databinding.ActivityMainBinding
import kg.sunrise.bimed.ui.auth.AuthActivity
import kg.sunrise.bimed.utils.extensions.setupWithNavController
import kg.sunrise.bimed.utils.extensions.transitionFade
import kg.sunrise.bimed.utils.preference.isAuthorized

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val internetConnectionLayout: ConstraintLayout by lazy {
        binding.inclNoInternet.clNoInternet
    }

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding = { it ->
        ActivityMainBinding.inflate(it)
    }

    override val navContainerId: Int = R.id.nav_host_container

    private var currentNavController: LiveData<NavController>? = null

    // todo: uncomment if have to implement showing bottom only in fragments below
//    private val navIdsForBottomNavShow = listOf(
//        R.id.menuFragment
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigation = binding.bottomNav

        val navGraphIds = listOf(
            R.navigation.menu_nav_graph,
            R.navigation.home_nav_graph,
            R.navigation.qr_code_nav_graph,
            R.navigation.categories_nav_graph,
            R.navigation.basket_nav_graph
        )

        val controller = bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        binding.bottomNav.setSelectedItemId(R.id.home_nav_graph)

        controller.observe(this) {
            it.addOnDestinationChangedListener { controller, destination, arguments ->
                //todo: Uncomment for reason explained above
//                if (destination.id in navIdsForBottomNavShow) {
//                    bottomNavigation.visible()
//                } else {
//                    bottomNavigation.gone()
//                }
            }
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            if (item.title == getString(R.string.Qr_code) && !isAuthorized(this)) {
                false
            } else {
                true
            }
        }

        currentNavController = controller
    }

    override fun onNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onBackPressed() {
        if (currentNavController?.value?.popBackStack() != true) {
            super.onBackPressed()
        }
    }

    private fun navigateToAuth() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        transitionFade()
    }
}