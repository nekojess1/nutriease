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

    private var patientClickListener: PatientClickListener? = null

    fun setPatientClickListener(listener: PatientClickListener) {
        patientClickListener = listener
    }

    inner class ViewHolder(binding: ItemHomePatientBinding) : RecyclerView.ViewHolder(binding.root) {
        private val nameTextView = binding.adapterHomePatientName
        private val patientPhoto = binding.adapterHomePatientImage
        private val container = binding.adapterHomePatientContainer

        fun bind(patientData: PatientDto) {
            setTexts(patientData)
            setUserImage(patientData, itemView.context)
            container.setOnClickListener {
                patientClickListener?.onPatientClick(patientData)
            }
        }

        private fun setTexts(patientData: PatientDto) {
            nameTextView.text = patientData.name
        }

        private fun setUserImage(patientData: PatientDto, context: Context) {
            if (patientData.patientPhoto.isNotEmpty()) {
                Glide.with(context)
                    .load(patientData.patientPhoto)
                    .into(patientPhoto)
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

    interface PatientClickListener {
        fun onPatientClick(patient: PatientDto)
    }

}
