package com.example.dictionary_public

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore  // Access a Cloud Firestore instance from your Activity (初始化Firebase)

        var i:Int=0
        val arrayList_main = ArrayList<Model>()
        db.collection("companies")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var ti:String = document.data["title"].toString()
                    var des:String = document.data["des2"].toString()



                    i=i+1
                    if(i==1)
                        arrayList_main.add(Model(ti,des,R.drawable.a1))
                    else if(i==2)
                        arrayList_main.add(Model(ti,des,R.drawable.a2))
                    else if(i==3)
                        arrayList_main.add(Model(ti,des,R.drawable.a3))
                    else if(i==4) {
                        arrayList_main.add(Model(ti, des, R.drawable.a4))
                    }
                    else if(i==5) {
                        arrayList_main.add(Model(ti, des, R.drawable.a5))
                    }
                    else if(i==6) {
                        arrayList_main.add(Model(ti, des, R.drawable.a6))
                    }
                    else if(i==7) {
                        arrayList_main.add(Model(ti, des, R.drawable.a7))
                    }
                    else if(i==8) {
                        arrayList_main.add(Model(ti, des, R.drawable.a8))
                        i=0
                    }
                }

                val myAdapter_main = MyAdapter(arrayList_main,this)
                recyclerView2.layoutManager=LinearLayoutManager(this)
                recyclerView2.adapter = myAdapter_main
            }


        button1.setOnClickListener(mListener)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_item,menu)


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.notes_menu ->{
                Toast.makeText(this,"Go to notes~",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,notes::class.java)
                this.startActivity(intent)

                return true
            }
            R.id.pen_menu ->{
                Toast.makeText(this,"Go to pen~",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,pen::class.java)
                this.startActivity(intent)

                return true
            }
            R.id.app_des ->{
                Toast.makeText(this,"Go to 說明頁面~",Toast.LENGTH_SHORT).show()
                AlertDialog.Builder(this)
                    //AlertDialog.Builder (context: Context)
                    //參數放要傳入的 MainActivity Context
                    .setTitle("APP使用說明")
                    .setMessage("1.本字典為有聲字典,所有單字和例句都會有人工語音幫妳念出來,不用再花時間去看KK音標.\n" +
                            "2.當遇到沒見過的單字也可以新增到筆記本裡面觀看!\n3.筆記本裡面的單字都是可以自由刪除的.\n" +
                            "4.每個單字裡面的例句解釋都是可以在Firebase自由新增和更改的~\n5.當想查詢特定字母開頭的單字時,可以使用關鍵字查詢單字.")  //訊息內容
                    .setPositiveButton("確認") {_,_ ->
                        Toast.makeText(this,"你按了確認~",Toast.LENGTH_SHORT).show()
                    }
                    .create()
                    .show()
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private val mListener = View.OnClickListener {

        val db = Firebase.firestore  // Access a Cloud Firestore instance from your Activity (初始化Firebase)
        var i:Int=0
        val arrayList_main = ArrayList<Model>()
        db.collection("companies")
            .orderBy("title")
            .startAt(add_editText1.text.toString())
            .endAt(add_editText1.text.toString()+"\uf8ff")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var ti:String = document.data["title"].toString()
                    var des:String = document.data["des2"].toString()


                        i = i + 1
                        if (i == 1)
                            arrayList_main.add(Model(ti, des, R.drawable.a1))
                        else if (i == 2)
                            arrayList_main.add(Model(ti, des, R.drawable.a2))
                        else if (i == 3)
                            arrayList_main.add(Model(ti, des, R.drawable.a3))
                        else if (i == 4) {
                            arrayList_main.add(Model(ti, des, R.drawable.a4))
                        } else if (i == 5) {
                            arrayList_main.add(Model(ti, des, R.drawable.a5))
                        } else if (i == 6) {
                            arrayList_main.add(Model(ti, des, R.drawable.a6))
                        } else if (i == 7) {
                            arrayList_main.add(Model(ti, des, R.drawable.a7))
                            i = 0
                        }

                }

                val myAdapter_main = MyAdapter(arrayList_main,this)
                recyclerView2.layoutManager=LinearLayoutManager(this)
                recyclerView2.adapter = myAdapter_main

            }

    }

}
