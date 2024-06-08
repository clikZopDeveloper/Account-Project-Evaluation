package com.example.account_project_evaluation.Model


import com.google.gson.annotations.SerializedName

data class GetExpensesBean(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String // Data loaded successfully.
):java.io.Serializable {
    data class Data(
        @SerializedName("amount")
        val amount: String, // 10.00
        @SerializedName("build")
        val build: String, // Billed
        @SerializedName("created_at")
        val createdAt: String, // 2024-06-08 13:04:37
        @SerializedName("customer_name")
        val customerName: String, // Customer 2
        @SerializedName("expense_category")
        val expenseCategory: String, // Travel
        @SerializedName("expense_date")
        val expenseDate: String, // 2024-06-19
        @SerializedName("expense_subcategory")
        val expenseSubcategory: String, // Cab
        @SerializedName("expense_type")
        val expenseType: String, // Labour
        @SerializedName("expenses_for")
        val expensesFor: Any, // null
        @SerializedName("file")
        val `file`: String, // ../invoices/3230873881220864-title.jpg
        @SerializedName("id")
        val id: Int, // 2
        @SerializedName("ids")
        val ids: Int, // 2
        @SerializedName("invoice_id")
        val invoiceId: Int, // 0
        @SerializedName("name")
        val name: String, // test
        @SerializedName("note")
        val note: String, // demo
        @SerializedName("payment_mode")
        val paymentMode: String, // Cash
        @SerializedName("ref_no")
        val refNo: String, // 123456
        @SerializedName("trans_id")
        val transId: String,
        @SerializedName("updated_at")
        val updatedAt: Any, // null
        @SerializedName("user")
        val user: String, // Test user
        @SerializedName("user_id")
        val userId: Int, // 11
        @SerializedName("vendor_id")
        val vendorId: Int, // 2
        @SerializedName("vendor_name")
        val vendorName: String // Labor 1
    ):java.io.Serializable
}