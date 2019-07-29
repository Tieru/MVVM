package ru.vtb.android.mvvm.example.core.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

fun TextView.setOnTextChangedListener(listener: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            listener(text.toString())
        }
    })
}