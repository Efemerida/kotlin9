package com.project.kotlin9.ui.dialogs

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.project.kotlin9.R
import com.project.kotlin9.util.PasswordChangeListener

class PasswordChangeDialogFragment:DialogFragment()  {
    private var listener: PasswordChangeListener? = null
    fun setPasswordChangeListener(listener: PasswordChangeListener) {
        Log.d("adf","New LISTNER")
        this.listener = listener
    }
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is PasswordChangeListener) {
//            this.listener = context
//        } else {
//            throw RuntimeException("$context must implement PasswordChangeListener")
//        }
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_password_change, null)
            val oldPasswordEditText = dialogView.findViewById<EditText>(R.id.oldPasswordEditText)
            val newPasswordEditText = dialogView.findViewById<EditText>(R.id.newPasswordEditText)
            val repeatPasswordEditText = dialogView.findViewById<EditText>(R.id.repeatPasswordEditText)

            builder.setView(dialogView)
                .setTitle("Смена пароля")
                .setPositiveButton("Изменить") { dialog, _ ->
                    val oldPassword = oldPasswordEditText.text.toString()
                    val newPassword = newPasswordEditText.text.toString()
                    val repeatPassword = repeatPasswordEditText.text.toString()

                    if (oldPassword.isEmpty() || newPassword.isEmpty() || repeatPassword.isEmpty()) {
                        // Handle case when any field is empty
                        return@setPositiveButton
                    }

                    if (newPassword != repeatPassword) {
                        // Handle case when new passwords don't match
                        return@setPositiveButton
                    }

                    // Call listener method to change password
                    listener!!.onChangePassword(oldPassword, newPassword, repeatPassword)

                    dialog.dismiss()
                }
                .setNegativeButton("Отмена") { dialog, _ ->
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}