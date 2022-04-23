package ru.mirea.pr33

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<Person>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewName: TextView = itemView.findViewById(R.id.name)
        val textViewPhoneNumber: TextView = itemView.findViewById(R.id.phoneNumber)
        val imageViewSex: ImageView = itemView.findViewById(R.id.sex)   //конструктор, инициализация
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)  //преобразование layout в View
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.textViewName.text = ItemsViewModel.name
        holder.textViewPhoneNumber.text = ItemsViewModel.phoneNumber
        if (ItemsViewModel.sex == "M") {
            holder.imageViewSex.setImageResource(R.drawable.man)
        }else if (ItemsViewModel.sex == "W") {
            holder.imageViewSex.setImageResource(R.drawable.woman)
        }else {
            holder.imageViewSex.setImageResource(R.drawable.non) //заполнение View элементами
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}