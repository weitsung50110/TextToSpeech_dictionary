package com.example.dictionary_public

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.notes_row.view.*
import kotlinx.android.synthetic.main.pen_row.view.*
import kotlinx.android.synthetic.main.row.view.*

class pen_Adapter (val arrayList2:ArrayList<pen_Model>, val context: Context) : RecyclerView.Adapter<pen_Adapter.ViewHolder2>(){
    class ViewHolder2(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems (model2:pen_Model){
            itemView.pen_titleTv.text=model2.p_title
            itemView.pen_desTv.text=model2.p_des
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.pen_row,parent,false)
        return  ViewHolder2(v)
    }

    override fun getItemCount(): Int {
        return arrayList2.size
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        holder.bindItems(arrayList2[position])

        val db = Firebase.firestore  // Access a Cloud Firestore instance from your Activity (初始化Firebase)
        holder.itemView.pen_delete_buttonTv.setOnClickListener(){

            db.collection("agenda_books").document(holder.itemView.pen_titleTv.text.toString())
                .delete()
                .addOnSuccessListener { Toast.makeText(context,holder.itemView.pen_titleTv.text.toString()+"刪除成功!", Toast.LENGTH_SHORT).show()}
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(context,""+holder.itemView.pen_titleTv.text+ " Yes~~", Toast.LENGTH_SHORT).show()

        }
    }
}