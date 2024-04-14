package com.example.task

import android.content.Intent
import android.os.Bundle
import android.telecom.Call.Details
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private var isLoading = false
    private var currentPage = 1
    private val pageSize = 10
    private val retrofitClient = RetrofitClient.getClient()
    lateinit var apiService : ApiService
    private lateinit var progressbar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        progressbar = findViewById(R.id.progress_bar)
        recyclerView = findViewById(R.id.recycler_View)
        adapter = MainAdapter { selectedPost ->
            // Handle item click here, e.g., navigate to detail activity
            val intent = Intent(this@MainActivity, DetailsActvity::class.java)
            intent.putExtra("postId", selectedPost.title)
            startActivity(intent)
        }
       // adapter = MainAdapter()
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter




        apiService = retrofitClient.create(ApiService::class.java)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount
                    Log.d("main", dy.toString())



                    if (!isLoading) {

                        if ((visibleItemCount + pastVisibleItem) >= total) {
                            currentPage++
                            fetchData(currentPage, pageSize)
                        }

                    }

                    super.onScrolled(recyclerView, dx, dy)
                }
            }
        })
        fetchData(currentPage, pageSize)

    }

    private fun fetchData(page: Int, pageSize: Int) {
        isLoading = true
        progressbar.visibility= View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            var result = apiService.getPosts(page, pageSize)
            if (result.isSuccessful) {
                result.body()?.let { posts ->
                    withContext(Dispatchers.Main) {
                        progressbar.visibility= View.GONE
                        adapter.addPosts(posts)
                        isLoading = false
                    }
                }
            }

        }
    }
}