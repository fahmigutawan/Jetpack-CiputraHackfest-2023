package com.example.hackfestciputra2023.screen.onboarding

import androidx.annotation.DrawableRes

data class OnboardingData(
    @DrawableRes val imageId: Int,
    val title: String,
    val description: String
)

enum class AccountType {
    USER, SELLER
}
