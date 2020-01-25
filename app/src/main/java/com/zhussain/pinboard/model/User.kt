package com.zhussain.pinboard.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String,
    @Json(name = "username") val userName: String,
    val name: String,
    @Json(name = "profile_image") val profileImage: UserProfileImg
):Parcelable