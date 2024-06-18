package com.example.account_project_evaluation.Model


import com.google.gson.annotations.SerializedName

data class WalletLedgerBean(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String // Data loaded successfully.
) {
    data class Data(
        @SerializedName("credit_amount")
        val creditAmount: CreditAmount,
        @SerializedName("debit_amount")
        val debitAmount: DebitAmount,
        @SerializedName("expenses")
        val expenses: List<Expense>,
        @SerializedName("user_wallet")
        val userWallet: UserWallet,
        @SerializedName("wallet_history")
        val walletHistory: List<WalletHistory>
    ) {
        data class CreditAmount(
            @SerializedName("credit_amount")
            val creditAmount: String // 10000.00
        )

        data class DebitAmount(
            @SerializedName("debit_amount")
            val debitAmount: String // 500.00
        )

        data class Expense(
            @SerializedName("amount")
            val amount: String, // 500.00
            @SerializedName("build")
            val build: String, // Billed
            @SerializedName("created_at")
            val createdAt: String, // 2024-06-18 16:19:50
            @SerializedName("customer")
            val customer: String, // Customer 1
            @SerializedName("expense_category")
            val expenseCategory: String, // Travel
            @SerializedName("expense_date")
            val expenseDate: String, // 0000-00-00
            @SerializedName("expense_subcategory")
            val expenseSubcategory: String, // Cab
            @SerializedName("expense_type")
            val expenseType: String,
            @SerializedName("expenses_for")
            val expensesFor: Any, // null
            @SerializedName("file")
            val `file`: String, // ../invoices/8628036994170648-expertz-jpg.jpg
            @SerializedName("id")
            val id: Int, // 2
            @SerializedName("ids")
            val ids: Int, // 1
            @SerializedName("invoice_id")
            val invoiceId: Int, // 8
            @SerializedName("name")
            val name: String, // travel
            @SerializedName("note")
            val note: String,
            @SerializedName("payment_mode")
            val paymentMode: String, // Bank Transfer
            @SerializedName("ref_no")
            val refNo: String,
            @SerializedName("trans_id")
            val transId: String,
            @SerializedName("updated_at")
            val updatedAt: Any, // null
            @SerializedName("user_id")
            val userId: Int, // 10
            @SerializedName("vendor_id")
            val vendorId: Int // 0
        )

        data class UserWallet(
            @SerializedName("address")
            val address: String, // Chd
            @SerializedName("city")
            val city: String, // Chandigarh
            @SerializedName("created_at")
            val createdAt: String, // 2024-05-01 18:24:40
            @SerializedName("email")
            val email: String, // user1@gmail.com
            @SerializedName("id")
            val id: Int, // 10
            @SerializedName("joining_date")
            val joiningDate: String, // 2024-05-01
            @SerializedName("last_ip")
            val lastIp: String, // ::1
            @SerializedName("last_login")
            val lastLogin: String, // 2024-06-18 16:46:39
            @SerializedName("mobile")
            val mobile: String, // 9876987698
            @SerializedName("name")
            val name: String, // User 1
            @SerializedName("password")
            val password: String, // 123456
            @SerializedName("role")
            val role: String, // Staff
            @SerializedName("state")
            val state: String, // Chandigarh
            @SerializedName("status")
            val status: Int, // 1
            @SerializedName("token")
            val token: String, // mwcmtSD3cJsR
            @SerializedName("username")
            val username: String, // User 1
            @SerializedName("wallet_amt")
            val walletAmt: String // 9500.00
        )

        data class WalletHistory(
            @SerializedName("amount")
            val amount: String, // 10000.00
            @SerializedName("created_at")
            val createdAt: String, // 2024-05-01 18:25:25
            @SerializedName("customer_id")
            val customerId: Int, // 10
            @SerializedName("id")
            val id: Int, // 1
            @SerializedName("remarks")
            val remarks: String,
            @SerializedName("updated_at")
            val updatedAt: Any // null
        )
    }
}