package com.example.covidtrackerapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.covidtrackerapp.R
import com.example.covidtrackerapp.model.CountryItem
import kotlinx.android.synthetic.main.single_country_layout.view.*


class CountryAdapter :RecyclerView.Adapter<CountryAdapter.CountryItemViewHolder>(){

    inner class CountryItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

        // step 1: handle the two list lists for list
        private val differCallBack = object : DiffUtil.ItemCallback<CountryItem>(){
            override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
                return oldItem.tld[0] == newItem.tld[0]
            }

            override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
                 return   oldItem == newItem
            }



    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {

        return CountryItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_country_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {

        val countryItem = differ.currentList[position]

        holder.itemView.apply {

           // Glide.with(this).load(countryItem.flags[0]).into(ivCountryFlag)
            tvCountryName.text = countryItem.name.common

            setOnClickListener{
                onItemClickListener?.let { it(countryItem) } //if not null it goes to let block --> it refers to onItemClickListener
            }
        }

    }

    private var onItemClickListener:((CountryItem) -> Unit)? = null

    fun setOnClickListener(listener : (CountryItem)->Unit){
        onItemClickListener = listener
    }

}