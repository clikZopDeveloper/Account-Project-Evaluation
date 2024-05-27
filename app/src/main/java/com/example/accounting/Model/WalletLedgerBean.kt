package com.example.accounting.Model


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
        val expenses: List<Any>,
        @SerializedName("user_wallet")
        val userWallet: UserWallet,
        @SerializedName("wallet_history")
        val walletHistory: List<Any>
    ) {
        data class CreditAmount(
            @SerializedName("credit_amount")
            val creditAmount: Any // null
        )

        data class DebitAmount(
            @SerializedName("debit_amount")
            val debitAmount: Any // null
        )

        data class UserWallet(
            @SerializedName("address")
            val address: String, // address
            @SerializedName("city")
            val city: String, // Bokaro
            @SerializedName("created_at")
            val createdAt: String, // 2024-05-03 13:33:09
            @SerializedName("email")
            val email: String, // test@gmail.com
            @SerializedName("id")
            val id: Int, // 11
            @SerializedName("joining_date")
            val joiningDate: String, // 2024-05-03
            @SerializedName("last_ip")
            val lastIp: String, // 2405:201:5c0f:89a1:e976:21c2:3b3b:ac92
            @SerializedName("last_login")
            val lastLogin: String, // 2024-05-27 17:39:40
            @SerializedName("mobile")
            val mobile: String, // 8787878787
            @SerializedName("name")
            val name: String, // Test user
            @SerializedName("password")
            val password: String, // 123456
            @SerializedName("role")
            val role: String, // Staff
            @SerializedName("state")
            val state: String, // Jharkhand
            @SerializedName("status")
            val status: Int, // 1
            @SerializedName("token")
            val token: String, // NotYVuiwncGD
            @SerializedName("username")
            val username: String, // Test
            @SerializedName("wallet_amt")
            val walletAmt: String // 10000.00
        )
    }
}