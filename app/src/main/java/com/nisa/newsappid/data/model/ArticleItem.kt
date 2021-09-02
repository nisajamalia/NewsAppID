package com.nisa.newsappid.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleItem(
    val author: String = "",
    val content: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val source: SourceItem,
    val title: String = "",
    val url: String = "",
    val urlToImage: String = ""
) : Parcelable