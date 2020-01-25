package com.zhussain.pinboard.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserProfileImg(
    val small : String,
    val medium : String,
    val large : String
):Parcelable