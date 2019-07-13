package com.wind.githubuserssearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wind.githubuserssearch.R
import com.wind.githubuserssearch.adapter.MainAdapter
import com.wind.githubuserssearch.data.UserInfo
import com.wind.githubuserssearch.databinding.ActivityMainBinding
import com.wind.githubuserssearch.viewmodel.MainViewModel
import com.wind.githubuserssearch.widget.WaitingView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private val mainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.search.setOnClickListener {
            viewModel.onSearch()
        }
        binding.recyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.userList.observe(this, Observer<PagedList<UserInfo>> {
            mainAdapter.submitList(it)
        })
        viewModel.waitingViewVisibility.observe(this, Observer<Boolean> {
            if(it) WaitingView.show(this@MainActivity) else WaitingView.dismiss()
        })
    }
}
