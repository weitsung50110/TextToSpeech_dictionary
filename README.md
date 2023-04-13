# TextToSpeech_dictionary
1.本字典為有聲字典,所有單字和例句都會有人工語音幫妳念出來,不用再花時間去看KK音標. <br />
2.當遇到沒見過的單字,想要記錄起來,可以把單字新增到筆記本裡面,就不怕之後會忘記了.<br />
3."筆記本"裡面的單字都是可以自由刪除的.<br />
4.每個單字裡面的例句解釋都是可以在Firebase自由新增和更改的~ <br />
5.當想查詢特定字母開頭的單字時,可以使用關鍵字查詢單字.<br />
6.單字使用CardView在RecyclerView裡面上下滑動,讓使用者更方便使用.<br />

此作品是我自己在大學課程「行動應用程式設計」的期末專題，該作品獲得課堂老師 95 分的評價。<br />
該作品是使用 Android Studio製作，用Kotlin 、SQL語言以及 Firebase 的綜合應用，<br />
並透過採用 Google AI 技術的 API，將文字轉換為自然流暢的語音，不管是中文還是英文，單字和例句機器人都可以幫妳念出來。<br /><br />
對於這個app，我特別使用Firebase雲端資料庫，讓使用者輸入的單字可以存在雲端上面，以此避免掉存在本機裝置造成資料不見的風險。<br />
本作品提供的功能如下: <br />
A. 新增單字、單字解釋至雲端資料庫 <br />
B. 將喜歡的單字加入「我的最愛」 <br />
C. 單字和例句都有合成語音幫忙念出來 <br />
D. 單字可刪除和修改。<br />

##Video
<iframe width="560" height="315" src="https://www.youtube.com/embed/oBl6HfmIn9g" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

## Main page in mobile phone
![](https://github.com/a1233z/text_to_voice_dicitionary_public/blob/master/github_images/0.png)

## Firebase Database
* 我會把字典的單字解釋上傳到firebase資料庫裡面~

![](https://github.com/a1233z/text_to_voice_dicitionary_public/blob/master/github_images/1.png)
![](https://github.com/a1233z/text_to_voice_dicitionary_public/blob/master/github_images/2.png)

## TEXTTOSPEECH
* You can choose the line you like, when you press the volume buttom, AI will read out the line you choose in english or chinese for you.

![](https://github.com/a1233z/text_to_voice_dicitionary_public/blob/master/github_images/3.png)

## Keyword search
![](https://github.com/a1233z/text_to_voice_dicitionary_public/blob/master/github_images/4.png)

## Connect to others activities
* 點右上角的按鈕可以連接去其他Activity

![](https://github.com/a1233z/text_to_voice_dicitionary_public/blob/master/github_images/5.png)

## Save words in your notebook
* 突然想到什麼單字不會,可以記錄在app裡面!!

![](https://github.com/a1233z/text_to_voice_dicitionary_public/blob/master/github_images/6.png)

## Pop up window for description
* 用AlertDialog做了說明的彈掉視窗!!

![](https://github.com/a1233z/text_to_voice_dicitionary_public/blob/master/github_images/7.png)
