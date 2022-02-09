package com.example.newsappmvvmusingkotlin.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappmvvmretrofitroomdb.R
import com.example.newsappmvvmretrofitroomdb.databinding.FragmentBreakingNewsBinding
import com.example.newsappmvvmretrofitroomdb.databinding.FragmentSavedNewsBinding
import com.example.newsappmvvmusingkotlin.Adapters.NewsAdapter
import com.example.newsappmvvmusingkotlin.NewsActivity
import com.example.newsappmvvmusingkotlin.ui.NewsViewModel


class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter:NewsAdapter

    private lateinit var binding: FragmentSavedNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSavedNewsBinding.inflate(inflater, container, false);

        return binding.root;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as NewsActivity).viewModel
        setupRecyclerView()


        newsAdapter.setOnItemClickListener {

            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }



    }


    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvSavedNews.apply {

            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }
    }
}