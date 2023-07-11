package com.example.listedassignment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listedassignment.R
import com.example.listedassignment.activities.MainActivity
import com.example.listedassignment.adapter.RecentListAdapter
import com.example.listedassignment.adapter.TopListAdapter
import com.example.listedassignment.models.TopLink
import com.example.listedassignment.viewmodel.MainViewModel


class RecentLinksFragments : Fragment() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_recent_links_fragments, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var data =  (activity as MainActivity).dataList

        var recentLinkList =  data.data.recent_links;

        recyclerView.layoutManager = LinearLayoutManager(activity)
        var adapter = RecentListAdapter(recentLinkList, context)
        recyclerView.adapter = adapter


    }


}