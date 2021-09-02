package com.nisa.newsappid.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nisa.newsappid.R
import com.nisa.newsappid.databinding.ActivityMainBinding
import com.nisa.newsappid.ui.detail.DetailActivity
import com.nisa.newsappid.utils.Resource
import com.nisa.newsappid.utils.showToast

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
    private val newsAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set up recycler view
        with(binding.rvNews){
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
            setHasFixedSize(true)
        }

        viewModel.newsData.observe(this){response ->
            when(response){
                is Resource.Success -> {
                    binding.progressCircular.isVisible = false
                    response.data.articles.map { news ->
                        newsAdapter.add(MainItem(news){
                            //ketika user klik item data akan dikirim melalui parcelable
                            // dan akan ditampilkan ke detail activity
                            startActivity(Intent(this, DetailActivity::class.java)
                                .putExtra(DetailActivity.DETAIL_EXTRA, it))
                        })
                    }
                }
                is Resource.Loading -> {
                    binding.progressCircular.isVisible = true
                }
                is Resource.Error -> {
                    binding.progressCircular.isVisible = false
                    showToast(response.message)
                }
                is Resource.Empty -> {
                    binding.progressCircular.isVisible = false
                    showToast("Data is empty")
                }
            }
        }


    }
}