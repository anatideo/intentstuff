package com.anatideo.intentstuff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anatideo.model.Song

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val strValue = "K-Pop is nice"
        val boolValue = true
        val integerValue = 10
        val songsList = listOf(
                Song("Red Velvet"),
                Song("Day6"),
                Song("Girls' Generation")
        )
        val firstIndexList = songsList[0]

        val otherActivity = OtherActivity.newIntent(
                context = this,
                stringValue = strValue,
                booleanValue = boolValue,
                intValue = integerValue,
                listValue = songsList,
                firstIndexListValue = firstIndexList
        )

        startActivity(otherActivity)
    }
}