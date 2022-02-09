package com.example.newsappmvvmusingkotlin.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsappmvvmretrofitroomdb.R
import com.example.newsappmvvmusingkotlin.ui.Models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article_preview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(holder.imageView)
            holder.tvSource.text = article.source.name
            holder.tvTitle.text = article.title
            holder.tvDescription.text = article.description
            holder.tvPublishedAt.text = article.publishedAt


            setOnClickListener {
                // "?." it's mean != null
                onItemClickLisetener?.let {
                    // "it" refer to OnItemClickListener Method
                    it(article)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    //《《★₪₪₪₪₪₪--------⤘⤘⤘⤘⤘Click Listener⤘⤘⤘⤘⤘⤘--------★₪₪₪₪₪₪》》
    private var onItemClickLisetener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickLisetener = listener
    }
    //《《★₪₪₪₪₪₪--------⤘⤘⤘⤘⤘Click Listener⤘⤘⤘⤘⤘⤘--------★₪₪₪₪₪₪》》


    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.ivArticleImage)
        val tvSource: TextView = itemView.findViewById(R.id.tvSource)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvPublishedAt: TextView = itemView.findViewById(R.id.tvPublishedAt)

    }


    //《《★₪₪₪₪₪₪--------⤘⤘⤘⤘⤘DiffUtils--------⤘⤘⤘⤘⤘★₪₪₪₪₪₪》》

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            //because url is also unique for each article
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    //《《★₪₪₪₪₪₪--------⤘⤘⤘⤘⤘DiffUtils--------⤘⤘⤘⤘⤘★₪₪₪₪₪₪》》


}