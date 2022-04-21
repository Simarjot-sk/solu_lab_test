package com.solu_lab.test.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.solu_lab.test.R
import com.solu_lab.test.data.model.CoinItem
import com.solu_lab.test.databinding.ItemCoinBinding


class CoinAdapter(
    private val coinList: List<CoinItem>
) : RecyclerView.Adapter<CoinViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val item = coinList[position]
        holder.bind(item)
    }

    override fun getItemCount() = coinList.size
}

class CoinViewHolder(private val itemBinding: ItemCoinBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(coinItem: CoinItem) {
        val requestBuilder: RequestBuilder<Drawable> = Glide.with(itemView.context)
            .asDrawable().sizeMultiplier(0.1f)

        Glide.with(itemBinding.imageView)
            .load(coinItem.pictures?.frontImage?.url)
            .thumbnail(requestBuilder)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(itemBinding.imageView)

        itemBinding.textView.text = coinItem.name
    }

    companion object {
        fun create(parent: ViewGroup): CoinViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCoinBinding.inflate(layoutInflater, parent, false)
            return CoinViewHolder(binding)
        }
    }
}