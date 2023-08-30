package org.nekojess.nutriease.ui.patientList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.ItemPatientListBinding
import org.nekojess.nutriease.domain.dto.PatientDto

class PatientListAdapter :
    RecyclerView.Adapter<PatientListAdapter.ViewHolder>() {

    private var patientClickListener: PatientClickListener? = null

    private var patientList: MutableList<PatientDto> = mutableListOf()

    fun updateList(newList: List<PatientDto>) {
        patientList.clear()
        patientList.addAll(newList)
        notifyDataSetChanged()
    }

    fun setPatientClickListener(listener: PatientClickListener) {
        patientClickListener = listener
    }

    inner class ViewHolder(binding: ItemPatientListBinding) : RecyclerView.ViewHolder(binding.root) {
        private val nameTextView = binding.adapterPatientListName
        private val patientPhoto = binding.adapterPatientListImage
        private val container = binding.adapterPatientListContainer
        private val localityTextView = binding.adapterPatientListLocality

        fun bind(patientData: PatientDto) {
            setTexts(patientData)
            setUserImage(patientData, itemView.context)
            container.setOnClickListener {
                patientClickListener?.onPatientClick(patientData)
            }
        }

        private fun setTexts(patientData: PatientDto) {
            nameTextView.text = patientData.name
            localityTextView.text = itemView.context.getString(
                R.string.patient_profile_locality,
                patientData.city,
                patientData.uf
            )
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
    ): PatientListAdapter.ViewHolder {
        val binding = ItemPatientListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientListAdapter.ViewHolder, position: Int) {
        holder.bind(patientList[position])
    }

    override fun getItemCount(): Int {
        return patientList.size
    }

    interface PatientClickListener {
        fun onPatientClick(patient: PatientDto)
    }

}
