package com.ferdikanat.countryapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ferdikanat.countryapp.R
import com.ferdikanat.countryapp.databinding.ItemCountryBinding
import com.ferdikanat.countryapp.model.Country
import com.ferdikanat.countryapp.util.downloadURL
class CountryAdapter(var countryList: ArrayList<Country>, private var onClick:(position: Int) -> Unit): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(var view: ItemCountryBinding): RecyclerView.ViewHolder(view.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater, R.layout.item_country,parent,false)

        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.countryTV.text = countryList[position].name
        holder.view.regionTV.text = countryList[position].region
        holder.view.countryIV.downloadURL(countryList[position].flagUrl)
        holder.view.cvItem.setOnClickListener{
            onClick(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Country>){
        countryList = newList as ArrayList<Country>
        notifyDataSetChanged()
    }

}