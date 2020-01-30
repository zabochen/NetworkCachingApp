package ua.ck.networkcachingapp.presentation.ui.loading

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import ua.ck.networkcachingapp.R

class LoadingDialogFragment(
    val cancel: () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val inflater = requireActivity().layoutInflater
            AlertDialog.Builder(it)
                .setView(inflater.inflate(R.layout.layout_loading, null))
                .setCancelable(false)
                .create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

}