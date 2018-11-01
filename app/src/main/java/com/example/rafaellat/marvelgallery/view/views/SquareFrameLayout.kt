package com.example.rafaellat.marvelgallery.view.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

//JvmOverloads annotation avoided telescoping constructors that are normally used to define a custom view in Android

class SquareFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec) // forcing the elem to always have the same dimensions
    }
}