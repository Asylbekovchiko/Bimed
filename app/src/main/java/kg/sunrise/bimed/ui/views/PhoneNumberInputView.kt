package kg.sunrise.bimed.ui.views

import android.content.Context
import android.content.res.TypedArray
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hbb20.CountryCodePicker
import com.santalu.maskara.Mask
import com.santalu.maskara.MaskChangedListener
import com.santalu.maskara.MaskStyle
import com.santalu.maskara.widget.MaskEditText
import kg.sunrise.bimed.R
import java.io.BufferedReader
import java.io.InputStreamReader

open class PhoneNumberInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputView(context, attrs, defStyleAttr) {

    private lateinit var countryCodePicker: CountryCodePicker

    private var maskChangedListener: MaskChangedListener? = null

    private lateinit var _isPhoneNumberValid: MutableLiveData<Boolean>
    val isPhoneNumberValid: LiveData<Boolean> = _isPhoneNumberValid

    private lateinit var countryCodes: MutableMap<String, String>

    override fun inflateView() {
        LayoutInflater.from(context).inflate(
            R.layout.view_phone_number_input,
            this,
            true
        )

        clLabelContainer = findViewById(R.id.cl_label_container)
        tvLabel = findViewById(R.id.tv_label)
        tvStar = findViewById(R.id.tv_star)
        etInput = findViewById(R.id.et_input)
        tvError = findViewById(R.id.tv_error)
        ivIcon = findViewById(R.id.iv_icon)
        countryCodePicker = findViewById(R.id.country_code_picker)


        countryCodes = getCountries()

        countryCodePicker.setOnCountryChangeListener {
            setupMask()
        }
        _isPhoneNumberValid = MutableLiveData(false)

        etInput.addTextChangedListener {
            maskChangedListener?.let { listener ->
                _isPhoneNumberValid.value = listener.isDone
            }
        }
    }

    override fun setUpEditText(styledAttrs: TypedArray) {
        etInput.inputType = InputType.TYPE_CLASS_PHONE

        setupMask()
    }

    private fun setupMask() {
        val currentMask = countryCodes[countryCodePicker.selectedCountryNameCode + countryCodePicker.selectedCountryCode]

        if (!currentMask.isNullOrEmpty()) {

            val mask = Mask(currentMask, '#', MaskStyle.NORMAL)
            etInput.removeTextChangedListener(maskChangedListener)

            maskChangedListener = MaskChangedListener(mask)
            etInput.setText("")
            etInput.hint = currentMask.replace("#", "5")

            etInput.addTextChangedListener(maskChangedListener)
        }
    }

    fun getPhone(): String {
        return "+${countryCodePicker.selectedCountryCode}${etInput.unMasked}"
    }

    fun getPhoneWithMask(): String {
        return "+${countryCodePicker.selectedCountryCode} ${etInput.masked}"
    }

    override fun setLayoutEnabled(isEnabled: Boolean) {
        super.setLayoutEnabled(isEnabled)

        countryCodePicker.isEnabled = isEnabled
    }

    private fun getCountries(): MutableMap<String, String> {
        val inputStream =  this.context.assets.open("countries.txt")

        val read = BufferedReader(InputStreamReader(inputStream))
        val codeMap = mutableMapOf<String, String>()

        read.useLines {
            it.forEach { s ->
                if (s.isNotEmpty()) {
                    val arr = s.split(";")
                    val code = arr[1] + arr[0]
                    codeMap[code] = if (arr.size > 3) arr[3].replace("X", "#") else ""
                }
            }
        }

        return codeMap
    }
}