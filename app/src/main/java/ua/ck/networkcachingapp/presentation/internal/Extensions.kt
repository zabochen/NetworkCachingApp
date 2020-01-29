package ua.ck.networkcachingapp.presentation.internal

import android.view.View
import com.google.android.material.snackbar.Snackbar

class Extensions {
    companion object {

        fun showSnackbar(view: View, message: String, duration: Int = Snackbar.LENGTH_LONG) {
            Snackbar.make(view, message, duration)
                .show()
        }
    }
}