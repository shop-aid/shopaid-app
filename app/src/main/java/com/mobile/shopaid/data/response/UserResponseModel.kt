package com.mobile.shopaid.data.response

data class UserResponseModel(
        val email: String,
        val charity_balance: String,
        val partner_breakdown: List<PartnerResponseModel>)