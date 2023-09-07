package org.nekojess.nutriease.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.FragmentPatientProfilePersonalDataBinding
import org.nekojess.nutriease.domain.dto.PatientDto

class PatientProfilePersonalData : Fragment() {
    private val binding: FragmentPatientProfilePersonalDataBinding by lazy {
        FragmentPatientProfilePersonalDataBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.patientProfileInfoRecyclerView.adapter =
            ItemListInformationAdapter(getPatientData())
        return binding.root
    }

    private fun getPatientData(): ArrayList<ItemListInformationAdapter.Item> {
        val data = arguments?.getParcelable<PatientDto>(PATIENT_DATA)
        val itemList = ArrayList<ItemListInformationAdapter.Item>()
        val fields = listOf(
            getString(R.string.sign_up_activity_birthday) to data?.birthday,
            getString(R.string.genre) to data?.genre,
            getString(R.string.phone) to data?.phone,
            getString(R.string.email) to data?.email
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

        fun newInstance(data: PatientDto): PatientProfilePersonalData {
            val fragment = PatientProfilePersonalData()
            val args = Bundle()
            args.putParcelable(PATIENT_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }
}
