package kg.sunrise.bimed.ui.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.santalu.maskara.widget.MaskEditText
import kg.sunrise.bimed.R
import kg.sunrise.bimed.utils.extensions.getColorCompat
import kg.sunrise.bimed.utils.extensions.gone
import kg.sunrise.bimed.utils.extensions.hideKeyboard
import kg.sunrise.bimed.utils.extensions.visible

open class TextInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    protected lateinit var clLabelContainer: ConstraintLayout
    protected lateinit var tvLabel: TextView
    protected lateinit var tvStar: TextView
    protected lateinit var etInput: MaskEditText
    protected lateinit var tvError: TextView
    protected lateinit var ivIcon: ImageView

    protected val defaultErrorMessage: String

    init {
        inflateView()

        val styledAttrs =
            context.obtainStyledAttributes(attrs, R.styleable.TextInputView)

        if (styledAttrs.hasValue(R.styleable.TextInputView_label)) {
            clLabelContainer.visible()

            tvLabel.text = styledAttrs.getString(R.styleable.TextInputView_label)

            tvStar.visibility = if (styledAttrs.getBoolean(
                    R.styleable.TextInputView_isRequired,
                    false
                )
            ) View.VISIBLE else View.GONE
        }

        defaultErrorMessage = styledAttrs.getString(R.styleable.TextInputView_errorText) ?: ""
        tvError.text = defaultErrorMessage

        setUpEditText(styledAttrs)

        if (styledAttrs.hasValue(R.styleable.TextInputView_editTextIcon)) {
            ivIcon.setImageDrawable(styledAttrs.getDrawable(R.styleable.TextInputView_editTextIcon))
            ivIcon.visible()
        }

        styledAttrs.recycle()
    }

    fun setInputText(text: String) {
        etInput.setText(text)
    }

    fun getInputText(): String {
        return etInput.text.toString()
    }

    protected open fun setLayoutEnabled(isEnabled: Boolean) {
        etInput.isFocusable = isEnabled
    }

    fun addTextChangedListener(onChange: (editText: EditText) -> Unit) {
        etInput.addTextChangedListener {
            onChange(etInput)
        }
    }

    fun setOnIconClickListener(onClick: () -> Unit) {
        ivIcon.setOnClickListener {
            onClick()
        }
    }

    fun showError(message: String? = null) {
        val errorMessage = message ?: defaultErrorMessage
        tvError.text = errorMessage
        setErrorViewsColor()
    }

    fun hideError() {
        setDefaultViewsColor()
    }

    fun hideKeyboard() {
        etInput.hideKeyboard()
    }

    private fun setDefaultViewsColor() {
        setViewsColor(R.color.black)
        etInput.setBackgroundResource(R.drawable.shape_rad_16dp_border_1dp_light)
        tvError.gone()
    }

    private fun setErrorViewsColor() {
        setViewsColor(R.color.secondary_red)
        etInput.setBackgroundResource(R.drawable.shape_rad_16dp_border_1dp_secondary_red)
        tvError.visible()
    }

    private fun setViewsColor(@ColorRes colorRes: Int) {
        tvLabel.setTextColor(context.getColorCompat(colorRes))
        tvStar.setTextColor(context.getColorCompat(colorRes))
    }

    protected open fun inflateView() {
        LayoutInflater.from(context).inflate(
            R.layout.view_text_input,
            this,
            true
        )

        clLabelContainer = findViewById(R.id.cl_label_container)
        tvLabel = findViewById(R.id.tv_label)
        tvStar = findViewById(R.id.tv_star)
        etInput = findViewById(R.id.et_input)
        tvError = findViewById(R.id.tv_error)
        ivIcon = findViewById(R.id.iv_icon)
    }

    protected open fun setUpEditText(styledAttrs: TypedArray) {
        etInput.hint = styledAttrs.getString(R.styleable.TextInputView_hint)

        if (styledAttrs.hasValue(R.styleable.TextInputView_android_inputType)) {
            val inputType = styledAttrs.getInt(
                R.styleable.TextInputView_android_inputType,
                EditorInfo.TYPE_NULL
            )
            etInput.inputType = inputType
        }
    }
}