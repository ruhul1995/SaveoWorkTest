package com.example.saveowork.Adapter

import Results
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.saveowork.R


open class MyAdapter(exampleList: List<Results>, context: Context, val itemClickListener: OnMovieListenerInterface) : RecyclerView.Adapter<MyAdapter.ExampleViewHolder>()
{

    val mExampleList: List<Results> = exampleList
    val context = context
    val mItemClickListener = itemClickListener

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val POSTER_IMAGE_PATH_PREFIX = "https://image.tmdb.org/t/p/w300"

        fun bind(property: Results, clickListener: OnMovieListenerInterface?){
            var mPoster: ImageView = itemView.findViewById(R.id.imageMovieImgV)
            var mMovieTitle: TextView = itemView.findViewById(R.id.titleMovieTv)
            mMovieTitle.text = property.title
            var poster_image_path = POSTER_IMAGE_PATH_PREFIX+property.poster_path
            Log.d("imagesssssss", poster_image_path)

            Glide.with(context).load(poster_image_path).into(mPoster)

            itemView.setOnClickListener{
                clickListener?.onMovieClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ExampleViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.vertical_single_row, parent, false)
        return ExampleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {

        //val currentItem: Results = mExampleList[position]
        holder.bind(mExampleList[position], mItemClickListener)
        //holder.mMovieTitle.setText(currentItem.title)
    }



    override fun getItemCount(): Int {
        return mExampleList.size
    }



    interface OnMovieListenerInterface {
        fun onMovieClick(position: Int)
    }
}

