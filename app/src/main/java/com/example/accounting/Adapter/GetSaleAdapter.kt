package com.example.accounting.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.Model.GetSalesBean
import com.example.accounting.Model.WalletLedgerBean
import com.example.accounting.R
import com.example.accounting.Utills.RvStatusClickListner


class GetSaleAdapter(
    var context: Activity,
    var list: List<GetSalesBean.Data>,
    var rvClickListner: RvStatusClickListner
) : RecyclerView.Adapter<GetSaleAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder { // infalte the item Layout
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_sales, parent, false)
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

        holder.tvInvoice.text = list[position].invoice
        holder.tvGSTType.text = list[position].gstType
        holder.tvPaymentStatus.text = list[position].paymentStatus
        holder.tvIsBilled.text = list[position].isBilled
        holder.tvDueDate.text = list[position].dueDate
        if (list[position].paymentStatus.equals("pending")) {
            holder.tvPaymentStatus.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.yellow_color
                )
            );
        } else if (list[position].paymentStatus.equals("Success")) {
            holder.tvPaymentStatus.setTextColor(ContextCompat.getColor(context, R.color.green));
        } else {
            holder.tvPaymentStatus.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.paymentsdk_color_red
                )
            );

        }
        holder.itemView.setOnClickListener {
            //  rvClickListner.clickPos(list[position].indexId)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val tvInvoice: TextView = itemview.findViewById(R.id.tvInvoice)
        val tvPaymentStatus: TextView = itemview.findViewById(R.id.tvPaymentStatus)
        val tvGSTType: TextView = itemview.findViewById(R.id.tvGSTType)
        val tvIsBilled: TextView = itemview.findViewById(R.id.tvIsBilled)
        val tvDueDate: TextView = itemview.findViewById(R.id.tvDueDate)
    }

}