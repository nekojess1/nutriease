package org.nekojess.nutriease.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.nekojess.nutriease.databinding.ItemHomePatientBinding
import org.nekojess.nutriease.domain.dto.PatientDto

class HomePatientsAdapter(private var data: List<PatientDto>) :
    RecyclerView.Adapter<HomePatientsAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemHomePatientBinding) : RecyclerView.ViewHolder(binding.root) {
        private val tvName = binding.itemPageTitle
        private val userPhoto = binding.adapterHomePatientImage

        fun bind(data: PatientDto) {
            setTexts(itemView.context, data)
        }

        private fun setTexts(context: Context, data: PatientDto) {
            tvName.text = data.name
            if(data.userImage.isNotEmpty()){
                Glide.with(context)
                    .load(data.userImage)
                    .into(userPhoto)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePatientsAdapter.ViewHolder {
        val binding = ItemHomePatientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomePatientsAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
