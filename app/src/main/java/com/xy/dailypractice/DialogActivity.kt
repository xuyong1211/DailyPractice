package com.xy.dailypractice

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import com.xy.dailypractice.databinding.ActivitySecondBinding
import kotlin.system.exitProcess


class DialogActivity  : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewBinding = ActivitySecondBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.button.setOnClickListener {
            MyDialog().show(supportFragmentManager,"")
//            finish()
//            handler.post {
//                exitProcess(0)
//            }
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        System.exit(0)
    }
}



class MyDialog : DialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var inflate = inflater.inflate(R.layout.dialog_layout, container, false)
        dialog?.window?.apply {
          setWindowAnimations(R.style.dialogFragment_anim)
        }?.setBackgroundDrawableResource(R.color.transparent)
        return inflate
    }

//    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
//        var animation: TranslateAnimation? = null
//        if (transit === FragmentTransaction.TRANSIT_FRAGMENT_OPEN) {
//            animation = if (enter) {
//                TranslateAnimation(
//                    Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f,
//                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
//                )
//            } else {
//                TranslateAnimation(
//                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -1f,
//                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
//                )
//            }
//        } else if (FragmentTransaction.TRANSIT_FRAGMENT_CLOSE == transit) {
//            animation = if (enter) {
//                TranslateAnimation(
//                    Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0f,
//                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
//                )
//            } else {
//                TranslateAnimation(
//                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f,
//                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
//                )
//            }
//        }
//        if (animation == null) {
//            animation = TranslateAnimation(0f, 0f, 0f, 0f)
//        }
//        animation.duration = 300
//        return animation
//    }


}