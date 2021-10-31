package com.example.dictionary_public

import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_another.*
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.android.synthetic.main.notes_row.view.*
import kotlinx.android.synthetic.main.row.view.*
import java.util.*
import kotlin.collections.ArrayList

class notes_Adapter (val arrayList2:ArrayList<notes_Model>, val context: Context) : RecyclerView.Adapter<notes_Adapter.ViewHolder2>(){
    class ViewHolder2(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems (model2:notes_Model){
            itemView.notes_titleTv.text=model2.title
            itemView.notes_desTv.text=model2.des
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.notes_row,parent,false)
        return  ViewHolder2(v)
    }

    override fun getItemCount(): Int {
        return arrayList2.size
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        holder.bindItems(arrayList2[position])

        val db = Firebase.firestore  // Access a Cloud Firestore instance from your Activity (初始化Firebase)
        holder.itemView.notes_delete_buttonTv.setOnClickListener(){

            db.collection("notes_book").document(holder.itemView.notes_titleTv.text.toString())
                .delete()
                .addOnSuccessListener { Toast.makeText(context,holder.itemView.notes_titleTv.text.toString()+"刪除成功!", Toast.LENGTH_SHORT).show()}
        }

        holder.itemView.card_start_buttonTv.setOnClickListener(){


            db.collection("notes_book")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {

                        if(document.data["title"].toString()==holder.itemView.notes_titleTv.text.toString())
                        {
                            if(document.data["star"].toString()=="0")
                            {
                                db.collection("notes_book").document(holder.itemView.notes_titleTv.text.toString())
                                    .update(mapOf("star" to 1))
                                    .addOnSuccessListener { Toast.makeText(context,holder.itemView.notes_titleTv.text.toString()+"變 1 了!", Toast.LENGTH_SHORT).show()}
                            }
                            else if(document.data["star"].toString()=="1"){
                                db.collection("notes_book").document(holder.itemView.notes_titleTv.text.toString())
                                    .update(mapOf("star" to 0))
                                    .addOnSuccessListener { Toast.makeText(context,holder.itemView.notes_titleTv.text.toString()+"變 0 了!", Toast.LENGTH_SHORT).show()}
                            }
                        }
                    }
                }

        }

        lateinit var mTTs: TextToSpeech
        mTTs = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if(status != TextToSpeech.ERROR){
                mTTs.language= Locale.CANADA
            }
        })

        holder.itemView.setOnClickListener {
            Toast.makeText(context,""+holder.itemView.notes_titleTv.text+ " Yes~~", Toast.LENGTH_SHORT).show()

            mTTs.speak(holder.itemView.notes_titleTv.text.toString(), TextToSpeech.QUEUE_FLUSH, null)
        }

    }
}