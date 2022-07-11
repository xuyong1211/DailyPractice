package com.xy.dailypractice.touchview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.ConfigurationCompat
import kotlin.math.abs

class PullUpConstraintLayout(context: Context,attributeSet: AttributeSet):ConstraintLayout(context,attributeSet) {
    var startX = 0
    var startY = 0
    var touchSlop = 0
    init {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {

        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                startX = event.x.toInt()
                startY = event.y.toInt()
            }
            MotionEvent.ACTION_MOVE ->{
                val dy = abs(event.y - startY)
                if(dy > touchSlop){
                    return true
                }
            }
        }
        return super.onInterceptTouchEvent(event)
    }
    var upDone = false
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                upDone = false
            }
            MotionEvent.ACTION_MOVE ->{
                requestDisallowInterceptTouchEvent(true)
                val dx = abs(event.x - startX)
                val dy = abs(event.y - startY)
                return if(dy > touchSlop && dy * 0.5 > dx){
                    upDone = true
                    true
                }else{
                    false
                }
            }
            MotionEvent.ACTION_UP ->{
                if(upDone){
                    if(this::pullUpListener.isInitialized){
                        pullUpListener()
                    }
                }
            }

            MotionEvent.ACTION_CANCEL->{
                if(upDone){
                    if(this::pullUpListener.isInitialized){
                        pullUpListener()
                    }
                }
            }
        }
        return true
    }


    lateinit var pullUpListener :()->Unit

}