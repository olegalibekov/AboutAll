package com.example.fehty.project1.ItemTouchHelper

//class RecyclerItemTouchHelper(dragDirs: Int, swipeDirs: Int, private val listener: RecyclerItemTouchHelperListener) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
//
//    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
//        return true
//    }
//
//    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        listener.onSwiped(viewHolder, direction, viewHolder.adapterPosition)
//    }
//
//    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
//        val foregroundView = (viewHolder as RecyclerViewAdapter.ViewHolder).viewForeground
//        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(foregroundView)
//    }
//
//    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
//        if (viewHolder != null) {
//            val foregroundView = (viewHolder as RecyclerViewAdapter.ViewHolder).viewForeground
//            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(foregroundView)
//        }
//    }
//
//    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
//        val foregroundView = (viewHolder as RecyclerViewAdapter.ViewHolder).viewForeground
//        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
//    }
//
//    override fun onChildDrawOver(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
//        val foregroundView = (viewHolder as RecyclerViewAdapter.ViewHolder).viewForeground
//        ItemTouchHelper.Callback.getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive)
//    }
//}