package com.project.kotlin9.ui.editPage

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.project.kotlin9.MainActivity
import com.project.kotlin9.R
import com.project.kotlin9.databinding.FragmentEditUserBinding
import com.project.kotlin9.util.User
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.Calendar


class HomeFragment : Fragment() {
    private var _binding: FragmentEditUserBinding? = null
    private var date =Calendar.getInstance()
    private lateinit var imagebytes: ByteArray
    private val binding get() = _binding!!
    private lateinit var pass:String
    private lateinit var  uri: Uri
    private var imagechanged =false

    var user:User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =FragmentEditUserBinding.inflate(inflater,container,false)
        val root: View = binding.root
        user =MainActivity.DataManager.getUserData()
        binding.firstName.setText(user!!.username)
        binding.username.setText(user!!.login)
        binding.lastName.setText(user!!.usersurname)
        binding.birthdate.setText(user!!.date)
        if(user!!.image!=null){
            val imagebytesdb= user!!.image!!.toBytes()
            binding.profilePhoto.setImageBitmap(BitmapFactory.decodeByteArray(imagebytesdb,0, imagebytesdb.size))
        }else {
            binding.profilePhoto.setImageResource(R.mipmap.ic_launcher)
        }

        binding.editProfileButton.setOnClickListener {
            findNavController().navigate(R.id.nav_editt)
        }

        binding.changePasswordButton.setOnClickListener {
            showChangePasswordDialog()
        }

        return root
    }


    private fun showChangePasswordDialog() {
        val builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_password_change, null)

        val currentPasswordEditText: EditText = dialogView.findViewById(R.id.oldPasswordEditText)
        val newPasswordEditText: EditText = dialogView.findViewById(R.id.newPasswordEditText)
        val confirmPasswordEditText: EditText = dialogView.findViewById(R.id.repeatPasswordEditText)

        builder.setView(dialogView)
            .setTitle("Смена пароля")
            .setPositiveButton("Сохранить") { dialog, which ->
                // Здесь добавьте логику для сохранения нового пароля
                val currentPassword = currentPasswordEditText.text.toString()
                val newPassword = newPasswordEditText.text.toString()
                val confirmPassword = confirmPasswordEditText.text.toString()
                // Проверка на правильность текущего пароля и совпадение нового и подтверждения
                Log.d("cat", user.toString())
                if (user!!.password.equals(currentPassword) && newPassword.isNotBlank() && confirmPassword.isNotBlank() &&
                    newPassword == confirmPassword
                ) {
                    user!!.password = newPassword

                    val db = Firebase.firestore
                    val id=MainActivity.DataManager.getId()
                    Toast.makeText(context, "Пароль успешно изменен", Toast.LENGTH_SHORT).show()
                    if (id != null) {
                        db.collection("users").document(id).set(user!!);
                        MainActivity.DataManager.setUserData(user!!)
                        findNavController().navigate(R.id.nav_edit)
                    }
                } else {
                    Toast.makeText(context, "Неверный текущий пароль или новый пароль не совпадает с подтверждением", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Отмена") { dialog, which ->
                // Закрываем диалоговое окно
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            uri= data?.data!!
            val imageFile = File(uri.path!!)
            val imageStream: InputStream = FileInputStream(imageFile)
            imagebytes= imageStream.readBytes()
            val user =MainActivity.DataManager.getUserData()
            if (!imagechanged && user != null) {
                if(user.image==null||!imagebytes.contentEquals(user.image!!.toBytes()))
                    imagechanged=true
            }
            binding.profilePhoto.setImageBitmap(BitmapFactory.decodeByteArray(imagebytes,0, imagebytes.size))

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                date.set(year, month, dayOfMonth)
                updateDateEditText()
            },
            date.get(Calendar.YEAR),
            date.get(Calendar.MONTH),
            date.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() // Ограничение на дату - сегодняшний день и ранее
        datePickerDialog.show()
    }
    private fun updateDateEditText() {
        val dateFormat = android.text.format.DateFormat.getDateFormat(context)
        binding.birthdate.setText(dateFormat.format(date.time))
    }
}