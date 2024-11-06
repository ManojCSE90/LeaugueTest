package com.example.leaguetest.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaguetest.databinding.ActivityMainBinding
import com.example.leaguetest.adapter.PostListAdapter
import com.example.leaguetest.models.NetworkResult
import com.example.leaguetest.models.post.UserPost
import com.example.leaguetest.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val postViewModel by viewModels<PostViewModel>()
    @Inject
    lateinit var postListAdapter: PostListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initMovieRecyclerView()
        registerObserver()

        postViewModel.getPostOfUsers()

    }

    private fun initMovieRecyclerView() {

        postListAdapter.setOnClick {

            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }
        with( binding.rvPostList){
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
            adapter = postListAdapter

        }

    }

    private fun registerObserver() {

        postViewModel.postOfUsers.observe(this) {
            updateTopRatedMovies(it)
        }
    }

    private fun updateTopRatedMovies(networkResult: NetworkResult<List<UserPost>>) {


        when (networkResult) {

            is NetworkResult.Failure -> {
                showOrHideProgress(false)

                //println("registerObserver Failure: ${networkResult.errorMsg}")
                //Toast.makeText(this, "Api Failed", Toast.LENGTH_SHORT).show()
            }

            is NetworkResult.Loading -> {
                //Toast.makeText(this, "Show loader", Toast.LENGTH_SHORT).show()
                showOrHideProgress(true)

                //println("registerObserver Loading: $networkResult")

            }

            is NetworkResult.Success -> {
                showOrHideProgress(false)

                //Toast.makeText(this, "Api success", Toast.LENGTH_SHORT).show()

                //println("registerObserver Success: ${networkResult.data}")
                networkResult.data?.let {
                    postListAdapter.updateData(it)
                }
            }
        }

    }

    private fun showOrHideProgress(show: Boolean) {

        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

}
