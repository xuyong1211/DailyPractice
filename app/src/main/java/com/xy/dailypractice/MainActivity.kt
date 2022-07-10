package com.xy.dailypractice

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import com.xy.dailypractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        viewBinding.ivGif.setImageResource(R.drawable.mygif)

        var animationDrawable = viewBinding.ivGif.drawable as AnimationDrawable
        animationDrawable.isOneShot = true
//        animationDrawable.start()


//        val alphaAnimation = AnimationUtils.loadAnimation(this,R.anim.simple_alpha)
//        alphaAnimation.repeatCount = -1
//        alphaAnimation.repeatMode = Animation.REVERSE
//        viewBinding.ivAnimation.startAnimation(alphaAnimation)
//        handler.postDelayed({
//            val alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_translate)
//            viewBinding.ivAnimation.startAnimation(alphaAnimation)
//        },0)
        viewBinding.tvHello.setOnClickListener {
//            animationDrawable.stop()
//            val alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_translate)
//            viewBinding.ivAnimation.startAnimation(alphaAnimation)
//            viewBinding.ivAnimation.animate().translationXBy(100f).setDuration(1000)
//                .start()
//            viewBinding.clBottom.animate().translationY(-(viewBinding.clBottom.height.toFloat()))
//                .setInterpolator(DecelerateInterpolator())
//                .setDuration(300)
//                .start()
        }


//        viewBinding.clBottom.post {
//            var animate = viewBinding.clBottom.animate()
//                .translationY(-(viewBinding.clBottom.height.toFloat()))
//                .setInterpolator(DecelerateInterpolator())
//                .setDuration(300)
//            animate
//                .start()
////
//            animate.setUpdateListener {
//
//            }
//            animate.setListener(object : Animator.AnimatorListener {
//                override fun onAnimationStart(animation: Animator?) {
//                    isAnimating = true
//                    state = 1
//                }
//
//                override fun onAnimationEnd(animation: Animator?) {
//                    isAnimating = false
//                    state = 1
//                }
//
//                override fun onAnimationCancel(animation: Animator?) {
//                }
//
//                override fun onAnimationRepeat(animation: Animator?) {
//                }
//            })
//
//        }


//        viewBinding.clContent.setOnClickListener {
//            if (!isAnimating) {
//                if (state == 0) {
//                    var animate = viewBinding.clBottom.animate()
//                        .translationY(-(viewBinding.clBottom.height.toFloat()))
//                        .setInterpolator(DecelerateInterpolator())
//                        .setDuration(300)
//                    animate.setListener(object : Animator.AnimatorListener {
//                        override fun onAnimationStart(animation: Animator?) {
//                            isAnimating = true
//                            state = 1
//                        }
//
//                        override fun onAnimationEnd(animation: Animator?) {
//                            isAnimating = false
//                            state = 1
//                        }
//
//                        override fun onAnimationCancel(animation: Animator?) {
//                        }
//
//                        override fun onAnimationRepeat(animation: Animator?) {
//                        }
//                    })
//                    animate.start()
//                } else {
//
//                }
//            }
//        }
        viewBinding.clContent.post {
            viewBinding.clContent.transitionToEnd()
        }
//        viewBinding.ivAnimation.animate().translationXBy(100f).setDuration(1000)
//            .start()
    }

    private var isAnimating = false
    private var state = 0 // 0 关  1 开

    fun getOpenAnimator() {
            viewBinding.clBottom.animate().translationY((viewBinding.clBottom.height.toFloat()))
                .setInterpolator(DecelerateInterpolator())
                .setDuration(300)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator?) {
                        isAnimating = true
                        state = 0
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        isAnimating = false
                        state = 0
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                })
                .start()
    }

    fun closeAnimator(){

    }
}