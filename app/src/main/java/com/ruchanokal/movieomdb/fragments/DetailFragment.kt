package com.ruchanokal.movieomdb.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ruchanokal.movieomdb.R
import com.ruchanokal.movieomdb.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val TAG = "DetailFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            val movie = DetailFragmentArgs.fromBundle(it).movie
            Log.i(TAG, "movie: $movie")

            Glide.with(requireContext()).load(movie.Poster).into(binding!!.filmImagePoster)

            binding!!.filmName.text = movie.Title
            binding!!.filmType.text = movie.Genre
            binding!!.filmImdbRate.text = movie.imdbRating
            binding!!.filmYear.text = movie.Year
            binding!!.filmLanguage.text = movie.Language
            binding!!.filmCountry.text = movie.Country
            binding!!.filmDirector.text = movie.Director
            binding!!.filmDescription.text = movie.Plot

        }

    }
}