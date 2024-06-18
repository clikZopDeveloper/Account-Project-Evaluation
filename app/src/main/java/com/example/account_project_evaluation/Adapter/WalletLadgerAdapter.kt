package com.example.account_project_evaluation.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.account_project_evaluation.Model.WalletLedgerBean
import com.example.account_project_evaluation.R
import com.example.account_project_evaluation.Utills.RvStatusClickListner
import com.stpl.antimatter.Utils.ApiContants


class WalletLadgerAdapter(var context: Activity, var list: List<WalletLedgerBean.Data.WalletHistory>, var rvClickListner: RvStatusClickListner) : RecyclerView.Adapter<WalletLadgerAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder { // infalte the item Layout
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_wallet_ledger, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setIsRecyclable(false)

   /*     holder.tvAdd.background = RoundView(context.resources.getColor(R.color.orange), RoundView.getRadius(20f))
        holder.tvQtyAdd.background = RoundView(context.resources.getColor(R.color.orange), RoundView.getRadius(100f))
        holder.tvQtyMinus.background = RoundView(context.resources.getColor(R.color.orange), RoundView.getRadius(100f))
        holder.tvQty.background = RoundView(Color.TRANSPARENT, RoundView.getRadius(20f), true, R.color.orange)
        holder.tvOff.background = RoundView(context.resources.getColor(R.color.orange), RoundView.getRadius(20f))
        holder.tvAdd.visibility = View.VISIBLE*/

        holder.tvAmount.text= ApiContants.currency+list[position].amount
        holder.tvRemarks.text= list[position].remarks
        holder.tvDate.text= list[position].createdAt

        holder.itemView.setOnClickListener {
         //  rvClickListner.clickPos(list[position].indexId)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val tvAmount: TextView = itemview.findViewById(R.id.tvAmount)
       val tvRemarks: TextView = itemview.findViewById(R.id.tvRemarks)
       val tvDate: TextView = itemview.findViewById(R.id.tvDate)
    }

}