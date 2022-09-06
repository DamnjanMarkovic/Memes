package com.damnjanmarkovic.memes.ui.memes.meme_selected

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.damnjanmarkovic.memes.R

class MemeSelected : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meme_selected)

        supportActionBar?.hide()
        val imageMemeSelected = findViewById<ImageView>(R.id.imageMemeSelected)

        val imageURL = intent.getStringExtra("URL")
        imageMemeSelected.load(imageURL){
            transformations()
        }




    }
}