package com.example.dictionary_public

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.android.synthetic.main.activity_notes.add_editText1


class notes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        actionBar.setTitle("單字卡 Notes")


        val db = Firebase.firestore  // Access a Cloud Firestore instance from your Activity (初始化Firebase)

        val arrayList_notes = ArrayList<notes_Model>()
        db.collection("notes_book")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    arrayList_notes.add(notes_Model(document.data["title"].toString(),document.data["des"].toString()))
                }
                val myAdapter_notes = notes_Adapter(arrayList_notes,this)
                notes_recyclerView1.layoutManager= LinearLayoutManager(this)
                notes_recyclerView1.adapter = myAdapter_notes
            }


        //ItemTouch()

        add_btn.setOnClickListener(mListener)
        select_btn.setOnClickListener(mListener2)
        big_start_btn.setOnClickListener(mListener3)
        search_btn.setOnClickListener(mListener4)
    }

    private val mListener = View.OnClickListener {
        val db = Firebase.firestore

        // Create a new user with a first and last name
        val notes_book = hashMapOf(
            "title" to add_editText1.text.toString(),
            "des" to add_editText2.text.toString(),
            "star" to "0"
        )

        db.collection("notes_book").document(add_editText1.text.toString())
            .set(notes_book)  //資料庫 集合名字 >>  companies    ,   並且把欄位 "name" , "address" , "number" 丟進去資料庫 集合裡面~~
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "新增成功 !!", Toast.LENGTH_SHORT).show()
            }
    }

    private val mListener2 = View.OnClickListener {
        //textView2.text ="" //保持一開始 textView1 裡面是空的
        val db = Firebase.firestore  // Access a Cloud Firestore instance from your Activity (初始化Firebase)

        val arrayList_notes = ArrayList<notes_Model>()
        db.collection("notes_book")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //var Html_des:String="<font color='#4D0000'>"+document.data["title"].toString()+ "</font>"
                    //textView2.append(Html.fromHtml(Html_des))
                    //textView2.append("\n")

                    arrayList_notes.add(notes_Model(document.data["title"].toString(),document.data["des"].toString()))


                }
                val myAdapter_notes = notes_Adapter(arrayList_notes,this)
                notes_recyclerView1.layoutManager= LinearLayoutManager(this)
                notes_recyclerView1.adapter = myAdapter_notes

            }
    }

    private val mListener3 = View.OnClickListener {
        //textView2.text ="" //保持一開始 textView1 裡面是空的
        val db = Firebase.firestore  // Access a Cloud Firestore instance from your Activity (初始化Firebase)

        val arrayList_notes = ArrayList<notes_Model>()
        db.collection("notes_book")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //var Html_des:String="<font color='#4D0000'>"+document.data["title"].toString()+ "</font>"
                    //textView2.append(Html.fromHtml(Html_des))
                    //textView2.append("\n")
                    if(document.data["star"].toString()=="1")
                    {
                        arrayList_notes.add(notes_Model(document.data["title"].toString(),document.data["des"].toString()))
                    }

                }
                val myAdapter_notes = notes_Adapter(arrayList_notes,this)
                notes_recyclerView1.layoutManager= LinearLayoutManager(this)
                notes_recyclerView1.adapter = myAdapter_notes

            }
    }

    private val mListener4 = View.OnClickListener {
        //textView2.text ="" //保持一開始 textView1 裡面是空的
        val db = Firebase.firestore  // Access a Cloud Firestore instance from your Activity (初始化Firebase)

        val arrayList_notes = ArrayList<notes_Model>()
        db.collection("notes_book")
            .orderBy("title")
            .startAt(search_editText.text.toString())
            .endAt(search_editText.text.toString()+"\uf8ff")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //var Html_des:String="<font color='#4D0000'>"+document.data["title"].toString()+ "</font>"
                    //textView2.append(Html.fromHtml(Html_des))
                    //textView2.append("\n")

                    arrayList_notes.add(notes_Model(document.data["title"].toString(),document.data["des"].toString()))


                }
                val myAdapter_notes = notes_Adapter(arrayList_notes,this)
                notes_recyclerView1.layoutManager= LinearLayoutManager(this)
                notes_recyclerView1.adapter = myAdapter_notes

            }
    }


}

