package com.example.accounting.Model


import com.google.gson.annotations.SerializedName

data class LoginBean(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String // Userlogged in successfully.
) {
    data class Data(
        @SerializedName("address")
        val address: String, // address
        @SerializedName("city")
        val city: String, // Bokaro
        @SerializedName("created_at")
        val createdAt: String, // 2024-05-0313:33:09
        @SerializedName("email")
        val email: String, // test@gmail.com
        @SerializedName("id")
        val id: Int, // 11
        @SerializedName("joining_date")
        val joiningDate: String, // 2024-05-03
        @SerializedName("last_ip")
        val lastIp: String, // 2409:4085:2d97:3169:b5d2:8062:c3ce:9ee3
        @SerializedName("last_login")
        val lastLogin: String, // 2024-05-2012:34:09
        @SerializedName("mobile")
        val mobile: String, // 8787878787
        @SerializedName("name")
        val name: String, // Testuser
        @SerializedName("password")
        val password: String, // 123456
        @SerializedName("role")
        val role: String, // Staff
        @SerializedName("state")
        val state: String, // Jharkhand
        @SerializedName("status")
        val status: Int, // 1
        @SerializedName("token")
        val token: String, // 9R9ClI7B4E0n
        @SerializedName("username")
        val username: String, // Test
        @SerializedName("wallet_amt")
        val walletAmt: String // 10000.00
    )
}