package com.project.kotlin9

import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.project.kotlin9.databinding.ActivityMainBinding
import com.project.kotlin9.util.User

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)


        val drawerLayout= binding.drawerLayout
        val navView: NavigationView = binding.navView

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_edit,R.id.nav_gallery,R.id.nav_music
            ), drawerLayout,
        )

        val navHostFragment =supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController =navHostFragment.navController
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener {
                _, destination, _ ->
            when(destination.id){
//                R.id.nav_home->{
//                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
//                    supportActionBar?.setHomeAsUpIndicator(null)
//                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
//                }
                R.id.nav_registation->{
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                else -> {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
            }
            if(!appBarConfiguration.topLevelDestinations.contains(destination.id)){
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    object DataManager{
        private var user:User?=null
        private var id:String?=null
        fun setUserData(user: User){
            this.user=user
        }
        fun getUserData():User?{
            return user
        }
        fun setId(id:String){
            this.id=id
        }
        fun getId():String?{
            return id
        }
        fun clearData(){
            user=null
            id=null
        }
    }
}