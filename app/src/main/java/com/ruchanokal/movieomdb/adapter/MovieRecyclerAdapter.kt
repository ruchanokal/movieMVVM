package com.ruchanokal.movieomdb.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruchanokal.movieomdb.databinding.MovieRecyclerRowBinding
import com.ruchanokal.movieomdb.fragments.SearchFragmentDirections
import com.ruchanokal.movieomdb.model.MovieModel
import com.ruchanokal.movieomdb.model.Search

class MovieRecyclerAdapter(val movieList : ArrayList<MovieModel>)
    : RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {

    private val TAG = "MovieRecyclerAdapter"

    class MovieViewHolder(val binding: MovieRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        Log.i(TAG,"onCreateViewHolder ")

        val binding = MovieRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        Log.i(TAG,"onBindViewHolder ")


        Log.i(TAG,"title: " + movieList.get(position).Title)
        Log.i(TAG,"year: " + movieList.get(position).Year)

        holder.binding.movieName.text = movieList.get(position).Title
        holder.binding.movieYear.text = movieList.get(position).Year
        holder.binding.movieType.text = movieList.get(position).Genre
        holder.binding.movieDesc.text = movieList.get(position).Plot
        Glide.with(holder.itemView.context).load(movieList.get(position).Poster).into(holder.binding.filmImage)

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


