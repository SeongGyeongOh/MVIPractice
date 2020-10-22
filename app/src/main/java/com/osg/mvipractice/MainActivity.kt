package com.osg.mvipractice

import android.arch.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.osg.mvipractice.adapter.MainAdapter
import com.osg.mvipractice.data.ApiHelperImpl
import com.osg.mvipractice.data.RetrofitBuilder
import com.osg.mvipractice.data.User
import com.osg.mvipractice.intent.MainIntent
import com.osg.mvipractice.viewstate.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


//따라쓴 코드 : https://medium.com/@abhiappmobiledeveloper/android-mvi-reactive-architecture-pattern-74e5f1300a87
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var MainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks(){
        buttonFetchUser.setOnClickListener
        {
            lifecycleScope.launch
            {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }

        }
    }

    private fun setupUI(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as
                        LinearLayoutManager).orientation)
            )
        }
        recyclerView.adapter = adapter
    }

    private fun setupViewModel(){
        mainViewModel = ViewModelProviders.of(this ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))).get(MainViewModel::class.java)
    }

    private fun observeViewModel(){
        lifecycleScope.launch
        {
            mainViewModel.state.collect
            {
                when (it) {
                    is MainState.Idle -> {
                    }
                    is MainState.Loading -> {
                        buttonFetchUser.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                    is MainState.Users ->
                    {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.GONE
                        renderList(it.user)
                    }
                    is MainState.Error ->
                    {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error,
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(users:List<User>){
        recyclerView.visibility = View.VISIBLE
        users.let { listOfUsers -> listOfUsers.let
            { adapter.addData(it)
            }
        }
        adapter.notifyDataSetChanged()
    }
}
}