package com.project.kotlin9.ui.home

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.project.kotlin9.MainActivity
import com.project.kotlin9.R
import com.project.kotlin9.databinding.FragmentHomeBinding
import com.project.kotlin9.util.User

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        binding.registerButton .setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_registationFragment)
        }
        binding.loginButton.setOnClickListener {
            val login = binding.username.text.toString()
            val password = binding.password.text.toString()
            val db = Firebase.firestore
            db.collection("users")
                .whereEqualTo("login", login)
                .whereEqualTo("password", password)
                .get().addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        // Нет документов, удовлетворяющих запросу
                    } else {
                        val user: User? = documents.documents.get(0).toObject<User>()
                        if (user != null) {
                            Log.d("ad", user.date!!)
                        }
                        val doc = documents.documents.get(0)

                        val header = (requireActivity() as AppCompatActivity).findViewById<NavigationView>(R.id.nav_view)
                            ?.getHeaderView(0)
                        var text = header?.findViewById<TextView>(R.id.headertextNameSurname)
                        text!!.text = buildString {
                            append(doc["username"])
                            append(" ")
                            append(doc["usersurname"])
                        }
                        text=header?.findViewById<TextView>(R.id.headertextLogin)
                        text!!.text = buildString {
                            append(doc["login"])
                        }
                        text=header?.findViewById<TextView>(R.id.headertextDate)
                        text!!.text = buildString {
                            append(doc["date"])
                        }
                        if(doc["image"]!=null){
                            val blb:Blob= doc["image"] as Blob
                            val arr=blb.toBytes()
                            val bitmap= BitmapFactory.decodeByteArray(arr, 0, arr.size)
                            val image =header?.findViewById<ImageView>(R.id.headerimageView)
                            image?.setImageBitmap(bitmap)

                        }
                        if (user != null) {
                            MainActivity.DataManager.setUserData(user)
                            MainActivity.DataManager.setId(doc.id)
                        }
                        findNavController().navigate(R.id.nav_edit)
                    }
                }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}