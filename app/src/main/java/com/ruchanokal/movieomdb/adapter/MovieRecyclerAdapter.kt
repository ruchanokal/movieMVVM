package com.ruchanokal.movieomdb.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruchanokal.movieomdb.R
import com.ruchanokal.movieomdb.databinding.MovieRecyclerRowBinding
import com.ruchanokal.movieomdb.fragments.SearchFragmentDirections
import com.ruchanokal.movieomdb.model.MovieModel
import com.ruchanokal.movieomdb.model.Search
import com.ruchanokal.movieomdb.util.downloadFromUrl
import com.ruchanokal.movieomdb.util.placeholderProgressBar

class MovieRecyclerAdapter(val movieList : ArrayList<MovieModel>)
    : RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {

    private val TAG = "MovieRecyclerAdapter"

    class MovieViewHolder(val binding: MovieRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        Log.i(TAG,"onCreateViewHolder ")

//        VIEW BINDING
//        val binding = MovieRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return MovieViewHolder(binding)

        val view = DataBindingUtil.inflate<MovieRecyclerRowBinding>(LayoutInflater.from(parent.context), R.layout.movie_recycler_row,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        Log.i(TAG,"title: " + movieList.get(position).Title)
        Log.i(TAG,"year: " + movieList.get(position).Year)

        holder.binding.movie = movieList.get(position)


        /*

        **** VIEW BINDING ****
        holder.binding.movieName.text = movieList.get(position).Title
        holder.binding.movieYear.text = movieList.get(position).Year
        holder.binding.movieType.text = movieList.get(position).Genre
        holder.binding.movieDesc.text = movieList.get(position).Plot
        holder.binding.filmImage.downloadFromUrl(movieList.get(position).Poster, placeholderProgressBar(holder.itemView.context))

         */






        holder.itemView.setOnClickListener {

            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(movieList.get(position))
            Navigation.findNavController(it).navigate(action)

        }


    }

    override fun getItemCount(): Int {
        Log.i(TAG,"getItemCount: " + movieList.size)

        return movieList.size
    }

    fun updateMovieList(newMovieList: List<MovieModel>) {

        Log.i(TAG,"updateMovieList: " + movieList)
        movieList.clear()
        movieList.addAll(newMovieList)
        Log.i(TAG,"updateMovieList-2: " + movieList)

        notifyDataSetChanged()
    }



}


