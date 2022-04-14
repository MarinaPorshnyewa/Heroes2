package com.example.loginandregistration.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import com.example.loginandregistration.R
import com.example.loginandregistration.databinding.FragmentSmallScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TEXT_BUTTON = "Add to favorite"

class CustomMyView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    var binding: FragmentSmallScreenBinding

    init {
        binding = FragmentSmallScreenBinding.inflate(
            LayoutInflater.from(context), this
        )
        attrs?.let { setAttributes(it) }
    }

    private fun setAttributes(attrs: AttributeSet) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.SmallScreen, 0, 0)
        try {
            binding.title.text = TEXT_BUTTON
            binding.smallImage.setImageResource(R.drawable.ic_star)

        } finally {
            ta.recycle()
        }
    }


}