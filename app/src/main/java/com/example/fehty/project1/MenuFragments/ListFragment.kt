package com.example.fehty.project1.MenuFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fehty.project1.Adapter.RecyclerViewAdapter
import com.example.fehty.project1.ItemTouchHelper.RecyclerItemTouchHelper
import com.example.fehty.project1.ItemTouchHelper.RecyclerItemTouchHelperListener
import com.example.fehty.project1.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), RecyclerItemTouchHelperListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    private val adapter = RecyclerViewAdapter(this)
    private val list = arrayListOf("Russia", "England", "Spain", "Canada", "USA", "Egypt", "Australia", "Germany", "Portugal", "Brazil")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        recyclerView.adapter = adapter

        adapter.setList(list)


        val itemTouchHelperCallBack = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        adapter.removeItem(viewHolder.adapterPosition)
    }

    fun goToActivity() {
        val fragmentBack = BackFragment()
        fragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragmentBack)
                ?.commit()
    }
}
