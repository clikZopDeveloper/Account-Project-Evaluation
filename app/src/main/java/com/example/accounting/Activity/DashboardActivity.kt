package com.example.accounting.Activity

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
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.Adapter.CommonFieldDrawerAdapter
import com.example.accounting.Fragment.ExpensesFragment
import com.example.accounting.Fragment.HomeFragment
import com.example.accounting.Fragment.SettingFragment
import com.example.accounting.Fragment.WalletLadgerFragment
import com.example.accounting.Model.*
import com.example.accounting.R
import com.example.accounting.Utills.*
import com.example.accounting.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stpl.antimatter.Utils.ApiContants

class DashboardActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var rcNav: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        );

        //  val drawerLayout: DrawerLayout = binding.drawerLayout
        //  val navView: NavigationView = binding.navView
        //     val navBottomView: BottomNavigationView = binding.appBarMain.bottomNavView

        //val headerView: View = binding.navView.getHeaderView(0)

        // rcNav = headerView.findViewById<RecyclerView>(R.id.rcNaDrawer)


        //  val navController = findNavController(R.id.nav_host_fragment_activity_main)

        binding.appBarMain.appbarLayout.tvWalletBal.setText(
            PrefManager.getString(
                ApiContants.walletBalance,
                "0"
            )
        )

        binding.appBarMain.appbarLayout.ivMenu.setOnClickListener {
            //      drawerLayout.open()
        }

        //   handleRecyclerDrawer()
        //  navBottomView.setOnNavigationItemSelectedListener(mBottomNavigation)

        Log.d("token>>>>>", PrefManager.getString(ApiContants.AccessToken, ""))

        //    Log.d("asdad", PrefManager.getString(ApiContants.dayStatus, ""))

        //  setupActionBarWithNavController(navController, appBarConfiguration)
        //  navBottomView.setupWithNavController(navController)

        val bottomNavigationView =
            findViewById<View>(R.id.bottom_nav_view) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(mBottomNavigation)
        GeneralUtilities.goToFragment(
            this,
            HomeFragment(),
            R.id.container,
            true
        )

    }

    fun setTitle(title: kotlin.String) {
        binding.appBarMain.appbarLayout.tvTitle.text = title
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            // only show dialog while there's back stack entry
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            // or just go back to main activity
            super.onBackPressed();
        }
    }

    private val mBottomNavigation =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    //mRecyclerView.releasePlayer();
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_expenses -> {
                    replaceFragment(ExpensesFragment())
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_wallet_ledger -> {
                    replaceFragment(WalletLadgerFragment())
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_setting -> {
                    replaceFragment(SettingFragment())
                    //   mRecyclerView.releasePlayer();
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }


    fun handleRecyclerDrawer() {
        rcNav.layoutManager = LinearLayoutManager(this)
        var mAdapter = CommonFieldDrawerAdapter(this, getMenus(), object :
            RvClickListner {
            override fun clickPos(pos: Int) {
            }
        })
        rcNav.adapter = mAdapter
        // rvMyAcFiled.isNestedScrollingEnabled = false

    }

    private fun getMenus(): ArrayList<MenuModelBean> {
        var menuList = ArrayList<MenuModelBean>()
        menuList.add(MenuModelBean(0, "Lead", "", R.drawable.ic_dashbord))
        menuList.add(MenuModelBean(1, "Task", "", R.drawable.ic_dashbord))
        menuList.add(MenuModelBean(2, "My Customers", "", R.drawable.ic_dashbord))
        menuList.add(MenuModelBean(3, "Template", "", R.drawable.ic_dashbord))
        menuList.add(MenuModelBean(4, "Chart", "", R.drawable.ic_dashbord))
        menuList.add(MenuModelBean(5, "Staff", "", R.drawable.ic_dashbord))
        menuList.add(MenuModelBean(6, "Sticky Notes", "", R.drawable.ic_dashbord))
        menuList.add(MenuModelBean(7, "Holidays", "", R.drawable.ic_dashbord))

        return menuList
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.addToBackStack(fragment.toString())
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
*/

    override fun onDestroy() {
        super.onDestroy()
        // Start the LocationService when the app is closed
        //   startService(Intent(this, LocationService::class.java))
    }

}