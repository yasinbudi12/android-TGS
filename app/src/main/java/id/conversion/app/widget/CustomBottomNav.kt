package id.conversion.app.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import id.conversion.app.R


@SuppressLint("RestrictedApi")
class CustomBottomNav @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {

    private var materialShapeDrawable: MaterialShapeDrawable

    private var fabSize                                  = 0F
    private var fabCradleMargin                          = 0F
    private var fabCradleRoundedCornerRadius             = 0F
    private var cradleVerticalOffset                     = 0F

    init {

        val ta                       = context.theme.obtainStyledAttributes(attrs, R.styleable.CustomBottomNav, 0, 0)
        fabSize                      = ta.getDimension(R.styleable.CustomBottomNav_fab_size, 0F)
        fabCradleMargin              = ta.getDimension(R.styleable.CustomBottomNav_fab_cradle_margin, 0F)
        fabCradleRoundedCornerRadius = ta.getDimension(R.styleable.CustomBottomNav_fab_cradle_rounded_corner_radius, 0F)
        cradleVerticalOffset         = ta.getDimension(R.styleable.CustomBottomNav_cradle_vertical_offset, 0F)

        val topCurvedEdgeTreatment  = TopCurvedEdgeTreatment(fabCradleMargin, fabCradleRoundedCornerRadius, cradleVerticalOffset).apply {
            fabDiameter = fabSize
        }

        val shapeAppearanceModel = ShapeAppearanceModel.Builder()
            .setTopEdge(topCurvedEdgeTreatment)
            .build()

        materialShapeDrawable    = MaterialShapeDrawable(shapeAppearanceModel).apply {
            setTint(ContextCompat.getColor(context, R.color.colorWhite))
            paintStyle              = Paint.Style.FILL_AND_STROKE
            fillColor               = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorWhite))
            val colorInt            = ContextCompat.getColor(context, R.color.green_cyan_stroke)
            val colorStateList      = ColorStateList.valueOf(colorInt)
            strokeColor             = colorStateList
            strokeWidth             = 0.8f
        }
        background                      = materialShapeDrawable
    }

}