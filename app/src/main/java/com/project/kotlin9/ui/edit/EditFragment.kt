package com.project.kotlin9.ui.edit

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.project.kotlin9.MainActivity
import com.project.kotlin9.databinding.FragmentEditBinding
import com.project.kotlin9.util.User
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.Calendar

class EditFragment : Fragment(){


    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var user: User
    private lateinit var  uri: Uri
    private lateinit var imagebytes: ByteArray
    private var date =Calendar.getInstance()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = MainActivity.DataManager.getUserData()!!

        // Заполнение данными полей
        binding.firstName.setText(user.username)
        binding.lastName.setText(user.usersurname)
        binding.birthdate.setText(user.date)
        binding.username.setText(user.login)

        // Обработка кнопки сохранения изменений
        binding.saveButton.setOnClickListener {
            saveChanges()
        }

        binding.selectPhotoButton.setOnClickListener{
            showDatePickerDialog()
        }

        binding.selectPhotoButton.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }
    }
    private fun saveChanges() {
        val newFirstName = binding.firstName.text.toString()
        val newLastName = binding.lastName.text.toString()
        val newBirthDate = binding.birthdate.text.toString()

        // Обновление данных пользователя
        user.username = newFirstName
        user.usersurname = newLastName
        user.date = newBirthDate

        val db = Firebase.firestore
        FirebaseFirestore.setLoggingEnabled(true);

        if(binding.firstName.text.isBlank()){
            Toast.makeText(context, "Введите имя", Toast.LENGTH_SHORT).show()
        }
        else if(binding.lastName.text.isBlank()){
            Toast.makeText(context, "Введите фамилию", Toast.LENGTH_SHORT).show()
        }
        else if(binding.birthdate.text.isBlank()){
            Toast.makeText(context, "Введите дату рождения", Toast.LENGTH_SHORT).show()
        }
        else {
            val userbld = User.Builder()
                .username(binding.firstName.text.toString())
                .usersurname(binding.lastName.text.toString())
                .login(binding.username.text.toString())
                .password(user.password!!)
                .date(binding.birthdate.text.toString())

            if (this::uri.isInitialized) {
                userbld.image(imagebytes)
            }
            val user = userbld.build()
            val db = Firebase.firestore
            val id=MainActivity.DataManager.getId()
            if (id != null) {
                db.collection("users").document(id).set(user!!);
                MainActivity.DataManager.setUserData(user!!)
                findNavController().popBackStack()
            }
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