package org.nekojess.nutriease.ui.configuration

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.ActivitySettingsBinding
import java.util.Locale

class ConfigurationActivity: AppCompatActivity() {
    private val binding: ActivitySettingsBinding by lazy {
        ActivitySettingsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setChooseLanguage()
        setHeaderConfig()
        setContentView(binding.root)
    }

    override fun onRestart() {
        setChooseLanguage()
        super.onRestart()
    }

    private fun setHeaderConfig() {
        binding.configurationActivityHeader.setTitle(getString(R.string.settings))
        binding.configurationActivityHeader.setBackButtonListener { finish() }
    }


    private fun setChooseLanguage() {
        val genreList = resources.getStringArray(R.array.language_list)
        val adapter = ArrayAdapter(
            this,
            R.layout.simple_list_item,
            genreList
        )
        binding.changeLanguageText.setAdapter(adapter)
        binding.changeLanguageText.setOnItemClickListener { adapterView, view, i, l ->
            if (i == 0) selectLanguage("en")
            else selectLanguage("pt")
        }
    }

    private fun selectLanguage (language: String){
        val locale = Locale(language)
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        recreate()
    }
}
