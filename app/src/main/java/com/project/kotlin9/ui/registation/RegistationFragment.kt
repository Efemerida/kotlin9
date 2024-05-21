package com.project.kotlin9.ui.registation

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.project.kotlin9.databinding.FragmentRegistationBinding
import com.project.kotlin9.util.User
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.Calendar


class RegistationFragment : Fragment() {

    private lateinit var imagebytes: ByteArray
    private lateinit var  uri: Uri
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    private var date =Calendar.getInstance()
    private var _binding: FragmentRegistationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistationBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.selectPhotoButton.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }

        binding.birthdate.setOnClickListener{
            showDatePickerDialog();
        }
        binding.registerButton.setOnClickListener {
            val db = Firebase.firestore
            FirebaseFirestore.setLoggingEnabled(true);

            if(binding.firstName.text.isBlank()){
                Toast.makeText(context, "Введите имя", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(binding.lastName.text.isBlank()){
                Toast.makeText(context, "Введите фамилию", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(binding.password.text.isBlank()){
                Toast.makeText(context, "Введите пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(binding.confirmPassword.text.isBlank()){
                Toast.makeText(context, "Подтвердите пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(!binding.confirmPassword.text.equals(binding.password.text)){
                Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(binding.birthdate.text.isBlank()){
                Toast.makeText(context, "Введите дату рождения", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userbld= User.Builder()
                .username(binding.firstName.text.toString())
                .usersurname(binding.lastName.text.toString())
                .login(binding.username.text.toString())
                .password(binding.password.text.toString())
                .date(binding.birthdate.text.toString())

            if(this::uri.isInitialized){

                userbld.image(imagebytes)
            }
            val user =userbld.build()
            db.collection("users").add(user).addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }
            findNavController().popBackStack()

        }
        return root
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
             uri= data?.data!!
            val imageFile = File(uri.path!!)
            val imageStream: InputStream = FileInputStream(imageFile)
            imagebytes= imageStream.readBytes()
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}