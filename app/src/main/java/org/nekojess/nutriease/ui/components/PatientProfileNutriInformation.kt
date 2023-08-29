package org.nekojess.nutriease.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.FragmentPatientProfileNutriInfoBinding
import org.nekojess.nutriease.domain.dto.PatientDto

class PatientProfileNutriInformation : Fragment() {
    private val binding: FragmentPatientProfileNutriInfoBinding by lazy {
        FragmentPatientProfileNutriInfoBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.patientNutriInfoRecyclerView.adapter =
            ItemListInformationAdapter(getPatientNutritionalInfo())
        return binding.root
    }

    private fun getPatientNutritionalInfo(): ArrayList<ItemListInformationAdapter.Item> {
        val data = arguments?.getParcelable<PatientDto>(PATIENT_DATA)
        val itemList = ArrayList<ItemListInformationAdapter.Item>()
        val fields = listOf(
            getString(R.string.weight) to data?.weight,
            getString(R.string.height) to data?.height,
            getString(R.string.paf) to data?.paf,
            getString(R.string.target) to data?.target,
            getString(R.string.food_restriction) to data?.foodRestriction,
            getString(R.string.food_preference) to data?.foodPreference,
        )
        itemList.addAll(fields.mapNotNull { (title, description) ->
            description?.let {
                ItemListInformationAdapter.Item(title, it)
            }
        })
        return itemList
    }

    companion object {
        private const val PATIENT_DATA = "patientData"

        fun newInstance(data: PatientDto): PatientProfileNutriInformation {
            val fragment = PatientProfileNutriInformation()
            val args = Bundle()
            args.putParcelable(PATIENT_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }

}
