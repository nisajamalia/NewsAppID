package com.nisa.newsappid.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SourceItem(
    val id: String,
    val name: String
): Parcelable