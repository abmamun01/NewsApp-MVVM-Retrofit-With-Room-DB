package com.example.newsappmvvmusingkotlin.ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappmvvmretrofitroomdb.R
import com.example.newsappmvvmretrofitroomdb.databinding.FragmentBreakingNewsBinding
import com.example.newsappmvvmusingkotlin.Adapters.NewsAdapter
import com.example.newsappmvvmusingkotlin.NewsActivity
import com.example.newsappmvvmusingkotlin.Repository.NewsRepository
import com.example.newsappmvvmusingkotlin.Utils.Resource
import com.example.newsappmvvmusingkotlin.ui.DB.ArticleDatabase
import com.example.newsappmvvmusingkotlin.ui.NewsViewModel
import com.example.newsappmvvmusingkotlin.ui.NewsViewModelProviderFactory


class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {


    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: FragmentBreakingNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {

        binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)

        return binding.root;
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val newsRepository = NewsRepository(ArticleDatabase(requireContext()))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)



        Toast.makeText(requireContext(), "Hello", Toast.LENGTH_LONG).show()
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {

            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->

                        Log.e("TAG", "An Error occurred $message")
                    }
                }
                is Resource.Loading ->
                    showProgressBar()
            }
        })
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }


    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {

            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }
    }

}