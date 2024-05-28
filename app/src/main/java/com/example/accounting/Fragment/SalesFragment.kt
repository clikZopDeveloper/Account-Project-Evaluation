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
import com.example.accounting.databinding.FragmentSalesBinding
import com.google.gson.JsonElement
import com.stpl.antimatter.Utils.ApiContants

class SalesFragment : Fragment(), ApiResponseListner {

    private lateinit var apiClient: ApiController
    private var _binding: FragmentSalesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = com.example.accounting.databinding.FragmentSalesBinding.inflate(
            inflater,
            container,
            false
        )

        val root: View = binding.root

        val titleText = (activity as DashboardActivity?)
        titleText?.setTitle("All Sales")

        binding.fbAddArchitect.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), AddSalesActivity::class.java).putExtra("way","Add Sales"))
        }

        apiClient = ApiController(activity, this)
  //    ApiContants.movabalebutton(binding.fbAddArchitect,requireActivity())

        apiGetExpenses()

        return root
    }

    fun apiGetExpenses() {
        SalesApp.isAddAccessToken = true
        val params = Utility.getParmMap()
        apiClient.progressView.showLoader()
        apiClient.getApiPostCall(ApiContants.getExpenses, params)
    }

    override fun success(tag: String?, jsonElement: JsonElement) {
        try {
            apiClient.progressView.hideLoader()
            if (tag == ApiContants.getExpenses) {
                val stateBean = apiClient.getConvertIntoModel<StateBean>(
                    jsonElement.toString(),
                    StateBean::class.java
                )
                if (stateBean.error == false) {

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

    override fun onResume() {
        super.onResume()
        //   apiAllGet()
    }

}