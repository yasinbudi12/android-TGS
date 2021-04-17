package id.conversion.app.widget

import androidx.annotation.RestrictTo
import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.ShapePath

class TopCurvedEdgeTreatment(
    var fabCradleMargin: Float, var fabCradleRoundedCornerRadius: Float, var cradleVerticalOffset: Float
) : EdgeTreatment(), Cloneable {
    /**
     * Returns current fab diameter in pixels.
     *
     * @hide
     */
    /**
     * Sets the fab diameter the size of the fab in pixels.
     *
     * @hide
     */
    @get:RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    var fabDiameter = 0f
    /**
     * Returns vertical offset, in pixels, of the [FloatingActionButton] being cradled. An
     * offset of 0 indicates the vertical center of the [FloatingActionButton] is positioned on
     * the top edge.
     */
    /**
     * Returns the horizontal offset, in pixels, of the cradle from center.
     *
     * @hide
     */
    /** Sets the horizontal offset, in pixels, of the cradle from center.  */
    @get:RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    var horizontalOffset: Float

    override fun getEdgePath(
        length: Float, center: Float, interpolation: Float, shapePath: ShapePath
    ) {
        if (fabDiameter == 0f) {
            // There is no cutout to draw.
            shapePath.lineTo(length, 0f)
            return
        }
        val cradleDiameter = fabCradleMargin * 2 + fabDiameter
        val cradleRadius = cradleDiameter / 2f
        val roundedCornerOffset = interpolation * fabCradleRoundedCornerRadius
        val middle = center + horizontalOffset

        // The center offset of the cutout tweens between the vertical offset when attached, and the
        // cradleRadius as it becomes detached.
        val verticalOffset = interpolation * cradleVerticalOffset + (1 - interpolation) * cradleRadius
        val verticalOffsetRatio = verticalOffset / cradleRadius
        if (verticalOffsetRatio >= 1.0f) {
            // Vertical offset is so high that there's no curve to draw in the edge, i.e., the fab is
            // actually above the edge so just draw a straight line.
            shapePath.lineTo(length, 0f)
            return  // Early exit.
        }

        // Calculate the path of the cutout by calculating the location of two adjacent circles. One
        // circle is for the rounded corner. If the rounded corner circle radius is 0 the corner will
        // not be rounded. The other circle is the cutout.

        // Calculate the X distance between the center of the two adjacent circles using pythagorean
        // theorem.
        val distanceBetweenCenters = cradleRadius + roundedCornerOffset
        val distanceBetweenCentersSquared = distanceBetweenCenters * distanceBetweenCenters
        val distanceY = verticalOffset + roundedCornerOffset
        val distanceX = Math.sqrt((distanceBetweenCentersSquared - distanceY * distanceY).toDouble()).toFloat()

        // Calculate the x position of the rounded corner circles.
        val leftRoundedCornerCircleX = middle - distanceX
        val rightRoundedCornerCircleX = middle + distanceX

        // Calculate the arc between the center of the two circles.
        val cornerRadiusArcLength = Math.toDegrees(Math.atan((distanceX / distanceY).toDouble())).toFloat()
        val cutoutArcOffset = ARC_QUARTER - cornerRadiusArcLength

        // Draw the starting line up to the left rounded corner.
        shapePath.lineTo( /* x= */leftRoundedCornerCircleX, 0f)

        // Draw the arc for the left rounded corner circle. The bounding box is the area around the
        // circle's center which is at `(leftRoundedCornerCircleX, roundedCornerOffset)`.
        shapePath.addArc( /* left= */
            leftRoundedCornerCircleX - roundedCornerOffset, 0f,  /* right= */
            leftRoundedCornerCircleX + roundedCornerOffset,  /* bottom= */
            roundedCornerOffset * 2,  /* startAngle= */
            ANGLE_UP.toFloat(),  /* sweepAngle= */
            cornerRadiusArcLength
        )

        // Draw the cutout circle.
        shapePath.addArc( /* left= */
            middle - cradleRadius,  /* top= */
            -cradleRadius - verticalOffset,  /* right= */
            middle + cradleRadius,  /* bottom= */
            cradleRadius - verticalOffset,  /* startAngle= */
            ANGLE_LEFT - cutoutArcOffset,  /* sweepAngle= */
            cutoutArcOffset * 2 - ARC_HALF
        )

        // Draw an arc for the right rounded corner circle. The bounding box is the area around the
        // circle's center which is at `(rightRoundedCornerCircleX, roundedCornerOffset)`.
        shapePath.addArc( /* left= */
            rightRoundedCornerCircleX - roundedCornerOffset, 0f,  /* right= */
            rightRoundedCornerCircleX + roundedCornerOffset,  /* bottom= */
            roundedCornerOffset * 2,  /* startAngle= */
            ANGLE_UP - cornerRadiusArcLength,  /* sweepAngle= */
            cornerRadiusArcLength
        )

        // Draw the ending line after the right rounded corner.
        shapePath.lineTo( /* x= */length, 0f)
    }

    companion object {
        private const val ARC_QUARTER = 90
        private const val ARC_HALF = 180
        private const val ANGLE_UP = 270
        private const val ANGLE_LEFT = 180
    }

    /**
     * @param fabMargin the margin in pixels between the cutout and the fab.
     * @param roundedCornerRadius the radius, in pixels, of the rounded corners created by the cutout.
     * A value of 0 will produce a sharp cutout.
     * @param cradleVerticalOffset vertical offset, in pixels, of the [FloatingActionButton]
     * being cradled. An offset of 0 indicates the vertical center of the [     ] is positioned on the top edge.
     */
    init {
        cradleVerticalOffset = cradleVerticalOffset
        require(cradleVerticalOffset >= 0) { "cradleVerticalOffset must be positive." }
        horizontalOffset = 0f
    }
}