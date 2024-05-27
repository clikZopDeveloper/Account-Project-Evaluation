package com.example.accounting.Fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.accounting.Activity.DashboardActivity
import com.example.accounting.Adapter.WalletLadgerAdapter
import com.example.accounting.ApiHelper.ApiController
import com.example.accounting.ApiHelper.ApiResponseListner
import com.example.accounting.Model.WalletLedgerBean
import com.example.accounting.R
import com.example.accounting.Utills.GeneralUtilities
import com.example.accounting.Utills.RvStatusClickListner
import com.example.accounting.Utills.SalesApp
import com.example.accounting.Utills.Utility

import com.example.accounting.databinding.FragmentWalletLadgerBinding
import com.google.gson.JsonElement
import com.stpl.antimatter.Utils.ApiContants

class WalletLadgerFragment : Fragment(), ApiResponseListner {
    private lateinit var apiClient: ApiController
    private var _binding: FragmentWalletLadgerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWalletLadgerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val titleText = (activity as DashboardActivity?)
        titleText?.setTitle("Wallet Ledger")
        apiClient = ApiController(activity, this)
        apiWalletLadger()

        return root
    }

    fun apiWalletLadger() {
        SalesApp.isAddAccessToken = true
        val params = Utility.getParmMap()
        apiClient.progressView.showLoader()
        params["fromDt"] = ""
        params["fromDt"] = ""
        apiClient.getApiPostCall(ApiContants.GetWalletLadger, params)
    }


    fun dialogUpdateTask(taskID: Int)  {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Are you sure you want to update task?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                // Delete selected note from database

            }
            .setNegativeButton("No") { dialog, id ->
                // Dismiss the dialog
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()

    }

    override fun success(tag: String?, jsonElement: JsonElement) {
        try {
            apiClient.progressView.hideLoader()
            if (tag == ApiContants.GetWalletLadger) {
                var walletLedgerBean = apiClient.getConvertIntoModel<WalletLedgerBean>(
                    jsonElement.toString(),
                    WalletLedgerBean::class.java
                )
                if (walletLedgerBean.error == false) {
               //     handleAllTask(walletLedgerBean.data.walletHistory)
                } else {
                    Toast.makeText(activity, walletLedgerBean.msg, Toast.LENGTH_SHORT).show()
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

    fun handleAllTask(data: List<WalletLedgerBean.Data>) {
        binding.rcTask.layoutManager =
            LinearLayoutManager(requireContext())
       val mAllAdapter = WalletLadgerAdapter(requireActivity(), data, object :
           RvStatusClickListner {
            override fun clickPos(status: String, pos: Int) {
                dialogUpdateTask(pos)
            }
        })
        binding.rcTask.adapter = mAllAdapter
        mAllAdapter.notifyDataSetChanged()
        // rvMyAcFiled.isNestedScrollingEnabled = false
    }

    fun openAddTaskDialog() {
        val dialog: Dialog = GeneralUtilities.openBootmSheetDailog(
            R.layout.dialog_add_task, R.style.AppBottomSheetDialogTheme,
            requireActivity()
        )
        val ivClose = dialog.findViewById<ImageView>(R.id.ivClose)
        val btnAddTask = dialog.findViewById<TextView>(R.id.btnAddTask) as TextView
        val edAddTask = dialog.findViewById<EditText>(R.id.edAddTask) as EditText

        btnAddTask.setOnClickListener {
            if (TextUtils.isEmpty(edAddTask.text.toString())) {
                Toast.makeText(activity, "Please enter task", Toast.LENGTH_SHORT).show()

            } else {
                dialog.dismiss()
            }


        }
        ivClose.setOnClickListener { dialog.dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}