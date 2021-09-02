package com.nisa.newsappid.ui.home

import android.view.View
import com.nisa.newsappid.R
import com.nisa.newsappid.data.model.ArticleItem
import com.nisa.newsappid.databinding.NewsItemViewBinding
import com.nisa.newsappid.utils.loadImage
import com.xwray.groupie.viewbinding.BindableItem

class MainItem(
    private val article: ArticleItem,
    private val onClick: (ArticleItem) -> Unit
): BindableItem<NewsItemViewBinding>(){
    override fun bind(viewBinding: NewsItemViewBinding, position: Int) {
        //ngambil data dari news
        viewBinding.apply {
            itemName.text = article.title
            itemImage.loadImage(article.urlToImage)
            itemDesc.text = article.description
            itemCard.setOnClickListener {
                onClick(article)
            }
        }
    }

    override fun getLayout(): Int = R.layout.news_item_view

    override fun initializeViewBinding(view: View): NewsItemViewBinding {
        return NewsItemViewBinding.bind(view)
    }
}