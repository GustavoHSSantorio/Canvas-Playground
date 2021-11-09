package com.example.canvasplayground.view.chart

import android.animation.Animator
import android.content.Context
import android.content.res.TypedArray
import android.database.DataSetObserver
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.example.canvasplayground.R
import java.util.*

class LineChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.LineChartStyle,
    defStyleRes: Int = R.style.LineChartView
) : View(context, attrs, defStyleAttr), AnimatedChart {

    @ColorInt
    var lineColor: Int = 0
        set(value) {
            field = value
            linePaint.color = lineColor
            invalidate()
        }

    @ColorInt
    var fillColor: Int = 0
        set(value) {
            field = value
            setupFillPaint(value)
            invalidate()
        }

    var lineWidth: Float = 0.toFloat()
        set(value) {
            field = value
            linePaint.strokeWidth = field
            invalidate()
        }

    var animateChanges: Boolean = false
        set(value) {
            field = value
            configureAnimation()
        }

    var adapter: ChartAdapter? = null
        set(value) {
            field?.unregisterDataSetObserver(dataSetObserver)
            field = value
            field?.registerDataSetObserver(dataSetObserver)
            populatePathAndAnimate()
        }

    private var chartAnimator: ChartAnimator? = null

    private var linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        set(value) {
            field = value
            invalidate()
        }

    private var fillPaint = Paint(Paint.ANTI_ALIAS_FLAG and Paint.FILTER_BITMAP_FLAG)
        set(value) {
            field = value
            invalidate()
        }

    private var cornerRadius: Float = 0.toFloat()
        set(value) {
            field = value
            if (field != 0f) {
                linePaint.pathEffect = CornerPathEffect(field)
                fillPaint.pathEffect = CornerPathEffect(field)
            } else {
                linePaint.pathEffect = null
                fillPaint.pathEffect = null
            }
            invalidate()
        }

    @FillType
    private var fillType = FillType.NONE
        set(value) {
            field = value
            populatePath()
        }

    private val fillEdge: Float?
        get() {
            return when (fillType) {
                FillType.NONE -> null
                FillType.UP -> contentRect.top
                FillType.DOWN -> contentRect.bottom + FILL_PADDING_DOWN
                FillType.TOWARD_ZERO -> {
                    val zero = scaleHelper!!.getY(0f)
                    val bottom = contentRect.bottom
                    zero.coerceAtMost(bottom)
                }
                else -> throw IllegalStateException(
                    String.format(Locale.US, "Unknown fill-type: %d", fillType)
                )
            }
        }

    private val isFillInternal: Boolean
        get() = isFill

    private var isGradientFill: Boolean = false
        set(value) {
            field = value
            setupFillPaint(fillColor)
            invalidate()
        }

    private val linePath = Path()
    private val renderPath = Path()
    private var scaleHelper: ScaleHelper? = null
    private val contentRect = RectF()
    private var pathAnimator: Animator? = null

    private var animationDelay: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    private var isFill: Boolean
        get() {
            return when (fillType) {
                FillType.UP, FillType.DOWN, FillType.TOWARD_ZERO -> true
                else -> false
            }
        }
        set(fill) {
            fillType = when (fill) {
                true -> FillType.DOWN
                else -> FillType.NONE
            }
        }

    private val animator: Animator?
        get() = chartAnimator?.getAnimation(this)

    private val dataSetObserver = object : DataSetObserver() {
        override fun onChanged() {
            super.onChanged()
            populatePathAndAnimate()
        }

        override fun onInvalidated() {
            super.onInvalidated()
            clearData()
        }
    }

    init {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.LineChartView,
            defStyleAttr, defStyleRes
        )

        configureAttr(a)

        a.recycle()

        configurePaints()
        configureEditMode()
    }

    private fun configureAttr(a: TypedArray) {
        a.run {
            lineColor = getColor(
                R.styleable.LineChartView_linechart_lineColor,
                ContextCompat.getColor(context, R.color.purple_200)
            )

            fillColor = getColor(
                R.styleable.LineChartView_linechart_fillColor,
                ContextCompat.getColor(context, R.color.purple_200)
            )

            lineWidth = getDimension(R.styleable.LineChartView_linechart_lineWidth, 0f)
            cornerRadius = getDimension(R.styleable.LineChartView_linechart_cornerRadius, 0f)
            isGradientFill = a.getBoolean(R.styleable.LineChartView_linechart_gradientFill, false)
            animationDelay = getInt(R.styleable.LineChartView_linechart_aimationDelay, 0)
            animateChanges = getBoolean(R.styleable.LineChartView_linechart_animateChanges, false)
            fillType = getInt(R.styleable.LineChartView_linechart_fillType, FillType.NONE)
        }
    }

    private fun configurePaints() {
        linePaint.style = Paint.Style.STROKE
        linePaint.color = lineColor
        linePaint.strokeWidth = lineWidth
        linePaint.strokeMiter = 2f
        linePaint.strokeCap = Paint.Cap.SQUARE
        linePaint.pathEffect = CornerPathEffect(0f)
        setupFillPaint(fillColor)
    }

    private fun setupFillPaint(@ColorInt color : Int){
        fillPaint.style = Paint.Style.FILL
        if (!isGradientFill) {
            fillPaint.color = color
        } else {
            setGradientFillColor(color)
        }
    }

    private fun setGradientFillColor(color: Int) {
        val gradient = LinearGradient(
            0f,
            0f,
            0f,
            contentRect.top,
            color,
            Color.TRANSPARENT,
            Shader.TileMode.MIRROR
        )
        fillPaint.shader = gradient
    }


    private fun configureEditMode() {
        if (isInEditMode) {
            this.adapter = object : ChartAdapter() {
                private val yData = floatArrayOf(68f,22f,31f,57f,35f,79f,86f,47f,34f,55f,80f,72f,99f,66f,47f,42f,56f,64f,66f,80f,97f,10f,43f,12f,25f,71f,47f,73f,49f,36f)

                override val count: Int
                    get() = yData.size

                override fun getItem(index: Int): Any {
                    return yData[index]
                }

                override fun getY(index: Int): Float {
                    return yData[index]
                }
            }
        }
    }

    private fun configureAnimation() {
        if (animateChanges)
            chartAnimator = ChartAnimatorImp()
    }

    private fun populatePath() {
        if (adapter == null || width == 0 || height == 0)
            return

        if (adapter!!.count < 2) {
            clearData()
            return
        }

        scaleHelper = ScaleHelper(adapter!!, contentRect, lineWidth, isFillInternal)
        linePath.reset()

        for (i in 0 until adapter!!.count) {
            if (i < adapter!!.count) {
                val x = scaleHelper!!.getX(adapter!!.getX(i))
                val y = scaleHelper!!.getY(adapter!!.getY(i))

                if (i == 0)
                    linePath.moveTo(x, y)
                else
                    linePath.lineTo(x, y)
            }
        }

        fillEdge?.let {
            val lastX = scaleHelper!!.getX((adapter!!.count - 1).toFloat())
            val yByIndex = getYByIndex(adapter!!.count - 1)
            linePath.lineTo(lastX, yByIndex)
            linePath.lineTo(contentRect.right + FILL_PADDING_END, yByIndex)
            linePath.lineTo(contentRect.right + FILL_PADDING_END, it)
            linePath.lineTo(contentRect.left, it)
        }

        renderPath.reset()
        renderPath.addPath(linePath)
        invalidate()
    }

    private fun getYByIndex(index: Int) = scaleHelper!!.getY(adapter!!.getY(index))

    private fun populatePathAndAnimate() {
        populatePath()
        if (chartAnimator != null)
            doPathAnimation()
    }

    private fun clearData() {
        scaleHelper = null
        renderPath.reset()
        linePath.reset()
        invalidate()
    }

    private fun doPathAnimation() {
        pathAnimator?.cancel()
        pathAnimator = animator
        pathAnimator?.start()
    }

    private fun updateContentRect() {
        contentRect.set(
            paddingStart.toFloat(),
            paddingTop.toFloat(),
            (width - paddingEnd).toFloat(),
            (height - paddingBottom).toFloat()
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldW: Int, oldH: Int) {
        super.onSizeChanged(w, h, oldW, oldH)
        updateContentRect()
        populatePath()
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        super.setPadding(left, top, right, bottom)
        updateContentRect()
        populatePath()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isFill) {
            canvas.drawPath(renderPath, fillPaint)
        }

        canvas.drawPath(renderPath, linePaint)
    }

    override fun setAnimationPath(animationPath: Path) {
        renderPath.reset()
        renderPath.addPath(animationPath)
        renderPath.rLineTo(0f, 0f)
        invalidate()
    }

    override fun getPath(): Path =
        linePath

    companion object {
        const val FILL_PADDING_END = 100f
        const val FILL_PADDING_DOWN = 10f
    }
}