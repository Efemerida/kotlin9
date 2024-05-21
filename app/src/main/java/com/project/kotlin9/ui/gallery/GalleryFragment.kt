package com.project.kotlin9.ui.gallery

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.LocationServices
import com.project.kotlin9.databinding.FragmentGalleryBinding
import org.json.JSONException


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    companion object {
        private const val REQUEST_CODE_LOCATION_PERMISSION = 1001
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.fetchWeatherButton.setOnClickListener {
            if (hasLocationPermission()) {
                getCurrentLocation()
            } else {
                requestLocationPermission()
            }
        }

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestLocationPermission()
    }
    private fun hasLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE_LOCATION_PERMISSION
        )
    }
    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val lat = it.latitude
                val lon = it.longitude
                getWeather(lat, lon)
            }
        }
    }
    private fun getWeather(lat: Double, lon: Double) {
        val apiKey = "36ac2f208e45408eb22173705230508"
        val url = "https://api.weatherapi.com/v1/current.json?key=$apiKey&q=$lat,$lon&lang=ru"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    println(response)
                    val main = response.getJSONObject("location")
                    val region = main.getString("name")
                    val current = response.getJSONObject("current")
                    val temp_c = current.getString("temp_c")
                    val condition = current.getJSONObject("condition")
                    val text = condition.getString("text")
                    val weatherInfo = "Текущая температура: $temp_c\nОсадки: $text"
                    binding.currentTempTextView.text = weatherInfo
                    binding.locationTextView.text = region.toString()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Log.e("WeatherFragment", "Error: ${error.message}")
            }
        )

        Volley.newRequestQueue(requireContext()).add(request)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}