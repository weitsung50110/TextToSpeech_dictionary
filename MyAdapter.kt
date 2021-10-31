package com.example.dictionary_public

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*

class MyAdapter (val arrayList2:ArrayList<Model>, val context: Context) : RecyclerView.Adapter<MyAdapter.ViewHolder2>(){
    class ViewHolder2(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindItems (model2:Model){
            itemView.titleTv.text=model2.title
            itemView.desTv.text=model2.des
            itemView.imageTv.setImageResource(model2.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
        return  ViewHolder2(v)
    }

    override fun getItemCount(): Int {
        return arrayList2.size
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        holder.bindItems(arrayList2[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(context,""+holder.itemView.titleTv.text+ " Yes~~",Toast.LENGTH_SHORT).show()

            val model3 = arrayList2.get(position)
            var gTitle:String = model3.title
            var gDescription:String = model3.des
            var gImgview:Int = model3.img

            val intent = Intent(context,AnotherActivity::class.java)

            intent.putExtra("iTitle",gTitle)
            intent.putExtra("iDescription",gDescription)
            intent.putExtra("iImgview",gImgview)

            context.startActivity(intent)
        }
    }
}