package org.nekojess.nutriease.ui.createPatient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.nekojess.nutriease.databinding.ActivityCreatePatientBinding

class CreatePatientActivity : AppCompatActivity() {
    private val binding: ActivityCreatePatientBinding by lazy {
        ActivityCreatePatientBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
