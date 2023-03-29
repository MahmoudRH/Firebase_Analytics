package com.mahmoudhabib.firebaseanalytics

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        analytics = Firebase.analytics
        val selectContentButton = findViewById<Button>(R.id.button_select_content)
        val screenViewButton = findViewById<Button>(R.id.button_screen_view)

        selectContentButton.setOnClickListener {
            selectContentEvent("1","imageView","image")
        }

        screenViewButton.setOnClickListener {
                trackScreenEvent()
        }
    }

    private fun trackScreenEvent() {
            analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW){
                param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
                param(FirebaseAnalytics.Param.SCREEN_NAME, "Home")
            }
    }

    private fun selectContentEvent(id: String, name: String, contentType: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
            param(FirebaseAnalytics.Param.ITEM_ID, id)
            param(FirebaseAnalytics.Param.ITEM_NAME, name)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, contentType)
        }
    }
}