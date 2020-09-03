package com.zainalsalamun.project.movieapp.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zainalsalamun.project.movieapp.BuildConfig
import com.zainalsalamun.project.movieapp.activity.MainActivity
import com.zainalsalamun.project.movieapp.R
import com.zainalsalamun.project.movieapp.activity.DetailTvShowActivity
import com.zainalsalamun.project.movieapp.adapter.TvShowAdapter
import com.zainalsalamun.project.movieapp.model.TvShow
import com.zainalsalamun.project.movieapp.viewmodel.TvShowViewModel

class FragmentTvShow : Fragment() {

    private lateinit var rvTVShow: RecyclerView
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var prBar: ProgressBar
    private lateinit var adapter: TvShowAdapter
    private var querySearch: String? = null

    companion object {
        internal const val APP_ID = BuildConfig.API_KEY
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        querySearch = savedInstanceState?.getString("querytv")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d("URL", "query: $query")
                Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
                querySearch = query
                showRecyclerCardView(querySearch)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("querytv", querySearch)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvTVShow = view.findViewById(R.id.rv_tvshow)
        prBar = view.findViewById(R.id.progressBartv)

        setHasOptionsMenu(true)
        showRecyclerCardView(querySearch)

        rvTVShow.setHasFixedSize(true)
    }


    private fun showRecyclerCardView(query: String?) {
        adapter = TvShowAdapter()
        adapter.notifyDataSetChanged()

        rvTVShow.layoutManager = LinearLayoutManager(context)
        rvTVShow.adapter = adapter

        tvShowViewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)
        if (query == null){
            tvShowViewModel.setTVShows(1, "")
        } else {
            tvShowViewModel.setTVShows(2, query)
        }
        showLoading(true)

        tvShowViewModel.getTVShows().observe(this, androidx.lifecycle.Observer { tvShowItems ->
            if (tvShowItems != null) {
                adapter.setData(tvShowItems)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : TvShowAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TvShow) {
                showSelectedData(data)
            }
        })
    }

    private fun showSelectedData(tvShow: TvShow) {
        val moveObjectIntent = Intent(activity, DetailTvShowActivity::class.java)
        moveObjectIntent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvShow)
        startActivity(moveObjectIntent)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            prBar.visibility = View.VISIBLE
        } else {
            prBar.visibility = View.GONE
        }
    }

}