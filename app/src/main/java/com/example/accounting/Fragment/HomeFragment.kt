package com.example.accounting.Fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.accounting.Activity.*
import com.example.accounting.ApiHelper.ApiController
import com.example.accounting.ApiHelper.ApiResponseListner
import com.example.accounting.Model.*
import com.example.accounting.R
import com.example.accounting.Utills.*
import com.google.gson.JsonElement
import com.stpl.antimatter.Utils.ApiContants

class HomeFragment : Fragment(), ApiResponseListner {

    private lateinit var apiClient: ApiController
    private var _binding: com.example.accounting.databinding.FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = com.example.accounting.databinding.FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        val root: View = binding.root
        apiClient = ApiController(activity, this)

        val titleText = (activity as DashboardActivity?)
        titleText?.setTitle("Dashboard")
        binding.fbAddArchitect.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), AddExpensesActivity::class.java).putExtra("way","Add Expenses"))
        }
  //    ApiContants.movabalebutton(binding.fbAddArchitect,requireActivity())

      //  apiAllGet()
    //    apiCallDashboard()

        binding.apply {
            refreshLayout.setOnRefreshListener {
            //    apiCallDashboard()
                refreshLayout.isRefreshing = false
            }
            binding.fbSearch.setOnClickListener {
                if (binding.edSearch.text.toString().isNullOrEmpty()){
                    Toast.makeText(requireContext(),"Enter Search Key",Toast.LENGTH_SHORT).show()
                }else{

                }
            }

           /* tvAllTaskData.setOnClickListener {
                (context as DashboardActivity).replaceFragment(StaffFragment())
            }*/
        }

        return root
    }

    fun apiAllGet() {
        SalesApp.isAddAccessToken = true
        val params = Utility.getParmMap()
        apiClient.progressView.showLoader()
        apiClient.getApiPostCall(ApiContants.getState, params)
    }

    fun apiCallDashboard() {
        SalesApp.isAddAccessToken = true
        val params = Utility.getParmMap()
        params["mobile"] = PrefManager.getString(ApiContants.mobileNumber, "")
        params["password"] = PrefManager.getString(ApiContants.password, "")
        // apiClient.progressView.showLoader()
        apiClient.getApiPostCall(ApiContants.dashboard, params)

    }

    override fun success(tag: String?, jsonElement: JsonElement) {
        try {
            apiClient.progressView.hideLoader()
            if (tag == ApiContants.logout) {
                val baseResponseBean = apiClient.getConvertIntoModel<BaseResponseBean>(
                    jsonElement.toString(),
                    BaseResponseBean::class.java
                )
                Toast.makeText(activity, baseResponseBean.msg, Toast.LENGTH_SHORT).show()
                PrefManager.clear()
                GeneralUtilities.launchActivity(
                    requireContext() as AppCompatActivity?,
                    LoginActivity::class.java
                )
                requireActivity().finishAffinity()
            }

            if (tag == ApiContants.getState) {
                val stateBean = apiClient.getConvertIntoModel<StateBean>(
                    jsonElement.toString(),
                    StateBean::class.java
                )
                if (stateBean.error == false) {
                    SalesApp.stateList.clear()
                    SalesApp.stateList.addAll(stateBean.data)
                }
            }

        } catch (e: Exception) {
            Log.d("error>>", e.localizedMessage)
        }
    }

    override fun failure(tag: String?, errorMessage: String) {
        apiClient.progressView.hideLoader()
        Utility.showSnackBar(requireActivity(), errorMessage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun getMenusAllLead(data: DashboardBean.Data.AllLead): ArrayList<MenuModelBean> {
        var menuList = ArrayList<MenuModelBean>()
      //  menuList.add(MenuModelBean(1, "Add Lead", data.newLeads.toString(), R.drawable.ic_dashbord))
        menuList.add(MenuModelBean(2, "new lead", data.newLeads.toString(), R.drawable.ic_dashbord))
        menuList.add(
            MenuModelBean(
                3,
                "pending",
                data.pendingLeads.toString(),
                R.drawable.ic_dashbord
            )
        )
        menuList.add(
            MenuModelBean(
                6,
                "processed",
                data.processingLeads.toString(),
                R.drawable.ic_dashbord
            )
        )
        menuList.add(
            MenuModelBean(
                4,
                "converted",
                data.convertedLeads.toString(),
                R.drawable.ic_dashbord
            )
        )
        menuList.add(
            MenuModelBean(
                5,
                "call scheduled",
                data.callScheduled.toString(),
                R.drawable.ic_dashbord
            )
        )

        menuList.add(
            MenuModelBean(
                8,
                "visit scheduled",
                data.visitScheduled.toString(),
                R.drawable.ic_dashbord
            )
        )
        menuList.add(
            MenuModelBean(
                9,
                "visit done",
                data.visitDone.toString(),
                R.drawable.ic_dashbord
            )
        )

/*
         menuList.add(
             MenuModelBean(
                 11,
                 "booked",
                 data.convertedLeads.toString(),
                 R.drawable.ic_dashbord
             )
         )*/
/*
  menuList.add(
             MenuModelBean(
                 11,
                 "completed",
                 data.convertedLeads.toString(),
                 R.drawable.ic_dashbord
             )
         )

          menuList.add(
             MenuModelBean(
                 11,
                 "cancelled",
                 data.convertedLeads.toString(),
                 R.drawable.ic_dashbord
             )
         )
*/

        menuList.add(
            MenuModelBean(
                12,
                "not interested",
                data.notInterested.toString(),
                R.drawable.ic_dashbord
            )
        )

        menuList.add(
            MenuModelBean(
                13,
                "not picked",
                data.notPicked.toString(),
                R.drawable.ic_dashbord
            )
        )
        menuList.add(
            MenuModelBean(
                14,
                "wrong number",
                data.wrongNumber.toString(),
                R.drawable.ic_dashbord
            )
        )
        menuList.add(
            MenuModelBean(
                15,
                "not reachable",
                data.notReachable.toString(),
                R.drawable.ic_dashbord
            )
        )
        menuList.add(
            MenuModelBean(
                16,
                "channel partner",
                data.channelPartner.toString(),
                R.drawable.ic_dashbord
            )
        )

        return menuList
    }

    private fun getMenusTodayLead(data: DashboardBean.Data.TodayLead): ArrayList<MenuModelBean> {
        var menuList = ArrayList<MenuModelBean>()
       // menuList.add(MenuModelBean(1, "Add Lead", data.newLeads.toString(), R.drawable.ic_dashbord))
        menuList.add(MenuModelBean(2, "new lead", data.newLeads.toString(), R.drawable.ic_dashbord))
        menuList.add(
            MenuModelBean(
                3,
                "pending",
                data.pendingLeads.toString(),
                R.drawable.ic_dashbord
            )
        )
        menuList.add(
            MenuModelBean(
                6,
                "processed",
                data.processingLeads.toString(),
                R.drawable.ic_dashbord
            )
        )
            menuList.add(
                MenuModelBean(
                    4,
                    "converted",
                    data.convertedLeads.toString(),
                    R.drawable.ic_dashbord
                )
            )
        menuList.add(
            MenuModelBean(
                5,
                "call scheduled",
                data.callScheduled.toString(),
                R.drawable.ic_dashbord
            )
        )

        menuList.add(
            MenuModelBean(
                8,
                "visit scheduled",
                data.visitScheduled.toString(),
                R.drawable.ic_dashbord
            )
        )
        menuList.add(
            MenuModelBean(
                9,
                "visit done",
                data.visitDone.toString(),
                R.drawable.ic_dashbord
            )
        )

/*
         menuList.add(
             MenuModelBean(
                 11,
                 "booked",
                 data.convertedLeads.toString(),
                 R.drawable.ic_dashbord
             )
         )*/
/*
  menuList.add(
             MenuModelBean(
                 11,
                 "completed",
                 data.convertedLeads.toString(),
                 R.drawable.ic_dashbord
             )
         )

          menuList.add(
             MenuModelBean(
                 11,
                 "cancelled",
                 data.convertedLeads.toString(),
                 R.drawable.ic_dashbord
             )
         )
*/

        menuList.add(
            MenuModelBean(
                12,
                "not interested",
                data.notInterested.toString(),
                R.drawable.ic_dashbord
            )
        )

        menuList.add(
            MenuModelBean(
                13,
                "not picked",
                data.notPicked.toString(),
                R.drawable.ic_dashbord
            )
        )
        menuList.add(
            MenuModelBean(
                14,
                "wrong number",
                data.wrongNumber.toString(),
                R.drawable.ic_dashbord
            )
        )
        menuList.add(
            MenuModelBean(
                15,
                "not reachable",
                data.notReachable.toString(),
                R.drawable.ic_dashbord
            )
        )
        menuList.add(
            MenuModelBean(
                16,
                "channel partner",
                data.channelPartner.toString(),
                R.drawable.ic_dashbord
            )
        )

        return menuList
    }

    fun callPGURL(url: String) {
        Log.d("weburl", url)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.android.chrome")
        try {
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            // Chrome browser presumably not installed so allow user to choose instead
            intent.setPackage(null)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        //   apiAllGet()
    }

}