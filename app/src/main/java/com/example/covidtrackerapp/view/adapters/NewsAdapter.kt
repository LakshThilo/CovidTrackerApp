package com.example.covidtrackerapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.covidtrackerapp.R
import com.example.covidtrackerapp.model.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){


    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // step 1: handle the two list lists for list
    private val differCallBack = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
           // return oldItem.id == newItem.id // this what we normally do but we dont have any id in ul
            return oldItem.url == newItem.url

        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    /*  step 2: will be to create our async list differ -- list differ is the tool that take out
        two list and compare them and calculate the difference
        as you can see async so asynchronous it will run in the background */

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        //now did not pass dataSet via constructor  - we need to get item count from differ
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        // setting our view accordingly

        val article =  differ.currentList[position] // current item

        // by calling apply we can immediately reference our views
        holder.itemView.apply {

            // loading image
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)

            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            //implement listener

            setOnClickListener{
                onItemClickListener?.let { it(article) } //if not null it goes to let block --> it refers to onItemClickListener
            }

        }
    }

  /*    Adding item click listener to our single adapter item because later it want be able to click on those
        because our article fragment opens up with web view --> having such a listener is just easier to access
        that on click event from the out side*/

    //// we passing current article when we click the item to that lambda function so we will be able to open the correct webView page
    private var onItemClickListener:((Article) -> Unit)? = null  //lambda taking article as parameter

    //set onClickListener --->>> item click listener is outside our use adapter
    fun setOnClickListener(listener : (Article)->Unit){
        onItemClickListener = listener
    }
}








/**
 *  Normally if you have a recycler View Adapter  and we pass that to NewsAdapter constructor parameter
 *  like -> NewsAdapter(dataSet: List<Article>) -->
 *      --  when every time you want to add article the it added to the list and u call adapter.notifyDataSetChanged()
 *
 *      --> it is very inefficient reason for that is --> recycler viewAdapter always update its whole
 *           items even that the items didn't change  solve that problem we can use what is called
 *
 *           ** diffUtil - its calculate the differences between two list and enable only us to update
 *                          those items that were difference and another advantage is that is will actually
 *                          happen in the background we don't block our main thread
 * */