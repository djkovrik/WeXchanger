package com.github.ybq.android.spinkit

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import com.github.ybq.android.spinkit.sprite.Sprite

/**
 * Created by ybq.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class SpinKitView(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
    ProgressBar(context, attrs, defStyleAttr, defStyleRes) {

    private val spinStyle: Style
    private var spinColor: Int = 0
    private var spinSprite: Sprite? = null

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.SpinKitViewStyle)
        : this(context, attrs, defStyleAttr, R.style.SpinKitView)

    init {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.SpinKitView, defStyleAttr,
            defStyleRes
        )
        spinStyle = Style.values()[a.getInt(R.styleable.SpinKitView_SpinKit_Style, 0)]
        spinColor = a.getColor(R.styleable.SpinKitView_SpinKit_Color, Color.WHITE)
        a.recycle()
        init()
        isIndeterminate = true
    }

    private fun init() {
        val sprite = SpriteFactory.create(spinStyle)?.apply { color = spinColor }
        indeterminateDrawable = sprite
    }

    override fun setIndeterminateDrawable(drawable: Drawable?) {
        if (drawable !is Sprite) {
            throw IllegalArgumentException("this drawable must be instanceof Sprite")
        }
        setIndeterminateDrawable(drawable)
    }

    private fun setIndeterminateDrawable(drawable: Sprite) {
        super.setIndeterminateDrawable(drawable)
        spinSprite = drawable
        if (spinSprite?.color == 0) {
            spinSprite?.color = spinColor
        }
        onSizeChanged(width, height, width, height)
        validateSprite()
    }

    override fun getIndeterminateDrawable(): Sprite? {
        return spinSprite
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        validateSprite()
    }

    private fun validateSprite() {
        if (visibility == View.VISIBLE) {
            spinSprite?.start()
        } else {
            spinSprite?.stop()
        }
    }


    fun setColor(color: Int) {
        this.spinColor = color
        spinSprite?.color = color
        invalidate()
    }

    override fun unscheduleDrawable(who: Drawable) {
        super.unscheduleDrawable(who)
        if (who is Sprite) {
            who.stop()
        }
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) {
            validateSprite()
        }
    }

    override fun onScreenStateChanged(screenState: Int) {
        super.onScreenStateChanged(screenState)
        if (screenState == View.SCREEN_STATE_OFF) {
            spinSprite?.stop()
        } else {
            spinSprite?.start()
        }
    }
}
