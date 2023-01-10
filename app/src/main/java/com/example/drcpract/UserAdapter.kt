package com.example.drcpract

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.drcpract.RoomDb.Task
import com.example.drcpract.databinding.RowviewUserLayoutBinding


class UserAdapter internal constructor(
    private val onItemClickListener: OnItemClickListenerData,
    private var arrayList: ArrayList<Task>,
    var context: Context,

) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        MyViewHolder(
            RowviewUserLayoutBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        try {
            val item = arrayList[position]

            holder.binding.txtName.text = item.name
            holder.binding.txtAddress.text = item.vicinity

            holder.binding.cardView.setOnClickListener {
                onItemClickListener.onItemClick(it,position)
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class MyViewHolder(val binding: RowviewUserLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

}


