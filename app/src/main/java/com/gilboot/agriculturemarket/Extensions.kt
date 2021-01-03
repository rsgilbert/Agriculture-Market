package com.gilboot.agriculturemarket

import android.app.Activity
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.gilboot.agriculturemarket.database.MarketDatabase
import com.google.android.material.snackbar.Snackbar

fun Activity.shortSnackbar(text: String) {
    Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT)
        .show()
}

fun Fragment.shortSnackbar(text: String) {
    requireActivity().shortSnackbar(text)
}


fun Activity.longSnackbar(text: String) {
    Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
        .show()
}

fun Fragment.longSnackbar(text: String) {
    requireActivity().longSnackbar(text)
}

val Activity.repository: Repository
    get() {
        val marketDatabase = MarketDatabase.getInstance(this)
        return Repository(marketDatabase.marketDao)
    }

val Fragment.repository: Repository
    get() = requireActivity().repository


fun EditText.isNotValid() = text.isNullOrBlank()