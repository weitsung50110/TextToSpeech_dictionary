package com.example.dictionary_public

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_another.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notes.*
import java.util.*
import kotlin.math.log

class AnotherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        var intent2 = intent
        var aTitle = intent2.getStringExtra("iTitle")
        var aDescription = intent2.getStringExtra("iDescription")
        var aImgview = intent2.getIntExtra("iImgview",0)

        actionBar.setTitle(aTitle)

        a_title.text=aTitle
        //a_des.text=aDescription
        a_img.setImageResource(aImgview)



        var items1 = arrayOfNulls<String>(10)
        for (i in 0..9) {
            items1[i] = "第" + (i + 1) + "段"
        }
        // 將內容與ArrayAdapter連結
        val adapter_Spinner1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items1)
        // 設置下拉列表的風格 (不一定需要)
        adapter_Spinner1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter_Spinner1

        var items2 = arrayOfNulls<String>(50)
        for (i in 0..49) {
            items2[i] = "第" + (i + 1) + "行"
        }
        // 將內容與ArrayAdapter連結
        val adapter_Spinner2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items2)
        // 設置下拉列表的風格 (不一定需要)
        adapter_Spinner2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter_Spinner2



        lateinit var mTTs: TextToSpeech
        mTTs = TextToSpeech(applicationContext,TextToSpeech.OnInitListener { status ->
            if(status != TextToSpeech.ERROR){
                mTTs.language= Locale.CANADA
            }
        })
        title_sound_btn.setOnClickListener{
            mTTs.speak(aTitle,TextToSpeech.QUEUE_FLUSH, null)
        }

        val db = Firebase.firestore  // Access a Cloud Firestore instance from your Activity (初始化Firebase)

        a_des.text ="" //保持一開始 textView1 裡面是空的
        db.collection("companies")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if(aTitle==document.data["title"].toString()){
                        for(j in 1..100){

                            var des:String = document.data["des$j"].toString()
                            if(des!="null"){
                                if(j==1||j==2){
                                    if(j==1){
                                        a_des.append("★\n")
                                    }
                                    var mes:String="<font color='#4D0000'>"+des+ "</font><br/>"
                                    a_des.append(Html.fromHtml(mes))
                                }
                                else{
                                    a_des.append(Html.fromHtml(des))
                                    a_des.append("\n")
                                }

                                if(j==2){
                                    a_des.append("\n\n")
                                }
                                else if(j%2==0){
                                    a_des.append("\n")
                                }
                            }
                            else{
                                break
                            }
                        }
                        for(q in 2..50){
                            for(k in 1..100){

                                des_sound_btn.setOnClickListener{
                                    var sp1:String=  (spinner1.getSelectedItemPosition()+1).toString()
                                    var sp2:String=  (spinner2.getSelectedItemPosition()+1).toString()
                                    if(sp1=="1"){
                                        sp1=""
                                    }
                                    var sp3:String = document.data["$sp1"+"des$sp2"].toString()
                                    var sp4:String = sp3.replace("<big>","").
                                        replace("</big>","").
                                        replace("<small>","").
                                        replace("</small>","").
                                        replace("<br>","").
                                        replace("<br/>","").
                                        replace("&nbsp;","").
                                        replace("noun","").
                                        replace("verb","")

                                    Log.d("qqq",sp4)
                                    mTTs.speak(sp4,TextToSpeech.QUEUE_FLUSH, null)
                                }


                                var des:String = document.data["$q"+"des$k"].toString()
                                if(des!="null"){
                                    if(k==1||k==2){
                                        if(k==1){
                                            a_des.append("\n\n★\n")
                                        }
                                        var mes:String="<font color='#4D0000'>"+des+ "</font><br/>"
                                        a_des.append(Html.fromHtml(mes))

                                    }
                                    else{
                                        a_des.append(Html.fromHtml(des))
                                        a_des.append("\n")
                                    }

                                    if(k==2){
                                        a_des.append("\n\n")
                                    }
                                    else if(k%2==0){
                                        a_des.append("\n")
                                    }

                                }
                                else{
                                    break
                                }
                            }


                        }
                    }
                }
            }
    }


}
