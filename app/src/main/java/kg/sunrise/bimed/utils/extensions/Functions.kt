package kg.sunrise.bimed.utils.extensions

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import kg.sunrise.bimed.utils.preference.setLocalization
import java.util.*

fun setLocale(context: Context, lang: String) {
    val locale = Locale(lang)
    Locale.setDefault(locale)
    val resources: Resources = context.resources
    val config: Configuration = resources.configuration
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.displayMetrics)

    setLocalization(context, lang)
}