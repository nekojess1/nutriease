package org.nekojess.nutriease.ui.patientList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.ActivityPatientListBinding
import org.nekojess.nutriease.domain.dto.PatientDto
import org.nekojess.nutriease.ui.components.PatientProfileBottomSheet
import org.nekojess.nutriease.ui.createPatient.CreatePatientActivity
import org.nekojess.nutriease.util.PatientListUtil.sortByName

class PatientListActivity : AppCompatActivity(), PatientListAdapter.PatientClickListener {

    private val binding: ActivityPatientListBinding by lazy {
        ActivityPatientListBinding.inflate(layoutInflater)
    }

    private var patientList: List<PatientDto> = emptyList()

    private val adapter = PatientListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPatientList()
        setHeaderConfig()
        setAddPatientButton()
        configPatientList()
        setContentView(binding.root)
    }

    private fun initPatientList() {
        patientList = intent.getParcelableArrayListExtra(PATIENT_LIST) ?: emptyList()
    }

    private fun configPatientList() {
        adapter.patientList = patientList.sortByName()
        adapter.setPatientClickListener(this)
        binding.patientListActivityRecyclerView.adapter = adapter
        setNameFilter()
    }

    private fun setNameFilter() {
        binding.patientListActivityNameText.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val filteredPatientList =
                        patientList.filter { it.name.contains(s.toString(), ignoreCase = true) }
                    adapter.patientList = filteredPatientList.sortByName()
                }
                override fun afterTextChanged(s: Editable?) {}
            }
        )
    }

    private fun setAddPatientButton() {
        binding.patientListActivityAddPatientButton.setOnClickListener {
            startActivity(Intent(this, CreatePatientActivity::class.java))
        }
    }

    private fun setHeaderConfig() {
        binding.patientListActivityHeader.setTitle(getString(R.string.patients_list))
        binding.patientListActivityHeader.setBackButtonListener { finish() }
    }

    override fun onPatientClick(patient: PatientDto) {
        val bottomSheetFragment = PatientProfileBottomSheet(patient)
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    companion object {
        const val PATIENT_LIST = "patientList"

        fun newInstance(context: Context, patientList: List<PatientDto>): Intent {
            val intent = Intent(context, PatientListActivity::class.java)
            intent.putParcelableArrayListExtra(PATIENT_LIST, ArrayList(patientList))
            return intent
        }
    }

}
