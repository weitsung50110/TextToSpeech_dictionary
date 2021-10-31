package com.example.dictionary_public

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.android.synthetic.main.activity_pen.*

class pen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pen)


        val db = Firebase.firestore  // Access a Cloud Firestore instance from your Activity (初始化Firebase)

        val arrayList_notes = ArrayList<pen_Model>()
        db.collection("agenda_books")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    arrayList_notes.add(pen_Model(document.data["p_title"].toString(),document.data["p_des"].toString()))
                }
                val myAdapter_notes = pen_Adapter(arrayList_notes,this)
                pen_recyclerView.layoutManager= LinearLayoutManager(this)
                pen_recyclerView.adapter = myAdapter_notes
            }



        //ItemTouch()

        pen_button1.setOnClickListener(mListener)
        pen_button2.setOnClickListener(mListener2)
    }
    private val mListener = View.OnClickListener {
        val db = Firebase.firestore

        // Create a new user with a first and last name
        val agenda_book = hashMapOf(
            "p_title" to pen_title_text1.text.toString(),
            "p_des" to pen_title_text2.text.toString()
        )

        db.collection("agenda_books").document(pen_title_text1.text.toString())
            .set(agenda_book)  //資料庫 集合名字 >>  companies    ,   並且把欄位 "name" , "address" , "number" 丟進去資料庫 集合裡面~~
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "新增成功 !!", Toast.LENGTH_SHORT).show()
            }
    }
    private val mListener2 = View.OnClickListener {
        //textView2.text ="" //保持一開始 textView1 裡面是空的
        val db = Firebase.firestore  // Access a Cloud Firestore instance from your Activity (初始化Firebase)

        val arrayList_notes = ArrayList<pen_Model>()
        db.collection("agenda_books")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //var Html_des:String="<font color='#4D0000'>"+document.data["title"].toString()+ "</font>"
                    //textView2.append(Html.fromHtml(Html_des))
                    //textView2.append("\n")

                    arrayList_notes.add(pen_Model(document.data["p_title"].toString(),document.data["p_des"].toString()))


                }
                val myAdapter_notes = pen_Adapter(arrayList_notes,this)
                pen_recyclerView.layoutManager= LinearLayoutManager(this)
                pen_recyclerView.adapter = myAdapter_notes

            }
    }
}
