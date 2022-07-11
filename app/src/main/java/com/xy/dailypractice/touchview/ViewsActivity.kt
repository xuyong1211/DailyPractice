package com.xy.dailypractice.touchview

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.xy.dailypractice.R

class ViewsActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_views
        )
        findViewById<TextView>(R.id.textView).setOnClickListener {
            Log.d(TAG,"click")
        }
    }
    private  val TAG = "ViewsActivity"

}