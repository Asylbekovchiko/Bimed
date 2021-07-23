package kg.sunrise.bimed.ui.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.card.MaterialCardView
import kg.sunrise.bimed.R
import kg.sunrise.bimed.utils.extensions.gone
import kg.sunrise.bimed.utils.extensions.visible

class ProductManipulationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr)  {

    private var itemType = ProductItemType.DETAIL

    private val tvOriginalPrice: TextView
    private val tvDiscountPrice: TextView

    private val ivFavourite: ImageView
    private val cardFavourite: MaterialCardView

    private val clBasketContent: MaterialCardView
    private val tvBasketPrice: TextView

    private val clButtonsContent: ConstraintLayout
    private val cardMinus: MaterialCardView
    private val cardPlus: MaterialCardView
    private val tvItemsCount: TextView

    private var itemsCount = 0 // default items count = 0
    private var isFavourite = false

    init {
        val styledAttrs =
            context.obtainStyledAttributes(attrs, R.styleable.ProductManipulationView)

        if (styledAttrs.hasValue(R.styleable.ProductManipulationView_itemType)) {
            val index = styledAttrs.getInt(R.styleable.ProductManipulationView_itemType, 0)
            itemType = ProductItemType.values()[index]
        }

        val layout = when (itemType) {
            ProductItemType.DETAIL -> R.layout.layout_product_detail_manipulation
            ProductItemType.ADAPTER -> R.layout.layout_product_rv_manipulation
        }

        styledAttrs.recycle()

        LayoutInflater.from(context).inflate(
            layout,
            this,
            true
        )

        tvOriginalPrice = findViewById(R.id.tv_original_price)
        tvDiscountPrice = findViewById(R.id.tv_discount_price)
        ivFavourite = findViewById(R.id.iv_like)
        cardFavourite = findViewById(R.id.btn_like)
        clBasketContent = findViewById(R.id.btn_basket)
        tvBasketPrice = findViewById(R.id.tv_basket_price)
        clButtonsContent = findViewById(R.id.cl_buttons)
        cardMinus = findViewById(R.id.btn_remove)
        cardPlus = findViewById(R.id.btn_add)
        tvItemsCount = findViewById(R.id.tv_items_count)
    }

    fun setItemsCount(count: Int) {
        tvItemsCount.text = count.toString()
    }

    fun addItem() {
        setItemsCount(itemsCount + 1)
    }

    fun removeItem() {
        if (itemsCount != 0) {
            setItemsCount(itemsCount - 1)
        } else {
            showBasketContent()
        }
    }

    fun setFavourite(isFavourite: Boolean) {
        val icon = if (isFavourite) R.drawable.ic_card_heart_checked
        else R.drawable.ic_card_heart_unchecked

        ivFavourite.setImageResource(icon)
    }

    fun addToFavourite() {
        setFavourite(true)
    }

    fun removeFromFavourite() {
        setFavourite(false)
    }

    fun setupPrice(originalPrice: Int, discountPrice: Int) {
        tvOriginalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        tvOriginalPrice.text = resources.getString(R.string.price_value, originalPrice.toString())
        tvDiscountPrice.text = resources.getString(R.string.price_value, discountPrice.toString())
    }

    fun basketPressed() {
        clBasketContent.gone()
        clButtonsContent.visible()

        addItem()
    }

    fun showBasketContent() {
        clBasketContent.visible()
        clButtonsContent.gone()
    }
}

enum class ProductItemType {
    DETAIL,
    ADAPTER
}