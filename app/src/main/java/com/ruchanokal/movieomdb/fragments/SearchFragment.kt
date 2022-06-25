package com.ruchanokal.movieomdb.fragments


import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ruchanokal.movieomdb.R
import com.ruchanokal.movieomdb.adapter.MovieRecyclerAdapter
import com.ruchanokal.movieomdb.databinding.FragmentSearchBinding
import com.ruchanokal.movieomdb.viewmodel.SearchViewModel


class SearchFragment : Fragment() {

    private var binding : FragmentSearchBinding? = null
    private lateinit var viewModel : SearchViewModel
    var movieName = ""
    private val TAG = "SearchFragment"

    private val adapter = MovieRecyclerAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = androidx.lifecycle.ViewModelProvider(this).get(SearchViewModel::class.java)

        val llm  =  LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        binding!!.movieRecyclerView.layoutManager = llm
        binding!!.movieRecyclerView.adapter = adapter

        binding!!.searchButton.setOnClickListener {

            movieName = binding!!.editTextMovieName.text.toString()
            viewModel.refreshData(movieName)

            val inputMethodManager = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it?.windowToken, 0)
        }

        binding!!.editTextMovieName.setOnEditorActionListener { textView, i, keyEvent ->

            if (i == EditorInfo.IME_ACTION_DONE) {

                movieName = binding!!.editTextMovieName.text.toString()
                viewModel.refreshData(movieName)


                true
            }
            false
        }

        observeLiveData()


    }

    private fun observeLiveData() {

//        viewModel.movieSearchData.observe(viewLifecycleOwner, Observer { movieData ->
//
//            movieData?.let {
//
//                binding!!.contentLayout.visibility = View.VISIBLE
//                adapter.updateMovieList(movieData.search)
//
//                Log.i(TAG,"first ")
//                Log.i(TAG,"first title: " + movieData.search.get(0).title)
//                Log.i(TAG,"movieData: " + movieData.search)
//
//            }
//        })

        viewModel.movieDetailsListData.observe(viewLifecycleOwner, Observer { movieListData ->

            movieListData?.let {


                binding!!.contentLayout.visibility = View.VISIBLE
                adapter.updateMovieList(movieListData)

                Log.i(TAG,"first title: " + movieListData.get(0).Title)
                Log.i(TAG,"movieData: " + movieListData)



            }



        })

        viewModel.movieLoading.observe(viewLifecycleOwner, Observer {

            if (it) {
                binding!!.contentLayout.visibility = View.GONE
                binding!!.progressBarSearch.visibility = View.VISIBLE
                binding!!.errorTryAgainText.visibility = View.GONE
                binding!!.errorNoFilm.visibility = View.GONE
            } else {
                binding!!.progressBarSearch.visibility = View.GONE
            }

        })


        viewModel.movieError.observe(viewLifecycleOwner, Observer {

            if (it){

                binding!!.contentLayout.visibility = View.GONE
                binding!!.progressBarSearch.visibility = View.GONE
                binding!!.errorTryAgainText.visibility = View.VISIBLE

            } else {
                binding!!.errorTryAgainText.visibility = View.GONE
            }

        })

        viewModel.movieNullData.observe(viewLifecycleOwner, Observer {

            if (it){

                binding!!.contentLayout.visibility = View.GONE
                binding!!.progressBarSearch.visibility = View.GONE
                binding!!.errorNoFilm.visibility = View.VISIBLE

            } else {
                binding!!.errorNoFilm.visibility = View.GONE
            }

        })


    }
}