package com.damnjanmarkovic.memes.ui.memes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.damnjanmarkovic.memes.R
import com.damnjanmarkovic.memes.models.Meme

class MemesAdapter (private val onClickListener: OnClickListener<Meme>, private val memes: List<Meme?>): RecyclerView.Adapter<MemesAdapter.MainViewHolder>()
//class MemesAdapter(private val onClickListener: OnClickListener) : ListAdapter<Meme, MemesAdapter.MainViewHolder>(MyDiffUtil)
{

    inner class MainViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun bindData(meme: Meme){
            val name = view.findViewById<TextView>(R.id.memeName)
            val image = view.findViewById<ImageView>(R.id.memeImage)

            if (meme != null) {
                name.text = meme.name
            }
            if (meme != null) {
                image.load(meme.url){
                    transformations(CircleCropTransformation())
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.memes_item, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            memes[position]?.let { meme -> onClickListener.onClick(meme) }
        }
        memes[position]?.let {
            holder.bindData(it)

        }
    }

    override fun getItemCount(): Int {
        return memes.size
    }




}
class OnClickListener<T>(val clickListener: (meme: T) -> Unit) {
    fun onClick(meme: T) = clickListener(meme)
}
