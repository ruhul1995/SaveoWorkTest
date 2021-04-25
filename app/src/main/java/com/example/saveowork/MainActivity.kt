package com.example.saveowork

import PopularMoviesPojo
import Results
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saveowork.Adapter.MyAdapter
import com.example.saveowork.RetrofitClient.ApiClient
import com.example.saveowork.RetrofitClient.ApiRequests
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), MyAdapter.OnMovieListenerInterface {
    private var apiInterface: ApiRequests? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null

    private var mLayoutManager: RecyclerView.LayoutManager? = null

    var obj: List<Results>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiInterface = ApiClient.retrofit.create(ApiRequests::class.java)


        getFirstNetworkCall();
        mRecyclerView = findViewById(R.id.recyclerViewMnActivity)
    }

    private fun getFirstNetworkCall() {
        //api key : 6c97fd958e68bd6fbf932b9f3a995306

        val call: Call<PopularMoviesPojo>? = apiInterface?.getPopular("6c97fd958e68bd6fbf932b9f3a995306")
        if (call != null) {
            call.enqueue(object : Callback<PopularMoviesPojo> {
                override fun onResponse(
                        call: Call<PopularMoviesPojo>,
                        response: Response<PopularMoviesPojo>
                ) {
                    Log.d("value", response.body().toString())
                    val resource: PopularMoviesPojo? = response.body()
                    obj = resource?.results ?: listOf()


                    mLayoutManager = GridLayoutManager(applicationContext, 3)
                    mRecyclerView?.layoutManager = mLayoutManager
                    mRecyclerView?.setHasFixedSize(true)
                    mAdapter = MyAdapter(obj!!, applicationContext, this@MainActivity)
                    mRecyclerView?.adapter = mAdapter


                }

                override fun onFailure(call: Call<PopularMoviesPojo>, t: Throwable) {

                    Log.d("value", "Failed in calling Network Call");
                }
            })
        }
    }

    override fun onMovieClick(position: Int)
    {
        //Log.d("Success", "onMovieListenerClicked : onMovie Clicked is success $position")

        var mImage : String? = obj?.get(position)?.poster_path
        var title :String? =obj?.get(position)?.title
        var releaseDate : String? = obj?.get(position)?.release_date
        var movieLanguage : String? = obj?.get(position)?.original_language
        var movieOverview : String? = obj?.get(position)?.overview
        var popularity : String? = obj?.get(position)?.popularity.toString()
        //Log.d("Success", "MovieNameClicked : onMovie Clicked is success" + title)


        val intent = Intent(this, MovieInfoActivity::class.java).apply{
            putExtra("data", position)
            putExtra("title", title)
            putExtra("releaseDate", releaseDate)
            putExtra("movieLanguage", movieLanguage)
            putExtra("movieOverview", movieOverview)
            putExtra("popular", popularity)
            //sending movie image's path
            putExtra("mImage", mImage)
        }
        startActivity(intent)
    }
}