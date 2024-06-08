package com.example.account_project_evaluation.Activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.account_project_evaluation.Adapter.CommonFieldDrawerAdapter
import com.example.account_project_evaluation.ApiHelper.ApiController
import com.example.account_project_evaluation.ApiHelper.ApiResponseListner
import com.example.account_project_evaluation.Fragment.*
import com.example.account_project_evaluation.Model.*
import com.example.account_project_evaluation.R
import com.example.account_project_evaluation.Utills.*
import com.example.account_project_evaluation.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.JsonElement
import com.stpl.antimatter.Utils.ApiContants

class DashboardActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var rcNav: RecyclerView
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        );

        //  val drawerLayout: DrawerLayout = binding.drawerLayout

        //  val navView: NavigationView = binding.navView
             val navBottomView: BottomNavigationView = binding.appBarMain.bottomNavView

        //val headerView: View = binding.navView.getHeaderView(0)

        // rcNav = headerView.findViewById<RecyclerView>(R.id.rcNaDrawer)

          navController = findNavController(R.id.nav_host_fragment_activity_main)

    /*    binding.appBarMain.appbarLayout.tvWalletBal.setText(
            PrefManager.getString(
                ApiContants.walletBalance,
                "0"
            )
        )*/

        binding.appBarMain.appbarLayout.ivMenu.setOnClickListener {
            //      drawerLayout.open()
        }

        //   handleRecyclerDrawer()
        //  navBottomView.setOnNavigationItemSelectedListener(mBottomNavigation)

        Log.d("token>>>>>", PrefManager.getString(ApiContants.AccessToken, ""))

        //    Log.d("asdad", PrefManager.getString(ApiContants.dayStatus, ""))

        //  setupActionBarWithNavController(navController, appBarConfiguration)
          navBottomView.setupWithNavController(navController)

     /*   val bottomNavigationView = findViewById<View>(R.id.bottom_nav_view) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(mBottomNavigation)
        GeneralUtilities.goToFragment(
            this,
            HomeFragment(),
            R.id.container,
            true
        )*/
    }

    fun setTitle(title: kotlin.String) {
        binding.appBarMain.appbarLayout.tvTitle.text = title
    }

    fun setWalletAmt(amt: kotlin.String) {
        binding.appBarMain.appbarLayout.tvWalletBal.text = ApiContants.currency+amt
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id != R.id.navigation_home) {
            navController.popBackStack(R.id.navigation_home, false)
        } else {
            super.onBackPressed()
        }
    }

      override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Start the LocationService when the app is closed
        //   startService(Intent(this, LocationService::class.java))
    }

}