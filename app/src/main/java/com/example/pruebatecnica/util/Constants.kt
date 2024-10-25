package com.example.pruebatecnica.util

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

object Constants {
    const val BASE_URL = "https://api.chucknorris.io"
    const val ID_CATEGORY = "category"

    fun showMessage(view: View, context: Context, message: String){
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }
}