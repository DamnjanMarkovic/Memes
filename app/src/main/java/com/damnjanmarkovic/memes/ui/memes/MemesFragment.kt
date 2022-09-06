package com.damnjanmarkovic.memes.ui.memes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.damnjanmarkovic.memes.R
import com.damnjanmarkovic.memes.databinding.MemesBinding
import com.damnjanmarkovic.memes.helpers.ScreenState
import com.damnjanmarkovic.memes.models.Meme
import com.damnjanmarkovic.memes.ui.memes.meme_selected.MemeSelected

class MemesFragment : Fragment() {

    private var _binding: MemesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MemesViewModel by lazy{
        ViewModelProvider(this).get(MemesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {

        _binding = MemesBinding.inflate(inflater, container, false)

        viewModel.characterLivedata.observe(viewLifecycleOwner) { state ->
            if (container != null) {
                processCharactersResponse(state, container)
            }
        }

        return binding.root
    }



    private fun processCharactersResponse(
        state: ScreenState<List<Meme?>?>,
        container: ViewGroup,

        ){

        val progressbar = container?.findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = container?.findViewById<RecyclerView>(R.id.charactersRv)
        val textError = container?.findViewById<TextView>(R.id.textError)

        when(state){
            is ScreenState.Loading -> {
                if (progressbar != null) {
                    progressbar.visibility = View.VISIBLE
                }
                if (textError != null) {
                    textError.visibility = View.GONE
                }
            }
            is ScreenState.Success -> {
                if (progressbar != null) {
                    progressbar.visibility = View.GONE
                }
                if (textError != null) {
                    textError.visibility = View.GONE
                }
                if(state.data != null) {
                    val adapter = MemesAdapter(
                        OnClickListener(onMemeClicked()),
                        state.data)
                    recyclerView?.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    recyclerView?.adapter = adapter
                }
            }
            is ScreenState.Error -> {
                if (progressbar != null) {
                    progressbar.visibility = View.GONE
                }
                if (textError != null) {
                    textError.visibility = View.VISIBLE
                    textError.text = state.message
                }
            }
        }
    }


    private fun onMemeClicked() = { meme: Meme ->

        val intent = Intent(this.context, MemeSelected::class.java)
        intent.putExtra("URL", meme.url)
        startActivity(intent)
/*
        Log.e("Success", meme.name.toString())
        Toast.makeText(applicationContext, "${meme.name}", Toast.LENGTH_SHORT).show()
*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}