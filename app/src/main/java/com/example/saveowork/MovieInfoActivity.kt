package com.example.saveowork

import Results

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class MovieInfoActivity : AppCompatActivity() {


    var mTitle: TextView? = null
    var mReleaseDate : TextView? = null
    var mMovieLanguage: TextView? = null
    var mMovieOverview: TextView? = null
    var mPopularity : TextView? = null
    var lobj: List<Results>? = null
    private val POSTER_IMAGE_PATH_PREFIX = "https://image.tmdb.org/t/p/w300"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movieinfo)


        mTitle = findViewById(R.id.movieTitleTxtV)
        mReleaseDate = findViewById(R.id.releaseDateTxtV)
        mMovieLanguage = findViewById(R.id.orgLanguageTxtV)
        mMovieOverview = findViewById(R.id.overviewTxtV)
        mPopularity = findViewById(R.id.popularityTxtV)

        val bundle :Bundle ?=intent.extras

        var mTitleR : String? = bundle?.getString("title")
        mTitle?.setText("Title  "+"\n"+ mTitleR)

        var mReleaseDateR : String? = bundle?.getString("releaseDate")
        mReleaseDate?.setText("ReleaseDate  "+"\n" + mReleaseDateR)

        var mMovieLanguageR : String? = bundle?.getString("movieLanguage")
        mMovieLanguage?.setText("Language  " + "\n" + mMovieLanguageR)

        var mPopularityR : String? = bundle?.getString("popular").toString()
        mPopularity?.setText("Popularity  " + "\n" + mPopularityR)

        var mMovieOverviewR : String? = bundle?.getString("movieOverview")
        mMovieOverview?.setText("Overview "+
                "\n\n"+ mMovieOverviewR)

        var mImg : String? =  POSTER_IMAGE_PATH_PREFIX + bundle?.getString("mImage")
        var imgView : ImageView  = findViewById<View>(R.id.currentMovieImgView) as ImageView
        //val myBitmap = BitmapFactory.decodeFile(mImg)
        // imgView = findViewById(R.id.currentMovieImgView)
       // imgView?.setImageBitmap(myBitmap)
        Glide.with(this).load(mImg).into(imgView)

        Log.d("Success", "data received " + mImg)

    }
}

