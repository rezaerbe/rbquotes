package com.erbe.rbquotes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erbe.rbquotes.data.Quote
import com.erbe.rbquotes.databinding.RwquoteItemBinding

class QuoteAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    private var quotes: List<Quote> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val rwquoteItemBinding =
            RwquoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(rwquoteItemBinding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.bind(quote)
    }

    override fun getItemCount(): Int = quotes.size

    fun setQuotes(quotes: List<Quote>) {
        this.quotes = quotes
        notifyDataSetChanged()
    }

    inner class QuoteViewHolder(val rwquoteItemBinding: RwquoteItemBinding) :
        RecyclerView.ViewHolder(rwquoteItemBinding.root) {
        fun bind(quote: Quote) {
            rwquoteItemBinding.quoteText.text = quote.text
            rwquoteItemBinding.quoteAuthor.text = quote.author
            rwquoteItemBinding.quoteDate.text = quote.date

            rwquoteItemBinding.root.setOnClickListener {
                clickListener.onClick(quote)
            }
        }
    }
}

interface ClickListener {
    fun onClick(quote: Quote)
}