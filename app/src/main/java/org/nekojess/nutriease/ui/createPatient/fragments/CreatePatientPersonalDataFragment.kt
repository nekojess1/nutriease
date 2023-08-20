package org.nekojess.nutriease.ui.createPatient.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.FragmentCreatePatientPersonalDataBinding
import org.nekojess.nutriease.domain.dto.PatientDto
import org.nekojess.nutriease.util.DateTextWatcher
import org.nekojess.nutriease.util.StringUtils.EMPTY_STRING
import org.nekojess.nutriease.util.VerificationUtils.isNotEmptyText

class CreatePatientPersonalDataFragment : Fragment() {
    private val binding: FragmentCreatePatientPersonalDataBinding by lazy {
        FragmentCreatePatientPersonalDataBinding.inflate(layoutInflater)
    }

    private var userImage : Uri? = null

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                binding.createPatientFragmentUserImage.setImageURI(uri)
                userImage = uri
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureContinueButton()
        setStatesList()
        setGenreList()
        setDateChangedListener()
        setChangeImageListener()
        setHeaderConfig()
        return binding.root
    }

    private fun setChangeImageListener() {
        binding.createPatientFragmentChangePhoto.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_CODE
            )
        } else {
            pickImageLauncher.launch("image/*")
        }
    }

    private fun setHeaderConfig() {
        binding.createPatientPersonalDataFragmentHeader.setTitle(getString(R.string.register_patient))
        binding.createPatientPersonalDataFragmentHeader.setBackButtonListener { requireActivity().finish() }
    }

    private fun setDateChangedListener() {
        binding.createPatientFragmentBirthdayText.addTextChangedListener(DateTextWatcher())
    }

    private fun setStatesList() {
        val ufs = resources.getStringArray(R.array.states_br)
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_list_item,
            ufs
        )
        binding.createPatientFragmentUfText.setAdapter(adapter)
    }

    private fun setGenreList() {
        val genreList = resources.getStringArray(R.array.genre_list)
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_list_item,
            genreList
        )
        binding.createPatientFragmentGenreText.setAdapter(adapter)
    }

    private fun configureContinueButton() {
        binding.createPatientContinueButton.setOnClickListener {
            if (binding.createPatientFragmentName.isNotEmptyText()) {
                val action =
                    CreatePatientPersonalDataFragmentDirections
                        .actionCreatePatientPersonalDataFragmentToCreatePatientNutriInfoFragment(
                            getPersonalData()
                        )
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }

    private fun getPersonalData(): PatientDto {
        return PatientDto(
            name = binding.createPatientFragmentNameText.text.toString(),
            birthday = binding.createPatientFragmentBirthdayText.text.toString(),
            uf = binding.createPatientFragmentUfText.text.toString(),
            city = binding.createPatientFragmentCityText.text.toString(),
            email = binding.createPatientFragmentEmailText.text.toString(),
            phone = binding.createPatientFragmentPhoneText.text.toString(),
            genre = binding.createPatientFragmentGenreText.text.toString(),
            userImage = userImage?.toString() ?: EMPTY_STRING
        )
    }

    companion object {
        private const val PERMISSION_CODE = 1000
    }
}
