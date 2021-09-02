package com.nisa.newsappid.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nisa.newsappid.R
import com.nisa.newsappid.data.model.ArticleItem
import com.nisa.newsappid.databinding.ActivityDetailBinding
import com.nisa.newsappid.utils.loadImage

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by viewBinding()
    private var newsItem: ArticleItem? = null

    companion object {
        const val DETAIL_EXTRA = "detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        newsItem = intent?.extras?.getParcelable(DETAIL_EXTRA)
        newsItem?.let {
            binding.apply {
                detailImage.loadImage(it.urlToImage)
                detailNama.text = it.title
                detailDeskripsi.text = it.description
            }
        }
    }
}