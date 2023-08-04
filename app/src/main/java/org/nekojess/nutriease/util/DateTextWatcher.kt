package org.nekojess.nutriease.util

import android.text.Editable
import android.text.TextWatcher
import java.text.ParseException

class DateTextWatcher : TextWatcher {
    private var isRunning = false
    private var isDeleting = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable?) {
        if (isRunning || isDeleting) {
            return
        }

        isRunning = true

        try {
            val formattedDate = formatText(editable)
            editable?.replace(0, editable.length, formattedDate)
        } catch (_: ParseException) {}

        isRunning = false
    }

    private fun formatText(editable: Editable?): String {
        val unformattedText = editable.toString().replace("[^\\d.]".toRegex(), "")
        val formattedDate = StringBuilder()

        var i = 0
        val size = unformattedText.length

        while (i < size) {
            when {
                i < 2 -> {
                    i = if (size >= 2) {
                        formattedDate.append(unformattedText.substring(i, 2))
                        formattedDate.append("/")
                        2
                    } else {
                        formattedDate.append(unformattedText.substring(i, size))
                        size
                    }
                }
                i < 4 -> {
                    i = if (size >= 4) {
                        formattedDate.append(unformattedText.substring(i, 4))
                        formattedDate.append("/")
                        4
                    } else {
                        formattedDate.append(unformattedText.substring(i, size))
                        size
                    }
                }
                i < 8 -> {
                    i = if (size >= 8) {
                        formattedDate.append(unformattedText.substring(i, 8))
                        8
                    } else {
                        formattedDate.append(unformattedText.substring(i, size))
                        size
                    }
                }
                else -> {
                    formattedDate.append(unformattedText.substring(i, size))
                    i = size
                }
            }
        }

        return formattedDate.toString()
    }
}