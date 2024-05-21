package com.example.yourapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.yourapp.fragments.ApiMusicFragment
import com.example.yourapp.fragments.LocalMusicFragment


class MusicPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LocalMusicFragment()
            1 -> ApiMusicFragment()
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
