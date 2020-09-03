package com.zainalsalamun.project.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zainalsalamun.project.movieapp.R
import com.zainalsalamun.project.movieapp.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.CardViewViewHolder>(){

    private var onItemClickCallback : OnItemClickCallback? = null
    private var pathPoster = "https://image.tmdb.org/t/p/w185"
    private val listMovie = ArrayList<Movie>()

    fun setData(items: ArrayList<Movie>){
        listMovie.clear()
        listMovie.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(movieDB: Movie){
            with(itemView){
                tvm_title.text = movieDB.title
                tvm_rating.text = movieDB.rating.toString()
                Picasso.get().load(pathPoster+movieDB.posterPath).into(ivm_poster)
                tvm_overview.text = movieDB.overview
                btn_detail.setOnClickListener{onItemClickCallback?.onItemClicked(movieDB)}
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Movie)
    }

}