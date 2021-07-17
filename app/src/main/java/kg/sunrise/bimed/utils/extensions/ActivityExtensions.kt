package kg.sunrise.bimed.utils.extensions

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import kg.sunrise.bimed.R
import kg.sunrise.bimed.ui.auth.AuthActivity
import kg.sunrise.bimed.ui.main.MainActivity

fun Activity.transitionFade() {
    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
}


fun Activity.navigateToMain() {
    val intent = Intent(this, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(intent)
    transitionFade()
}
fun Activity.navigateToAuth() {
    val intent = Intent(this, AuthActivity::class.java)
    startActivity(intent)
    transitionFade()
}