package org.nekojess.nutriease.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.nekojess.nutriease.domain.OnboardingDto
import org.nekojess.tcc.databinding.ItemPageBinding

class OnboardingAdapter(private var data: List<OnboardingDto>) :
    RecyclerView.Adapter<OnboardingAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(binding: ItemPageBinding) : RecyclerView.ViewHolder(binding.root) {
        private val title = binding.itemPageTitle
        private val description = binding.itemPageDescription
        private val image = binding.itemPageImage

        fun bind(data: OnboardingDto) {
            title.text = data.title
            description.text = data.description
            data.imageId?.let {
                image.setImageResource(it)
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
