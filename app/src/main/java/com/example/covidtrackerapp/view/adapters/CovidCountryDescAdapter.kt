package com.example.covidtrackerapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtrackerapp.R
import com.example.covidtrackerapp.model.Article
import com.example.covidtrackerapp.model.remote.covidApi.CountryDescription
import kotlinx.android.synthetic.main.single_country_layout.view.*


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
class CovidCountryDescAdapter: RecyclerView.Adapter<CovidCountryDescAdapter.CovidDescViewHolder>() {

    inner class CovidDescViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView)


    // step 1: handle the two list lists for list
    private val differCallBack = object : DiffUtil.ItemCallback<CountryDescription>(){

        override fun areItemsTheSame(oldItem: CountryDescription, newItem: CountryDescription): Boolean {

            return oldItem.CountryCode == newItem.CountryCode

        }

        override fun areContentsTheSame(oldItem: CountryDescription, newItem: CountryDescription): Boolean {
            return oldItem == newItem
        }

    }

    /*  step 2: will be to create our async list differ -- list differ is the tool that take out
       two list and compare them and calculate the difference
       as you can see async so asynchronous it will run in the background */

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidDescViewHolder {

        return CovidDescViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_country_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CovidDescViewHolder, position: Int) {

        val country =  differ.currentList[position]

        holder.itemView.apply {
         tvCountryName.text =   country.Country
         tvTotalConfirmCases.text = country.TotalConfirmed.toString()
         tvTotalDeathCases.text = country.TotalDeaths.toString()
         tvTotalRecovered.text = country.TotalRecovered.toString()

            setOnClickListener{
                onItemClickListener?.let { it(country) } //if not null it goes to let block --> it refers to onItemClickListener
            }

        }
    }

    /*    Adding item click listener to our single adapter item because later it want be able to click on those
      because our article fragment opens up with web view --> having such a listener is just easier to access
      that on click event from the out side*/

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((CountryDescription) -> Unit)? = null

    fun setOnClickListener(listener : (CountryDescription)->Unit) {
        onItemClickListener = listener
    }

}