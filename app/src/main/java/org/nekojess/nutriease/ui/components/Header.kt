package org.nekojess.nutriease.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import org.nekojess.nutriease.databinding.HeaderBinding

class Header @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: HeaderBinding = HeaderBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setTitle(title: String){
        binding.headerTitle.text = title
    }

    fun setBackButtonListener(listener: () -> Unit){
        binding.headerBackButton.setOnClickListener {
            listener.invoke()
        }
    }

}
