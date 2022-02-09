package com.example.newsappmvvmusingkotlin


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsappmvvmretrofitroomdb.R
import com.example.newsappmvvmretrofitroomdb.databinding.ActivityNewsBinding
import com.example.newsappmvvmusingkotlin.Repository.NewsRepository
import com.example.newsappmvvmusingkotlin.ui.DB.ArticleDatabase
import com.example.newsappmvvmusingkotlin.ui.NewsViewModel
import com.example.newsappmvvmusingkotlin.ui.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    private lateinit var binding:ActivityNewsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater);
        setContentView(binding.root)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)



        Log.d("LKSLDJFSDF", "NewsActivity: "+viewModel)

        // BottomNavigation e click korle direct j fragment r name menu create korechi sekhane cole jabe
        val navController=findNavController(R.id.newsNavHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)




    }
}