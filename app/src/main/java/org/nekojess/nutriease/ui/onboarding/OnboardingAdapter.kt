package org.nekojess.nutriease.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.nekojess.nutriease.databinding.ItemPageBinding
import org.nekojess.nutriease.domain.dto.OnboardingDto

class OnboardingAdapter(private var data: List<OnboardingDto>) :
    RecyclerView.Adapter<OnboardingAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(binding: ItemPageBinding) : RecyclerView.ViewHolder(binding.root) {
        private val tvTitle = binding.itemPageTitle
        private val tvDescription = binding.itemPageDescription
        private val ivIllustration = binding.itemPageImage
        private val btnContinue = binding.itemPageButton

        fun bind(data: OnboardingDto) {
            setTexts(data)
            setImage(data)
            setButton(data)
        }

        private fun setTexts(data: OnboardingDto) {
            tvTitle.text = data.title
            tvDescription.text = data.description
            tvDescription.isVisible = data.description.isNotEmpty()
        }

        private fun setButton(data: OnboardingDto) {
            data.onClickListener?.let { listener ->
                btnContinue.isVisible = true
                btnContinue.setOnClickListener { listener.invoke() }
            }
        }

        private fun setImage(data: OnboardingDto) {
            data.imageId?.let {
                ivIllustration.setImageResource(it)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnboardingAdapter.Pager2ViewHolder {
        val binding = ItemPageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Pager2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingAdapter.Pager2ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
