package com.target.targetcasestudy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.DealItemAdapter
import com.target.targetcasestudy.viewmodel.DealListViewModel

class DealListFragment : Fragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    val viewModel: DealListViewModel by viewModels()
    viewModel.isLoading().observe(this, Observer { isLoading ->
      // do stuff to the UI
    })
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view =  inflater.inflate(R.layout.fragment_deal_list, container, false)
    recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
    recyclerView.adapter = DealItemAdapter()

    return view
  }

  fun updateProgressBar() {

  }

  companion object {
    private lateinit var recyclerView: RecyclerView
  }
}
